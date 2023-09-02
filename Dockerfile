#FROM maven:3.8.1-jdk-8 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src/ ./src
#RUN mvn clean install -DskipTests -T 4

FROM openjdk:8
#WORKDIR /app
#COPY --from=build /app/target/drones-app.jar ./drones-app.jar
COPY target/drones-app.jar ./drones-app.jar
EXPOSE 8080:8080
CMD ["java","-jar","drones-app.jar"]