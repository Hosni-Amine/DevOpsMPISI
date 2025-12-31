# Exercice Pratique DevOps - Rapport de Réalisation

Ce document présente l'état de réalisation de chaque tâche demandée dans l'exercice pratique DevOps.

---

## 1. Initialisation du projet

### Tâche 1.1 : Forker le repository GitHub du projet
**Commentaire :** Le repository GitHub a été forké dans le compte personnel.

---

### Tâche 1.2 : Cloner le projet localement

**Commentaire :** Le projet a été cloné localement dans `/Users/macbook/Desktop/DevOpsMPISI`.

---

### Tâche 1.3 : Vérifier que Maven fonctionne : `mvn clean test`

**Commentaire :** 
- Commande exécutée : `./mvnw clean test`
- **Résultat :** BUILD SUCCESS - 5 tests exécutés, 0 échecs, 0 erreurs

**Capture d'écran de la vérification :**

![Résultat de mvn clean test](docs/images/maven-clean-test-success.png)

---

## 2. Configuration SonarCloud

### Tâche 2.1 : Créer un compte SonarCloud (avec GitHub)
**Commentaire :** Le compte SonarCloud a été créé et lié à GitHub.

---

### Tâche 2.2 : Créer une nouvelle organisation
**Commentaire :** L'organisation SonarCloud "hosni-mohamed-amine" a été créée et configurée dans le projet.

- Dans `pom.xml` (ligne 44) : `<sonar.organization>hosni-mohamed-amine</sonar.organization>`
- Dans `sonar-project.properties` (ligne 2) : `sonar.organization=hosni-mohamed-amine`

---

### Tâche 2.3 : Importer le projet depuis GitHub
**Commentaire :** 
- Le projet a été publié sur GitHub et importé dans SonarCloud : https://github.com/Hosni-Amine/DevOpsMPISI.git
- URL du projet SonarCloud : https://sonarcloud.io/project/overview?id=Hosni-Amine_DevOpsMPISI

---

### Tâche 2.4 : Générer un token d'authentification
**Commentaire :** 

- Token créé dans SonarCloud : My Account → Security → Existing Tokens
- Secret ajouté dans GitHub : Repository → Settings → Secrets and variables → Actions
- Capture d'écran du token : ![Token SonarCloud généré](docs/images/sonarcloud-token-generated.png)
- Capture d'écran du secret GitHub : ![Secret SONAR_TOKEN dans GitHub](docs/images/github-secret-sonar-token.png)

### Tâche 2.5 : Ajouter les propriétés SonarCloud dans pom.xml
**Commentaire :** 
- Toutes les propriétés SonarCloud sont correctement configurées dans le `pom.xml`

**- Configuration dans `pom.xml` (lignes 42-51) :**
```xml
<sonar.host.url>https://sonarcloud.io</sonar.host.url>
<sonar.organization>hosni-mohamed-amine</sonar.organization>
<sonar.projectKey>Hosni-Amine_DevOpsMPISI</sonar.projectKey>
<sonar.sourceEncoding>UTF-8</sonar.sourceEncoding>
<sonar.sources>src/main/java</sonar.sources>
<sonar.tests>src/test/java</sonar.tests>
<sonar.java.binaries>target/classes</sonar.java.binaries>
<sonar.coverage.jacoco.xmlReportPaths>target/site/jacoco/jacoco.xml</sonar.coverage.jacoco.xmlReportPaths>
<sonar.exclusions>**/*Application.java,**/dto/**,**/config/**</sonar.exclusions>
```

---

### Tâche 2.6 : Lancer une analyse locale : `mvn clean verify sonar:sonar -Dsonar.token=VOTRE_TOKEN`
**Commentaire :** 
- Analyse locale SonarCloud exécutée avec succès

**Commande exécutée :**
```bash
./mvnw clean verify sonar:sonar -Dsonar.token=fd562425fc31710858cc461ccc507d7f80134873
```

**Résultat :**
-  **ANALYSIS SUCCESSFUL**
-  **BUILD SUCCESS**
- Temps total : 59.501 s
- Temps d'analyse : 53.827 s

- Capture d'écran de l'analyse locale réussie : ![Analyse locale SonarCloud réussie](docs/images/sonarcloud-local-analysis-success.png)

---

### Tâche 2.7 : Vérifier les résultats sur le dashboard SonarCloud
**Commentaire :** 
- Dashboard SonarCloud accessible et résultats vérifiés

**Résultats de l'analyse :**
- **Quality Gate** : Failed (1 condition échouée)
- **Security** : 0 Open issues, Grade A 
- **Reliability** : 1 Open issues, Grade E
- **Maintainability** : 13 Open issues, Grade A 
- **Coverage** : 15.4% (64 Lines to cover)
- **Duplications** : 0.0% (231 Lines)
- **Security Hotspots** : 1
- **Lines of Code** : 161 lignes

  - Capture d'écran du dashboard SonarCloud : ![Dashboard SonarCloud - Résultats de l'analyse](docs/images/sonarcloud-dashboard-results.png)

---

## 3. Pipeline GitHub Actions

### Tâche 3.1 : Créer le répertoire `.github/workflows/`
**Commentaire :** 
- Le répertoire `.github/workflows/` existe

---

### Tâche 3.2 : Créer le fichier `ci.yml` avec le pipeline CI/CD
**Commentaire :** 
- Le fichier `ci.yml` est créé et contient un pipeline CI/CD
- Déclenchement sur push et pull request vers main/master/develop

---

### Tâche 3.3 : Ajouter le secret SONAR_TOKEN dans GitHub
**Commentaire :** 
- Secret `SONAR_TOKEN` ajouté dans GitHub Secrets (déjà fait dans la tâche 2.4)
- Utilisé dans le pipeline via : `${{ secrets.SONAR_TOKEN }}`
- Voir la tâche 2.4 pour les détails et la capture d'écran

---