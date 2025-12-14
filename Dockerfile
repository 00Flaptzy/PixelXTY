# ========= BACKEND SPRING BOOT =========

# Primera etapa: Construcción
FROM maven:3.8.8-amazoncorretto-17 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Segunda etapa: Ejecución
FROM eclipse-temurin:17-jre-alpine  
LABEL maintainer="pixelxty@gmail.com"
WORKDIR /app

# Copiar el JAR
COPY --from=builder /app/target/*.jar app.jar

# Variables de entorno PARA RENDER
ENV SPRING_PROFILES_ACTIVE=prod
ENV PORT=10000  

# Puerto dinámico
EXPOSE ${PORT}

# HEALTHCHECK CORREGIDO para Render
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT}/health || exit 1

# ENTRYPOINT CORREGIDO - OBLIGATORIO PARA RENDER
ENTRYPOINT ["sh", "-c", "java -Dserver.address=0.0.0.0 -Dserver.port=${PORT} -jar app.jar"]