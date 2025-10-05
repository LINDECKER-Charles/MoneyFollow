# MoneyFollow

Application de suivi de dépenses personnelles développée avec Spring Boot et Angular.

## 🚀 Technologies

### Backend
- **Java 17**
- **Spring Boot 3.2**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

### Frontend
- **Angular 17**
- **TypeScript**
- **Angular Material**
- **SCSS**

## 📁 Structure du projet

```
MoneyFollow/
├── backend/                    # API Spring Boot
│   ├── src/main/java/com/moneyfollow/
│   │   ├── controller/         # Contrôleurs REST
│   │   ├── service/           # Logique métier
│   │   ├── repository/        # Accès aux données
│   │   ├── model/             # Entités JPA
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── security/          # Configuration sécurité
│   │   ├── config/            # Configuration Spring
│   │   └── exception/         # Gestion des erreurs
│   └── pom.xml
├── frontend/                   # Application Angular
│   ├── src/app/
│   │   ├── components/        # Composants Angular
│   │   ├── services/          # Services Angular
│   │   ├── models/            # Interfaces TypeScript
│   │   └── guards/            # Guards de navigation
│   └── package.json
└── database/                   # Scripts SQL
```

## 🛠️ Installation

### Prérequis
- Java 17+
- Node.js 18+
- PostgreSQL 13+
- Maven 3.8+

### Backend (Spring Boot)
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend (Angular)
```bash
cd frontend
npm install
ng serve
```

### Base de données
1. Créer une base de données PostgreSQL nommée `moneyfollow`
2. Configurer les paramètres dans `backend/src/main/resources/application.properties`

## 🌐 URLs

- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **Base de données**: localhost:5432

## 📋 Fonctionnalités prévues

- [ ] Authentification utilisateur (inscription/connexion)
- [ ] Gestion du profil utilisateur
- [ ] Ajout de dépenses avec catégories
- [ ] Visualisation des dépenses (graphiques)
- [ ] Filtres et recherche
- [ ] Statistiques et rapports
- [ ] Export des données

## 🔧 Configuration

### Variables d'environnement
Créer un fichier `backend/src/main/resources/application-local.properties` :
```properties
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
jwt.secret=votre-cle-secrete-jwt
```

## 🤝 Contribution

1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -m 'Ajout nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT.
