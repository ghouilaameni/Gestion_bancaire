package interfaceg;

import service.*;
import javax.swing.*;
import java.awt.*;

public class AdminLogin {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll();

        // Create a background label with the image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\f.jpg"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout()); // Allows adding components on top of the background

        // Admin panel with GridBagLayout
        JPanel adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setOpaque(false); // Make the panel transparent to show the background
        adminPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the components

        // GridBagConstraints for positioning components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size
        usernameLabel.setForeground(Color.WHITE); // Set text color to white

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        usernameField.setForeground(Color.BLACK); // Set text color to black
        usernameField.setBackground(new Color(255, 255, 255)); // Set background to white

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size
        passwordLabel.setForeground(Color.WHITE); // Set text color to white

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        passwordField.setForeground(Color.BLACK); // Set text color to black
        passwordField.setBackground(new Color(255, 255, 255)); // Set background to white


        // Buttons
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Retour");
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);

        // Add components to admin panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        adminPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        adminPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        adminPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        adminPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across both columns
        adminPanel.add(loginButton, gbc);

        gbc.gridy = 3;
        adminPanel.add(backButton, gbc);

        // Add action listeners for buttons
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Use ValidationService to validate inputs
            if (!ValidationService.isFieldFilled(username) || !ValidationService.isFieldFilled(password)) {
                return; // If validation fails, stop the login process
            }

            try {
                // Adjust to use username instead of CIN
                if (BankingAppContext.authservice.verifyAdminCredentials(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    AdminMenu.show();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> MainMenu.show());

        // Add admin panel to the background label
        backgroundLabel.add(adminPanel, BorderLayout.CENTER);

        // Add the background label to the frame
        frame.add(backgroundLabel);
        frame.revalidate();
        frame.repaint();
    }
}
