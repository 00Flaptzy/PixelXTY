# Dockerfile
# ========= BACKEND SPRING BOOT =========

# Primera etapa: Construcción
FROM maven:3.8.8-amazoncorretto-17 AS builder

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de construcción
COPY pom.xml .
COPY src ./src

# Descargar dependencias (cache)
RUN mvn dependency:go-offline -B

# Construir el proyecto
RUN mvn clean package -DskipTests

# Segunda etapa: Ejecución
# Segunda etapa: Ejecución
FROM eclipse-temurin:17-jdk-jammy

# Información del mantenedor
LABEL maintainer="pixelxty@gmail.com"

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Puerto que expone la aplicación
EXPOSE 8080

# Salud check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8081/api/auth/health || exit 1

# Comando para ejecutar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar"]