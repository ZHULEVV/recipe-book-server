FROM gradle:8.13-jdk21 AS builder

WORKDIR /app

COPY gradle/ gradle/
COPY gradlew gradlew.bat ./
COPY settings.gradle.kts build.gradle.kts ./
COPY server/build.gradle.kts server/

RUN gradle :server:dependencies --no-daemon || true

COPY server/src server/src
RUN gradle :server:buildFatJar --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=builder /app/server/build/libs/*-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
