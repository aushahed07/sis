FROM eclipse-temurin:8-jre

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]