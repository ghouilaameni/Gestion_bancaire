package interfaceg;

import gb.Client;
import service.ClientService;
import service.ValidationService;

import javax.swing.*;
import java.awt.*;

public class AddClientForm {
    private JFrame parentFrame;
    private ClientService clientService;

    public AddClientForm(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService) {
        AddClientForm form = new AddClientForm(parentFrame, clientService);
        form.display();
    }

    public void display() {
        // Clear previous content of the parent frame
        parentFrame.getContentPane().removeAll();
        parentFrame.setTitle("Add New Client");

        // Set the layout to BorderLayout for the frame
        parentFrame.setLayout(new BorderLayout());

        // Add background image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\az.jpg"); // Replace with the path to your image
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout()); // Set layout for precise positioning
        parentFrame.add(backgroundLabel, BorderLayout.CENTER);

        // Create a transparent panel for the form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make panel transparent to show the background image
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Set up the layout constraints for the form elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create labels and text fields for input
        JLabel cinLabel = new JLabel("CIN:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel lastNameLabel = new JLabel("Surname:");
        JLabel phoneLabel = new JLabel("Phone:");

        JTextField cinField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);

        // Add labels and fields to the panel
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

        // Create OK and Cancel buttons
        JButton okButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        // Customize button appearance
        Color blueGray = new Color(100, 150, 200); // Softer blue-gray color
        okButton.setBackground(blueGray);
        okButton.setForeground(Color.WHITE);
        cancelButton.setBackground(blueGray);
        cancelButton.setForeground(Color.WHITE);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String cin = cinField.getText().trim();
            String name = nameField.getText().trim();
            String surname = lastNameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (!ValidationService.validateNumericField(cin, cin) ||
                !ValidationService.validateName(name) ||
                !ValidationService.validateSurname(surname) ||
                !ValidationService.validatePhoneNumber(phone)) {
                return; // ValidationService handles error messages
            }

            try {
                Client client = new Client(cin, name, surname, phone);
                clientService.ajouterClient(client);
                JOptionPane.showMessageDialog(parentFrame, "Client added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                AdminClientMenu.show(); // Return to the main menu
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parentFrame, "Error adding client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> AdminClientMenu.show());

        // Add buttons to the panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(okButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Add the form panel to the background label
        backgroundLabel.add(panel);

        // Refresh the parent frame
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
