package interfaceg;
import javax.swing.*;


public class BankingAppGUI {
    public static void main(String[] args) {
        // Create the main frame
        BankingAppContext.mainFrame = new JFrame("Banking Application");
        BankingAppContext.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a larger window size
        BankingAppContext.mainFrame.setSize(800, 600);

        // Center the window on the screen
        BankingAppContext.mainFrame.setLocationRelativeTo(null);

        // Initialize the main menu
        MainMenu.show();

        // Make the window visible
        BankingAppContext.mainFrame.setVisible(true);
    }
}
