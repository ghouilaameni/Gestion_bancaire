package interfaceg;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UserMenu {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll(); // Clear previous content

        // Create a new panel with a GridBagLayout for centered design
        JPanel userMenuPanel = new JPanel() {
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

        userMenuPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for precise control over button sizes
        userMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around panel

        // Title Label
        JLabel userLabel = new JLabel("User Menu", SwingConstants.CENTER);  
        userLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // Larger font for title
        userLabel.setForeground(new Color(0, 102, 204)); // Blue text

        // Buttons
        JButton viewAccountButton = new JButton("View your information");
        JButton viewAccountsButton = new JButton("View your accounts"); 
        JButton accountOperationsButton = new JButton("Account operations"); 
        JButton addAccountButton = new JButton("Add Bank Account"); 
        JButton backButton = new JButton("Back"); 

        // Button Styling
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(250, 40);
        JButton[] buttons = {viewAccountButton, viewAccountsButton, accountOperationsButton, addAccountButton, backButton};
        
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setPreferredSize(buttonSize);
            button.setFocusPainted(false);
            // No background color set; using default button styling
        }

        backButton.setBackground(new Color(0, 102, 204)); // Blue for 'Back'
        backButton.setForeground(Color.WHITE); // White text for 'Back'

        // Button Actions
        // Uncomment and set appropriate action listeners for each button
        viewAccountButton.addActionListener(e -> AccountInfo.show(frame, BankingAppContext.clientservice,BankingAppContext.cin));
        viewAccountsButton.addActionListener(e -> UserAccountsWindow.show(frame));
        accountOperationsButton.addActionListener(e -> AccountOperationsWindow.show(frame));
        addAccountButton.addActionListener(e -> showAccountTypeOptions()); // Action for adding another account
        backButton.addActionListener(e -> UserLogin.show());

        // Layout with GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between elements

        gbc.gridy = 0;
        userMenuPanel.add(userLabel, gbc);

        gbc.gridy++;
        userMenuPanel.add(viewAccountButton, gbc);

        gbc.gridy++;
        userMenuPanel.add(viewAccountsButton, gbc);

        gbc.gridy++;
        userMenuPanel.add(accountOperationsButton, gbc);

        gbc.gridy++;
        userMenuPanel.add(addAccountButton, gbc); // Added the button to add another account

        gbc.gridy++;
        userMenuPanel.add(backButton, gbc);

        // Add panel to frame and update
        frame.add(userMenuPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showAccountTypeOptions() {
        Object[] options = {"Checking Account", "Savings Account"};
        int selection = JOptionPane.showOptionDialog(BankingAppContext.mainFrame,
                "Choose the type of account to add:",
                "Add Account",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (selection == 0) {
            // User chose "Checking Account"
        	CheckingAccountForm.show();
        } else if (selection == 1) {
            // User chose "Savings Account"
        	SavingsAccountForm.show();
        }
    }
}
