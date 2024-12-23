package interfaceg;
import javax.swing.*;
import service.ValidationService;
public class DeleteAccountForm {

    public static void show() {
        // Create a text field for the account number input
        JTextField accountNumberField = new JTextField();

        // Prepare the dialog message with input fields
        Object[] message = {
            "Numéro du compte à supprimer:", accountNumberField
        };

        // Show the dialog box for account deletion
        int option = JOptionPane.showConfirmDialog(BankingAppContext.mainFrame, message, "Supprimer un Compte", JOptionPane.OK_CANCEL_OPTION);
        // If the user clicks OK
        if (option == JOptionPane.OK_OPTION) {
            String numero = accountNumberField.getText();
            if(ValidationService.validateNumericField(numero, "Account number " ));
            try {
                // Call the service to delete the account with the given account number
                BankingAppContext.compteservice.supprimerCompte(numero);

                // Show success message
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Compte supprimé avec succès.");
            } catch (Exception ex) {
                // Handle any errors (e.g., account not found, invalid account number)
                JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Erreur: Impossible de supprimer le compte. Veuillez vérifier le numéro du compte.");
            }
        }
    }
}
