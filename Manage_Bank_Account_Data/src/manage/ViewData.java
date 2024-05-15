package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableViewData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewData frame = new ViewData();
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
	public ViewData() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(10, 10, 866, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH TÀI KHOẢN NGÂN HÀNG");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(143, 10, 599, 57);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 866, 338);
		contentPane.add(scrollPane);
		
		tableViewData = new JTable();
		tableViewData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"H\u1ECD v\u00E0 T\u00EAn ", "SDT ", "CCCD ", "M\u1EADt Kh\u1EA9u ", "Gi\u1EDBi T\u00EDnh ", "S\u1ED1 D\u01B0"
			}
		));
		tableViewData.getColumnModel().getColumn(0).setPreferredWidth(137);
		tableViewData.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableViewData.getColumnModel().getColumn(2).setPreferredWidth(113);
		tableViewData.getColumnModel().getColumn(3).setPreferredWidth(79);
		tableViewData.getColumnModel().getColumn(4).setPreferredWidth(56);
		tableViewData.getColumnModel().getColumn(5).setPreferredWidth(124);
		scrollPane.setViewportView(tableViewData);
		// Hiển thị dữ liệu ở bảng
		displayData();
	}

    private void displayData() {
        try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankAccount", "root", "Lisa2703");
	        String query = "SELECT * FROM bank_accounts";

            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            // Tạo DefaultTableModel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Họ và Tên");
            model.addColumn("SDT");
            model.addColumn("CCCD");
            model.addColumn("Mật Khẩu");
            model.addColumn("Giới Tính");
            model.addColumn("Số Dư");

            // Đổ dữ liệu từ ResultSet vào DefaultTableModel
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getString("cccd"),
                    rs.getString("pass"),
                    rs.getString("gender"),
                    rs.getInt("So_du")
                });
            }

            // Gán DefaultTableModel vào tableViewData
            tableViewData.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi
        }
    }
}
