# Use the official Maven image to build the application
# This image includes both Maven and JDK
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY target/product-service-1.jar /app/reactiveProduct.jar

# Expose the application port (replace with your actual port if different)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
