# Usamos un JDK 17 oficial
FROM eclipse-temurin:17-jdk

# Copiamos el JAR que se generará con Maven
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Exponemos el puerto que usará Render
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
