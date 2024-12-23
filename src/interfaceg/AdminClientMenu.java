package interfaceg;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdminClientMenu {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll(); // Clear previous content

        // Create main panel with custom background
        JPanel adminClientPanel = new JPanel(new GridBagLayout()) {
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
        adminClientPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Header Label
        JLabel clientLabel = new JLabel("Gestion des Clients", SwingConstants.CENTER);
        clientLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        clientLabel.setForeground(new Color(0, 102, 204));

        // Buttons
        JButton addClientButton = createMenuButton("Ajouter un client");
        JButton viewClientButton = createMenuButton("Consulter un client");
        JButton updateClientButton = createMenuButton("Modifier un client");
        JButton deleteClientButton = createMenuButton("Supprimer un client");
        JButton viewAllButton = createMenuButton("Afficher tous les clients");
        JButton backButton = createMenuButton("Retour");

        // Button Actions
        addClientButton.addActionListener(e -> AddClientForm.show(BankingAppContext.mainFrame, BankingAppContext.clientservice));
        viewClientButton.addActionListener(e -> ViewClientForm.show(BankingAppContext.mainFrame, BankingAppContext.clientservice));
        updateClientButton.addActionListener(e -> UpdateClientForm.show(BankingAppContext.mainFrame, BankingAppContext.clientservice));
        deleteClientButton.addActionListener(e -> DeleteClientForm.show(BankingAppContext.mainFrame, BankingAppContext.bankservice));
        viewAllButton.addActionListener(e -> ViewAllClientsForm.show(BankingAppContext.mainFrame, BankingAppContext.clientservice));
        backButton.addActionListener(e -> AdminMenu.show());

        // Layout Components
        gbc.gridx = 0;
        gbc.gridy = 0;
        adminClientPanel.add(clientLabel, gbc);

        gbc.gridy++;
        adminClientPanel.add(addClientButton, gbc);

        gbc.gridy++;
        adminClientPanel.add(viewClientButton, gbc);

        gbc.gridy++;
        adminClientPanel.add(updateClientButton, gbc);

        gbc.gridy++;
        adminClientPanel.add(deleteClientButton, gbc);

        gbc.gridy++;
        adminClientPanel.add(viewAllButton, gbc);

        gbc.gridy++;
        adminClientPanel.add(backButton, gbc);

        // Finalize UI update
        frame.add(adminClientPanel, BorderLayout.CENTER);
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
}
