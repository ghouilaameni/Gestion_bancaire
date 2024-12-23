package gb;

public class CompteCourant extends Compte {
    private double decouvert; // Montant du découvert autorisé

    // Constructeur
    public CompteCourant(String numeroCompte, String cinClient, double solde, double decouvert) {
        super(numeroCompte, cinClient, solde);
        this.decouvert = decouvert;
    }

    // Getter et setter pour le découvert
    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
    public void afficherDetails() {
        System.out.println("Compte Courant - Numéro: " + getNumeroCompte());
        System.out.println("CIN du propriétaire: " + getCinClient());
        System.out.println("Solde: " + getSolde());
        System.out.println("Découvert autorisé: " + decouvert);
        System.out.println("Date d'ouverture: " + getDateOuverture());
    }
}
