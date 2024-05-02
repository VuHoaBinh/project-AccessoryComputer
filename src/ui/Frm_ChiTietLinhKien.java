package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.LinhKien;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;

public class Frm_ChiTietLinhKien extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaLK;
	private JTextField txtNgayNhap;
	private JTextField txtTenLK;
	private JTextField txtThuongHieu;
	private JTextField txtSoLuongTon;
	private JTextField txtLoai;
	private JTextField txtGiaBan;
	private JTextField txtBaoHanh;
	private JLabel lblGiaNhap;
	private JTextField txtGiaNhap;
	private JTextArea txtMoTa;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frm_ChiTietLinhKien frame = new Frm_ChiTietLinhKien(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Frm_ChiTietLinhKien(LinhKien linhKien) {
		
		setBounds(100, 100, 646, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaLK = new JLabel("Mã Linh Kiện:");
		lblMaLK.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMaLK.setBounds(24, 55, 104, 21);
		contentPane.add(lblMaLK);
		
		txtMaLK = new JTextField();
		txtMaLK.setEditable(false);
		txtMaLK.setBounds(149, 52, 148, 21);
		contentPane.add(txtMaLK);
		txtMaLK.setColumns(10);
		
		JLabel lblNgayNhap = new JLabel("Ngày Nhập:");
		lblNgayNhap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgayNhap.setBounds(336, 166, 104, 21);
		contentPane.add(lblNgayNhap);
		
		txtNgayNhap = new JTextField();
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(454, 163, 148, 21);
		contentPane.add(txtNgayNhap);
		
		JLabel lblTenLK = new JLabel("Tên Linh Kiện:");
		lblTenLK.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTenLK.setBounds(336, 55, 104, 21);
		contentPane.add(lblTenLK);
		
		txtTenLK = new JTextField();
		txtTenLK.setEditable(false);
		txtTenLK.setColumns(10);
		txtTenLK.setBounds(454, 55, 148, 21);
		contentPane.add(txtTenLK);
		
		JLabel lblThuongHieu = new JLabel("Thương Hiệu:");
		lblThuongHieu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThuongHieu.setBounds(24, 127, 104, 21);
		contentPane.add(lblThuongHieu);
		
		txtThuongHieu = new JTextField();
		txtThuongHieu.setEditable(false);
		txtThuongHieu.setColumns(10);
		txtThuongHieu.setBounds(149, 128, 148, 21);
		contentPane.add(txtThuongHieu);
		
		JLabel lblSoLuongTon = new JLabel("Số Lượng Tồn:");
		lblSoLuongTon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSoLuongTon.setBounds(24, 163, 104, 21);
		contentPane.add(lblSoLuongTon);
		
		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setEditable(false);
		txtSoLuongTon.setColumns(10);
		txtSoLuongTon.setBounds(149, 164, 148, 21);
		contentPane.add(txtSoLuongTon);
		
		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoai.setBounds(336, 125, 104, 21);
		contentPane.add(lblLoai);
		
		txtLoai = new JTextField();
		txtLoai.setEditable(false);
		txtLoai.setColumns(10);
		txtLoai.setBounds(454, 125, 148, 21);
		contentPane.add(txtLoai);
		
		JLabel lblGiaBan = new JLabel("Giá Bán:");
		lblGiaBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGiaBan.setBounds(336, 91, 104, 21);
		contentPane.add(lblGiaBan);
		
		txtGiaBan = new JTextField();
		txtGiaBan.setEditable(false);
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(454, 91, 148, 21);
		contentPane.add(txtGiaBan);
		
		JLabel lblBaoHanh = new JLabel("Bảo Hành:");
		lblBaoHanh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBaoHanh.setBounds(336, 197, 104, 21);
		contentPane.add(lblBaoHanh);
		
		txtBaoHanh = new JTextField();
		txtBaoHanh.setEditable(false);
		txtBaoHanh.setColumns(10);
		txtBaoHanh.setBounds(454, 198, 148, 21);
		contentPane.add(txtBaoHanh);
		
		JLabel lblMoTa = new JLabel("Mô tả linh kiện:");
		lblMoTa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMoTa.setBounds(24, 201, 104, 21);
		contentPane.add(lblMoTa);
		
		JLabel lblNewLabel = new JLabel("CHI TIẾT LINH KIỆN");
		lblNewLabel.setForeground(new Color(0, 85, 170));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(227, 0, 213, 30);
		contentPane.add(lblNewLabel);
		
		lblGiaNhap = new JLabel("Giá Nhập:");
		lblGiaNhap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGiaNhap.setBounds(24, 91, 104, 21);
		contentPane.add(lblGiaNhap);
		
		txtGiaNhap = new JTextField();
		txtGiaNhap.setEditable(false);
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(149, 91, 148, 21);
		contentPane.add(txtGiaNhap);
		
		txtMoTa = new JTextArea();
		txtMoTa.setLineWrap(true);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setRows(20);
		txtMoTa.setEditable(false);
		txtMoTa.setBounds(24, 247, 578, 203);
		contentPane.add(txtMoTa);
		
		txtMaLK.setText(linhKien.getMaLinhKien());
		txtTenLK.setText(linhKien.getTenLinhKien());
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		txtGiaNhap.setText(decimalFormat.format(linhKien.getGiaNhap()));
		txtGiaBan.setText(decimalFormat.format(linhKien.getGiaBan()));
		txtThuongHieu.setText(linhKien.getThuongHieu().getTenThuongHieu());
		txtLoai.setText(linhKien.getLoaiLinhKien().getTenLoaiLinhKien());
		txtSoLuongTon.setText(String.valueOf(linhKien.getSoLuong()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		txtNgayNhap.setText(dateFormat.format(linhKien.getNgayNhap()));
		txtBaoHanh.setText(String.valueOf(linhKien.getBaoHanh()));
		txtMoTa.setText(linhKien.getMoTa().toString());
		
		
		
		
	}

}
