package net.oussama;


/**
 * Classe qui gère la manipulation du tableau d'employés.
 * Cette classe contient toutes les méthodes nécessaires pour ajouter,
 * modifier, supprimer et rechercher des employés.
 */
public class GestionEmployes {
    // Constantes
    private static final int MAX_EMPLOYES = 50; // Nombre maximum d'employés

    // Variables
    private Employe[] tableauEmployes; // Tableau pour stocker les employés
    private int nombreEmployes; // Nombre actuel d'employés dans le tableau


    public GestionEmployes() {
        this.tableauEmployes = new Employe[MAX_EMPLOYES];
        this.nombreEmployes = 0;
    }

    /**
     * Ajoute un nouvel objet Employe au tableau
     */
    public boolean ajouterEmploye(Employe employe) {
        // Vérifier si le tableau est plein
        if (nombreEmployes >= MAX_EMPLOYES) {
            return false;
        }

        // Vérifier si l'ID existe déjà
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getId() == employe.getId()) {
                return false;
            }
        }

        // Ajouter l'employé au tableau
        tableauEmployes[nombreEmployes] = employe;
        nombreEmployes++;

        return true;
    }

    /**
     * Modifie un employé existant dans le tableau
     */
    public boolean modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        // Chercher l'employé avec l'ID spécifié
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getId() == id) {
                // Mettre à jour les informations si elles sont fournies
                if (!nouveauNom.isEmpty()) {
                    tableauEmployes[i].setNom(nouveauNom);
                }

                if (!nouveauPoste.isEmpty()) {
                    tableauEmployes[i].setPoste(nouveauPoste);
                }

                if (nouveauSalaire > 0) {
                    tableauEmployes[i].setSalaire(nouveauSalaire);
                }

                return true;
            }
        }

        return false; // Employé non trouvé
    }

    /**
     * Supprime un employé du tableau à l'aide de son identifiant
     */
    public boolean supprimerEmploye(int id) {
        // Chercher l'employé avec l'ID spécifié
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getId() == id) {
                // Déplacer tous les employés suivants d'une position vers l'arrière
                for (int j = i; j < nombreEmployes - 1; j++) {
                    tableauEmployes[j] = tableauEmployes[j + 1];
                }

                // Réduire le nombre d'employés
                nombreEmployes--;

                // Effacer la dernière référence pour éviter les fuites de mémoire
                tableauEmployes[nombreEmployes] = null;

                return true;
            }
        }

        return false; // Employé non trouvé
    }

    /**
     * Affiche tous les employés du tableau
     */
    public void afficherEmployes() {
        System.out.println("\n=== LISTE DES EMPLOYÉS ===");

        // Vérifier s'il y a des employés dans le tableau
        if (nombreEmployes == 0) {
            System.out.println("Aucun employé enregistré.");
            return;
        }

        // Afficher les informations de chaque employé
        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println((i + 1) + ". " + tableauEmployes[i]);
        }
    }

    /**
     * Recherche un employé par nom ou poste, et affiche ses informations
     */
    public boolean rechercherEmploye(String critere) {
        boolean trouve = false;

        // Convertir le critère en minuscules pour une recherche insensible à la casse
        String critereLower = critere.toLowerCase();

        // Parcourir tous les employés
        for (int i = 0; i < nombreEmployes; i++) {
            String nomLower = tableauEmployes[i].getNom().toLowerCase();
            String posteLower = tableauEmployes[i].getPoste().toLowerCase();

            // Vérifier si le nom ou le poste contient le critère
            if (nomLower.contains(critereLower) || posteLower.contains(critereLower)) {
                if (!trouve) {
                    System.out.println("\nEmployés trouvés :");
                    trouve = true;
                }

                System.out.println("- " + tableauEmployes[i]);
            }
        }

        // Afficher un message si aucun employé n'a été trouvé
        if (!trouve) {
            System.out.println("Aucun employé trouvé avec ce critère.");
        }

        return trouve;
    }

    /**
     * Calcule la somme des salaires des employés présents dans le tableau
     */
    public double calculerMasseSalariale() {
        double total = 0.0;

        // Additionner les salaires de tous les employés
        for (int i = 0; i < nombreEmployes; i++) {
            total += tableauEmployes[i].getSalaire();
        }

        return total;
    }

    /**
     * Trie et affiche les employés par salaire dans l'ordre choisi
     */
    public void trierEmployesParSalaire(boolean ordreCroissant) {
        // Vérifier s'il y a des employés dans le tableau
        if (nombreEmployes <= 1) {
            System.out.println("Pas assez d'employés pour effectuer un tri.");
            return;
        }

        // Créer une copie du tableau pour ne pas modifier l'ordre original
        Employe[] tableauTrie = new Employe[nombreEmployes];
        for (int i = 0; i < nombreEmployes; i++) {
            tableauTrie[i] = tableauEmployes[i];
        }

        // Tri à bulles (pour la simplicité)
        for (int i = 0; i < nombreEmployes - 1; i++) {
            for (int j = 0; j < nombreEmployes - i - 1; j++) {
                int comparaison = Employe.compareParSalaire(tableauTrie[j], tableauTrie[j + 1]);

                // Inverser la comparaison si l'ordre est décroissant
                if (!ordreCroissant) {
                    comparaison = -comparaison;
                }

                // Échanger les éléments si nécessaire
                if (comparaison > 0) {
                    Employe temp = tableauTrie[j];
                    tableauTrie[j] = tableauTrie[j + 1];
                    tableauTrie[j + 1] = temp;
                }
            }
        }

        // Afficher le résultat
        System.out.println("\nEmployés triés par salaire (" +
                (ordreCroissant ? "ordre croissant" : "ordre décroissant") +
                ") :");

        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println((i + 1) + ". " + tableauTrie[i]);
        }
    }

    /**
     * Vérifie si le tableau d'employés est plein
     */
    public boolean isTableauPlein() {
        return nombreEmployes >= MAX_EMPLOYES;
    }

    /**
     * Vérifie si le tableau d'employés est vide
     */
    public boolean isTableauVide() {
        return nombreEmployes == 0;
    }

    /**
     * Retourne le nombre d'employés actuellement dans le tableau
     */
    public int getNombreEmployes() {
        return nombreEmployes;
    }
}