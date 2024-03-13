# Use Amazon Corretto as the base image
FROM amazoncorretto:17 AS builder

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project (assuming Gradle as the build tool)
RUN ./gradlew build

# Use a lightweight base image for the final image
FROM amazoncorretto:17

EXPOSE 8080

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/simple-messenger-system-1.0-SNAPSHOT.jar simple-messenger-system.jar

# Run the JAR file
ENTRYPOINT ["java","-jar","simple-messenger-system.jar"]