package gb;

public class CompteEpargne extends Compte {
	// Taux d'intérêt du compte épargne
    private double tauxInteret; 

    // Constructeur
    public CompteEpargne(String numeroCompte, String cinClient, double solde, double tauxInteret) {
        super(numeroCompte, cinClient, solde);
        this.tauxInteret = tauxInteret;
    }

    // Getter et setter pour le taux d'intérêt
    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    // Implémentation de la méthode abstraite
    public void afficherDetails() {
        System.out.println("Compte Épargne - Numéro: " + getNumeroCompte());
        System.out.println("CIN du propriétaire: " + getCinClient());
        System.out.println("Solde: " + getSolde());
        System.out.println("Taux d'intérêt: " + tauxInteret);
        System.out.println("Date d'ouverture: " + getDateOuverture());
    }
}
