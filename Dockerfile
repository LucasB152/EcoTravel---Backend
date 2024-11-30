# Phase de build
FROM openjdk:21-jdk AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier build.gradle (ou build.gradle.kts si vous utilisez Kotlin)
COPY build.gradle .
# Si vous avez un fichier settings.gradle, vous pouvez aussi le copier :
# COPY settings.gradle .

# Copier le wrapper Gradle
COPY gradlew .
COPY gradle gradle/

# Copier les sources
COPY src src

# Donner les permissions d'exécution au wrapper gradle
RUN chmod +x gradlew

# Exécuter Gradle pour construire l'application (avec l'option clean pour éviter les conflits)
RUN ./gradlew clean build -x test

# Phase d'exécution (image finale)
FROM openjdk:21-jdk

# Définir le répertoire de travail pour l'exécution
WORKDIR /app

# Copier l'artefact généré depuis l'image précédente (généralement dans build/libs)
COPY --from=build /app/build/libs/eco-travel-backend.jar eco-travel-backend.jar

# Exposer le port de l'application (port 8080 est courant pour Spring Boot)
EXPOSE 8080

# Commande pour exécuter l'application Spring Boot
CMD ["java", "-jar", "eco-travel-backend.jar"]
