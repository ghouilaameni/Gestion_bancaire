package interfaceg;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public static void show() {
        JFrame frame = BankingAppContext.mainFrame;
        frame.getContentPane().removeAll();

        // Create a background label
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\abstract-bluish-paint-background-wallpaper1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20)); // Adjusted top padding for the title

        // Title label for PayZen
        JLabel welcomeLabel = new JLabel("Welcome to PayZen", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 47));
        welcomeLabel.setForeground(new Color(255, 255, 255));

        // Panel for buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 50); // Horizontal spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create buttons
        JButton adminButton = new JButton();
        JButton userButton = new JButton();

        // Set button sizes
        adminButton.setPreferredSize(new Dimension(150, 150));
        userButton.setPreferredSize(new Dimension(150, 150));

        // Add text labels above the buttons
        JLabel adminLabel = new JLabel("Admin", SwingConstants.CENTER);
        JLabel userLabel = new JLabel("User", SwingConstants.CENTER);

        // Set font size for labels
        adminLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // Label font size
        userLabel.setFont(new Font("Verdana", Font.BOLD, 24));

        // Set button images using ImageIcon
        adminButton.setIcon(new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\admin1.jpg"));
        userButton.setIcon(new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\user1.jpg"));

        // Position Admin Label and Button (slightly higher)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 50, 10, 50); // Reduced top padding more
        buttonPanel.add(adminLabel, gbc);

        gbc.gridy = 1;
        buttonPanel.add(adminButton, gbc);

        // Position User Label and Button (slightly higher)
        gbc.gridx = 2; // Move user components farther to the right
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 50, 10, 50); // Reduced top padding more
        buttonPanel.add(userLabel, gbc);

        gbc.gridy = 1;
        buttonPanel.add(userButton, gbc);

        // Add action listeners for buttons
        adminButton.addActionListener(e -> AdminLogin.show());
        userButton.addActionListener(e -> UserLogin.show());

        // Add components to the main panel
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the background label
        backgroundLabel.add(mainPanel);

        // Add the background label to the frame
        frame.add(backgroundLabel);
        frame.revalidate();
        frame.repaint();
    }
}
