package gb;

public class Client {
	// Le CIN doit être final pour l'empêcher d'être
	//modifié après la création
	private final String cin;   
    private String nom;
    private String prenom;
    private String telephone;

    // Constructeur pour créer un nouveau client
    public Client(String cin, String nom, String prenom, String telephone) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }
    //getters and setters
    // Méthode d'accès pour obtenir le CIN 
    //pas de setter car le CIN est ne peut pas être modifié  
    public String getCin() {
        return cin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // Méthode pour afficher les informations du client
    public void afficherClient() {
        System.out.println("CIN: " + cin);
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Téléphone: " + telephone);
    }
}
