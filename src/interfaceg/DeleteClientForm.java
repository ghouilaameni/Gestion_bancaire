package interfaceg;

import service.BankService;
import service.ValidationService;
import javax.swing.*;
import java.awt.*;

public class DeleteClientForm {
    private JFrame parentFrame;
    private BankService bankService;

    public DeleteClientForm(JFrame parentFrame, BankService bankService) {
        this.parentFrame = parentFrame;
        this.bankService = bankService;
    }

    public static void show(JFrame parentFrame, BankService bankService) {
        DeleteClientForm form = new DeleteClientForm(parentFrame, bankService);
        form.display();
    }

    public void display() {
        // Hide the parent frame
        parentFrame.setVisible(false);

        // Create the Delete Client frame
        JFrame formFrame = new JFrame("Delete Client");
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setSize(500, 250); // Increased size
        formFrame.setLocationRelativeTo(parentFrame);  // Position relative to parent frame

        // Create the panel with a GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create input fields and labels
        JLabel cinLabel = new JLabel("CIN of client to delete:");
        JTextField cinField = new JTextField(20);

        // Layout the components
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cinLabel, gbc);

        gbc.gridx = 1;
        panel.add(cinField, gbc);

        // Create the buttons
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Return");

        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(0, 102, 204));
        deleteButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);

        // Add action listener for the Delete button
        deleteButton.addActionListener(e -> {
            String cin = cinField.getText().trim();

            // Validate the CIN field
            if (!ValidationService.isFieldFilled(cin) || !ValidationService.validateNumericField(cin,cin)) {
                return;  // If validation fails, return without proceeding
            }

            try {
                // Attempt to delete the client using the bank service
                bankService.supprimerClientEtComptes(cin);
                JOptionPane.showMessageDialog(formFrame, "Client deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the Cancel button
        cancelButton.addActionListener(e -> {
            formFrame.dispose();  // Close the DeleteClientForm window
            parentFrame.setVisible(true);  // Show the parent frame again
        });

        // Layout the buttons at the bottom of the form
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;  // Span across two columns
        panel.add(deleteButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Add the panel directly to the frame (no scroll bar needed)
        formFrame.add(panel);

        // Show the form
        formFrame.setVisible(true);
    }
}
