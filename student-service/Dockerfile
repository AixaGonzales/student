# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml and download dependencies to leverage docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and package the jar
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root group and user for security compliance
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the packaged jar from the build stage
COPY --from=build /app/target/student-service-1.0.0.jar app.jar

# Change directory ownership to the non-root user
RUN chown -R appuser:appgroup /app

# Run the container under the non-root user
USER appuser

# Expose default application port
EXPOSE 8090

# Set environment variables for production execution
ENV SPRING_PROFILES_ACTIVE=prod

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
