# 📋 Commandes d'installation MoneyFollow

## 🔍 Vérification des prérequis

```powershell
# Vérifier Java (requis : 17+)
java -version

# Vérifier Maven (requis : 3.8+)
mvn -version

# Vérifier Node.js (requis : 18+)
node -version

# Vérifier npm
npm -version
```

## 📦 Installation des dépendances

### Backend (Spring Boot)
```powershell
# Se placer dans le dossier backend
cd backend

# Installer les dépendances Maven
mvn clean install

# Alternative si Maven n'est pas installé globalement
./mvnw clean install
```

### Frontend (Angular)
```powershell
# Se placer dans le dossier frontend
cd frontend

# Installer les dépendances npm
npm install

# Alternative avec yarn (si installé)
yarn install
```

## 🐳 Configuration PostgreSQL avec Docker

```powershell
# Démarrer PostgreSQL avec Docker Compose
docker compose up postgres
sg docker -c "docker compose up postgres"

# Vérifier que le conteneur fonctionne
docker ps

# Arrêter PostgreSQL
docker compose down
```

## 🚀 Démarrage des applications

### Backend (Spring Boot)
```powershell
cd backend

# Démarrer l'API Spring Boot
mvn spring-boot:run

# Alternative avec le wrapper
./mvnw spring-boot:run

# L'API sera disponible sur : http://localhost:8080
```

### Frontend (Angular)
```powershell
cd frontend

# Démarrer le serveur de développement Angular
npm install -g @angular/cli
ng serve

# Alternative avec npm
npm start

# L'application sera disponible sur : http://localhost:4200
```

## 🔧 Commandes utiles

### Maven
```powershell
# Nettoyer le projet
mvn clean

# Compiler sans les tests
mvn clean compile -DskipTests

# Exécuter les tests
mvn test

# Créer le package JAR
mvn package
```

### Angular
```powershell
# Générer un composant
ng generate component nom-composant

# Générer un service
ng generate service nom-service

# Build de production
ng build --prod

# Lancer les tests
ng test
```

### Docker
```powershell
# Démarrer tous les services
docker-compose up -d

# Voir les logs
docker-compose logs -f

# Redémarrer un service
docker-compose restart postgres

# Supprimer les conteneurs et volumes
docker-compose down -v
```

## ✅ Ordre d'exécution recommandé

1. **Vérifier les prérequis**
2. **Installer les dépendances backend** : `mvn clean install`
3. **Installer les dépendances frontend** : `npm install`
4. **Démarrer PostgreSQL** : `docker-compose up -d postgres`
5. **Démarrer le backend** : `mvn spring-boot:run`
6. **Démarrer le frontend** : `ng serve`

## 🌐 URLs de développement

- **Frontend Angular** : http://localhost:4200
- **Backend Spring Boot** : http://localhost:8080
- **PostgreSQL** : localhost:5432 (base: moneyfollow)