package manage;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main_Project {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankAccount";
    private static final String USER = "root";
    private static final String PASSWORD = "Lisa2703";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Kết nối thành công tới cơ sở dữ liệu.");
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối tới cơ sở dữ liệu: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginApp frame = new LoginApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

