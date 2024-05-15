package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BankAccountData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankAccountData frame = new BankAccountData();
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
	public BankAccountData() {
		setTitle("Chương trình quản lí thông ti tài khoản ngân hàng ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnaddData = new JButton("Thêm Thông Tin");
		btnaddData.setForeground(new Color(0, 0, 0));
		btnaddData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnaddData.setBackground(new Color(0, 255, 0));
		btnaddData.setBounds(165, 168, 185, 43);
		btnaddData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        AddData addDataWindow = new AddData();
		        addDataWindow.setVisible(true);
		    }
		});
		contentPane.add(btnaddData);
		
		JButton btndeleteData = new JButton("Xóa Thông Tin");
		btndeleteData.setBackground(new Color(0, 255, 0));
		btndeleteData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btndeleteData.setBounds(417, 274, 185, 43);
		btndeleteData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DeleteData deleteDataWindow = new DeleteData();
		        deleteDataWindow.setVisible(true);
		    }
		});
		contentPane.add(btndeleteData);
		
		JButton btneditData = new JButton("Sửa Thông Tin");
		btneditData.setBackground(new Color(0, 255, 0));
		btneditData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btneditData.setBounds(165, 221, 185, 43);
		btneditData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        FindAndEditData findandeditDataWindow = new FindAndEditData();
		        findandeditDataWindow.setVisible(true);
		    }
		});
		contentPane.add(btneditData);
		
		JButton btnfindData = new JButton("Tìm Kiếm Thông Tin");
		btnfindData.setBackground(new Color(0, 255, 0));
		btnfindData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnfindData.setBounds(165, 274, 185, 43);
		btnfindData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	FindAndEditData findandeditDataWindow = new FindAndEditData();
		        findandeditDataWindow.setVisible(true);
		    }
		});
		contentPane.add(btnfindData);
		
		JButton btnViewData = new JButton("Xem Thông Tin");
		btnViewData.setForeground(Color.BLACK);
		btnViewData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewData.setBackground(Color.GREEN);
		btnViewData.setBounds(417, 168, 185, 43);
		btnViewData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ViewData viewDataWindow = new ViewData();
		        viewDataWindow.setVisible(true);
		    }
		});
		contentPane.add(btnViewData);
		
		JButton btnNapRut = new JButton("Nạp / Rút ");
		btnNapRut.setForeground(Color.BLACK);
		btnNapRut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNapRut.setBackground(Color.GREEN);
		btnNapRut.setBounds(417, 221, 185, 43);
		btnNapRut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	NapRut naprutWindow = new NapRut();
		        naprutWindow.setVisible(true);
		    }
		});
		contentPane.add(btnNapRut);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(10, 10, 766, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("QUẢN LÍ THÔNG TIN TÀI KHOẢN NGÂN HÀNG\r\n");
		lblLogo.setBounds(10, 10, 746, 84);
		panel.add(lblLogo);
		lblLogo.setForeground(Color.BLUE);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setToolTipText("");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD, 30));
	}
}
