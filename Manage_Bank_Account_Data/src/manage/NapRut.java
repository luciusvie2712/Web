package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class NapRut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSDT;
	private JTextField textPass;
	private JTextField textNap;
	private JTextField textSDTRut;
	private JTextField textPassRut;
	private JTextField textRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NapRut frame = new NapRut();
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
	public NapRut() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNap = new JPanel();
		panelNap.setBackground(new Color(255, 239, 213));
		panelNap.setBounds(0, 0, 389, 463);
		contentPane.add(panelNap);
		panelNap.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nạp Tiền Tài Khoản");
		lblNewLabel.setBackground(new Color(255, 240, 245));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 41, 369, 76);
		panelNap.add(lblNewLabel);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSDT.setBounds(10, 127, 103, 34);
		panelNap.add(lblSDT);
		
		JLabel lblPass = new JLabel("Mật khẩu:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setBounds(10, 171, 103, 34);
		panelNap.add(lblPass);
		
		textSDT = new JTextField();
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSDT.setBounds(123, 127, 239, 34);
		panelNap.add(textSDT);
		textSDT.setColumns(10);
		
		textPass = new JTextField();
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPass.setColumns(10);
		textPass.setBounds(123, 171, 239, 34);
		panelNap.add(textPass);
		
		JLabel lblNap = new JLabel("Số Tiền Nạp:");
		lblNap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNap.setBounds(10, 215, 103, 34);
		panelNap.add(lblNap);
		
		textNap = new JTextField();
		textNap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNap.setColumns(10);
		textNap.setBounds(123, 215, 239, 34);
		panelNap.add(textNap);
		
		JLabel lblNewLabel_1 = new JLabel("(*) Số tiền nạp phải từ 50.000 trở lên");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(123, 259, 239, 25);
		panelNap.add(lblNewLabel_1);
		
		JButton btnNap = new JButton("NẠP");
		btnNap.setForeground(new Color(255, 250, 250));
		btnNap.setBackground(new Color(0, 0, 205));
		btnNap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNap.setBounds(111, 302, 167, 58);
		btnNap.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String sdt = textSDT.getText();
		        String pass = textPass.getText();
		        int amount = Integer.parseInt(textNap.getText());

		        // Kiểm tra thông tin SDT và mật khẩu
		        boolean validInfo = checkValidInfo(sdt, pass);
		        
		        // Kiểm tra số tiền nạp
		        boolean validAmount = checkValidAmount(amount);

		        if (validInfo && validAmount) {
		            // Cập nhật số dư trong cơ sở dữ liệu
		            updateBalance(sdt, amount);
		            // Thông báo thành công
		            JOptionPane.showMessageDialog(null, "Nạp tiền thành công!");
		        } else {
		            // Thông báo lỗi
		            JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ!");
		        }
		    }
		});
		panelNap.add(btnNap);
		
		JPanel panelRut = new JPanel();
		panelRut.setBackground(new Color(255, 250, 205));
		panelRut.setBounds(397, 0, 389, 463);
		contentPane.add(panelRut);
		panelRut.setLayout(null);
		
		JLabel lblLogoRut = new JLabel("Rút  Tiền Tài Khoản\r\n");
		lblLogoRut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoRut.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogoRut.setBackground(new Color(255, 240, 245));
		lblLogoRut.setBounds(10, 46, 369, 76);
		panelRut.add(lblLogoRut);
		
		JLabel lblSDT_1 = new JLabel("SDT:");
		lblSDT_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSDT_1.setBounds(10, 132, 103, 34);
		panelRut.add(lblSDT_1);
		
		JLabel lblPass_1 = new JLabel("Mật khẩu:");
		lblPass_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass_1.setBounds(10, 176, 103, 34);
		panelRut.add(lblPass_1);
		
		JLabel lblNap_1 = new JLabel("Số Tiền Nạp:");
		lblNap_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNap_1.setBounds(10, 220, 103, 34);
		panelRut.add(lblNap_1);
		
		textSDTRut = new JTextField();
		textSDTRut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSDTRut.setColumns(10);
		textSDTRut.setBounds(123, 132, 239, 34);
		panelRut.add(textSDTRut);
		
		textPassRut = new JTextField();
		textPassRut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPassRut.setColumns(10);
		textPassRut.setBounds(123, 176, 239, 34);
		panelRut.add(textPassRut);
		
		textRut = new JTextField();
		textRut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRut.setColumns(10);
		textRut.setBounds(123, 221, 239, 34);
		panelRut.add(textRut);
		
		JLabel lblNewLabel_1_1 = new JLabel("(*) Số tiền rút phải từ 50.000 trở lên \r\n");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_1_1.setBounds(123, 265, 239, 25);
		panelRut.add(lblNewLabel_1_1);
		
		JButton btnRut = new JButton("RÚT");
		btnRut.setForeground(new Color(255, 250, 250));
		btnRut.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRut.setBackground(new Color(0, 0, 205));
		btnRut.setBounds(110, 300, 167, 58);
		btnRut.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String sdtrut = textSDTRut.getText();
		        String passrut = textPassRut.getText();
		        int amountrut = Integer.parseInt(textRut.getText());

		        // Kiểm tra thông tin SDT và mật khẩu
		        boolean validInfo = checkValidInfoRut(sdtrut, passrut);
		        
		        // Kiểm tra số tiền nạp
		        boolean validAmount = checkValidAmountRut(amountrut);

		        if (validInfo && validAmount) {
		            // Cập nhật số dư trong cơ sở dữ liệu
		            updateBalanceRut(sdtrut, amountrut);
		            // Thông báo thành công
		            //JOptionPane.showMessageDialog(null, "Rút tiền thành công!");
		        } else {
		            // Thông báo lỗi
		            JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ!");
		        }
		    }
		});
		panelRut.add(btnRut);
	}
	
	private boolean checkValidInfo(String sdt, String pass) {
		try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM bank_accounts WHERE phone_number = ? AND pass = ?";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, sdt);
	        stmt.setString(2, pass);

	        ResultSet rs = stmt.executeQuery();
	        
	        // Kiểm tra xem có kết quả trả về hay không
	        if (rs.next()) {
	            // Tài khoản hợp lệ
	            return true;
	        } else {
	            // Tài khoản không hợp lệ
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Xử lý lỗi
	        return false;
	    }
	}

	private boolean checkValidAmount(int amount) {
	    return amount >= 50000;
	}

	private void updateBalance(String sdt, int amount) {
		try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");

	        String query = "UPDATE bank_accounts SET So_du = So_du + ? WHERE phone_number = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, amount);
	        stmt.setString(2, sdt);
	        
	        int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Số dư đã được cập nhật.");
	        } else {
	            System.out.println("Không thể cập nhật số dư.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private boolean checkValidInfoRut(String sdtrut, String passrut) {
	    try {
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM bank_accounts WHERE phone_number = ? AND pass = ?";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, sdtrut);
	        stmt.setString(2, passrut);

	        ResultSet rs = stmt.executeQuery();
	        
	        // Kiểm tra xem có kết quả trả về hay không
	        if (rs.next()) {
	            // Tài khoản hợp lệ
	            return true;
	        } else {
	            // Tài khoản không hợp lệ
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private boolean checkValidAmountRut(int amountrut) {
	    return amountrut >= 50000;
	}
	private void updateBalanceRut(String sdtrut, int amountrut) {
	    boolean success = false; // Biến đánh dấu việc cập nhật số dư thành công
	    
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT So_du FROM bank_accounts WHERE phone_number = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, sdtrut);

	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int currentBalance = rs.getInt("So_du");
	            if (currentBalance >= amountrut) {
	                // Số dư đủ để rút
	                query = "UPDATE bank_accounts SET So_du = So_du - ? WHERE phone_number = ?";
	                stmt = conn.prepareStatement(query);
	                stmt.setInt(1, amountrut);
	                stmt.setString(2, sdtrut);

	                int rowsAffected = stmt.executeUpdate();
	                
	                if (rowsAffected > 0) {
	                    success = true; // Đánh dấu cập nhật số dư thành công
	                    System.out.println("Rút tiền thành công.");
	                    JOptionPane.showMessageDialog(null, "Rút tiền thành công!");
	                } else {
	                    System.out.println("Không thể cập nhật số dư.");
	                    JOptionPane.showMessageDialog(null, "Không thể rút tiền. Vui lòng thử lại sau!");
	                }
	            } else {
	                // Số dư không đủ để rút
	                System.out.println("Số dư không đủ.");
	                JOptionPane.showMessageDialog(null, "Số dư không đủ để rút tiền!");
	            }
	        } else {
	            // Không tìm thấy tài khoản
	            System.out.println("Tài khoản không tồn tại.");
	            JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại!");
	        }
	        
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Kiểm tra biến success để hiển thị thông báo "Rút tiền thành công!" nếu cần
	    if (!success) {
	        JOptionPane.showMessageDialog(null, "Không thể rút tiền. Vui lòng thử lại sau!");
	    }
	}
}
