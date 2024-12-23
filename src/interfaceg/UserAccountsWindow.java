package interfaceg;

import gb.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserAccountsWindow {

    public static void show(JFrame parentFrame) {
        // Hide the parent frame
        parentFrame.setVisible(false);

        // Create a new frame for the accounts display
        JFrame frame = new JFrame("Affichage de tous les comptes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Retrieve the logged-in user's CIN
        String userCin = BankingAppContext.cin;

        // Use the client service to find accounts associated with the CIN
        List<String> comptesAssocies = BankingAppContext.compteservice.trouverComptesParCIN(userCin);

        if (comptesAssocies == null || comptesAssocies.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Aucun compte trouvé pour cet utilisateur.");
            parentFrame.setVisible(true); // Show parent if no accounts to display
        } else {
            // Define column names
            String[] columnNames = {"Numéro de compte", "Type de compte", "Solde", "Détails"};

            // Create a DefaultTableModel with column names and an empty data array
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (String numeroCompte : comptesAssocies) {
                // Fetch account details using the account number
                Compte compte = BankingAppContext.compteservice.trouverCompte(numeroCompte);

                if (compte != null) {
                    Object[] rowData = null;

                    if (compte instanceof CompteCourant) {
                        CompteCourant compteCourant = (CompteCourant) compte;
                        rowData = new Object[]{
                            compteCourant.getNumeroCompte(),
                            "Courant",
                            compteCourant.getSolde(),
                            "Découvert: " + compteCourant.getDecouvert()
                        };
                    } else if (compte instanceof CompteEpargne) {
                        CompteEpargne compteEpargne = (CompteEpargne) compte;
                        rowData = new Object[]{
                            compteEpargne.getNumeroCompte(),
                            "Epargne",
                            compteEpargne.getSolde(),
                            "Taux d'intérêt: " + compteEpargne.getTauxInteret()
                        };
                    }

                    if (rowData != null) {
                        tableModel.addRow(rowData);
                    }
                }
            }

            // Create the JTable with the table model
            JTable accountsTable = new JTable(tableModel);
            accountsTable.setFillsViewportHeight(true);

            // Create a scroll pane to make the table scrollable
            JScrollPane scrollPane = new JScrollPane(accountsTable);

            // Add the scroll pane to the center of the frame
            frame.add(scrollPane, BorderLayout.CENTER);

            // Create a return button
            JButton returnButton = new JButton("Retour");
            returnButton.addActionListener(e -> {
                frame.dispose(); // Close this window
                parentFrame.setVisible(true); // Show the parent frame again
            });

            // Add the return button to the bottom of the frame
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(returnButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Display the frame
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        }
    }
}
