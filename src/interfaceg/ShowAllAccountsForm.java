package interfaceg;
import gb.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

public class ShowAllAccountsForm {

    public static void show() {
        // Retrieve all accounts from the CompteService
        Map<String, Compte> comptes = BankingAppContext.compteservice.getAllComptes();

        // Check if there are any accounts to display
        if (comptes.isEmpty()) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Aucun compte enregistré.");
        } else {
            // Define column names based on account type
            String[] columnNames = {"Numéro de compte", "Solde", "Date d'ouverture", "Détails"};

            // Create a DefaultTableModel with column names and an empty data array
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Compte compte : comptes.values()) {
                // Prepare row data based on account type
                Object[] rowData = null;

                if (compte instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) compte;
                    rowData = new Object[] {
                        compteCourant.getNumeroCompte(),
                        compteCourant.getSolde(),
                        compteCourant.getDateOuverture(),
                        "Découvert: " + compteCourant.getDecouvert()  // Show overdraft value for CompteCourant
                    };
                } else if (compte instanceof CompteEpargne) {
                    CompteEpargne compteEpargne = (CompteEpargne) compte;
                    rowData = new Object[] {
                        compteEpargne.getNumeroCompte(),
                        compteEpargne.getSolde(),
                        compteEpargne.getDateOuverture(),
                        "Taux d'intérêt: " + compteEpargne.getTauxInteret()  // Show interest rate for CompteEpargne
                    };
                }

                // Add the row data to the table model if valid rowData is found
                if (rowData != null) {
                    tableModel.addRow(rowData);
                }
            }

            // Create the JTable with the table model
            JTable accountsTable = new JTable(tableModel);
            accountsTable.setFillsViewportHeight(true);

            // Create a scroll pane to make the table scrollable
            JScrollPane scrollPane = new JScrollPane(accountsTable);

            // Display the table in a dialog inside a scroll pane
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, scrollPane, "Affichage de tous les comptes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
