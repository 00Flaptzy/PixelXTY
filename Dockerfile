# Build stage
FROM maven:3.8.8-amazoncorretto-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# OBLIGATORIO PARA RENDER
EXPOSE 10000

# OBLIGATORIO: Esta línea EXACTA
ENTRYPOINT ["java", "-Dserver.address=0.0.0.0", "-Dserver.port=10000", "-jar", "app.jar"]