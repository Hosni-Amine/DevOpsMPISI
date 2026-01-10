# Push vers Docker Hub

**Objectif :** Publier l'image Docker sur Docker Hub pour la rendre accessible partout.

---

## Partie 1 : Créer un Compte Docker Hub

## Partie 2 : Se Connecter à Docker Hub

## Partie 3 / 4 : Tag et Push de l'image

**Résultat :**

![Push Docker vers Docker Hub](docs/docker-hub/Screenshot%202026-01-05%20at%2001.01.57.png)

---

### Partie 5 : Vérifier sur Docker Hub

**Résultat :**

![Vérification sur Docker Hub](docs/docker-hub/Screenshot%202026-01-05%20at%2001.07.00.png)

---

### Partie 6 : Tester le Pull

**Commandes :**
```bash
# Supprimer l'image locale
docker rmi hosniamine/devopsmpisi-app:latest

# Pull depuis Docker Hub
docker pull hosniamine/devopsmpisi-app:latest

# Vérifier
docker images
```

**Résultat :**

![Test du Pull depuis Docker Hub](docs/docker-hub/Screenshot%202026-01-10%20at%2022.29.12.png)

---