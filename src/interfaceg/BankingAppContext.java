package interfaceg;
import javax.swing.JFrame;
import service.*;

public class BankingAppContext {
    public static JFrame mainFrame;
    public static ClientService clientservice;
    public static CompteService compteservice;
    public static OperationService operationservice;
    public static AuthService authservice;
    public static BankService bankservice;
    public static String cin;

    static {
        clientservice = new ClientService();
        compteservice = new CompteService(clientservice);
        operationservice = new OperationService(compteservice);
        authservice = new AuthService(clientservice);
        bankservice=new BankService(clientservice,compteservice);
    }
}
