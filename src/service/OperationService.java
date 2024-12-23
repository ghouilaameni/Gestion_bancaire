package service;

import exception.*;
import gb.*;

public class OperationService {

    private CompteService serviceCompte;  // Service to manage accounts

    // Constructor
    public OperationService(CompteService serviceCompte) {
        this.serviceCompte = serviceCompte;
    }

    // Method to perform withdrawal based on numeroCompte
    public void retirer(String numeroCompte, double montant) throws InsufficientFundsException, CompteNotFoundException {
        // Retrieve the account using numeroCompte from the ServiceCompte's HashMap
        Compte compte = serviceCompte.trouverCompte(numeroCompte);  // Assuming getComptes() returns the existing HashMap

        if (compte == null) {
            throw new CompteNotFoundException("Compte avec le numéro " + numeroCompte + " n'existe pas.");
        }

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à retirer doit être positif.");
        }

        // Si le compte est un CompteCourant, vérifier le solde et le découvert
        if (compte instanceof CompteCourant) {
            double limite = compte.getSolde() + ((CompteCourant) compte).getDecouvert();
            if (montant > limite) {
                throw new InsufficientFundsException("Fonds insuffisants pour le retrait.");
            }
        } else if (montant > compte.getSolde()) {
            // Pour les comptes epargne, vérifier simplement le solde
            throw new InsufficientFundsException("Fonds insuffisants pour le retrait.");
        }
        compte.setSolde(compte.getSolde() - montant);
        System.out.println("Retrait effectué avec succès. Nouveau solde: " + compte.getSolde());
    }

    // Method to perform deposit based on numeroCompte
    public void verser(String numeroCompte, double montant) throws CompteNotFoundException {
        // Retrieve the account using numeroCompte from the ServiceCompte's HashMap
        Compte compte = serviceCompte.trouverCompte(numeroCompte);  // Assuming getComptes() returns the existing HashMap

        if (compte == null) {
            throw new CompteNotFoundException("Compte avec le numéro " + numeroCompte + " n'existe pas.");
        }

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à verser doit être positif.");
        }

        // Perform the deposit
        compte.setSolde(compte.getSolde() + montant);
        System.out.println("Versement effectué avec succès. Nouveau solde: " + compte.getSolde());
    }
    public void virer(String numeroCompteSource, String numeroCompteDest, double montant) 
            throws InsufficientFundsException, CompteNotFoundException {
        
        // Retrieve the source and destination accounts from the ServiceCompte's HashMap
        Compte compteSource = serviceCompte.trouverCompte(numeroCompteSource);
        Compte compteDest = serviceCompte.trouverCompte(numeroCompteDest);

        if (compteSource == null) {
            throw new CompteNotFoundException("Compte source avec le numéro " + numeroCompteSource + " n'existe pas.");
        }
        if (compteDest == null) {
            throw new CompteNotFoundException("Compte destination avec le numéro " + numeroCompteDest + " n'existe pas.");
        }

        // Ensure that the source and destination accounts are different
        if (numeroCompteSource.equals(numeroCompteDest)) {
            throw new IllegalArgumentException("Les numéros de compte source et destination doivent être différents.");
        }

        retirer(numeroCompteSource, montant);
        verser(numeroCompteDest, montant);

        System.out.println("Virement effectué avec succès de " + numeroCompteSource + " vers " + numeroCompteDest);
    }
}
