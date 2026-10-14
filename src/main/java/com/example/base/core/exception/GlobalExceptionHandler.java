package com.example.base.core.exception;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleApiException(APIException e) {
        return switch (e.getType()) {
            case APIExceptionType.NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            case APIExceptionType.BUSINESS_ERROR -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
            case APIExceptionType.CONFLICT_ERROR ->  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            case APIExceptionType.FORBIDDEN -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        };
    }
    //padrão RFC 7807
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException manvex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problem.setTitle("Validation Error");

        var errors = manvex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage()
                )).toList();

        problem.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(problem);
    }
}
