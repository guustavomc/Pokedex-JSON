# Usa Maven com JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila o projeto e gera o JAR final
RUN mvn clean package

# Usa uma imagem menor apenas com o JRE 21 para rodar a aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR correto gerado pelo maven-assembly-plugin
COPY --from=build /app/target/Pokedex-JSON-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Copy pokedex.json to /app
COPY pokedex.json /app/pokedex.json

CMD ["java", "-jar", "app.jar"]