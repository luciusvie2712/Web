package manage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối đến máy chủ RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Lấy dịch vụ từ xa từ máy chủ
            BankAccountService bankService = (BankAccountService) registry.lookup("BankAccountService");
            
         // Nhập số điện thoại và mật khẩu từ người dùng
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập số điện thoại:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Nhập mật khẩu:");
            String password = scanner.nextLine();

            // Gọi phương thức từ xa để kiểm tra thông tin đăng nhập và lấy số dư
            int balance = bankService.getAccountBalance(phoneNumber, password);
            if (balance != -1) {
                System.out.println("Đăng nhập thành công! Số dư trong tài khoản của bạn là: " + balance);
            } else {
                System.out.println("Số điện thoại hoặc mật khẩu không đúng!");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
