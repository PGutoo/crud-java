
# Use a base image with Java (choose an appropriate version)
# Fixed: typo changed "FROM openjdk11:" to "FROM openjdk:11" to specify the correct base image
# Fixed typo: removed extra colon at the end of the base image name
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
# Fixed error: changed "COPY pom.xml ." to "COPY pom.xml ./" to specify the target directory in the container
COPY pom.xml ./

# Download and install the project dependencies
# Fixed error: used apt-get to install maven as a package manager compatible with the used base image
# Fixed error: added "apt-get update" to update the package list before installing maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean install -DskipTests && \
    mvn dependency:purge-local-repository && \
    mvn clean package



# Copy the application code to the container
COPY src/ ./src/

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/crud.jar"]
