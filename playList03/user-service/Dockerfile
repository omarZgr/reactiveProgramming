# Use the official Maven image with OpenJDK 17
FROM maven:latest AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

# Use a smaller image to run the application
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/user-service-1.jar app.jar

# Expose the application port (replace with your actual port if different)
EXPOSE 8092

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
