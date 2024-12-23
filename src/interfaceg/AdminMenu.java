package interfaceg;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdminMenu {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll(); // Clear previous content

        // Panel for Admin Menu with GridBagLayout
        JPanel adminMenuPanel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// Override the paintComponent method to add the background image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load the image from a file path (adjust the path as needed)
                    Image img = ImageIO.read(new File("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\car.jpg"));
                    // Draw the image as the background
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle image loading error
                }
            }
        };

        adminMenuPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for precise control over button sizes
        adminMenuPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80)); // Padding around panel

        // GridBag constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Space between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons fill horizontally

        // Components
        JLabel adminLabel = new JLabel("Admin Menu", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Verdana", Font.BOLD, 50)); // Larger font for the title
        adminLabel.setForeground(new Color(0, 102, 204));

        // Creating the buttons with updated English titles
        JButton clientButton = new JButton("Manage Clients");
        JButton accountButton = new JButton("Manage Accounts");
        JButton backButton = new JButton("Back");

        // Set uniform size for clientButton, accountButton, and backButton
        Dimension buttonSize = new Dimension(300, 60); // Set uniform width and height for all buttons
        clientButton.setPreferredSize(buttonSize);
        accountButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        // Styling the buttons
        clientButton.setFont(new Font("Arial", Font.PLAIN, 18));
        accountButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);

        // Layout constraints for the title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across 2 columns for the title
        adminMenuPanel.add(adminLabel, gbc);

        // Layout constraints for the buttons
        gbc.gridwidth = 1; // Reset to 1 column for buttons
        gbc.gridx = 1; // Shift buttons to the right by increasing gridx
        gbc.gridy = 1;
        adminMenuPanel.add(clientButton, gbc);

        gbc.gridy = 2;
        adminMenuPanel.add(accountButton, gbc);

        gbc.gridy = 3;
        adminMenuPanel.add(backButton, gbc);

        // Button Actions
        backButton.addActionListener(e -> MainMenu.show());
        clientButton.addActionListener(e -> AdminClientMenu.show());
        accountButton.addActionListener(e -> AdminCompteMenuForm.show());

        // Add panel to frame and refresh
        frame.add(adminMenuPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
