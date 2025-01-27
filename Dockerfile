# Use an official Maven image to build the project
FROM openjdk:17-oracle AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]