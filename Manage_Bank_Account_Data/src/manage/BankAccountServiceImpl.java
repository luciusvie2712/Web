package manage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankAccountServiceImpl extends UnicastRemoteObject implements BankAccountService {
	private static final long serialVersionUID = 1L;
	protected BankAccountServiceImpl() throws RemoteException {
        super();
    }
    public int getAccountBalance(String sdt, String matKhau) throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
            String query = "SELECT So_du FROM bank_accounts WHERE phone_number = ? AND pass = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, sdt);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("So_du");
            } else {
                // Đăng nhập thất bại, trả về giá trị âm
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static void main(String[] args) {
        try {
            // Tạo và đăng ký dịch vụ RMI
            BankAccountService bankService = new BankAccountServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankAccountService", bankService);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
