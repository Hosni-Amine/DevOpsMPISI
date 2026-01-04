# Dockerisation de l'Application Spring Boot

## Étape 2.2 : Compiler l'Application

**Commande :**
```bash
./mvnw clean package -DskipTests
```

**Fichier généré :**
```
target/projetMPISI-0.0.1-SNAPSHOT.jar
```

---

## Partie 3 : Création du Dockerfile

**Contenu du Dockerfile :**
```dockerfile
# Image de base Java 17
FROM eclipse-temurin:17-jre-jammy

# Métadonnées
LABEL maintainer="amine.hosni02@gmail.com"
LABEL description="Application Spring Boot DevOps MPISI"
LABEL version="1.0"

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier le JAR de l'application
COPY target/projetMPISI-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### Étape 3.3 : Construire l'Image

**Définition :** La commande `docker build` crée une image Docker à partir du Dockerfile.

**Commande :**
```bash
docker build -t projetmpisi:1.0 .
```

**Résultat attendu :**

![Build Docker réussi](docs/dockerization/Screenshot%202026-01-04%20at%2016.07.35.png)

---

### Étape 3.4 : Vérifier l'Image Créée

**Commandes :**
```bash
# Lister les images Docker
docker images

# Inspecter l'image
docker inspect projetmpisi:1.0
```

**Résultat attendu :**

![Liste des images Docker](docs/dockerization/Screenshot%202026-01-04%20at%2016.13.44.png)

---

### Étape 4.2 : Créer le Dockerfile Multi-Stage

**Description :**

Nous avons remplacé le Dockerfile simple par un Dockerfile multi-stage pour optimiser l'image Docker. Cette approche sépare la phase de compilation (stage 1) de la phase d'exécution (stage 2), ce qui permet de créer une image finale beaucoup plus légère et sécurisée. 

Le premier stage utilise Maven pour compiler l'application directement dans le conteneur, ce qui élimine le besoin de compiler l'application en local avant de créer l'image. Le second stage utilise une image JRE Alpine ultra-légère et inclut des améliorations de sécurité (utilisateur non-root, healthcheck) ainsi que des optimisations de performance (variables d'environnement Java configurées).

Cette approche réduit significativement la taille de l'image finale (de ~450MB à ~200MB) tout en améliorant la sécurité et la maintenabilité du conteneur.

---

### Étape 4.3 : Reconstruire avec le Dockerfile Optimisé

**Commentaire :** L'image optimisée devrait être significativement plus légère grâce l'élimination des outils de build dans l'image finale. Le build multi-stage compile l'application directement dans le conteneur, éliminant le besoin de compiler en local avant.

**Résultat :**

![Build Docker multi-stage réussi](docs/dockerization/Screenshot%202026-01-04%20at%2016.41.05.png)

---

## Partie 5 : Configuration de MySQL avec Docker

### Étape 5.1 : Modifier application.properties

**Définition :** Nous devons utiliser des variables d'environnement pour la configuration de la base de données, permettant de changer facilement entre environnements.

**Fichier :** `src/main/resources/application.properties`

---

### Étape 5.2 : Recompiler l'Application

**Commentaire :** La recompilation est nécessaire pour intégrer les modifications de `application.properties` dans le JAR qui sera utilisé dans l'image Docker.

**Résultat :**

![Build Docker avec nouvelle configuration](docs/dockerization/Screenshot%202026-01-04%20at%2017.42.29.png)

---

## Partie 6 : Docker Compose - Orchestration Multi-Conteneurs

### Étape 6.1 : Comprendre Docker Compose

**Définition :** Docker Compose est un outil qui permet de définir et d'exécuter des applications Docker multi-conteneurs. Il utilise un fichier YAML pour configurer les services de l'application.

**Avantages :**
- Démarrer plusieurs conteneurs avec une seule commande
- Gérer automatiquement le réseau entre conteneurs
- Persister les données avec des volumes
- Définir l'ordre de démarrage et les dépendances

---

### Étape 6.2 : Créer docker-compose.yml

**Fichier :** `docker-compose.yml`

**Description :** Ce fichier définit deux services (MySQL et l'application Spring Boot) avec leurs configurations, réseaux, volumes et dépendances.

---

### Étape 7.1 : Démarrer les Services

**Définition :** `docker compose up` construit (si nécessaire) et démarre tous les services définis dans `docker-compose.yml`.

**Résultat :**

![Démarrage des services Docker Compose](docs/dockerization/Screenshot%202026-01-04%20at%2019.22.32.png)

---

### Étape 7.2 : Vérifier les Conteneurs

**Résultat :**

![Vérification des conteneurs Docker Compose](docs/dockerization/Screenshot%202026-01-04%20at%2019.31.27.png)

---

### Étape 7.3 : Consulter les Logs

**Résultat :**

![Vérification des conteneurs Docker Compose](docs/dockerization/Screenshot%202026-01-04%20at%2022.50.00.png)

---

### Étape 7.4 : Tester l'Application

**1. Liste des utilisateurs (vide au début) :**
```bash
curl -s http://localhost:8080/api/users
```
```json
[]
```

**2. Créer un utilisateur :**
```bash
curl -s -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "username": "Test User", "email": "test@example.com"}'
```
```json
{"id":1,"username":"Test User","email":"test@example.com"}
```

**3. Récupérer un utilisateur par ID :**
```bash
curl -s http://localhost:8080/api/users/1
```
```json
{"id":1,"username":"Test User","email":"test@example.com"}
```

**Résultat dans le navigateur :**

![Test de l'API dans le navigateur](docs/dockerization/Screenshot%202026-01-04%20at%2023.05.24.png)

---

### Étape 9.1 : Se Connecter à MySQL depuis le Conteneur

**Résultat :**

![Connexion à MySQL depuis le conteneur](docs/dockerization/Screenshot%202026-01-04%20at%2023.18.23.png)

---