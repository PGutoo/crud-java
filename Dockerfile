# Using a base image with Maven and OpenJDK 11 to build the application

# Updated the base image to use maven:3.8.3-openjdk-11-slim because maven is needed to build the application

FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory inside the container

# No changes needed

WORKDIR /app

# Copy the entire application code to the container

# No changes needed

COPY . .

# Build the application using Maven, skipping tests

# No changes needed

RUN mvn clean package -DskipTests

# Using a different base image with OpenJDK 11 to run the application

# Updated the base image to use openjdk:11-jre-slim because we only need OpenJDK to run the application, not Maven

FROM openjdk:11-jre-slim

# Copy the built JAR file from the previous build stage to the current container

# No changes needed

COPY --from=build /app/target/crud.jar /crud.jar

# Expose port 8080 for the application to listen on

# No changes needed

EXPOSE 8080

# Install curl to check the application health status

# Updated "RUN apt-get update && apt-get install curl -y" to install curl using apt-get

# Also, added "RUN apt-get install -y procps" to install procps to check the container logs

RUN apt-get update \

&& apt-get install -psy curl

proc# Define the command to run the application

# No changes needed

CMD ["sh", "-c", "curl -s --retry-connrefused --connect-timeout 5 http://localhost:8080/ || exit 1"]