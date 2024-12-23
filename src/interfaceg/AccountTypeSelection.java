package interfaceg;

import javax.swing.*;

public class AccountTypeSelection {

    public static void show() {
        Object[] options = {"Compte Courant", "Compte Épargne"};
        int selection = JOptionPane.showOptionDialog(BankingAppContext.mainFrame,
                "Choisissez le type de compte à ajouter :",
                "Ajouter un compte",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (selection == 0) {
            // User chose "Compte Courant"
            CompteCourantForm.show();
        } else if (selection == 1) {
            // User chose "Compte Épargne"
            CompteEpargneForm.show();
        }
    }
}
