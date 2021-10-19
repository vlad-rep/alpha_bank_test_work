FROM openjdk:16

COPY build/libs/*.jar app.jar

EXPOSE 8081/tcp

ENTRYPOINT ["java", "-jar", "/app.jar"]