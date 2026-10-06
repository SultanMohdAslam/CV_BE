# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Grant execution rights on Gradle wrapper
RUN chmod +x gradlew

# Pre-fetch dependencies to leverage Docker layer caching
RUN ./gradlew dependencies --no-daemon || true

# Copy source code and build executable jar
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# Identify the executable jar (ignoring plain jar) and move to standard location
RUN cp $(find build/libs/ -name "*.jar" ! -name "*-plain.jar" | head -n 1) /app/app.jar

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Run as non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy built application jar from builder stage
COPY --from=builder /app/app.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
