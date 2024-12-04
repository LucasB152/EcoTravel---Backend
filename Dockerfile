# Utilisez une image de base avec Java 21
FROM openjdk:21-jdk-slim as build

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier Gradle et le répertoire du projet
COPY . .

# Donner les permissions d'exécution à gradlew
RUN chmod +x gradlew

# Exécuter Gradle pour construire l'application
RUN ./gradlew build -x test

# Utiliser une image plus petite pour l'exécution de l'application
FROM openjdk:21-jdk-slim

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers nécessaires depuis l'image de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exposer le port sur lequel votre application fonctionne (par défaut 8080)
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
