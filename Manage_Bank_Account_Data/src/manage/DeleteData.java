package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import javax.swing.JButton;

public class DeleteData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCCCD;
	private JTextField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteData frame = new DeleteData();
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
	public DeleteData() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogoDelete = new JLabel("XÓA TÀI KHOẢN THEO CCCD");
		lblLogoDelete.setBackground(new Color(255, 255, 255));
		lblLogoDelete.setForeground(new Color(51, 0, 204));
		lblLogoDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoDelete.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogoDelete.setBounds(10, 10, 616, 66);
		contentPane.add(lblLogoDelete);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 240));
		panel.setBounds(10, 86, 616, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCCCD.setBounds(128, 54, 92, 34);
		panel.add(lblCCCD);
		
		textCCCD = new JTextField();
		textCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCCCD.setBounds(230, 55, 257, 34);
		panel.add(textCCCD);
		textCCCD.setColumns(10);
		
		JLabel lblPass = new JLabel("Mật Khẩu");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setBounds(128, 98, 92, 34);
		panel.add(lblPass);
		
		textPass = new JTextField();
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPass.setBounds(230, 99, 257, 34);
		panel.add(textPass);
		textPass.setColumns(10);
		
		JButton btnDelete = new JButton("XÓA");
		btnDelete.setBackground(new Color(204, 102, 0));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(230, 164, 134, 34);
		panel.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("NHẬP THÔNG TIN TÀI KHOẢN CẦN XÓA");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(128, 10, 359, 34);
		panel.add(lblNewLabel);
		btnDelete.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        String cccd = textCCCD.getText();
		        String password = textPass.getText();
		        
		        if (checkIfDataExists(cccd, password)) {
		            // Nếu dữ liệu tồn tại, thực hiện truy vấn xóa
		            if (deleteDataFromDatabase(cccd)) {
		                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công");
		            } else {		                
		                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công");
		            }
		        } else {		            
		            JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại");
		        }
		    }
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private boolean checkIfDataExists(String cccd, String password) {
		 try {
			 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
		        String query = "SELECT * FROM bank_accounts WHERE cccd = ? AND pass = ?";
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, cccd);
		        statement.setString(2, password);

		        ResultSet resultSet = statement.executeQuery();

		        // Kiểm tra xem có kết quả trả về không
		        if (resultSet.next()) {
		            // Nếu có, dữ liệu tồn tại trong cơ sở dữ liệu
		            return true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    // Nếu không có kết quả trả về hoặc có lỗi xảy ra, trả về false
		    return false;
	}


	private boolean deleteDataFromDatabase(String cccd) {
		try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "DELETE FROM bank_accounts WHERE cccd = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, cccd);
	        int rowsDeleted = statement.executeUpdate();

	        if (rowsDeleted > 0) {
	            // Nếu có ít nhất một hàng bị xóa
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    // Nếu không có hàng nào bị xóa hoặc có lỗi xảy ra
	    return false;
	}
}	
