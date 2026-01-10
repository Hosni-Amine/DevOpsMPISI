# Déployer votre application Spring Boot sur Kubernetes en local avec Minikube

---

## Partie 1 : Installation de Minikube

**Commandes :**
```bash
# Installer Minikube avec Homebrew
brew install minikube

# Installer kubectl
brew install kubectl

# Démarrer Minikube
minikube start --driver=docker

# Vérifier
kubectl get nodes
```

**Résultat :**

![Minikube Installation Success](docs/kubernites/Screenshot%202026-01-10%20at%2023.29.52.png)

---

## Partie 2 : Créer les Fichiers Kubernetes

**Structure crée :**
```
k8s/
├── deployment.yaml
├── service.yaml
├── configmap.yaml
└── secret.yaml
```

---

## Partie 3 : Déployer MySQL

**Fichier MySQL Deployment (k8s/mysql-deployment.yaml)**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - name: mysql
        image: mysql:8.0
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: "12345"
        - name: MYSQL_DATABASE
          value: "projetmpisi_db"
        - name: MYSQL_USER
          value: "appuser"
        - name: MYSQL_PASSWORD
          value: "apppass123"
        ports:
        - containerPort: 3306
---
apiVersion: v1
kind: Service
metadata:
  name: mysql
spec:
  selector:
    app: mysql
  ports:
  - protocol: TCP
    port: 3306
    targetPort: 3306
```

---

## Partie 4 : Déployer sur Kubernetes

**Commandes :**
```bash
# Appliquer les configurations
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/secret.yaml
kubectl apply -f k8s/mysql-deployment.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Vérifier le déploiement
kubectl get all
```

**Résultat :**

![Kubernetes Deployment Ready](docs/kubernites/Screenshot%202026-01-10%20at%2023.47.25.png)

---

## Partie 5 : Tester l'Application

**Commandes :**
```bash
# Obtenir l'URL Minikube
minikube service spring-app-service --url

# Tester l'endpoint health
curl http://127.0.0.1:54879/actuator/health

# Tester l'API
curl http://127.0.0.1:54879/api/users

# Ou ouvrir dans le navigateur
minikube service spring-app-service
```

**Résultat :**

![Kubernetes Application Test](docs/kubernites/Screenshot%202026-01-11%20at%2000.09.17.png)

---