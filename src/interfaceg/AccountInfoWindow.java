package interfaceg;
import javax.swing.*;
import exception.ClientNotFoundException;
import gb.Client;
public class AccountInfoWindow {
    public static void show() {
        // Assume userClient has a method to retrieve user information (e.g., name, email, balance)
        // Replace these with actual methods from your userClient or database.
        Client c;
        try {
            c = BankingAppContext.clientservice.trouverClient(BankingAppContext.cin);
            String userName = c.getNom();
            String userPreNom = c.getPrenom();
            String telephone = c.getTelephone();  // Get user telephone
            // Construct the message to display the account information
            String accountInfoMessage = "<html><body>"
                    + "<h3>User Information</h3>"
                    + "<p><strong>Name:</strong> " + userName + " " + userPreNom + "</p>"
                    + "<p><strong>Phone:</strong> " + telephone + "</p>"
                    // Add actual balance or other relevant info here
                    + "<p><strong>Account Balance:</strong> Not available</p>"  // Replace with actual account balance
                    + "</body></html>";

            // Display the account info in a JOptionPane
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, accountInfoMessage, "Account Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
    }
}
