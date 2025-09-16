# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven/Gradle build file and download dependencies
COPY pom.xml .
COPY src ./src

# Package the application
RUN ./mvnw clean package -DskipTests

# Copy the built jar to the working directory
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
