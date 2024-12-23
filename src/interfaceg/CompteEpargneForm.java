package interfaceg;

import javax.swing.*;

import exception.ClientNotFoundException;
import service.ValidationService;

import java.awt.*;

public class CompteEpargneForm {

    public static void show() {
        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set up GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create labels and input fields
        JLabel cinLabel = new JLabel("CIN du client:");
        JTextField cinField = new JTextField(20);
        JLabel numeroLabel = new JLabel("Numéro du compte:");
        JTextField numeroField = new JTextField(20);
        JLabel soldeLabel = new JLabel("Solde initial:");
        JTextField soldeField = new JTextField(20);
        JLabel tauxLabel = new JLabel("Taux d'intérêt (%):");
        JTextField tauxField = new JTextField(20);

        // Set larger font for the fields and labels
        cinLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        numeroLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        soldeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tauxLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        cinField.setFont(new Font("Arial", Font.PLAIN, 16));
        numeroField.setFont(new Font("Arial", Font.PLAIN, 16));
        soldeField.setFont(new Font("Arial", Font.PLAIN, 16));
        tauxField.setFont(new Font("Arial", Font.PLAIN, 16));

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
        panel.add(tauxLabel, gbc);
        gbc.gridx = 1;
        panel.add(tauxField, gbc);

        // Show the form in a dialog with the panel
        int option = JOptionPane.showConfirmDialog(BankingAppContext.mainFrame, panel, "Ajouter un Compte Épargne", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Get the values from the input fields
                String cin = cinField.getText().trim();
                String numero = numeroField.getText().trim();
                double solde = Double.parseDouble(soldeField.getText().trim());
                double taux = Double.parseDouble(tauxField.getText().trim());

                // Validation of user inputs
                if (!ValidationService.validateNumericField(cin,cin) || !ValidationService.validateNumericField(numero,numero) || solde < 0 || taux < 0) {
                    return;
                }

                // Call the service method to add "Compte Épargne"
                BankingAppContext.compteservice.ajouterCompteEpargne(numero, cin, solde, taux);
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Compte Épargne ajouté avec succès.");
            } catch (NumberFormatException e) {
                // Handle invalid number format
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Veuillez entrer des valeurs numériques valides pour le solde et le taux d'intérêt.");
            } catch (ClientNotFoundException e) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, e.getMessage());
            
            }
            catch (Exception e) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Une erreur est survenue. Veuillez réessayer.");
            }
        }
    }
}
