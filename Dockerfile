FROM adoptopenjdk:11-jre-hotspot
EXPOSE 80
COPY build/libs/*.jar /app/product_service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/product_service-0.0.1-SNAPSHOT.jar"]