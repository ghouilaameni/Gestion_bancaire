package gb;

import java.time.LocalDate;

public abstract class Compte {
	//Attributs d'un compte
    private String numeroCompte;
    private String cinClient;
    private double solde;
    private LocalDate dateOuverture;
    
 // Constructeur pour créer un nouveau compte
    public Compte(String numeroCompte, String cinClient, double solde) {
        this.numeroCompte = numeroCompte;
        this.cinClient = cinClient;
        this.solde = solde;
        this.dateOuverture =LocalDate.now();
    }
 // Getters and Setters
 // Les setters pour numeroCompte et cinProprietaire ne sont pas inclus pour préserver l'intégrité des données.
 // Ces attributs identifient de manière unique un compte et un client, et ne doivent pas être modifiables
 // afin d'éviter les incohérences dans le système.
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getCinClient() {
        return cinClient;
    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public LocalDate getDateOuverture() {
        return dateOuverture;
    }
    //methode abstraite
    public abstract void afficherDetails();
}