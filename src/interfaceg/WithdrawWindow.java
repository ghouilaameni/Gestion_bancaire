package interfaceg;

import javax.swing.*;
import java.awt.*;

public class WithdrawWindow {
    public static void show(JFrame parentFrame, String numeroCompte) {
        // Hide the parent frame
        parentFrame.setVisible(false);
        // Create a new frame for this window
        JFrame frame = new JFrame("Withdraw Funds");
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
        JLabel title = new JLabel("Withdraw Funds");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align left, can adjust as needed
        panel.add(title);

        // Space between title and input fields
        panel.add(Box.createVerticalStrut(15));

        // Input field for amount
        JLabel amountLabel = new JLabel("Enter amount to withdraw:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align left

        JTextField amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setMaximumSize(amountField.getPreferredSize());

        // Align input fields more to the right
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));  // Move it to the right
        amountPanel.add(amountLabel);
        amountPanel.add(amountField);

        panel.add(amountPanel);

        // Space between amount input and buttons
        panel.add(Box.createVerticalStrut(20));

        // Balance label
        JLabel balanceLabel = new JLabel("Your current balance: $5000.00"); // Example balance
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        balanceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align left
        panel.add(balanceLabel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Withdraw Button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawButton.setPreferredSize(new Dimension(120, 40));
        withdrawButton.setBackground(new Color(33, 150, 243));  // Blue color
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFocusPainted(false);
        withdrawButton.addActionListener(e -> handleWithdraw(frame, numeroCompte, amountField.getText()));

        // Return Button
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

        buttonPanel.add(withdrawButton);
        buttonPanel.add(returnButton);

        // Add components
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void handleWithdraw(JFrame frame, String numeroCompte, String montantInput) {
        try {
            // Parsing input to double
            double montant = Double.parseDouble(montantInput);
            if (montant <= 0) {
                throw new NumberFormatException("The amount must be positive.");
            }
            // Example: Use the actual balance before this step
            double balance = 5000.00;  // Assume the balance is $5000 for now
            if (montant > balance) {
                throw new Exception("Insufficient funds.");
            }

            // Simulate the withdrawal
            BankingAppContext.operationservice.retirer(numeroCompte, montant);
            JOptionPane.showMessageDialog(frame, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
