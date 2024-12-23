package interfaceg;

import gb.*;
import javax.swing.*;
import java.awt.*;

public class AccountOperationsWindow {

    public static void show(JFrame parentFrame) {
        parentFrame.setVisible(false); // Hide parent frame

        // Create a new frame for account operations with a larger size
        JFrame frame = new JFrame("Account Operations");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500); // Increased window size
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Main panel with padding and a soft light gray background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245)); // Light gray background

        // Title with modern font
        JLabel titleLabel = new JLabel("Account Operations");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Spacer
        mainPanel.add(Box.createVerticalStrut(30));

        // Input for account number with label, aligned to the right
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.X_AXIS));
        accountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel accountLabel = new JLabel("Enter your account number:");
        accountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountLabel.setForeground(new Color(51, 51, 51)); // Dark gray

        JTextField accountField = new JTextField(10); // Smaller field for better appearance
        accountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountField.setMaximumSize(new Dimension(200, 30));
        accountField.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1, true));

        accountPanel.add(accountLabel);
        accountPanel.add(Box.createHorizontalStrut(20));
        accountPanel.add(accountField);

        mainPanel.add(accountPanel);

        // Spacer
        mainPanel.add(Box.createVerticalStrut(30));

        // Buttons with unified color and styling
        JButton proceedButton = new JButton("Proceed");
        JButton backButton = new JButton("Back");

        proceedButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        proceedButton.setBackground(new Color(52, 152, 219)); // Soft blue button
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false);
        proceedButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        backButton.setBackground(new Color(41, 128, 185)); // Slightly darker blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        proceedButton.addActionListener(e -> handleAccountOperations(accountField.getText(), frame, parentFrame));
        backButton.addActionListener(e -> {
            frame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(mainPanel.getBackground()); // Match parent panel background
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(proceedButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel);

        // Add main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Center and display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void handleAccountOperations(String accountNumber, JFrame frame, JFrame parentFrame) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Invalid account number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve the account using the account number
        Compte account = BankingAppContext.compteservice.trouverCompte(accountNumber);
        if (account == null) {
            JOptionPane.showMessageDialog(frame, "The account number does not exist. Please check and try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verify the CIN of the account matches the client's CIN
        if (!account.getCinClient().equals(BankingAppContext.cin)) {
            JOptionPane.showMessageDialog(frame, "The account number does not match your CIN. Operation not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show operation options
        showOperationOptions(frame, parentFrame, accountNumber);
    }

    private static void showOperationOptions(JFrame frame, JFrame parentFrame, String accountNumber) {
        frame.setVisible(false); // Hide current frame

        // Create a new frame for operation selection with larger size
        JFrame operationFrame = new JFrame("Select Operation");
        operationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        operationFrame.setSize(600, 400); // Increased size
        operationFrame.setLayout(new BorderLayout());

        // Main panel
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));
        operationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        operationPanel.setBackground(new Color(245, 245, 245)); // Light gray background

        // Title
        JLabel operationLabel = new JLabel("Choose an operation:");
        operationLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        operationLabel.setForeground(new Color(0, 51, 102)); // Dark blue
        operationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        operationPanel.add(operationLabel);

        // Spacer
        operationPanel.add(Box.createVerticalStrut(20));

        // Buttons for operations with refined colors
        JButton withdrawButton = createOperationButton("Withdraw");
        JButton depositButton = createOperationButton("Deposit");
        JButton transferButton = createOperationButton("Transfer");
        JButton backButton = createOperationButton("Back");

        withdrawButton.addActionListener(e -> handleWithdraw(accountNumber, operationFrame));
        depositButton.addActionListener(e -> handleDeposit(accountNumber, operationFrame));
        transferButton.addActionListener(e -> handleTransfer(accountNumber, operationFrame));
        backButton.addActionListener(e -> {
            operationFrame.dispose();
            frame.setVisible(true);
        });

        // Add the buttons to the panel with extra space to align them centrally
        operationPanel.add(Box.createVerticalStrut(10));
        operationPanel.add(withdrawButton);
        operationPanel.add(Box.createVerticalStrut(10));
        operationPanel.add(depositButton);
        operationPanel.add(Box.createVerticalStrut(10));
        operationPanel.add(transferButton);
        operationPanel.add(Box.createVerticalStrut(10));
        operationPanel.add(backButton);

        // Center the buttons to the left slightly by adjusting their alignment
        operationPanel.add(Box.createVerticalStrut(30));

        // Add panel to frame
        operationFrame.add(operationPanel, BorderLayout.CENTER);

        // Center and display the frame
        operationFrame.setLocationRelativeTo(null);
        operationFrame.setVisible(true);
    }

    private static JButton createOperationButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(52, 152, 219)); // Soft blue button (same for all)
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40)); // Larger buttons
        button.setPreferredSize(new Dimension(300, 50)); // Same size for all buttons
        return button;
    }

    private static void handleWithdraw(String accountNumber, JFrame operationFrame) {
        WithdrawWindow.show(operationFrame, accountNumber);
    }

    private static void handleDeposit(String accountNumber, JFrame operationFrame) {
        DepositWindow.show(operationFrame, accountNumber);
    }

    private static void handleTransfer(String accountNumber, JFrame operationFrame) {
        TransferWindow.show(operationFrame, accountNumber);
    }
}