# 🧭 Fiche de survie Angular CLI

## ⚙️ Initialisation & gestion du projet

| Commande                              | Description                                           |
| ------------------------------------- | ----------------------------------------------------- |
| `npm install -g @angular/cli`         | Installe Angular CLI globalement                      |
| `ng new mon-projet`                   | Crée un nouveau projet Angular                        |
| `ng serve` ou `ng s`                  | Lance le serveur local (`http://localhost:4200`)      |
| `ng serve --port 4500`                | Lance l'app sur un port spécifique                    |
| `ng build`                            | Compile le projet pour la production                  |
| `ng build --configuration production` | Build optimisé pour la prod                           |
| `ng test`                             | Lance les tests unitaires                             |
| `ng e2e`                              | Lance les tests end-to-end                            |
| `ng lint`                             | Analyse le code pour vérifier le style et les erreurs |
| `ng update`                           | Met à jour Angular et ses dépendances                 |
| `ng version` ou `ng v`                | Affiche la version actuelle d'Angular et CLI          |

---

## 🧩 Génération d'éléments (le cœur de la CLI)

| Commande                     | Description                                                |
| ---------------------------- | ---------------------------------------------------------- |
| `ng g c nom`                 | Crée un **composant** (`.ts`, `.html`, `.css`, `.spec.ts`) |
| `ng g c nom --standalone`    | Crée un composant **standalone**                           |
| `ng g component dossier/nom` | Crée un composant dans un sous-dossier                     |
| `ng g s nom`                 | Crée un **service** injectable                             |
| `ng g guard nom`             | Crée un **guard** (CanActivate, etc.)                      |
| `ng g pipe nom`              | Crée un **pipe** personnalisé                              |
| `ng g directive nom`         | Crée une **directive**                                     |
| `ng g module nom`            | Crée un **module Angular**                                 |
| `ng g interface nom`         | Crée une **interface TypeScript**                          |
| `ng g class nom`             | Crée une **classe TypeScript**                             |
| `ng g enum nom`              | Crée un **enum TypeScript**                                |

🧠 Astuce : tu peux abréger tous les mots-clés : `component → c`, `service → s`, `module → m`, `guard → g`, `pipe → p`.

---

## 🚀 Déploiement & production

| Commande                              | Description                                       |
| ------------------------------------- | ------------------------------------------------- |
| `ng build --configuration production` | Build optimisé pour la mise en ligne              |
| `ng deploy`                           | Déploie sur GitHub Pages ou un provider configuré |
| `ng build --base-href /monapp/`       | Définit un sous-dossier pour le déploiement       |
| `ng build --output-path dist/monapp`  | Définit le dossier de sortie                      |

---

## 🧠 Raccourcis utiles pendant le dev

| Commande                   | Description                                    |
| -------------------------- | ---------------------------------------------- |
| `Ctrl + C`                 | Arrête le serveur local                        |
| `ng cache clean`           | Vide le cache de la CLI                        |
| `ng analytics off`         | Désactive les stats de la CLI                  |
| `ng generate environments` | Crée les fichiers `environment.ts` si manquant |
| `ng build --watch`         | Rebuild automatique à chaque modification      |

---

## 🧱 Fichiers de configuration importants

| Fichier                                | Rôle                                       |
| -------------------------------------- | ------------------------------------------ |
| `angular.json`                         | Structure du projet et paramètres de build |
| `package.json`                         | Dépendances npm                            |
| `tsconfig.json`                        | Options du compilateur TypeScript          |
| `src/environments/environment.ts`      | Variables d'environnement de dev           |
| `src/environments/environment.prod.ts` | Variables d'environnement de prod          |

---

## 🎨 Gestion des styles et UI

| Commande                                   | Description                                       |
| ------------------------------------------ | ------------------------------------------------- |
| `ng add @angular/material`                 | Installe Angular Material                         |
| `ng add tailwindcss`                       | Configure Tailwind CSS                            |
| `ng generate @angular/material:navigation` | Crée une navigation Material                      |
| `ng g c shared/navbar`                     | Crée un composant de barre de navigation partagée |

---

## 💡 Tips rapides

* Vérifier les erreurs globales :

  ```bash
  ng build --configuration production
  ```
* Lancer le navigateur automatiquement :

  ```bash
  ng serve -o
  ```
* Créer un composant sans fichiers de test :

  ```bash
  ng g c nom --skip-tests
  ```

---

## 🧭 Résumé rapide

| Catégorie      | Commande clé                                     |
| -------------- | ------------------------------------------------ |
| 🚀 Démarrage   | `ng new`, `ng serve`                             |
| ⚙️ Génération  | `ng g c`, `ng g s`, `ng g guard`                 |
| 🧠 Maintenance | `ng lint`, `ng update`                           |
| 📦 Build/Prod  | `ng build --configuration production`            |
| 💅 UI          | `ng add tailwindcss`, `ng add @angular/material` |
