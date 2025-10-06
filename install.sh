# Démarrer PostgreSQL avec Docker Compose
docker-compose up -d postgres

# Se placer dans le dossier backend
cd backend

# Installer les dépendances Maven
mvn clean install
# Démarrer l'API Spring Boot
mvn spring-boot:run


# Se placer dans le dossier frontend
cd ../frontend

# Installer les dépendances npm
npm install

# Démarrer le serveur de développement Angular
npm install -g @angular/cli






