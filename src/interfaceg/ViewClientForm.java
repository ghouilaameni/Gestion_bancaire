package interfaceg;

import gb.Client;
import service.ClientService;
import service.ValidationService;

import javax.swing.*;
import java.awt.*;

public class ViewClientForm {
    private JFrame parentFrame;
    private ClientService clientService;

    public ViewClientForm(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService) {
        ViewClientForm form = new ViewClientForm(parentFrame, clientService);
        form.display();
    }

    public void display() {
        // Hide the parent frame
        parentFrame.setVisible(false);

        // Create a new frame for the view client form
        JFrame formFrame = new JFrame("ViewClient");
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setSize(500, 300); // Set a fixed size for the form
        formFrame.setLocationRelativeTo(parentFrame); // Center the form on the parent window

        // Create a panel for the form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245)); // Light gray background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Set up the layout constraints for form elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Create label and text field for CIN input
        JLabel cinLabel = new JLabel("CIN du client:");
        JTextField cinField = new JTextField(20);
        cinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cinField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Layout the CIN label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cinLabel, gbc);

        gbc.gridx = 1;
        panel.add(cinField, gbc);

        // Create OK and Cancel buttons
        JButton okButton = new JButton("Consulter");
        JButton cancelButton = new JButton("Return");

        // Customize button appearance
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(new Color(0, 102, 204)); // Blue button
        okButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(204, 0, 0)); // Red button
        cancelButton.setForeground(Color.WHITE);

        // Add action listener for the OK button
        okButton.addActionListener(e -> {
            String cin = cinField.getText().trim(); // Get CIN from the field

            // Validate the CIN using ValidationService
            if (!ValidationService.validateNumericField(cin,cin)) {
                return; // ValidationService handles error messages
            }

            try {
                // Find client by CIN using client service
                Client client = clientService.trouverClient(cin);

                if (client != null) {
                    // Prepare client details to be displayed
                    String clientDetails = "CIN: " + client.getCin() + "\n" +
                                           "Nom: " + client.getNom() + "\n" +
                                           "Prénom: " + client.getPrenom() + "\n" +
                                           "Téléphone: " + client.getTelephone();
                    // Display client details in a message dialog
                    JOptionPane.showMessageDialog(formFrame, clientDetails, "Détails du Client", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // If client not found, show an error message
                    JOptionPane.showMessageDialog(formFrame, "Client non trouvé avec le CIN: " + cin, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formFrame, "Erreur lors de la recherche du client: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the Cancel button
        cancelButton.addActionListener(e -> {
            formFrame.dispose(); // Close the current form
            parentFrame.setVisible(true); // Show the parent frame again
        });

        // Layout the buttons at the bottom of the form
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(okButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Add the panel to a scroll pane (optional)
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(450, 250)); // Set preferred size for scrolling

        // Add the scroll pane to the frame
        formFrame.add(scrollPane);

        // Show the form
        formFrame.setVisible(true);
    }
}
