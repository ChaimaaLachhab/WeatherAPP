# Application de gestion météorologique

L'application de gestion météorologique est une application Java console qui permet aux utilisateurs de stocker et de gérer des informations météorologiques pour différentes villes.

## Structure du Projet

L'application se compose des éléments suivants :

1. **Classes Principales** :
   - `Menu`: Gère l'interface utilisateur.
   - `City`: Stocke les informations actuelles de la météo pour une ville donnée.
   - `CityHistory`: Stocke l'historique des données météorologiques pour une ville donnée.

2. **Utilisation de JDBC** : 
   - Les données météorologiques actuelles et historiques sont stockées dans une base de données MySQL.
   - JDBC (Java Database Connectivity) est utilisé pour établir la connexion entre l'application et la base de données.

## Implémentation

- **Classe `City`** :
   - Les fonctionnalités incluent la création, la lecture, la mise à jour et la suppression des enregistrements de ville.
   - Les méthodes `createCity`, `readCity`, `updateCity`, et `deleteCity` sont définies pour gérer ces opérations.

- **Classe `CityHistory`** :
   - Les fonctionnalités incluent la création, la lecture, la mise à jour et la suppression des enregistrements historiques de la ville.
   - Les méthodes `createCityHistory`, `readCityHistory`, `updateCityHistory`, et `deleteCityHistory` sont définies pour gérer ces opérations.

- **Classe `Menu`** :
   - La classe `Menu` gère l'interface utilisateur.
   - Elle fournit une interface conviviale pour rechercher et sélectionner une ville spécifique, afficher les prévisions météorologiques actuelles, accéder à l'historique météorologique, et plus encore.

## Utilisation

1. **Installation de Java** :
   - Assurez-vous d'avoir Java JDK (version 11 ou supérieure) installé sur votre machine.

2. **Installation de MySQL** :
   - Assurez-vous d'avoir MySQL installé et une base de données nommée `weatherApp` créée sur votre serveur MySQL.

3. **Configuration de la Base de Données** :
   - Configurez les variables `URL`, `USERNAME`, et `PASSWORD` dans la classe `ConnectionDB` pour se connecter à votre base de données.

4. **Exécution de l'Application** :
   - Exécutez la classe `Menu` pour démarrer l'application.

## Instructions d'utilisation

1. Cloner ou télécharger le projet à partir du dépôt GitHub.
2. Importer le projet dans votre IDE Java préféré (par exemple, IntelliJ, Eclipse, etc.).
3. Configurer votre base de données MySQL en exécutant le script fourni (`weatherApp.sql`) pour créer la structure de la base de données.
4. Assurez-vous que les dépendances JDBC sont correctement ajoutées à votre projet. Si nécessaire, téléchargez le pilote JDBC MySQL et ajoutez-le à votre projet.
5. Exécutez l'application à partir de votre IDE.

## Auteur

- **CHAIMAA LACHHAB** - [Chaimaa Lachhab](https://github.com/ChaimaaLachhab, sous la supervision de l'école ENAA Béni Mellal.


