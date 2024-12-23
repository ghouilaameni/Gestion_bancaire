package interfaceg;

import javax.swing.*;
import java.awt.*;
import service.ClientService;
import service.ValidationService;

public class UpdateClientForm {

    private JFrame parentFrame;  // The AdminClientMenu frame
    private ClientService clientService;

    public UpdateClientForm(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService) {
        UpdateClientForm form = new UpdateClientForm(parentFrame, clientService);
        form.display();
    }

    public void display() {
        // Hide the parent frame
        parentFrame.setVisible(false);

        // Create the Modify Client frame
        JFrame formFrame = new JFrame("Modify Client");
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setSize(500, 300);
        formFrame.setLocationRelativeTo(parentFrame);  // Position relative to parent frame

        // Create the panel with a GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create input fields and labels
        JLabel cinLabel = new JLabel("Client CIN to Modify:");
        JTextField cinField = new JTextField(20);
        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("New Last Name:");
        JTextField lastNameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("New Phone:");
        JTextField phoneField = new JTextField(20);

        // Layout the components
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cinLabel, gbc);

        gbc.gridx = 1;
        panel.add(cinField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Create the buttons
        JButton okButton = new JButton("Modify");
        JButton cancelButton = new JButton("Return");

        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(new Color(0, 102, 204));
        okButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);

        // Add action listener for the OK button
        okButton.addActionListener(e -> {
            String cin = cinField.getText().trim();
            String name = nameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phone = phoneField.getText().trim();

            // Validate fields using ValidationService
            if (!ValidationService.isFieldFilled(cin) || !ValidationService.isFieldFilled(name) ||
                !ValidationService.isFieldFilled(lastName) || !ValidationService.isFieldFilled(phone) ||
                !ValidationService.validateNumericField(cin,cin) ||
                !ValidationService.validateName(name) ||
                !ValidationService.validateSurname(lastName) ||
                !ValidationService.validatePhoneNumber(phone)) {
                return;  // If any validation fails, return without proceeding
            }

            try {
                // Update client using client service
                clientService.mettreAJourClient(cin, name, lastName, phone);
                JOptionPane.showMessageDialog(formFrame, "Client updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the Cancel button
        cancelButton.addActionListener(e -> {
            formFrame.dispose();  // Close the ModifyClientForm window
            parentFrame.setVisible(true);  // Show the AdminClientMenu again
        });

        // Layout the buttons at the bottom of the form
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;  // Span across two columns
        panel.add(okButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Add the panel to a scroll pane (optional)
        JScrollPane scrollPane = new JScrollPane(panel);
        formFrame.add(scrollPane);

        // Show the form
        formFrame.setVisible(true);
    }
}
