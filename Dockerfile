# ======= Etapa 1: Build =======
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Carpeta de trabajo
WORKDIR /app

# Copia los archivos de Maven
COPY pom.xml .
COPY src ./src

# Construye el jar
RUN mvn clean package -DskipTests
 
# ======= Etapa 2: Run =======
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el jar generado en la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","/app.jar"]
