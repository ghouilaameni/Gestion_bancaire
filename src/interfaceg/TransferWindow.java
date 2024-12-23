package interfaceg;

import service.ValidationService;
import javax.swing.*;
import java.awt.*;

public class TransferWindow {
    public static void show(JFrame parentFrame,String numeroCompteSource) {
        // Create a new frame for this window
    	parentFrame.setVisible(false);
        JFrame frame = new JFrame("Transfer Funds");
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
        JLabel title = new JLabel("Transfer Funds");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align left, can adjust as needed
        panel.add(title);

        // Space between title and input fields
        panel.add(Box.createVerticalStrut(15));

        // Input field for destination account
        JLabel destAccountLabel = new JLabel("Enter destination account number:");
        destAccountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        destAccountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField destAccountField = new JTextField(15);
        destAccountField.setFont(new Font("Arial", Font.PLAIN, 16));
        destAccountField.setMaximumSize(destAccountField.getPreferredSize());

        // Input field for amount
        JLabel amountLabel = new JLabel("Enter amount to transfer:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setMaximumSize(amountField.getPreferredSize());

        // Align input fields more to the right
        JPanel destAccountPanel = new JPanel();
        destAccountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        destAccountPanel.add(destAccountLabel);
        destAccountPanel.add(destAccountField);

        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        amountPanel.add(amountLabel);
        amountPanel.add(amountField);

        panel.add(destAccountPanel);
        panel.add(amountPanel);

        // Space between amount input and buttons
        panel.add(Box.createVerticalStrut(20));

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Transfer Button
        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.BOLD, 14));
        transferButton.setPreferredSize(new Dimension(120, 40));
        transferButton.setBackground(new Color(33, 150, 243));  // Blue color
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        transferButton.addActionListener(e -> handleTransfer(numeroCompteSource, destAccountField.getText(), amountField.getText()));

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

        buttonPanel.add(transferButton);
        buttonPanel.add(returnButton);
        // Add components to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void handleTransfer(String numeroCompteSource, String numeroCompteDest, String montantInput) {
        try {
            if (!ValidationService.validateNumericField(numeroCompteDest, "Account number")) {
                return;
            }

            double montant = Double.parseDouble(montantInput);
            if (montant <= 0) {
                throw new NumberFormatException("The amount must be positive.");
            }

            BankingAppContext.operationservice.virer(numeroCompteSource, numeroCompteDest, montant);
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(BankingAppContext.mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
