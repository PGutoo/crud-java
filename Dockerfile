# Use a base image with Java (choose an appropriate version)
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .

# Download and install the project dependencies
RUN mvn clean install -DskipTests

# Copy the application code to the container
COPY src/ ./src/

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/your-spring-boot-app.jar"]
