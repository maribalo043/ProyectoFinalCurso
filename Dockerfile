# ===== Etapa 1: Build =====
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml y src
COPY pom.xml .
COPY src ./src

# Compilamos el JAR y saltamos los tests
RUN mvn clean package -DskipTests

# ===== Etapa 2: Run =====
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR generado en la etapa build y lo renombramos a app.jar
COPY --from=build /app/target/*.jar app.jar

# Puerto que usará Render
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","/app.jar"]
