package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginApp extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsername;
    private JPasswordField passwordFieldLogin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public LoginApp() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogoLogin = new JLabel("ĐĂNG NHẬP ADMIN");
		lblLogoLogin.setForeground(new Color(255, 250, 250));
		lblLogoLogin.setBackground(new Color(240, 255, 255));
		lblLogoLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoLogin.setBounds(10, 19, 416, 61);
		contentPane.add(lblLogoLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(50, 90, 335, 136);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 90, 29);
		panel.add(lblNewLabel);
		
		textUsername = new JTextField();
		textUsername.setBounds(110, 10, 215, 29);
		panel.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(10, 49, 90, 29);
		panel.add(lblPassword);
		
		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBounds(110, 49, 215, 29);
		panel.add(passwordFieldLogin);
		
		JButton btnLoginButton = new JButton("LOGIN");
		btnLoginButton.setForeground(new Color(255, 250, 250));
		btnLoginButton.setBackground(new Color(255, 0, 255));
		btnLoginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoginButton.setBounds(110, 88, 98, 38);
		btnLoginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = textUsername.getText().trim();
		        String password = new String(passwordFieldLogin.getPassword()).trim();
		        
		        // Kiểm tra dữ liệu đăng nhập
		        if (validateLogin(username, password)) {
		            // Đăng nhập thành công
		            openBankAccountData();
		            dispose(); // Đóng cửa sổ của LoginApp
		        } else {
		            JOptionPane.showMessageDialog(LoginApp.this, "Tên người dùng hoặc mật khẩu không đúng.");
		        }
		    }
		});
        // Kết nối tới cơ sở dữ liệu
		panel.add(btnLoginButton);
		
	}
	private boolean validateLogin(String username, String password) {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM account_admin WHERE username = ? AND pass = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, username);
	        statement.setString(2, password);
	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        // Nếu có bất kỳ bản ghi nào khớp với tên người dùng và mật khẩu được nhập
	        if (resultSet.next()) {
	            return true; // Đăng nhập thành công
	        }
	        
	        // Nếu không có bản ghi nào khớp
	        return false;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}

	private void openBankAccountData() {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                BankAccountData frame = new BankAccountData();
	                frame.setVisible(true); // Mở giao diện BankAccountData
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}
