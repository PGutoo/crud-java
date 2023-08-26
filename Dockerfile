
# Using a base image with Maven and OpenJDK 11 to build the application
FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire application code to the container
COPY . .

# Build the application using Maven, skipping tests
RUN mvn clean package -DskipTests

# Using a different base image with OpenJDK 11 to run the application
FROM openjdk:11-jre-slim

# Copy the built JAR file from the previous build stage to the current container
COPY --from=build /app/target/crud.jar /crud.jar

# Expose port 8080 for the application to listen on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "/crud.jar"]
