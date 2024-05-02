package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class Frm_DangNhap extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTop;
	private JTextField txtTenDangNhap;
	private JPasswordField txtMatKhau;
	private JComboBox comboQuyenTruyCap;
	private JCheckBox checkNhoMK;
	private JButton btn_DangNhap;
	private JButton btn_Thoat;
	private JButton btn_TaoTK;
	private Frm_TrangChu tC;
	private TaiKhoan_DAO taiKhoan_DAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_DangNhap frame = new Frm_DangNhap();
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
	public Frm_DangNhap() {
		setTitle("Đăng nhập hệ thống");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		buiGUI();
	}
	private void buiGUI() {
		try {
			ConnectDB.getInstance().connect();
			taiKhoan_DAO = new TaiKhoan_DAO();
			System.out.println("Ket noi thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ket noi that bai");
		}

		// TOP:
		JPanel pnl_top = new JPanel();
		getContentPane().add(pnl_top, BorderLayout.NORTH);
		pnl_top.add(lblTop = new JLabel("Đăng Nhập Hệ Thống"));
		lblTop.setForeground(Color.red);
		Font font_lbl = new Font("Arial", Font.BOLD, 25);
		lblTop.setFont(font_lbl);
		
		//mid:
		JPanel pnl_mid = new JPanel();
//		pnl_mid.setPreferredSize(new Dimension(0, 300));
		getContentPane().add(pnl_mid,BorderLayout.CENTER);
		Box b, b1, b2, b3, b5;
		pnl_mid.setLayout(null);
		b  	= Box.createVerticalBox();
		b.setBounds(37, 27, 302, 118);
		b1 	= Box.createHorizontalBox();
		b2 	= Box.createHorizontalBox();
		b3 	= Box.createHorizontalBox();	
		b5 	= Box.createHorizontalBox();
		b.add(b1); 
		b.add(Box.createVerticalStrut(5));
		b.add(b2); 
		b.add(Box.createVerticalStrut(5));
		b.add(b3); 
		b.add(Box.createVerticalStrut(5));
		b.add(b5); 
		b.add(Box.createVerticalStrut(10));
		pnl_mid.add(b);
		
		JLabel lblTenDangNhap, lblMatKhau, lblQuyenTruyCap, lblNhoMatKhau, lblQuenMatKhau;
		b1.add(lblTenDangNhap 	= new JLabel("Tên Đăng Nhập: "));
		b1.add(Box.createHorizontalStrut(50));
//		b1.add(Box.createHorizontalGlue());
		b1.add(txtTenDangNhap	= new JTextField(20));
		
		b2.add(lblMatKhau		= new JLabel("Mật Khẩu: "));
		b2.add(Box.createHorizontalStrut(84));
		b2.add(txtMatKhau  		= new JPasswordField(20));
		
		
		
		b5.add(btn_DangNhap = new FixButton("Đăng Nhập","img/dangnhap.png",28,22));
		btn_DangNhap.addKeyListener(new KeyAdapter() {
            @Override
           public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Xử lý đăng nhập khi nhấn phím Enter
                    dangNhap();
                }
            }
        });
		txtMatKhau.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Xử lý đăng nhập khi nhấn phím Enter
		            dangNhap();
		        }
		    }
		});
		btn_DangNhap.setBackground(Color.green);
		btn_DangNhap.setForeground(Color.white);
		b5.add(Box.createRigidArea(new Dimension(50,0)));
		b5.add(btn_Thoat	= new FixButton("Thoát","img/thoathethong.png",28,22));
		btn_Thoat.setBackground(Color.red);
		btn_Thoat.setForeground(Color.white);
		
		//bot
		
		
		btn_DangNhap.addActionListener(this);
		btn_Thoat.addActionListener(this);
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	            System.exit(0);
	        }
	    });
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o == btn_DangNhap)
		{
			
			dangNhap();
			
		}	
	}
	public void dangNhap()
	{
		String tenDangNhap = txtTenDangNhap.getText();
		String matKhau = txtMatKhau.getText();
		System.out.println("tk: " + tenDangNhap + " mat khau: " + matKhau);
		try {
			TaiKhoan tk = taiKhoan_DAO.getTaiKhoanTuTenDangNhap(tenDangNhap, matKhau);
			if (tk != null) {
				this.hide();
				tC = new Frm_TrangChu(tk.getNhanVien());
				tC.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

	
		
			
	}




