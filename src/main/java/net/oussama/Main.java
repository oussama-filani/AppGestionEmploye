package net.oussama;

import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in); // Scanner pour lire les entrées utilisateur
    private static GestionEmployes gestionEmployes = new GestionEmployes(); // Instance de la classe de gestion

    public static void main(String[] args) {
        int choix;
        boolean continuer = true;

        System.out.println("=== APPLICATION DE GESTION DES EMPLOYÉS ===");

        // Boucle principale du programme
        while (continuer) {
            printMenu();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    saisirEtAjouterEmploye();
                    break;
                case 2:
                    saisirEtModifierEmploye();
                    break;
                case 3:
                    saisirEtSupprimerEmploye();
                    break;
                case 4:
                    gestionEmployes.afficherEmployes();
                    break;
                case 5:
                    saisirEtRechercherEmploye();
                    break;
                case 6:
                    System.out.println("Masse salariale totale : " + gestionEmployes.calculerMasseSalariale());
                    break;
                case 7:
                    saisirEtTrierEmployes();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

            // Pause avant de revenir au menu
            if (continuer) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                scanner.nextLine();
            }
        }

        // Fermer le scanner à la fin du programme
        scanner.close();
    }

    /**
     * Affiche le menu principal avec les options disponibles
     */
    public static void printMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("0. Quitter");
    }

    /**
     * Demande à l'utilisateur les informations d'un nouvel employé et l'ajoute
     */
    public static void saisirEtAjouterEmploye() {
        System.out.println("\n=== AJOUTER UN EMPLOYÉ ===");

        // Vérifier si le tableau est plein
        if (gestionEmployes.isTableauPlein()) {
            System.out.println("Impossible d'ajouter un nouvel employé : le tableau est plein.");
            return;
        }

        // Saisie des informations de l'employé
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Poste : ");
        String poste = scanner.nextLine();

        System.out.print("Salaire : ");
        double salaire = scanner.nextDouble();

        // Créer un nouvel objet Employe
        Employe nouvelEmploye = new Employe(id, nom, poste, salaire);

        // Ajouter l'employé
        if (gestionEmployes.ajouterEmploye(nouvelEmploye)) {
            System.out.println("Employé ajouté avec succès.");
        } else {
            System.out.println("Erreur : Impossible d'ajouter l'employé. L'ID existe peut-être déjà.");
        }
    }

    /**
     * Demande à l'utilisateur l'ID d'un employé à modifier et les nouvelles informations
     */
    public static void saisirEtModifierEmploye() {
        System.out.println("\n=== MODIFIER UN EMPLOYÉ ===");

        // Vérifier s'il y a des employés dans le tableau
        if (gestionEmployes.isTableauVide()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }

        // Saisie de l'ID de l'employé à modifier
        System.out.print("Entrez l'ID de l'employé à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        // Saisie des nouvelles informations
        System.out.print("Nouveau nom (laissez vide pour ne pas changer) : ");
        String nouveauNom = scanner.nextLine();

        System.out.print("Nouveau poste (laissez vide pour ne pas changer) : ");
        String nouveauPoste = scanner.nextLine();

        System.out.print("Nouveau salaire (entrez 0 pour ne pas changer) : ");
        double nouveauSalaire = scanner.nextDouble();

        // Modifier l'employé
        if (gestionEmployes.modifierEmploye(id, nouveauNom, nouveauPoste, nouveauSalaire)) {
            System.out.println("Employé modifié avec succès.");
        } else {
            System.out.println("Aucun employé trouvé avec cet ID.");
        }
    }

    /**
     * Demande à l'utilisateur l'ID d'un employé à supprimer
     */
    public static void saisirEtSupprimerEmploye() {
        System.out.println("\n=== SUPPRIMER UN EMPLOYÉ ===");

        // Vérifier s'il y a des employés dans le tableau
        if (gestionEmployes.isTableauVide()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }

        // Saisie de l'ID de l'employé à supprimer
        System.out.print("Entrez l'ID de l'employé à supprimer : ");
        int id = scanner.nextInt();

        // Supprimer l'employé
        if (gestionEmployes.supprimerEmploye(id)) {
            System.out.println("Employé supprimé avec succès.");
        } else {
            System.out.println("Aucun employé trouvé avec cet ID.");
        }
    }

    /**
     * Demande à l'utilisateur un critère de recherche (nom ou poste)
     */
    public static void saisirEtRechercherEmploye() {
        System.out.println("\n=== RECHERCHER UN EMPLOYÉ ===");

        // Vérifier s'il y a des employés dans le tableau
        if (gestionEmployes.isTableauVide()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }

        // Saisie du critère de recherche
        System.out.print("Entrez le nom ou le poste à rechercher : ");
        String critere = scanner.nextLine();

        // Rechercher les employés correspondants
        gestionEmployes.rechercherEmploye(critere);
    }

    /**
     * Demande à l'utilisateur l'ordre de tri (croissant ou décroissant)
     */
    public static void saisirEtTrierEmployes() {
        System.out.println("\n=== TRIER LES EMPLOYÉS PAR SALAIRE ===");

        // Vérifier s'il y a suffisamment d'employés pour le tri
        if (gestionEmployes.getNombreEmployes() <= 1) {
            System.out.println("Pas assez d'employés pour effectuer un tri.");
            return;
        }

        // Saisie de l'ordre de tri
        System.out.println("Choisissez l'ordre de tri :");
        System.out.println("1. Ordre croissant");
        System.out.println("2. Ordre décroissant");
        System.out.print("Votre choix : ");

        int choix = scanner.nextInt();
        boolean ordreCroissant = (choix == 1);

        // Trier les employés
        gestionEmployes.trierEmployesParSalaire(ordreCroissant);
    }
}