package net.oussama;

public class Employe {
    // Attributs privés de la classe
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    /**
     * Constructeur par défaut
     */
    public Employe() {
        this.id = 0;
        this.nom = "";
        this.poste = "";
        this.salaire = 0.0;
    }


    public Employe(int id, String nom, String poste, double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Getters et Setters pour accéder et modifier les attributs

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }


    public double getSalaire() {
        return salaire;
    }


    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public static int compareParSalaire(Employe e1, Employe e2) {
        return Double.compare(e1.getSalaire(), e2.getSalaire());
    }


    @Override
    public String toString() {
        return "ID: " + id +
                " | Nom: " + nom +
                " | Poste: " + poste +
                " | Salaire: " + salaire ;
    }
}