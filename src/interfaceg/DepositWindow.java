package interfaceg;

import javax.swing.*;
import java.awt.*;

public class DepositWindow {
    public static void show(JFrame parentFrame,String numeroCompte) {
        // Create a new frame for this window
    	parentFrame.setVisible(false);
        JFrame frame = new JFrame("Deposit Funds");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));  // Light background color
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title aligned slightly to the right
        JLabel title = new JLabel("Deposit Funds");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align left, can adjust as needed
        panel.add(title);

        // Space between title and input fields
        panel.add(Box.createVerticalStrut(15));

        // Input field for amount
        JLabel amountLabel = new JLabel("Enter amount to deposit:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setMaximumSize(amountField.getPreferredSize());

        // Align input fields more to the right
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        amountPanel.add(amountLabel);
        amountPanel.add(amountField);

        panel.add(amountPanel);

        // Space between amount input and buttons
        panel.add(Box.createVerticalStrut(20));

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Deposit Button
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));
        depositButton.setPreferredSize(new Dimension(120, 40));
        depositButton.setBackground(new Color(33, 150, 243));  // Blue color
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositButton.addActionListener(e -> handleDeposit(numeroCompte, amountField.getText()));
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnButton.setPreferredSize(new Dimension(120, 40));
        returnButton.setBackground(new Color(211, 47, 47));  // Red color
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(e -> {
            frame.dispose(); // Close this window
            parentFrame.setVisible(true); // Show the parent frame again
        });
        // Add buttons to the panel
        buttonPanel.add(depositButton);
        buttonPanel.add(returnButton);
        // Add components to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void handleDeposit(String numeroCompte, String montantInput) {
        try {
            double montant = Double.parseDouble(montantInput);
            if (montant <= 0) {
                throw new NumberFormatException("The amount must be positive.");
            }
            BankingAppContext.operationservice.verser(numeroCompte, montant);
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
