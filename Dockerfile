# Étape 1 : Utiliser une image Maven pour builder l'application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Définir le dossier de travail
WORKDIR /app

# Copier uniquement les fichiers de configuration Maven (pour tirer parti du cache Docker)
COPY pom.xml .
COPY src ./src

# Construire le jar sans exécuter les tests
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image JDK/JRE plus légère pour l’exécution
FROM eclipse-temurin:17-jdk-jammy

# Définir le dossier de travail
WORKDIR /app

# Copier le JAR généré depuis le builder
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port de l’application (exemple : 8080 ou 8081 selon ta config)
EXPOSE 8081

# Commande pour démarrer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
