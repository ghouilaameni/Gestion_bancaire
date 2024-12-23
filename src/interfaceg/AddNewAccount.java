package interfaceg;

import gb.Client;
import service.ClientService;
import service.ValidationService;
import javax.swing.*;
import java.awt.*;

public class AddNewAccount {
    private JFrame parentFrame;
    private ClientService clientService;

    public AddNewAccount(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService) {
        AddNewAccount form = new AddNewAccount(parentFrame, clientService);
        form.display();
    }

    public void display() {
        // Clear previous content of the parent frame
        parentFrame.getContentPane().removeAll();
        parentFrame.setTitle("Add New Client");

        // Create a background label with an image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\medam\\eclipse-workspace\\gestion_bancaire\\src\\photos\\az.jpg"); // Path to your image file
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Create a panel with a GridBagLayout for precise positioning
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent to let the background image show
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Set up the layout constraints for the form elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create labels and text fields for the input
     // Labels with increased font size
        JLabel cinLabel = new JLabel("CIN:");
        cinLabel.setFont(new Font("Verdana", Font.PLAIN, 20)); // Slightly larger font size
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel lastNameLabel = new JLabel("Surname:");
        lastNameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Verdana", Font.PLAIN, 20));

        // Text fields remain the same
        JTextField cinField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);


        // Layout the labels and fields in the panel
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

        // Customize button appearance with softer blue color
        Color blueGray = new Color(100, 150, 200); // Softer blue (adjust as needed)
        okButton.setBackground(blueGray);
        okButton.setForeground(Color.WHITE);

        cancelButton.setBackground(blueGray);
        cancelButton.setForeground(Color.WHITE);

        // Add action listener for the OK button
        okButton.addActionListener(e -> {
            String cin = cinField.getText().trim();
            String name = nameField.getText().trim();
            String surname = lastNameField.getText().trim();
            String phone = phoneField.getText().trim();

            // Validate inputs using ValidationService
            if (!ValidationService.validateNumericField(cin, cin) ||
                !ValidationService.validateName(name) ||
                !ValidationService.validateSurname(surname) ||
                !ValidationService.validatePhoneNumber(phone)) {
                return; // ValidationService handles error messages
            }

            try {
                // Add the client
                Client client = new Client(cin, name, surname, phone);
                clientService.ajouterClient(client);
                BankingAppContext.cin = cin;
                JOptionPane.showMessageDialog(parentFrame, "Client added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                UserMenu.show(); // Return to the main menu after success
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parentFrame, "Error adding client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the Cancel button
        cancelButton.addActionListener(e -> UserLogin.show());

        // Layout the buttons at the bottom of the form
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(okButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Add the panel to the background label
        backgroundLabel.add(panel);

        // Add the background label to the parent frame
        parentFrame.add(backgroundLabel, BorderLayout.CENTER);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
