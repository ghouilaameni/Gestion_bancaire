package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.ClientNotFoundException;
import gb.*;

public class CompteService {

    private HashMap<String, Compte> comptesMap;
    private ClientService serviceClient;  // Service to check if a CIN exists

    // Constructor
    public CompteService(ClientService serviceClient) {
        comptesMap = new HashMap<>();
        this.serviceClient = serviceClient;
    }
    public Map<String, Compte> getAllComptes() {
        return comptesMap;
    }
 // Generic method to add accounts
    private <T extends Compte> void ajouterCompte(String numeroCompte, String cin, T compte) throws ClientNotFoundException {
        serviceClient.trouverClient(cin);
        if (comptesMap.containsKey(numeroCompte)) {
            System.out.println("Le compte avec ce numéro existe déjà.");
            return;
        }
        comptesMap.put(numeroCompte, compte);
        System.out.println("Compte ajouté avec succès: " + compte.getClass().getSimpleName());
    }
    // Method to add a savings account
    public void ajouterCompteEpargne(String numeroCompte, String cin, double solde, double taux) throws ClientNotFoundException {
        CompteEpargne compteEpargne = new CompteEpargne(numeroCompte, cin, solde, taux);
        ajouterCompte(numeroCompte, cin, compteEpargne);
    }

    // Method to add a current account
    public void ajouterCompteCourant(String numeroCompte, String cin, double solde, double decouvert) throws ClientNotFoundException {
        CompteCourant compteCourant = new CompteCourant(numeroCompte, cin, solde, decouvert);
        ajouterCompte(numeroCompte, cin, compteCourant);
    }

    // Retrieve an account by account number
    public Compte trouverCompte(String numeroCompte) {
        if (comptesMap.containsKey(numeroCompte)) {
            return comptesMap.get(numeroCompte);
        } else {
            System.out.println("Aucun compte trouvé avec ce numéro.");
            return null;
        }
    }
    public void supprimerCompte(String numeroCompte) {
        // Check if the account exists in the map
        if (comptesMap.containsKey(numeroCompte)) {
            comptesMap.remove(numeroCompte); // Remove the account
            System.out.println("Compte avec le numéro " + numeroCompte + " supprimé avec succès.");
        } else {
            System.out.println("Aucun compte trouvé avec le numéro " + numeroCompte );
        }
    }
 // Find all accounts associated with a specific client's CIN
    public List<String> trouverComptesParCIN(String cin)
    {
    	//here the key is the string (the account number)
    	//the value is the compte
    	List <String> comptesAssocies =new ArrayList<>();
    	for(Map.Entry<String, Compte> entry :comptesMap.entrySet())
    	{
    		if(entry.getValue().getCinClient().equals(cin))
    		{
    			comptesAssocies.add(entry.getKey());	
    		}
    	}
    	return comptesAssocies;
    	
    }

    // Display all accounts
    public void afficherTousLesComptes() {
        if (comptesMap.isEmpty()) {
            System.out.println("Aucun compte enregistré.");
        } else {
            for (Compte compte : comptesMap.values()) {
                System.out.println("Numéro de compte: " + compte.getNumeroCompte() +
                        " | Solde: " + compte.getSolde() +
                        " | Date d'ouverture: " + compte.getDateOuverture());
            }
        }
    }
    public void showAllAccounts(String cin) {
        // Call the trouverComptesParCIN method to get the accounts associated with the CIN
        List<String> comptesAssocies = trouverComptesParCIN(cin);
        
        if (comptesAssocies.isEmpty()) {
            System.out.println("No accounts found for CIN: " + cin);
        } else {
            System.out.println("Accounts associated with CIN: " + cin);
            for (String accountNumber : comptesAssocies) {
                // Fetch account details using the account number
                Compte compte = trouverCompte(accountNumber); // Fetch the Compte object
                
                if (compte != null) {
                    System.out.println("---------------------------------");
                    System.out.println("Account Number: " + accountNumber);
                    System.out.println("Owner CIN: " + compte.getCinClient());
                    System.out.println("Balance: " + compte.getSolde());
                    
                    // Check the account type and display specific details
                    if (compte instanceof CompteEpargne) {
                        CompteEpargne compteEpargne = (CompteEpargne) compte;
                        System.out.println("Account Type: Compte Épargne");
                        System.out.println("Taux: " + compteEpargne.getTauxInteret());
                    } else if (compte instanceof CompteCourant) {
                        CompteCourant compteCourant = (CompteCourant) compte;
                        System.out.println("Account Type: Compte Courant");
                        System.out.println("Découvert: " + compteCourant.getDecouvert());
                    } else {
                        System.out.println("Account Type: Unknown");
                    }
                    System.out.println("---------------------------------");
                }
            }
        }
    }

  
    }

