#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
LABEL autodelete="true"
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean package -f /home/app/pom.xml

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/spring-app-0.0.1-SNAPSHOT.jar /usr/local/spring-app.jar
COPY agent/opentelemetry-javaagent-all.jar /usr/local/opentelemetry-javaagent-all.jar
EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java"]
CMD ["-jar", "/usr/local/spring-app.jar"]