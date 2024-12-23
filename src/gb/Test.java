package gb;

import service.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize services
        ClientService clientService = new ClientService();
        CompteService compteService = new CompteService(clientService);
        OperationService operationService = new OperationService(compteService);
        AuthService authService = new AuthService(clientService);
        String userCin;
        boolean exit = false;

        while (!exit) {
            // Welcome page
            System.out.println("\n--- Bienvenue ---");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Admin Login
                    System.out.print("Entrez votre CIN (Admin): ");
                    String adminCin = scanner.nextLine();
                    System.out.print("Entrez le mot de passe: ");
                    String adminPassword = scanner.nextLine();

                    try {
                        if (authService.verifyAdminCredentials(adminCin, adminPassword)) {
                            adminMenu(scanner, clientService, compteService);
                        } else {
                            System.out.println("Identifiants incorrects. Accès refusé.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    // User Login
                    System.out.print("Entrez votre CIN: ");
                    userCin = scanner.nextLine();

                    Client userClient = authService.verifyUserCredentials(userCin);  // Use the new method

                    if (userClient != null) {
                        userMenu(scanner, userClient, compteService, operationService);
                    } else {
                        System.out.println("CIN non trouvé. Accès refusé.");
                    }
                
                    break;

                case 3:
                    // Exit
                    exit = true;
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void adminMenu(Scanner scanner, ClientService clientService, CompteService compteService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des comptes bancaires");
            System.out.println("3. Retour");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminClientMenu(scanner, clientService);
                    break;

                case 2:
                    adminAccountMenu(scanner, compteService);
                    break;

                case 3:
                    back = true;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void adminClientMenu(Scanner scanner, ClientService clientService) {
        System.out.println("\n--- Gestion des clients ---");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Consulter un client");
        System.out.println("3. Modifier un client");
        System.out.println("4. Supprimer un client");
        System.out.println("5. Afficher tous les clients");
        System.out.print("Choisissez une action: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Entrez le CIN: ");
                    String cin = scanner.nextLine();
                    System.out.print("Entrez le nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le prénom: ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez le téléphone: ");
                    String telephone = scanner.nextLine();
                    clientService.ajouterClient(new Client(cin, nom, prenom, telephone));
                    System.out.println("Client ajouté avec succès.");
                    break;

                case 2:
                    System.out.print("Entrez le CIN: ");
                    cin = scanner.nextLine();
                    Client client = clientService.trouverClient(cin);
                    if (client != null) client.afficherClient();
                    break;

                case 3:
                    System.out.print("Entrez le CIN: ");
                    cin = scanner.nextLine();
                    System.out.print("Entrez le nouveau nom: ");
                    nom = scanner.nextLine();
                    System.out.print("Entrez le nouveau prénom: ");
                    prenom = scanner.nextLine();
                    System.out.print("Entrez le nouveau téléphone: ");
                    telephone = scanner.nextLine();
                    clientService.mettreAJourClient(cin, nom, prenom, telephone);
                    System.out.println("Client mis à jour avec succès.");
                    break;

                case 4:
                    System.out.print("Entrez le CIN: ");
                    cin = scanner.nextLine();
                    clientService.supprimerClient(cin);
                    System.out.println("Client supprimé avec succès.");
                    break;

                case 5:
                    clientService.afficherTousLesClients();
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void adminAccountMenu(Scanner scanner, CompteService compteService) {
        System.out.println("\n--- Gestion des comptes ---");
        System.out.println("1. Ajouter un compte courant");
        System.out.println("2. Ajouter un compte épargne");
        System.out.println("3. Supprimer un compte");
        System.out.println("4. Afficher tous les comptes");
        System.out.print("Choisissez une action: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    System.out.print("Entrez le CIN du client: ");
                    String cin = scanner.nextLine();
                    System.out.print("Entrez le numéro du compte: ");
                    String numero = scanner.nextLine();
                    System.out.print("Entrez le solde initial: ");
                    double solde = scanner.nextDouble();
                    System.out.print("Entrez le decouvert: ");
                    double decouvert = scanner.nextDouble();
                    scanner.nextLine();
                    compteService.ajouterCompteCourant(numero, cin, solde,decouvert); // Default overdraft
                    System.out.println("Compte ajouté avec succès.");
                    break;
                case 2: // Add savings account (Compte Epargne)
                    System.out.print("Entrez le CIN du client: ");
                    String cinEpargne = scanner.nextLine();
                    System.out.print("Entrez le numéro du compte: ");
                    String numeroEpargne = scanner.nextLine();
                    System.out.print("Entrez le solde initial: ");
                    double soldeEpargne = scanner.nextDouble();
                    System.out.print("Entrez le taux d'intérêt: ");
                    scanner.nextLine();
                    // Assuming CompteEpargne has no overdraft
                    compteService.ajouterCompteEpargne(numeroEpargne, cinEpargne, soldeEpargne,soldeEpargne);
                    System.out.println("Compte épargne ajouté avec succès.");
                    break;
    

                case 3:
                    System.out.print("Entrez le numéro du compte à supprimer: ");
                    numero = scanner.nextLine();
                    compteService.supprimerCompte(numero);
                    System.out.println("Compte supprimé avec succès.");
                    break;

                case 4:
                    compteService.afficherTousLesComptes();
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void userMenu(Scanner scanner, Client userClient, CompteService compteService, OperationService operationService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Menu Utilisateur ---");
            System.out.println("1. Consulter vos informations");
            System.out.println("2. Consulter vos comptes");
            System.out.println("3. Opérations sur un compte");
            System.out.println("4. Retour");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    userClient.afficherClient();
                    break;

                case 2:
                    compteService.showAllAccounts(userClient.getCin());
                    break;

                case 3:
                	  // Ask for the account number
                    System.out.print("Entrez votre numéro de compte: ");
                    String numeroCompte = scanner.nextLine();
                    
                    // Retrieve the account using the numeroCompte
                    Compte compte = compteService.trouverCompte(numeroCompte);  // Retrieve the account
                    if (compte == null) {
                        System.out.println("Le numéro de compte n'existe pas. Veuillez vérifier et réessayer.");
                        break; // Exit if the account doesn't exist
                    }

                    // Verify the CIN of the account matches the client's CIN
                    if (!compte.getCinClient().equals(userClient.getCin())) {  // Assuming `clientCin` contains the current client's CIN
                        System.out.println("Le numéro de compte ne correspond pas à votre CIN. Opération non autorisée.");
                        break; // Exit if the CIN does not match
                    }

                    // Operations options
                    System.out.println("\n--- Gestion des opérations ---");
                    System.out.println("1. Retirer de l'argent");
                    System.out.println("2. Déposer de l'argent");
                    System.out.println("3. Effectuer un virement");
                    System.out.print("Choisissez une action: ");
                    int operationChoice1 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (operationChoice1) {
                        case 1:
                            // Withdraw money
                            System.out.print("Entrez le montant à retirer: ");
                            double montantRetrait1 = scanner.nextDouble();
                            try {
                                operationService.retirer(numeroCompte, montantRetrait1);
                                System.out.println("Retrait effectué avec succès.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            // Deposit money
                            System.out.print("Entrez le montant à déposer: ");
                            double montantDepot1 = scanner.nextDouble();
                            try {
                                operationService.verser(numeroCompte, montantDepot1);
                                System.out.println("Dépôt effectué avec succès.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 3:
                            // Perform transfer (virement)
                            System.out.print("Entrez le numéro du compte destination: ");
                            String compteDest = scanner.nextLine();
                            System.out.print("Entrez le montant à transférer: ");
                            double montantVirement = scanner.nextDouble();
                            try {
                                operationService.virer(numeroCompte, compteDest, montantVirement);
                                System.out.println("Virement effectué avec succès.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Option invalide. Veuillez réessayer.");
                            break;
                    }
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
                    }
                    break;




            }
        }
    }

