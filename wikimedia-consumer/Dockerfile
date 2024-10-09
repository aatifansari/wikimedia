# Wikimedia Consumer

### STAGE 1: Download Dependencies
FROM maven:3.9.8-eclipse-temurin-17-alpine AS dependencies

# Set current working directory
WORKDIR /opt/app

# Copy maven files to container
COPY wikimedia-consumer/pom.xml wikimedia-consumer/pom.xml
COPY wikimedia-producer/pom.xml wikimedia-producer/pom.xml
COPY pom.xml .

# Run command to download dependencies
RUN mvn -B -e org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=wikimedia-producer

### STAGE 2: Build Image
FROM maven:3.9.8-eclipse-temurin-17-alpine AS builder

# Set current working directory
WORKDIR /opt/app

# Copy files from dependencies
COPY --from=dependencies /root/.m2 /root/.m2
COPY --from=dependencies /opt/app/ /opt/app

# Copy source code
COPY wikimedia-producer/src /opt/app/wikimedia-producer/src
COPY wikimedia-consumer/src /opt/app/wikimedia-consumer/src

RUN mvn -B -e clean install -DskipTests

### STAGE 3: Final Image
FROM openjdk:17-slim

# Set current working directory
WORKDIR /opt/app

# Copy files from builder stage
COPY --from=builder /opt/app/wikimedia-consumer/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]



