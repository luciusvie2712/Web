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
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FindAndEditData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFindCCCD;
	private JTextField textNameEdit;
	private JTextField textEditCCCD;
	private JTextField textEditPhoneNumber;
	private JTable tableData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindAndEditData frame = new FindAndEditData();
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
	public FindAndEditData() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogoFindAndEdit = new JLabel("TÌM KIẾM VÀ CHỈNH SỬA THÔNG TIN TÀI KHOẢN");
		lblLogoFindAndEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoFindAndEdit.setForeground(new Color(51, 0, 204));
		lblLogoFindAndEdit.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogoFindAndEdit.setBounds(10, 10, 966, 47);
		contentPane.add(lblLogoFindAndEdit);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 129, 449, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFindCCCD = new JLabel("CCCD:");
		lblFindCCCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFindCCCD.setBounds(10, 10, 84, 31);
		panel.add(lblFindCCCD);
		
		textFindCCCD = new JTextField();
		textFindCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFindCCCD.setBounds(104, 10, 335, 31);
		panel.add(textFindCCCD);
		textFindCCCD.setColumns(10);
		
		JButton btnFindData = new JButton("Tìm Kiếm");
		btnFindData.setBackground(new Color(102, 255, 102));
		btnFindData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFindData.setBounds(101, 51, 123, 31);
		btnFindData.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        findData();
		    }
		});
		panel.add(btnFindData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(469, 96, 507, 357);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"H\u1ECD v\u00E0 T\u00EAn ", "Gi\u1EDBi t\u00EDnh", "SDT", "CCCD", "M\u1EADt kh\u1EA9u", "S\u1ED1 D\u01B0 "
			}
		));
		tableData.getColumnModel().getColumn(0).setPreferredWidth(91);
		tableData.getColumnModel().getColumn(1).setPreferredWidth(52);
		tableData.getColumnModel().getColumn(2).setPreferredWidth(97);
		tableData.getColumnModel().getColumn(3).setPreferredWidth(88);
		tableData.getColumnModel().getColumn(4).setPreferredWidth(82);
		tableData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableData);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 259, 449, 148);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Họ và Tên:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 10, 87, 34);
		panel_1.add(lblName);
		
		textNameEdit = new JTextField();
		textNameEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNameEdit.setBounds(107, 10, 241, 34);
		panel_1.add(textNameEdit);
		textNameEdit.setColumns(10);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCCCD.setBounds(10, 54, 87, 34);
		panel_1.add(lblCCCD);
		
		textEditCCCD = new JTextField();
		textEditCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEditCCCD.setBounds(107, 54, 241, 34);
		panel_1.add(textEditCCCD);
		textEditCCCD.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("SDT: ");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNumber.setBounds(10, 98, 87, 34);
		panel_1.add(lblPhoneNumber);
		
		textEditPhoneNumber = new JTextField();
		textEditPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEditPhoneNumber.setBounds(107, 98, 241, 34);
		panel_1.add(textEditPhoneNumber);
		textEditPhoneNumber.setColumns(10);
		
		JButton btnEditName = new JButton("SỬA");
		btnEditName.setBackground(new Color(102, 255, 102));
		btnEditName.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditName.setBounds(358, 10, 85, 34);
		btnEditName.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        editName();
		    }
		});
		panel_1.add(btnEditName);
		
		JButton btnEditCCCD = new JButton("SỬA");
		btnEditCCCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditCCCD.setBackground(new Color(102, 255, 102));
		btnEditCCCD.setBounds(358, 54, 85, 34);
		btnEditCCCD.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        editCCCD();
		    }
		});
		panel_1.add(btnEditCCCD);
		
		JButton btnEditPhoneNumber = new JButton("SỬA");
		btnEditPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditPhoneNumber.setBackground(new Color(102, 255, 102));
		btnEditPhoneNumber.setBounds(358, 98, 85, 34);
		btnEditPhoneNumber.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        editPhoneNumber();
		    }
		});
		panel_1.add(btnEditPhoneNumber);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm tài khoản theo CCCD:\r\n");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 96, 449, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chỉnh sửa thông tin tài khoản đã tìm kiếm:");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 226, 449, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("THÔNG TIN TÀI KHOẢN");
		lblNewLabel_2.setForeground(new Color(138, 43, 226));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(469, 63, 507, 23);
		contentPane.add(lblNewLabel_2);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void findData() {
	    String cccd = textFindCCCD.getText().trim();
	    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
	    model.setRowCount(0);
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM bank_accounts WHERE cccd = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, cccd);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String name = resultSet.getString("full_name");
	            String gender = resultSet.getString("gender");
	            String phoneNumber = resultSet.getString("phone_number");
	            String cccdResult = resultSet.getString("cccd");
	            String password = resultSet.getString("pass");
	            String sodu = resultSet.getString("So_du");

	            // Thêm dữ liệu vào bảng
	            model.addRow(new Object[]{name, gender, phoneNumber, cccdResult, password, sodu});
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}
	
	private void editName() {
	    // Lấy dữ liệu mới từ trường nhập liệu textNameEdit
	    String newName = textNameEdit.getText().trim();
	    
	    // Lấy CCCD của dữ liệu được chọn
	    int selectedRow = tableData.getSelectedRow();
	    if (selectedRow != -1) {
	        String cccd = tableData.getValueAt(selectedRow, 3).toString(); // Lấy CCCD từ cột CCCD
	        
	        // Thực hiện cập nhật dữ liệu full_name vào cơ sở dữ liệu dựa trên CCCD
	        if (updateNameInDatabase(newName, cccd)) {
	            JOptionPane.showMessageDialog(contentPane, "Tên đã được cập nhật thành công.");
	            updateTableWithSearchedData(textFindCCCD.getText().trim());
	        } else {
	            JOptionPane.showMessageDialog(contentPane, "Đã xảy ra lỗi. Không thể cập nhật tên.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn một hàng để chỉnh sửa tên.");
	    }
	}
	private boolean updateNameInDatabase(String newName, String cccd) {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "UPDATE bank_accounts SET full_name = ? WHERE cccd = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, newName);
	        statement.setString(2, cccd);
	        
	        // Thực hiện cập nhật dữ liệu
	        int rowsUpdated = statement.executeUpdate();
	        statement.close();
	        connection.close();
	        
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private void editCCCD() {
		 String newCCCD = textEditCCCD.getText().trim();
		    
		    // Lấy CCCD của dữ liệu được chọn
		    int selectedRow = tableData.getSelectedRow();
		    if (selectedRow != -1) {
		        String oldCCCD = tableData.getValueAt(selectedRow, 3).toString(); // Lấy CCCD từ cột CCCD
		        
		        // Thực hiện cập nhật dữ liệu CCCD vào cơ sở dữ liệu dựa trên CCCD cũ
		        if (updateCCCDInDatabase(newCCCD, oldCCCD)) {       
		            JOptionPane.showMessageDialog(contentPane, "CCCD đã được cập nhật thành công.");
		            updateTableWithSearchedData(textFindCCCD.getText().trim());
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "Đã xảy ra lỗi. Không thể cập nhật CCCD.");
		        }
		    } else {
		        JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn một hàng để chỉnh sửa CCCD.");
		    }
	}
	private boolean updateCCCDInDatabase(String newCCCD, String oldCCCD) {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "UPDATE bank_accounts SET cccd = ? WHERE cccd = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, newCCCD);
	        statement.setString(2, oldCCCD);
	        
	        // Thực hiện cập nhật dữ liệu
	        int rowsUpdated = statement.executeUpdate();
	        statement.close();
	        connection.close();

	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private void editPhoneNumber() {
	    String newPhoneNumber = textEditPhoneNumber.getText().trim();
	    
	    // Lấy số điện thoại của dữ liệu được chọn
	    int selectedRow = tableData.getSelectedRow();
	    if (selectedRow != -1) {
	        String oldPhoneNumber = tableData.getValueAt(selectedRow, 2).toString(); // Lấy số điện thoại từ cột Số Điện Thoại
	        
	        // Thực hiện cập nhật dữ liệu số điện thoại vào cơ sở dữ liệu dựa trên số điện thoại cũ
	        if (updatePhoneNumberInDatabase(newPhoneNumber, oldPhoneNumber)) {
	            JOptionPane.showMessageDialog(contentPane, "Số điện thoại đã được cập nhật thành công.");
	            updateTableWithSearchedData(textFindCCCD.getText().trim());
	        } else {
	            JOptionPane.showMessageDialog(contentPane, "Đã xảy ra lỗi. Không thể cập nhật số điện thoại.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn một hàng để chỉnh sửa số điện thoại.");
	    }
	}
	private boolean updatePhoneNumberInDatabase(String newPhoneNumber, String oldPhoneNumber) {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "UPDATE bank_accounts SET phone_number = ? WHERE phone_number = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, newPhoneNumber);
	        statement.setString(2, oldPhoneNumber);
	        
	        // Thực hiện cập nhật dữ liệu
	        int rowsUpdated = statement.executeUpdate();
	        statement.close();
	        connection.close();
 
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	private void updateTableWithSearchedData(String cccd) {
	    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
	    model.setRowCount(0); // Xóa các hàng hiện có trong bảng để chuẩn bị cho dữ liệu mới

	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM bank_accounts WHERE cccd = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, cccd);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String name = resultSet.getString("full_name");
	            String gender = resultSet.getString("gender");
	            String phoneNumber = resultSet.getString("phone_number");
	            String cccdResult = resultSet.getString("cccd");
	            String password = resultSet.getString("pass");

	            // Thêm dữ liệu vào bảng
	            model.addRow(new Object[]{name, gender, phoneNumber, cccdResult, password});
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
