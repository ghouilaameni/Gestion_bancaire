package interfaceg;

import javax.swing.*;
import service.ValidationService;
import java.awt.*;
import exception.ClientNotFoundException;
public class CompteCourantForm {

    public static void show() {
        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set up GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create labels and input fields
        JLabel cinLabel = new JLabel("Client CIN:");
        JTextField cinField = new JTextField(20);
        JLabel numeroLabel = new JLabel("Account Number:");
        JTextField numeroField = new JTextField(20);
        JLabel soldeLabel = new JLabel("Initial Balance:");
        JTextField soldeField = new JTextField(20);
        JLabel decouvertLabel = new JLabel("Allowed Overdraft:");
        JTextField decouvertField = new JTextField(20);

        // Set larger font for the fields and labels
        cinLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        numeroLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        soldeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        decouvertLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        cinField.setFont(new Font("Arial", Font.PLAIN, 16));
        numeroField.setFont(new Font("Arial", Font.PLAIN, 16));
        soldeField.setFont(new Font("Arial", Font.PLAIN, 16));
        decouvertField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add components to the panel with constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cinLabel, gbc);
        gbc.gridx = 1;
        panel.add(cinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(numeroLabel, gbc);
        gbc.gridx = 1;
        panel.add(numeroField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(soldeLabel, gbc);
        gbc.gridx = 1;
        panel.add(soldeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(decouvertLabel, gbc);
        gbc.gridx = 1;
        panel.add(decouvertField, gbc);

        // Show the form in a dialog with the panel
        int option = JOptionPane.showConfirmDialog(BankingAppContext.mainFrame, panel, "Add Current Account", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Get the values from the input fields
                String cin = cinField.getText().trim();
                String numero = numeroField.getText().trim();
                double solde = Double.parseDouble(soldeField.getText().trim());
                double decouvert = Double.parseDouble(decouvertField.getText().trim());

                // Validation of user inputs
                // Ensure all fields pass validation before proceeding
                if (ValidationService.validateNumericField(cin, "CIN") && 
                    ValidationService.validateNumericField(numero, "Account Number") && 
                    solde >= 0 && decouvert >= 0) {

                    // Call the service method to add "Compte Courant"
                    BankingAppContext.compteservice.ajouterCompteCourant(numero, cin, solde, decouvert);
                    JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Current Account added successfully.");

                    // Close the dialog after successful operation
                    BankingAppContext.mainFrame.setVisible(true);  // Show the main frame again if needed
                } else {
                    // If validation fails, show an error message and prevent further action
                    JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Please enter valid inputs for all fields.");
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Please enter valid numeric values for balance and overdraft.");
            } catch (ClientNotFoundException e) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, e.getMessage());
            
            } catch (Exception e) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "An error occurred. Please try again.");
            }
        }
    }
}
