package interfaceg;

import service.*;
import javax.swing.*;
import java.awt.*;

public class UserLogin {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll(); // Clear previous content

        // Create a background label with image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\f.jpg"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout()); // Allows adding components on top

        // Main panel for the user login form
        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setOpaque(false); // Make the user panel transparent so the background is visible
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the user panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel cinLabel = new JLabel("CIN:");
        cinLabel.setFont(new Font("Verdana", Font.PLAIN, 20)); // Set the font size for CIN label
        cinLabel.setForeground(Color.WHITE); // Set the CIN label text color to white

        JTextField cinField = new JTextField(15);
        cinField.setFont(new Font("Verdana", Font.PLAIN, 18)); // Set the font size for the CIN field
        cinField.setForeground(Color.BLACK); // Set the text color inside the field (CIN input) to black
        cinField.setBackground(Color.WHITE); 
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Retour");
        JButton createAccountButton = new JButton("Create Account");

        // Button styles
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);

        
        // Layout - add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        userPanel.add(cinLabel, gbc);

        gbc.gridx = 1;
        userPanel.add(cinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        userPanel.add(loginButton, gbc);

        gbc.gridy = 2;
        userPanel.add(createAccountButton, gbc);

        gbc.gridy = 3;
        userPanel.add(backButton, gbc);

        // Button Actions
        loginButton.addActionListener(e -> {
            String cin = cinField.getText().trim(); // Trim any extra spaces

            // Validate CIN format
            if (!ValidationService.validateNumericField(cin, cin)) {
                return;
            }

            try {
                // Verify CIN for user login
                if (BankingAppContext.authservice.verifyUserCredentials(cin) != null) {
                    // Store the CIN in BankingAppContext for global access
                    BankingAppContext.cin = cin;
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    UserMenu.show(); // Navigate to user menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid CIN!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> MainMenu.show());

        createAccountButton.addActionListener(e -> AddNewAccount.show(BankingAppContext.mainFrame, BankingAppContext.clientservice));

        // Add the user panel to the background label
        backgroundLabel.add(userPanel);

        // Add the background label to the frame
        frame.add(backgroundLabel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
