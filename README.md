# Application de Gestion d'une Bibliothèque Municipale v2

## Contexte du Projet

Ce projet consiste à automatiser les tâches d'une bibliothèque municipale à travers une application console en Java 8. L'application permet de gérer l'inventaire des documents, les utilisateurs, les emprunts, et les réservations.

## Fonctionnalités Principales

1. **Gestion des Documents**
   - Ajouter, modifier, supprimer des documents
   - Afficher tous les documents
   - Rechercher des documents

2. **Gestion des Utilisateurs**
   - Ajouter, modifier, supprimer des utilisateurs
   - Afficher tous les utilisateurs
   - Rechercher des utilisateurs

3. **Gestion des Emprunts**
   - Emprunter un document (limites: 3 pour étudiants, 10 pour professeurs)
   - Retourner un document

4. **Gestion des Réservations**
   - Réserver un document
   - Annuler une réservation

5. **Quitter l'application**

## Structure de l'Application

L'application suit une architecture en couches:

- **Couche de Présentation**: `ConsoleUI.java`
- **Couche Métier**: Classes de gestion des documents, utilisateurs, et de la bibliothèque
- **Couche Utilitaire**: `DateUtils.java`, `InputValidator.java`
- **Couche de Persistance**: DAOs pour documents, utilisateurs, emprunts, et réservations

## Technologies Utilisées

- Java 8
- PostgreSQL
- git
- Github

## Prérequis

- Java Development Kit (JDK) 8
- PostgreSQL

## Installation

1. Cloner le dépôt:
   ```bash
   git clone https://github.com/JavaAura/Mokhlis_Belhaj_S1_B2_gestionBiblio2
   ```

2. Configurer la base de données PostgreSQL:
   - Installer PostgreSQL
   - Créer une base de données nommée `biblio_db`
   - Configurer le fichier `config.properties` dans le répertoire `utilitaire/`:
     ```properties
     jdbc.url=jdbc:postgresql://localhost:5432/biblio_db
     jdbc.username=your_username
     jdbc.password=your_password
     ```

3. Exécuter l'application:
   ```bash
   java -jar <chemin_vers_le_fichier_biblio.jar>
   ```

## Menu Interactif

L'application propose un menu interactif:

```
===== Menu Principal =====
1. Gestion des documents
2. Gestion des utilisateurs
3. Gestion des emprunts
4. Gestion des réservations
5. Quitter
==========================
Veuillez choisir une option : 
```

## Auteur

Mokhlis Belhaj