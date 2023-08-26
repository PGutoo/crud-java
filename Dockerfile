# Using a base image with Maven and OpenJDK 11 to build the application

FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory inside the container

WORKDIR /main

# Copy the entire application code to the container

COPY . .

# Build the application using Maven, skipping tests

RUN mvn clean package -DskipTests

# Using a different base image with OpenJDK 11 to run the application

FROM openjdk:11-jre-slim

# Copy the built JAR file from the previous build stage to the current container

COPY --from=build /app/target/crud.jar /crud.jar

# Expose port 8080 for the application to listen on

EXPOSE 10001

# Install curl to check the application health status

RUN apt-get update \

&& apt-get install -y curl

# Define the command to run the application

CMD ["sh", "-c", "curl -s --retry-connrefused --connect-timeout 5 http://localhost:10001/ || exit 1"]