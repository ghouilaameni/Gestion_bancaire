package service;
import java.util.HashMap;
import java.util.Map;
import exception.*;
import gb.Client;

public class ClientService {

    // This HashMap will store clients, using their CIN as the unique identifier
    private HashMap<String, Client> clientsMap;
    // Constructor
    public ClientService() {
        clientsMap = new HashMap<>();
    }
    public Map<String, Client> getAllClients() {
        return clientsMap;
    }
    // Add a new client to the system
    public void ajouterClient(Client client) {
        if (clientsMap.containsKey(client.getCin())) {
            System.out.println("Le client avec ce CIN existe déjà.");
        } else {
            clientsMap.put(client.getCin(), client);
            System.out.println("Client ajouté avec succès.");
        }
    }
    // Retrieve a client by CIN
    public Client trouverClient(String cin) throws ClientNotFoundException {
        if (clientsMap.containsKey(cin)) {
            return clientsMap.get(cin);
        } else {
            throw new ClientNotFoundException("Aucun client trouvé avec le CIN: " + cin);
        }
    }

    // Update client's information
    public void mettreAJourClient(String cin, String nom, String prenom, String telephone) throws ClientNotFoundException {
        // Find the client by CIN (throws ClientNotFoundException if not found)
        Client client = trouverClient(cin);  
        // Update the client's information
        client.setNom(nom);  // Update name
        client.setPrenom(prenom);  // Update last name
        client.setTelephone(telephone);  // Update phone number
        System.out.println("Client " + cin + " updated successfully.");
    }

    // Display all clients (optional)
    public void afficherTousLesClients() {
        if (clientsMap.isEmpty()) {
            System.out.println("Aucun client enregistré.");
        } else {
            for (Client client : clientsMap.values()) {
                System.out.println("CIN: " + client.getCin() + " | Nom: " + client.getNom() + " | Prénom: " + client.getPrenom());
            }
        }
    }
	public void supprimerClient(String cin) {
		clientsMap.remove(cin);
	}
}
