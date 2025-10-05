-- Création de la base de données MoneyFollow
-- Exécuter ce script en tant que superutilisateur PostgreSQL

-- Créer la base de données
CREATE DATABASE moneyfollow;

-- Créer un utilisateur pour l'application (optionnel)
-- CREATE USER moneyfollow_user WITH PASSWORD 'your_secure_password';
-- GRANT ALL PRIVILEGES ON DATABASE moneyfollow TO moneyfollow_user;

-- Se connecter à la base de données moneyfollow pour créer les tables
\c moneyfollow;

-- Les tables seront créées automatiquement par Hibernate
-- Ce fichier sert de référence pour l'initialisation manuelle si nécessaire
