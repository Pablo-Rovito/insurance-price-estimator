# Usamos una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim as build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml ./
RUN mvn clean install -DskipTests

# Copiar el código fuente del backend
COPY src /app/src

# Construir el JAR de la aplicación
RUN mvn package -DskipTests

# Usamos una imagen base más pequeña solo para ejecutar el JAR
FROM openjdk:17-jre-slim

# Copiar el archivo JAR del build
COPY --from=build /app/target/backend-*.jar /app/backend.jar

# Exponer el puerto 8080 para la aplicación
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "/app/backend.jar"]