package interfaceg;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdminCompteMenuForm {

    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll(); // Clear previous content

        // Create main panel with custom background
        JPanel adminComptePanel = new JPanel(new GridBagLayout()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load the image from a file path (adjust the path as needed)
                    Image img = ImageIO.read(new File("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\az.jpg"));
                    // Draw the image as the background
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle image loading error
                }
            }
        };
        adminComptePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Header Label
        JLabel compteLabel = new JLabel("Gestion des Comptes", SwingConstants.CENTER);
        compteLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        compteLabel.setForeground(new Color(0, 102, 204));

        // Buttons
        JButton addAccountButton = createMenuButton("Ajouter un compte");
        JButton deleteAccountButton = createMenuButton("Supprimer un compte");
        JButton showAllAccountsButton = createMenuButton("Afficher tous les comptes");
        JButton backButton = createMenuButton("Retour");

        // Button Actions
        addAccountButton.addActionListener(e -> showAccountTypeOptions());
        deleteAccountButton.addActionListener(e -> DeleteAccountForm.show());
        showAllAccountsButton.addActionListener(e -> ShowAllAccountsForm.show());
        backButton.addActionListener(e -> AdminMenu.show());

        // Layout Components
        gbc.gridx = 0;
        gbc.gridy = 0;
        adminComptePanel.add(compteLabel, gbc);

        gbc.gridy++;
        adminComptePanel.add(addAccountButton, gbc);

        gbc.gridy++;
        adminComptePanel.add(deleteAccountButton, gbc);

        gbc.gridy++;
        adminComptePanel.add(showAllAccountsButton, gbc);

        gbc.gridy++;
        adminComptePanel.add(backButton, gbc);

        // Finalize UI update
        frame.add(adminComptePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Helper method for creating styled menu buttons
    private static JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(250, 40));
        button.setBackground(new Color(240, 240, 240)); // Light gray color for buttons
        button.setFocusPainted(false);
        return button;
    }

    // Show options to select account type
    private static void showAccountTypeOptions() {
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
