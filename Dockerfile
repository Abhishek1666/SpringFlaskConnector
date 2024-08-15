# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project's build output (JAR file) to the container
COPY target/MajorProjectApplication.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
