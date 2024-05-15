package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class AddData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textSDT;
	private JTextField textCCCD;
	private JTextField textPass;
	private JTable tableData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddData frame = new AddData();
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
	public AddData() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogoAdd = new JLabel("THÊM THÔNG TIN TÀI KHOẢN\r\n");
		lblLogoAdd.setBounds(10, 10, 966, 33);
		lblLogoAdd.setForeground(new Color(0, 0, 139));
		lblLogoAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogoAdd.setBackground(new Color(204, 255, 153));
		contentPane.add(lblLogoAdd);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 53, 346, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Họ và Tên:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 80, 83, 31);
		panel.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textName.setBounds(103, 80, 233, 31);
		panel.add(textName);
		textName.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("SDT:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNumber.setBounds(10, 121, 83, 31);
		panel.add(lblPhoneNumber);
		
		textSDT = new JTextField();
		textSDT.setBounds(103, 121, 233, 31);
		panel.add(textSDT);
		textSDT.setColumns(10);
		
		JLabel lblCCCD = new JLabel("CCCD: ");
		lblCCCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCCCD.setBounds(10, 162, 83, 31);
		panel.add(lblCCCD);
		
		textCCCD = new JTextField();
		textCCCD.setBounds(103, 164, 233, 31);
		panel.add(textCCCD);
		textCCCD.setColumns(10);
		
		JLabel lblPass = new JLabel("Mật Khẩu:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setBounds(10, 203, 83, 31);
		panel.add(lblPass);
		
		textPass = new JTextField();
		textPass.setBounds(103, 205, 233, 31);
		panel.add(textPass);
		textPass.setColumns(10);
		
		JLabel lblSex = new JLabel("Giới Tính:");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSex.setBounds(10, 244, 83, 31);
		panel.add(lblSex);
		
		JRadioButton rdbtnNamRadioButton = new JRadioButton("Nam");
		rdbtnNamRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNamRadioButton.setBounds(103, 249, 55, 21);
		panel.add(rdbtnNamRadioButton);
		
		JRadioButton rdbtnNuRadioButton = new JRadioButton("Nữ");
		rdbtnNuRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNuRadioButton.setBounds(170, 249, 55, 21);
		panel.add(rdbtnNuRadioButton);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnNamRadioButton);
		genderGroup.add(rdbtnNuRadioButton);
		
		JButton btnAdd = new JButton("THÊM");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBackground(new Color(102, 255, 51));
		btnAdd.setBounds(103, 299, 122, 49);
		 btnAdd.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String name = textName.getText();
	                String phoneNumber = textSDT.getText();
	                String cccd = textCCCD.getText();
	                String pass = textPass.getText();
	                // Lấy giới tính từ RadioButton
	                String gender = rdbtnNamRadioButton.isSelected() ? "Nam" : "Nữ";
	                
	                // Kiểm tra điều kiện nhập thông tin
	                if (name.matches(".*\\d+.*")) {
	                    JOptionPane.showMessageDialog(AddData.this, "Tên không được chứa ký tự số");
	                    return;
	                }
	                if (phoneNumber.length() < 10) {
	                    JOptionPane.showMessageDialog(AddData.this, "Số điện thoại phải có ít nhất 10 ký tự");
	                    return;
	                }
	                if (cccd.length() != 10) {
	                    JOptionPane.showMessageDialog(AddData.this, "CCCD phải có đúng 10 ký tự");
	                    return;
	                }
	                if (pass.length() < 8) {
	                    JOptionPane.showMessageDialog(AddData.this, "Mật khẩu phải có ít nhất 8 ký tự");
	                    return;
	                }
	                // Thêm dữ liệu vào cơ sở dữ liệu
	                boolean success = addToDatabase(name, phoneNumber, cccd, pass, gender);
	                
	                if (success) {
	                    JOptionPane.showMessageDialog(AddData.this, "Thêm thông tin thành công");
	                    // Gọi phương thức để cập nhật bảng hiển thị dữ liệu trong giao diện
	                    updateTable();
	                } else {
	                    JOptionPane.showMessageDialog(AddData.this, "Thêm thông tin thất bại");
	                }
	            }
	        });
		panel.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("ĐIỀN THÔNG TIN");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 21, 259, 49);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(376, 93, 600, 360);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"H\u1ECD v\u00E0 T\u00EAn ", "Gi\u1EDBi t\u00EDnh ", "SDT ", "CCCD ", "M\u1EADt kh\u1EA9u", "S\u1ED1 D\u01B0"
			}
		));
		tableData.getColumnModel().getColumn(0).setPreferredWidth(98);
		tableData.getColumnModel().getColumn(1).setPreferredWidth(55);
		tableData.getColumnModel().getColumn(2).setPreferredWidth(94);
		tableData.getColumnModel().getColumn(3).setPreferredWidth(82);
		tableData.getColumnModel().getColumn(4).setPreferredWidth(84);
		tableData.getColumnModel().getColumn(5).setPreferredWidth(87);
		scrollPane.setViewportView(tableData);
		
		JLabel lblNewLabel_1 = new JLabel("THÔNG TIN TÀI KHOẢN MỚI\r\n");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(376, 53, 600, 33);
		contentPane.add(lblNewLabel_1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
	}
	 private boolean addToDatabase(String name, String phoneNumber, String cccd, String pass, String gender) {
	        String url = "jdbc:mysql://localhost:3306/BankAccount";
	        String username = "root";
	        String password = "Lisa2703";
	        String queryCheck = "SELECT * FROM bank_accounts WHERE full_name = ? AND phone_number = ? AND cccd = ?";
	        String queryInsert = "INSERT INTO bank_accounts (full_name, phone_number, cccd, pass, gender) VALUES (?, ?, ?, ?, ?)";

	        try (
	            Connection connection = DriverManager.getConnection(url, username, password);
	            PreparedStatement checkStatement = connection.prepareStatement(queryCheck);
	            PreparedStatement insertStatement = connection.prepareStatement(queryInsert)
	        ) {
	            // Kiểm tra xem có bản ghi nào đã tồn tại với thông tin tương tự hay không
	            checkStatement.setString(1, name);
	            checkStatement.setString(2, phoneNumber);
	            checkStatement.setString(3, cccd);
	            ResultSet resultSet = checkStatement.executeQuery();
	            if (resultSet.next()) {
	                // Nếu đã tồn tại bản ghi, thông báo cho người dùng và trả về false
	                JOptionPane.showMessageDialog(AddData.this, "Tài khoản đã tồn tại với thông tin này.");
	                return false;
	            } else {
	                // Nếu không có bản ghi nào tồn tại, thêm thông tin mới vào cơ sở dữ liệu
	                insertStatement.setString(1, name);
	                insertStatement.setString(2, phoneNumber);
	                insertStatement.setString(3, cccd);
	                insertStatement.setString(4, pass);
	                insertStatement.setString(5, gender);

	                int rowsAffected = insertStatement.executeUpdate();
	                return rowsAffected > 0;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    private void updateTable() {
	    	String DB_URL = "jdbc:mysql://localhost:3306/BankAccount";
	        String USER_NAME = "root";
	        String PASSWORD = "Lisa2703";
	    	try {
	    		Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	    		Statement stmt = conn.createStatement();
	            // Thực hiện truy vấn để lấy dữ liệu từ cơ sở dữ liệu
	    		 ResultSet rs = stmt.executeQuery("select * from bank_accounts");
	            // Tạo một DefaultTableModel để quản lý dữ liệu trong bảng
	            DefaultTableModel model = new DefaultTableModel();
	            // Thêm các cột vào model
	            model.addColumn("Họ và tên");
	            model.addColumn("SDT");
	            model.addColumn("CCCD");
	            model.addColumn("Mật khẩu");
	            model.addColumn("Giới tính");
	            model.addColumn("Số dư");
	            // Thêm dữ liệu từ ResultSet vào model
	            while (rs.next()) {
	                Object[] row = new Object[6];
	                row[0] = rs.getString(1);
	                row[1] = rs.getString(2);
	                row[2] = rs.getString(3);
	                row[3] = rs.getString(4);
	                row[4] = rs.getString(5);
	                row[5] = rs.getString(6);
	                model.addRow(row);
	            }
	            // Cập nhật model vào bảng
	            tableData.setModel(model);
	            
	            // Làm mới bảng để hiển thị dữ liệu mới
	            tableData.revalidate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
}
