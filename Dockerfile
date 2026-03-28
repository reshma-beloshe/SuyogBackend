# ---------- Step 1: Build Stage ----------
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only pom.xml first to leverage Docker caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Step 2: Runtime Stage ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set a non-root user (for better security)
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser

# Expose the application port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]