package interfaceg;
import javax.swing.*;
import java.awt.*;

public class CheckingAccountForm {

    public static void show() {
        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set up GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create labels and input fields
        JLabel accountNumberLabel = new JLabel("Account Number:");
        JTextField accountNumberField = new JTextField(20);
        JLabel balanceLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField(20);
        JLabel overdraftLabel = new JLabel("Overdraft Limit:");
        JTextField overdraftField = new JTextField(20);

        // Set larger font for the fields and labels
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        overdraftLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        accountNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceField.setFont(new Font("Arial", Font.PLAIN, 16));
        overdraftField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add components to the panel with constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(accountNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(accountNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(balanceLabel, gbc);
        gbc.gridx = 1;
        panel.add(balanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(overdraftLabel, gbc);
        gbc.gridx = 1;
        panel.add(overdraftField, gbc);

        // Show the form in a dialog with the panel
        int option = JOptionPane.showConfirmDialog(BankingAppContext.mainFrame, panel, "Add Checking Account", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Get the values from the input fields
                String accountNumber = accountNumberField.getText().trim();
                double balance = Double.parseDouble(balanceField.getText().trim());
                double overdraft = Double.parseDouble(overdraftField.getText().trim());

                /* Validation of user inputs
                if (ValidationService.validateNumericField(accountNumber,"account number ") || balance < 0 || overdraft < 0) {
                    JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Please enter valid values for all fields.");
                    return;
                }*/

                // Call the service method to add "Checking Account"
                BankingAppContext.compteservice.ajouterCompteCourant(accountNumber, BankingAppContext.cin, balance, overdraft);
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Checking account added successfully.");
                
                // Ask if the user wants to add another account
                int addAnother = JOptionPane.showConfirmDialog(BankingAppContext.mainFrame, "Do you want to add another account?", "Add Another Account", JOptionPane.YES_NO_OPTION);
                if (addAnother == JOptionPane.YES_OPTION) {
                    show();  // Show the form again to add another account
                }

            } catch (NumberFormatException e) {
                // Handle invalid number format
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Please enter valid numeric values for balance and overdraft limit.");
            } catch (Exception e) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "An error occurred. Please try again.");
            }
        }
    }
}
