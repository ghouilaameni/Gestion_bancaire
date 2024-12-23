package service;
import gb.*;
import exception.*;
import java.util.HashMap;
public class AuthService {

    private ClientService serviceClient; // To check if the client exists
    private HashMap<String, String> clientsCredentials; // Store CIN and Password pairs (For simplicity)
    private static final String ADMIN_USERNAME = "admin"; // Simple admin username
    private static final String ADMIN_PASSWORD = "admin123"; // Simple admin password

    // Constructor
    public AuthService(ClientService serviceClient) {
        this.serviceClient = serviceClient;
        this.clientsCredentials = new HashMap<>();
    }
    // Method to check if the given username and password match the admin credentials
    public boolean verifyAdminCredentials(String username, String password) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Admin login successful.");
            return true;
        } else {
            System.out.println("Incorrect username or password.");
            return false;
        }
    }
    public Client verifyUserCredentials(String cin) {
        try {
            return serviceClient.trouverClient(cin);
        } catch (Exception e) {
            System.out.println("Error during user verification: " + e.getMessage());
            return null;
        }
    }
    // Login method (authenticates a client based on CIN and password)
    public boolean login(String cin, String password) throws ClientNotFoundException, AuthenticationException {
        // Check if the client exists in the system
        if (serviceClient.trouverClient(cin)==null) {
            throw new ClientNotFoundException("Client avec le CIN " + cin + " n'existe pas.");
        }

        // Check if the password matches
        if (clientsCredentials.containsKey(cin) && clientsCredentials.get(cin).equals(password)) {
            System.out.println("Connexion réussie.");
            return true;
        } else {
            throw new AuthenticationException("Échec de l'authentification. Mot de passe incorrect.");
        }
    }
}