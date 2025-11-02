# Multi-stage Dockerfile for Spring Boot application
# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn -B -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Set JVM options for container environment
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Expose port (Render will override with $PORT)
EXPOSE 8080

# Run the application
# Render provides $PORT environment variable
# Spring profile is set via SPRING_PROFILES_ACTIVE env var
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} -jar app.jar"]
