package service;
import java.util.List;
import gb.Client;
import exception.ClientNotFoundException;
public class BankService {
	private final ClientService clientService;
    private final CompteService compteService;

    public BankService(ClientService clientService, CompteService compteService) {
        this.clientService = clientService;
        this.compteService = compteService;
    }
    public void supprimerClientEtComptes(String cin) throws ClientNotFoundException {
        Client client = clientService.trouverClient(cin);
        if (client == null) {
            throw new ClientNotFoundException("Aucun client trouvé avec ce CIN.");
        }

        List<String> comptesClient = compteService.trouverComptesParCIN(cin);
        for (String numeroCompte : comptesClient) {
            compteService.supprimerCompte(numeroCompte);
        }
        clientService.supprimerClient(cin);
        System.out.println("Client et ses comptes supprimés avec succès.");
    }
}
