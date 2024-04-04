FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the JAR file from the host to the container
COPY build/mdtohtml-0.0.1-SNAPSHOT.jar  /app/mdtohtml.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "mdtohtml.jar"]