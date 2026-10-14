FROM eclipse-temurin:25-jdk-jammy

WORKDIR /workspace

COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .

RUN chmod +X gradlew
RUN ./gradlew dependencies

COPY src src

CMD ["./gradlew", "bootRun"]