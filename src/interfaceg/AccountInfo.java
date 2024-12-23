package interfaceg;

import gb.Client;
import service.ClientService;
import javax.swing.*;
import exception.ClientNotFoundException;

public class AccountInfo {
    private JFrame parentFrame;
    private ClientService clientService;

    public AccountInfo(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService, String cin) {
        AccountInfo form = new AccountInfo(parentFrame, clientService);
        form.display(cin);
    }

    public void display(String cin) {
        try {
            // Find client by CIN using client service
            Client client = clientService.trouverClient(cin);

            if (client != null) {
                // Prepare client details to be displayed
                String clientDetails = "CIN: " + client.getCin() + "\n" +
                                       "Name: " + client.getNom() + "\n" +
                                       "Surname: " + client.getPrenom() + "\n" +
                                       "Phone: " + client.getTelephone();
                // Display client details in a message dialog
                JOptionPane.showMessageDialog(parentFrame, clientDetails, "Client Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClientNotFoundException e) {
            // Inform the user that the client was not found
            JOptionPane.showMessageDialog(parentFrame, "No client found with the provided CIN.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Handle unexpected errors
            JOptionPane.showMessageDialog(parentFrame, "An error occurred while retrieving client details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
