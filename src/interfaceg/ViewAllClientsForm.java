package interfaceg;

import gb.Client;
import service.ClientService;
import javax.swing.*;
import java.util.Map;

public class ViewAllClientsForm {
    private JFrame parentFrame;
    private ClientService clientService;

    public ViewAllClientsForm(JFrame parentFrame, ClientService clientService) {
        this.parentFrame = parentFrame;
        this.clientService = clientService;
    }

    public static void show(JFrame parentFrame, ClientService clientService) {
        ViewAllClientsForm form = new ViewAllClientsForm(parentFrame, clientService);
        form.display();
    }

    public void display() {
        JFrame formFrame = new JFrame("Liste des Clients");
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setSize(600, 400);
        formFrame.setLocationRelativeTo(parentFrame);

        Map<String, Client> clients = clientService.getAllClients();

        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(formFrame, "Aucun client trouvé.", "Aucune donnée", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"CIN", "Nom", "Prénom", "Téléphone"};
        Object[][] data = new Object[clients.size()][4];
        int i = 0;
        for (Client client : clients.values()) {
            data[i][0] = client.getCin();
            data[i][1] = client.getNom();
            data[i][2] = client.getPrenom();
            data[i][3] = client.getTelephone();
            i++;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        formFrame.add(scrollPane);
        formFrame.setVisible(true);
    }
}
