
# Use a base image with Java (choose an appropriate version)
FROM openjdk11:

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./

# Download and install the project dependencies
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean install -DskipTests

# Copy the application code to the container
COPY src/ ./src/

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/your-spring-boot-app.jar"]
# Fixed error: changed "COPY pom.xml ." to "COPY pom.xml ./", added missing slash to specify the target directory in the container
# Fixed error: used apt-get to install maven as a package manager compatible with the used base image
# Fixed error: added "apt-get update" to update the package list before installing maven
# Added comments explaining the changes made to the Dockerfile
