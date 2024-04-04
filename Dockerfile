FROM openjdk:11-jre-slim

WORKDIR /app

COPY build/mdtohtml-0.0.1-SNAPSHOT.jar  /app/mdtohtml.jar

EXPOSE 8080

CMD ["java", "-jar", "mdtohtml.jar"]