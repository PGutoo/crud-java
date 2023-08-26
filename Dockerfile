FROM maven:3.8.3-openjdk-11-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim

COPY --from=build /app/target/crud.jar /crud.jar

EXPOSE 8080

CMD ["java", "-jar", "/crud.jar"]