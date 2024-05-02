
package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import dao.KhachHang_dao;
import dao.LinhKien_Dao;
import dao.LoaiLinhKien_DAO;
import dao.ThuongHieu_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class Frm_TrangChu extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
	private JPanel contentPane;
	private JLabel lblTrangChu;
	private JPanel_HoaDon hD;
	private JPanel_KhachHang kH;
	private JPanel_LinhKien lK;
	private JPanel_NhanVien nV;
	private JPanel_ThongKe tK;
	private Frm_DangNhap dN;
	private JPanel pnlDangXuat;
	private JPanel pnlJPanelTrangChu;
	private JTextField txtNhapMaTim;
	private JTable tableDanhSachLinhKien;
	private JTable tableDanhSachGioHang;
	static JTextField txtSDT;
	static JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtTongTien;
	private JTextField txtTienKhachTra;
	private JTextField txtTienThoiLai;
	static JDateChooser dataChooserNgayLapHoaDon;
	private JLabel lblTime;
	private JLabel lbldate;
	private DefaultTableModel modelDanhSachLinhKien;
	private DefaultTableModel modelDanhSachGioHang;
	private JTextField txtThue;
	private LinhKien_Dao linhKien_DAO;
	private static String prefixMaHoaDon = "HD0000";
	private double thue = 0;
	private double tongTienSauThue = 0;
	private JTextField txtMaKH;
	static JTextField txtMaNV;
	private KhachHang_dao khachHang_dao;
	private static JTextField txtMaHoaDon;
	private JButton btnThanhToan;
	private Frm_ChiTietLinhKien chiTietLinhKien;
	private JButton lamMoiDanhSach;
	private JPanel pnlTHongKe;
	private Object img;
	private FixButton btnXoa;

	/**
	 * Launch the application.
	 */
	public Frm_TrangChu(NhanVien nhanVien) {

		try {
			ConnectDB.getInstance().connect();
			linhKien_DAO = new LinhKien_Dao();
			System.out.println("Ket noi thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ket noi that bai");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1469, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(0, 187, 187));
		pnlChucNang.setBounds(0, 0, 226, 698);
		contentPane.add(pnlChucNang);
		pnlChucNang.setLayout(null);
		hD = new JPanel_HoaDon();
		hD.setSize(1226, 656);
		hD.setVisible(false);
		kH = new JPanel_KhachHang();
		kH.setSize(1226, 656);
		kH.setVisible(false);
		lK = new JPanel_LinhKien();
		lK.setVisible(false);
		lK.setSize(1226, 656);
		nV = new JPanel_NhanVien();
		nV.setSize(1226, 656);
		nV.setVisible(false);
		dN = new Frm_DangNhap();
		tK = new JPanel_ThongKe();
		tK.setSize(1226, 656);
		tK.setVisible(false);

		JPanel pnlTrangChu = new JPanel();
		pnlTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				nV.setVisible(false);
				kH.setVisible(false);
				hD.setVisible(false);
				lK.setVisible(false);
				pnlJPanelTrangChu.setVisible(true);
				tK.setVisible(false);
			}
		});

		pnlTrangChu.setBackground(new Color(30, 144, 255));
		pnlTrangChu.setBounds(0, 159, 226, 50);
		pnlChucNang.add(pnlTrangChu);
		pnlTrangChu.setLayout(null);

		lblTrangChu = new JLabel("TRANG CHỦ");
		lblTrangChu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangChu.setForeground(new Color(0, 0, 0));
		lblTrangChu.setBackground(new Color(229, 229, 229));
		lblTrangChu.setBounds(0, 0, 226, 50);
		String imgTC = "img/icontrangchu.png";
        int setRongiconTC = 28; // Đặt chiều rộng mong muốn
        int setDaiiconTC = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgTC));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconTC * aspectRatio);
            int scaledHeight = setDaiiconTC;

            if (scaledWidth > setRongiconTC) {
                scaledWidth = setRongiconTC;
                scaledHeight = (int) (setRongiconTC / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblTrangChu.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlTrangChu.add(lblTrangChu);
		lblTrangChu.setFont(new Font("Times New Roman", Font.BOLD, 16));


		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlNhanVien.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlNhanVien.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (nhanVien.getChucVu().getMaChucVu().equals("NVQL")) {
					nV.setVisible(true);
					kH.setVisible(false);
					hD.setVisible(false);
					lK.setVisible(false);
					pnlJPanelTrangChu.setVisible(false);
					tK.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Chỉ nhân viên quản lý có quyền xem!");
				}
			}
		});
		pnlNhanVien.setBackground(new Color(0, 187, 187));
		pnlNhanVien.setBounds(0, 209, 226, 50);
		pnlChucNang.add(pnlNhanVien);
		pnlNhanVien.setLayout(null);

		JLabel lblNhanVien = new JLabel("NHÂN VIÊN");
		lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);

		lblNhanVien.setForeground(new Color(0, 0, 0));
		lblNhanVien.setIcon(new ImageIcon("D:\\QLLinhKIen\\QLLinhKIen\\img\\Icon\\iconnhanvien.png"));
		lblNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhanVien.setBounds(0, 0, 226, 50);
		String imgNV = "img/iconnhanvien.png";
        int setRongiconNV = 28; // Đặt chiều rộng mong muốn
        int setDaiiconNV = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgNV));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconNV * aspectRatio);
            int scaledHeight = setDaiiconNV;

            if (scaledWidth > setRongiconNV) {
                scaledWidth = setRongiconNV;
                scaledHeight = (int) (setRongiconNV / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblNhanVien.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlNhanVien.add(lblNhanVien);

		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlKhachHang.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlKhachHang.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				kH.setVisible(true);
				hD.setVisible(false);
				lK.setVisible(false);
				nV.setVisible(false);
				pnlJPanelTrangChu.setVisible(false);
				tK.setVisible(false);
			}
		});
		pnlKhachHang.setLayout(null);
		pnlKhachHang.setBackground(new Color(0, 187, 187));
		pnlKhachHang.setBounds(0, 259, 226, 50);
		pnlChucNang.add(pnlKhachHang);
		JLabel lblKhachHang = new JLabel("KHÁCH HÀNG");
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setForeground(new Color(0, 0, 0));
		lblKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblKhachHang.setBounds(0, 0, 226, 50);
		String imgKH = "img/iconkhachhang.png";
        int setRongiconKH = 28; // Đặt chiều rộng mong muốn
        int setDaiiconKH = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgKH));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconKH * aspectRatio);
            int scaledHeight = setDaiiconKH;

            if (scaledWidth > setRongiconKH) {
                scaledWidth = setRongiconKH;
                scaledHeight = (int) (setRongiconKH / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblKhachHang.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlKhachHang.add(lblKhachHang);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlHoaDon.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlHoaDon.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				hD.setVisible(true);
				kH.setVisible(false);
				lK.setVisible(false);
				nV.setVisible(false);
				pnlJPanelTrangChu.setVisible(false);
				tK.setVisible(false);
			}
		});
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setBackground(new Color(0, 187, 187));
		pnlHoaDon.setBounds(0, 308, 226, 50);
		pnlChucNang.add(pnlHoaDon);

		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setForeground(new Color(0, 0, 0));
		lblHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHoaDon.setBounds(0, 0, 226, 50);
		String imgHD = "img/hoadon.png";
        int setRongiconHD = 28; // Đặt chiều rộng mong muốn
        int setDaiiconHD = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgHD));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconHD * aspectRatio);
            int scaledHeight = setDaiiconHD;

            if (scaledWidth > setRongiconHD) {
                scaledWidth = setRongiconHD;
                scaledHeight = (int) (setRongiconHD / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblHoaDon.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlHoaDon.add(lblHoaDon);

		JPanel pnlLinhKien = new JPanel();
		pnlLinhKien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlLinhKien.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlLinhKien.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (nhanVien.getChucVu().getMaChucVu().equals("NVQL")) {
					lK.setVisible(true);
					hD.setVisible(false);
					kH.setVisible(false);
					nV.setVisible(false);
					pnlJPanelTrangChu.setVisible(false);
					tK.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Chỉ nhân viên quản lý có quyền xem!");
			}
		});
		pnlLinhKien.setLayout(null);
		pnlLinhKien.setBackground(new Color(0, 187, 187));
		pnlLinhKien.setBounds(0, 358, 226, 50);
		pnlChucNang.add(pnlLinhKien);

		JLabel lblLinhKinh = new JLabel("LINH KIỆN");
		lblLinhKinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinhKinh.setForeground(new Color(0, 0, 0));
		lblLinhKinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLinhKinh.setBounds(0, 0, 227, 50);
		String imgLK = "img/linhkien.png";
        int setRongiconLK = 28; // Đặt chiều rộng mong muốn
        int setDaiiconLK = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgLK));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconLK * aspectRatio);
            int scaledHeight = setDaiiconLK;

            if (scaledWidth > setRongiconLK) {
                scaledWidth = setRongiconLK;
                scaledHeight = (int) (setRongiconLK / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblLinhKinh.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlLinhKien.add(lblLinhKinh);

		JPanel pnlTroGiup = new JPanel();
		pnlTroGiup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlTroGiup.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlTroGiup.setBackground(new Color(0, 187, 187));
			}
		});
		pnlTroGiup.setLayout(null);
		pnlTroGiup.setBackground(new Color(0, 187, 187));
		pnlTroGiup.setBounds(0, 454, 226, 50);
		pnlChucNang.add(pnlTroGiup);

		JLabel lblTrGiup = new JLabel("TRỢ GIÚP");
		lblTrGiup.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrGiup.setForeground(new Color(0, 0, 0));
		lblTrGiup.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTrGiup.setBounds(0, 0, 226, 50);
		String imgPath = "img/trogiup.png";
	        int desiredWidth = 28; // Đặt chiều rộng mong muốn
	        int desiredHeight = 22; // Đặt chiều cao mong muốn

	        try {
	            // Đọc hình ảnh từ tệp
	            BufferedImage originalImage = ImageIO.read(new File(imgPath));

	            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
	            int originalWidth = originalImage.getWidth();
	            int originalHeight = originalImage.getHeight();
	            double aspectRatio = (double) originalWidth / originalHeight;

	            int scaledWidth = (int) (desiredHeight * aspectRatio);
	            int scaledHeight = desiredHeight;

	            if (scaledWidth > desiredWidth) {
	                scaledWidth = desiredWidth;
	                scaledHeight = (int) (desiredWidth / aspectRatio);
	            }

	            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

	            // Đặt hình ảnh vào JLabel
	            lblTrGiup.setIcon(new ImageIcon(scaledImage));
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Xử lý lỗi, ví dụ: Hiển thị thông báo hoặc thực hiện các hành động khác tùy thuộc vào nhu cầu của bạn
	        }
		pnlTroGiup.add(lblTrGiup);

		
		
		
		
		
		
		pnlDangXuat = new JPanel();
		pnlDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlDangXuat.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlDangXuat.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dN.setVisible(true);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		pnlDangXuat.setBackground(new Color(0, 187, 187));
		pnlDangXuat.setBounds(0, 631, 226, 50);
		pnlChucNang.add(pnlDangXuat);
		pnlDangXuat.setLayout(null);

		
		JLabel lblDangXuat = new JLabel("ĐĂNG XUẤT");
		lblDangXuat.setBounds(0, 0, 226, 50);
		pnlDangXuat.add(lblDangXuat);
		lblDangXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangXuat.setForeground(new Color(0, 0, 0));
		lblDangXuat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		String imgthoat = "img/thoat.png";
        int setRongiconThoat = 28; // Đặt chiều rộng mong muốn
        int setRongiconDai = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgthoat));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (desiredHeight * aspectRatio);
            int scaledHeight = desiredHeight;

            if (scaledWidth > desiredWidth) {
                scaledWidth = desiredWidth;
                scaledHeight = (int) (desiredWidth / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblDangXuat.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }




		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 226, 158);
		pnlChucNang.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 226, 158);
		panel_1.add(lblNewLabel_2);

		// Sử dụng đường dẫn tương đối
		String imagePath1 = "img/anhshop.png";  // Đặt đường dẫn tương đối ở đây

		try {
		    // Đọc hình ảnh từ tệp
		    BufferedImage originalImage = ImageIO.read(new File(imagePath1));

		    // Lấy kích thước của JLabel
		    int labelWidth = lblNewLabel_2.getWidth();
		    int labelHeight = lblNewLabel_2.getHeight();

		    // Thay đổi kích thước hình ảnh
		    Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

		    // Đặt hình ảnh vào JLabel
		    lblNewLabel_2.setIcon(new ImageIcon(scaledImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}


		pnlTHongKe = new JPanel();
		pnlTHongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlTHongKe.setBackground(new Color(30, 144, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlTHongKe.setBackground(new Color(0, 187, 187));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (nhanVien.getChucVu().getMaChucVu().equals("NVQL")) {
					lK.setVisible(false);
					hD.setVisible(false);
					kH.setVisible(false);
					nV.setVisible(false);
					pnlJPanelTrangChu.setVisible(false);
					tK.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Chỉ nhân viên quản lý có quyền xem!");
			}
		});
		pnlTHongKe.setLayout(null);
		pnlTHongKe.setBackground(new Color(0, 187, 187));
		pnlTHongKe.setBounds(0, 405, 226, 50);
		pnlChucNang.add(pnlTHongKe);

		JLabel lblThongKe = new JLabel("THỐNG KÊ");
		lblThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongKe.setForeground(Color.BLACK);
		lblThongKe.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThongKe.setBounds(0, 0, 226, 50);
		String imgTK = "img/thongke.png";
        int setRongiconTK = 28; // Đặt chiều rộng mong muốn
        int setDaiiconTK = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgTK));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (desiredHeight * aspectRatio);
            int scaledHeight = desiredHeight;

            if (scaledWidth > desiredWidth) {
                scaledWidth = desiredWidth;
                scaledHeight = (int) (desiredWidth / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblThongKe.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		pnlTHongKe.add(lblThongKe);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 619, 206, 2);
		pnlChucNang.add(panel_2);

		JPanel pnlView = new JPanel();
		pnlView.setBounds(225, 42, 1226, 656);
		pnlView.add(hD);
		pnlView.add(kH);
		pnlView.add(lK);
		pnlView.add(nV);
		pnlView.add(tK);
		contentPane.add(pnlView);
		pnlView.setLayout(null);

		pnlJPanelTrangChu = new JPanel();
		pnlJPanelTrangChu.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Trang chu\u0309", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		pnlJPanelTrangChu.setBounds(0, 0, 1226, 656);
		pnlView.add(pnlJPanelTrangChu);
		pnlJPanelTrangChu.setLayout(null);

		txtNhapMaTim = new JTextField();
		txtNhapMaTim.setToolTipText("");
		txtNhapMaTim.setBounds(20, 49, 478, 20);
		pnlJPanelTrangChu.add(txtNhapMaTim);
		txtNhapMaTim.setColumns(10);

		JLabel lblTimKiemLinhKien = new JLabel("Tìm kiếm theo mã linh kiện");
		lblTimKiemLinhKien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTimKiemLinhKien.setBounds(20, 21, 193, 25);
		pnlJPanelTrangChu.add(lblTimKiemLinhKien);

		JPanel pnlDanhSachLinhKien = new JPanel();
		pnlDanhSachLinhKien.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Danh sa\u0301ch linh ki\u00EA\u0323n", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 85, 170)));
		pnlDanhSachLinhKien.setBounds(20, 72, 625, 522);
		pnlJPanelTrangChu.add(pnlDanhSachLinhKien);
		pnlDanhSachLinhKien.setLayout(null);

		String[] header = { "Mã", "Tên LK", "Thương Hiệu", "Loại", "SL", "Đơn Giá", "Bảo Hành" };
		modelDanhSachLinhKien = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// k cho chỉnh sửa dữ liệu của cột
				return false;
			}
		};
		tableDanhSachLinhKien = new JTable(modelDanhSachLinhKien);
		JScrollPane scrollPane = new JScrollPane(tableDanhSachLinhKien);

		// thêm lk từ db vào model
		DocLinhKienTuDBVaoModel();
		scrollPane.setBounds(10, 21, 605, 491);
		pnlDanhSachLinhKien.add(scrollPane);

		JPanel pnlDanhSachGioHang = new JPanel();
		pnlDanhSachGioHang.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Danh sa\u0301ch gio\u0309 ha\u0300ng", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 85, 170)));
		pnlDanhSachGioHang.setBounds(665, 72, 537, 318);
		pnlJPanelTrangChu.add(pnlDanhSachGioHang);
		pnlDanhSachGioHang.setLayout(null);

		String[] header1 = { "Mã hóa đơn", "Mã linh kiện", "Tên linh kiện", "Bảo Hành", "Số lượng", "Đơn Giá",
				"Thành Tiền" };
		modelDanhSachGioHang = new DefaultTableModel(header1, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// k cho chỉnh sửa dữ liệu của cột
				return false;
			}
		};

		tableDanhSachGioHang = new JTable(modelDanhSachGioHang);
		JScrollPane scrollPane_1 = new JScrollPane(tableDanhSachGioHang);

		// Trong sự kiện mouseClicked cho tableDanhSachLinhKien

		tableDanhSachLinhKien.addMouseListener(new MouseAdapter() {

			private double thue1LK;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = tableDanhSachLinhKien.getSelectedRow();
					DecimalFormat df = new DecimalFormat("###,###");
					if (row != -1) {
						String maLK = tableDanhSachLinhKien.getValueAt(row, 0).toString();
						String soLuongLK = JOptionPane.showInputDialog(null, "Nhập số lượng cho " + maLK + ":");
						Integer soLuongInt = Integer.parseInt(soLuongLK);
						String soLuongTon = tableDanhSachLinhKien.getValueAt(row, 4).toString();
						Integer soLuongTon1 = Integer.parseInt(soLuongTon);

						if (soLuongInt < soLuongTon1) {
							if (soLuongLK != null && !soLuongLK.isEmpty()) {
								try {
									if (soLuongInt <= 0) {
										JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng > 0");
									} else {
										boolean kiemTra = false;
										for (int i = 0; i < modelDanhSachGioHang.getRowCount(); i++) {
											if (maLK.equals(modelDanhSachGioHang.getValueAt(i, 1).toString())) {
												// Nếu mã lk tồn tại thì cập nhật số lượng lên
												Integer currentSoLuong = (Integer) modelDanhSachGioHang.getValueAt(i,
														4);
												modelDanhSachGioHang.setValueAt(currentSoLuong + soLuongInt, i, 4);

												Double donGia = Double.parseDouble(modelDanhSachLinhKien
														.getValueAt(row, 5).toString().replace(",", ""));
												Double tienMoi = soLuongInt * donGia;
												String dfTienMoi = df.format(tienMoi);

												Double currentDonGia = Double.parseDouble(modelDanhSachGioHang
														.getValueAt(i, 6).toString().replace(",", ""));
												String dfTotalAmount = df.format(tienMoi + currentDonGia);
												modelDanhSachGioHang.setValueAt(dfTotalAmount, i, 6);

												kiemTra = true;
												break;
											}
										}

										if (!kiemTra) {
											// TODO: Thêm logic để xử lý số lượng
											// In ra console để kiểm tra
											System.out.println("Sản phẩm: " + maLK + ", Số lượng: " + soLuongInt);

											// Thêm dòng mới vào modelDanhSachGioHang
											String maHD = txtMaHoaDon.getText();
											String tenLK = modelDanhSachLinhKien.getValueAt(row, 1).toString();
											double donGia = Double.parseDouble(modelDanhSachLinhKien.getValueAt(row, 5)
													.toString().replace(",", ""));
											int baoHanh = Integer
													.parseInt(modelDanhSachLinhKien.getValueAt(row, 6).toString());
											double thanhTien = soLuongInt * donGia;
											System.out.println("thành tiền: " + thanhTien);
											thue1LK = thanhTien / 10;
											thue += thue1LK;

											tongTienSauThue += thanhTien + thue1LK;

											String dfDonGia = df.format(donGia);
											String dfThanhTien = df.format(thanhTien);
											Object[] newRow = { maHD, maLK, tenLK, baoHanh, soLuongInt, dfDonGia,
													dfThanhTien };
											modelDanhSachGioHang.addRow(newRow);
										}

										// Tính thue và tongTienSauThue sau vòng lặp
										double totalAmount = 0.0;
										for (int i = 0; i < modelDanhSachGioHang.getRowCount(); i++) {
											totalAmount += Double.parseDouble(
													modelDanhSachGioHang.getValueAt(i, 6).toString().replace(",", ""));
										}
										thue = totalAmount / 10;
										tongTienSauThue = totalAmount + thue;

										String dfThue = df.format(thue);
										String dfTongTienSauThue = df.format(tongTienSauThue);
										txtThue.setText(dfThue);
										txtTongTien.setText(dfTongTienSauThue);
									}
								} catch (NumberFormatException ex) {
									JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Số lượng mua hàng phải nhỏ hơn số lượng tồn");
						}
					}
				}
			}
		});

		// Trong sự kiện mouseClicked cho tableDanhSachGioHang

		tableDanhSachGioHang.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				double thueChuaSua=0;
				if (e.getClickCount() == 2) {
					int row = tableDanhSachGioHang.getSelectedRow();
					if (row != -1) {
						String maHD = txtMaHoaDon.getText();
						String maLK = tableDanhSachGioHang.getValueAt(row, 1).toString();
						LinhKien lk1 = new LinhKien();
						LinhKien_Dao lk_dao = new LinhKien_Dao();
						lk1 = lk_dao.timLinhKienTheoMa(maLK);
						int soLuongLK1 = lk1.getSoLuong();
						double donGia = Double
								.parseDouble(tableDanhSachGioHang.getValueAt(row, 5).toString().replace(",", ""));
						double thanhTienChuaSua = Double
								.parseDouble(tableDanhSachGioHang.getValueAt(row, 6).toString().replace(",", ""));
						String soLuongLK = JOptionPane.showInputDialog(null, "Thay đổi số lượng cho " + maLK + ":");
						Integer soLuongInt = Integer.parseInt(soLuongLK);
						if(soLuongInt < soLuongLK1) {
						if (soLuongLK != null && !soLuongLK.isEmpty()) {
							try {
								if (soLuongInt <= 0) {
									JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng > 0");
								} else {
									// TODO: Thêm logic để xử lý số lượng
									// In ra console để kiểm tra
									System.out.println("Sản phẩm: " + maLK + ", Số lượng: " + soLuongInt);

									// Thay đổi số lượng modelDanhSachGioHang

//									int baoHanh = Integer.parseInt(modelDanhSachLinhKien.getValueAt(row, 6).toString());

									thueChuaSua = thanhTienChuaSua / 10;
									thue -= thueChuaSua;
									tongTienSauThue -= (thanhTienChuaSua + thueChuaSua);
									HoaDon hd = new HoaDon(maHD);
									LinhKien lk = new LinhKien(maLK);
									ChiTietHoaDon cthd = new ChiTietHoaDon(hd, lk, soLuongInt, donGia);

									double thanhTien = cthd.tinhThanhTien();
									HoaDon hoaDon = new HoaDon();
									hoaDon.addItem(new ChiTietHoaDon(hd, lk, soLuongInt, donGia));
									double thue1 = hoaDon.tinhThue();
									thue +=thue1;
									tongTienSauThue += thanhTien + thue1;
									
									DecimalFormat df = new DecimalFormat("###,###");
									String dfThanhTien = df.format(thanhTien);
									modelDanhSachGioHang.setValueAt(soLuongInt, row, 4);
									modelDanhSachGioHang.setValueAt(dfThanhTien, row, 6);

									String dfThue = df.format(thue);
									String dfTongTienSauThue = df.format(tongTienSauThue);
									txtThue.setText(String.valueOf(dfThue));
									txtTongTien.setText(String.valueOf(dfTongTienSauThue));
								}
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
							}
						}
						}
						else {
							JOptionPane.showMessageDialog(null, "Số lượng thay đổi không được quá số lượng tồn!!");
						}
					}
				}
			}
		});

		scrollPane_1.setBounds(10, 21, 517, 246);
		pnlDanhSachGioHang.add(scrollPane_1);

		btnXoa = new FixButton("Xóa", "img/Xoa.png", 20, 18);
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setBounds(141, 276, 106, 31);
		pnlDanhSachGioHang.add(btnXoa);
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JButton btnXoaTatCa = new FixButton("Xóa Tất Cả","img/XoaTatCa.png",28,22);
		btnXoaTatCa.setForeground(new Color(0, 0, 0));
		btnXoaTatCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaTatCa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXoaTatCa.setBounds(274, 276, 136, 31);
		pnlDanhSachGioHang.add(btnXoaTatCa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Th\u00F4ng tin ho\u0301a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 85, 170)));
		pnlThongTinHoaDon.setBounds(665, 396, 537, 198);
		pnlJPanelTrangChu.add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);

		JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng :");
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTenKhachHang.setBounds(26, 120, 106, 14);
		pnlThongTinHoaDon.add(lblTenKhachHang);

		JLabel lblNgayLapHoaDon = new JLabel("Ngày Lập HĐ:");
		lblNgayLapHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNgayLapHoaDon.setBounds(26, 174, 106, 14);
		pnlThongTinHoaDon.add(lblNgayLapHoaDon);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSDT.setBounds(26, 58, 79, 14);
		pnlThongTinHoaDon.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setBounds(142, 57, 106, 20);
		pnlThongTinHoaDon.add(txtSDT);
		txtSDT.setColumns(10);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(142, 114, 106, 20);
		pnlThongTinHoaDon.add(txtTenKhachHang);

		dataChooserNgayLapHoaDon = new JDateChooser();
		dataChooserNgayLapHoaDon.getCalendarButton().setEnabled(false);
		dataChooserNgayLapHoaDon.setDateFormatString("yyyy-MM-dd");

		// Đặt giá trị mặc định là ngày hôm nay
		dataChooserNgayLapHoaDon.setDate(Calendar.getInstance().getTime());
		((JTextField) dataChooserNgayLapHoaDon.getDateEditor().getUiComponent()).setDisabledTextColor(Color.BLACK);
		dataChooserNgayLapHoaDon.setEnabled(false);

		dataChooserNgayLapHoaDon.setBounds(142, 168, 106, 20);
		pnlThongTinHoaDon.add(dataChooserNgayLapHoaDon);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDiaChi.setBounds(26, 144, 79, 20);
		pnlThongTinHoaDon.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(142, 138, 106, 20);
		pnlThongTinHoaDon.add(txtDiaChi);

		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTongTien.setBounds(290, 82, 95, 25);
		pnlThongTinHoaDon.add(lblTongTien);

		JLabel lblTienKhachTra = new JLabel("Tiền Khách Trả:");
		lblTienKhachTra.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTienKhachTra.setBounds(290, 118, 122, 18);
		pnlThongTinHoaDon.add(lblTienKhachTra);

		JLabel lblTienThoiLai = new JLabel("Tiền Thối Lại:");
		lblTienThoiLai.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTienThoiLai.setBounds(290, 151, 122, 20);
		pnlThongTinHoaDon.add(lblTienThoiLai);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(410, 85, 106, 20);
		pnlThongTinHoaDon.add(txtTongTien);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setColumns(10);
		txtTienKhachTra.setBounds(410, 116, 106, 20);
		pnlThongTinHoaDon.add(txtTienKhachTra);

		txtTienThoiLai = new JTextField();
		txtTienThoiLai.setEditable(false);
		txtTienThoiLai.setColumns(10);
		txtTienThoiLai.setBounds(410, 151, 106, 20);
		pnlThongTinHoaDon.add(txtTienThoiLai);

		JLabel lblThue = new JLabel("Thuế (VAT 10%):");
		lblThue.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblThue.setBounds(290, 58, 110, 14);
		pnlThongTinHoaDon.add(lblThue);

		txtThue = new JTextField();
		txtThue.setEditable(false);
		txtThue.setColumns(10);
		txtThue.setBounds(410, 55, 106, 20);
		pnlThongTinHoaDon.add(txtThue);

		JLabel lbMaKH = new JLabel("Mã KH:");
		lbMaKH.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbMaKH.setBounds(26, 84, 79, 20);
		pnlThongTinHoaDon.add(lbMaKH);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(142, 84, 106, 20);
		pnlThongTinHoaDon.add(txtMaKH);

		JLabel lblMaNV = new JLabel("Mã NV :");
		lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMaNV.setBounds(290, 26, 110, 14);
		pnlThongTinHoaDon.add(lblMaNV);
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(410, 23, 106, 20);

		pnlThongTinHoaDon.add(txtMaNV);
		txtMaNV.setText(nhanVien.getMaNhanVien());

		JLabel lblNewLabel_3_1 = new JLabel("1/2");
		lblNewLabel_3_1.setBounds(349, 601, 24, 20);
		pnlJPanelTrangChu.add(lblNewLabel_3_1);

		JButton btnLamMoiDanhSach = new FixButton("Làm Mới Danh Sách", "img/lamMoi.png", 28, 22);
		btnLamMoiDanhSach.setForeground(new Color(0, 0, 0));
		btnLamMoiDanhSach.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLamMoiDanhSach.setBounds(20, 595, 193, 31);
		pnlJPanelTrangChu.add(btnLamMoiDanhSach);

		JButton btnTaoHoaDon = new FixButton("Tạo hóa đơn","img/TaoHoaDon.png",28,22);
		btnTaoHoaDon.setForeground(new Color(0, 0, 0));
		btnLamMoiDanhSach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dm = (DefaultTableModel) tableDanhSachLinhKien.getModel();
				int rowCount1 = dm.getRowCount();
				for (int i = rowCount1 - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
				DocLinhKienTuDBVaoModel();
			}
		});
		btnTaoHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnTaoHoaDon.setBounds(892, 605, 148, 31);
		pnlJPanelTrangChu.add(btnTaoHoaDon);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 187, 187));
		panel.setBounds(225, 0, 1226, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel pnlTaiKhoan = new JPanel();
		pnlTaiKhoan.setBounds(1016, 11, 200, 24);
		panel.add(pnlTaiKhoan);
		pnlTaiKhoan.setBackground(new Color(229, 229, 229));
		pnlTaiKhoan.setLayout(null);
		JLabel lbltaikhoan = new JLabel();
		lbltaikhoan.setText(nhanVien.getTenNhanVien());
		lbltaikhoan.setBounds(0, 0, 200, 24);
		pnlTaiKhoan.add(lbltaikhoan);
		lbltaikhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lbltaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		String imgtaikhoan = "img/taikhoan.png";
        int setRongicontaikhoan = 28; // Đặt chiều rộng mong muốn
        int setDaiicontaikhoan = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgtaikhoan));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiicontaikhoan * aspectRatio);
            int scaledHeight = setDaiicontaikhoan;

            if (scaledWidth > setRongicontaikhoan) {
                scaledWidth = setRongicontaikhoan;
                scaledHeight = (int) (setRongicontaikhoan / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lbltaikhoan.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		txtMaNV.setText(nhanVien.getMaNhanVien());
		txtMaNV.setEditable(false);

		JLabel lbMaHoaDon = new JLabel("Mã hóa đơn :");
		lbMaHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbMaHoaDon.setBounds(26, 28, 95, 20);
		pnlThongTinHoaDon.add(lbMaHoaDon);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setColumns(10);
		txtMaHoaDon.setBounds(142, 27, 106, 20);
		HoaDon_Dao maHoaDon = new HoaDon_Dao();
		int currentLength = maHoaDon.laySoHoaDon();
		String maHoaDonUpdate = prefixMaHoaDon + (++currentLength);
		txtMaHoaDon.setText(maHoaDonUpdate);
		pnlThongTinHoaDon.add(txtMaHoaDon);

		JButton btnTim = new FixButton("Tìm");
		btnTim.setForeground(new Color(0, 0, 0));
		btnTim.setBounds(508, 46, 136, 25);
		String imgtim = "img/tim.png";
        int setRongicontim = 28; // Đặt chiều rộng mong muốn
        int setDaiicontim = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgtim));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiicontim * aspectRatio);
            int scaledHeight = setDaiicontim;

            if (scaledWidth > setRongicontim) {
                scaledWidth = setRongicontim;
                scaledHeight = (int) (setRongicontim / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            btnTim.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnTim.setBounds(508, 46, 136, 25);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maLinhKien = txtNhapMaTim.getText();
				LinhKien linhKien = new LinhKien();
				linhKien = linhKien_DAO.timLinhKienTheoMa(maLinhKien);
				if (!(linhKien == null)) {
					modelDanhSachLinhKien.getDataVector().removeAllElements();
					DecimalFormat decimalFormat = new DecimalFormat("###,###");
					Object[] obj = new Object[] { linhKien.getMaLinhKien(), linhKien.getTenLinhKien(),
							linhKien.getThuongHieu().getTenThuongHieu(),
							linhKien.getLoaiLinhKien().getTenLoaiLinhKien(), linhKien.getSoLuong(),
							decimalFormat.format(linhKien.getGiaBan()), linhKien.getBaoHanh() };
					modelDanhSachLinhKien.addRow(obj);
				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy linh kiện");

			}
		});
		pnlJPanelTrangChu.add(btnTim);

		JButton btnLen = new FixButton("","img/Len.png",26,20);
		btnLen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableDanhSachLinhKien.getSelectedRow();

		        if (selectedRow > 0) {
		            // Nếu dòng đã chọn không phải là dòng đầu tiên, giảm dòng được chọn
		            tableDanhSachLinhKien.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);

		            // Scroll to make the newly selected row visible
		            tableDanhSachLinhKien.scrollRectToVisible(tableDanhSachLinhKien.getCellRect(selectedRow - 1, 0, true));
		        }
		    }
		});
		btnLen.setBounds(306, 600, 36, 23);
		pnlJPanelTrangChu.add(btnLen);

		JButton btnXuong = new FixButton("","img/Xuong.png",20,18);
		btnXuong.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableDanhSachLinhKien.getSelectedRow();
		        int rowCount = tableDanhSachLinhKien.getRowCount();

		        if (selectedRow < rowCount - 1) {
		            // Nếu dòng đã chọn không phải là dòng cuối cùng, tăng dòng được chọn
		            tableDanhSachLinhKien.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);

		            // Scroll to make the newly selected row visible
		            tableDanhSachLinhKien.scrollRectToVisible(tableDanhSachLinhKien.getCellRect(selectedRow + 1, 0, true));
		        }
		    }
		});
		btnXuong.setBounds(370, 600, 36, 23);
		pnlJPanelTrangChu.add(btnXuong);
		
		lbldate = new JLabel("New label");
		lbldate.setBounds(21, 0, 82, 20);
		panel.add(lbldate);
		lbldate.setForeground(new Color(255, 255, 255));
		lbldate.setFont(new Font("Times New Roman", Font.BOLD, 16));

		lblTime = new JLabel("New label");
		lblTime.setBounds(21, 21, 110, 22);
		panel.add(lblTime);
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 16));
		date();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
		// Cập nhật thời gian ban đầu
		updateClock(timeFormat);
		// Lập lịch cập nhật thời gian mỗi giây
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> updateClock(timeFormat), 0, 1, TimeUnit.SECONDS);
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = tableDanhSachGioHang.getSelectedRow();
				String thanhTienTruoc = txtTongTien.getText().replace(",", "");
				String thueTruoc = txtThue.getText().replace(",", "");
				DecimalFormat df = new DecimalFormat("###,###");
				if (r >= 0) {
					String thanhTienTru = modelDanhSachGioHang.getValueAt(r, 6).toString().replace(",", "");
					double thanhTienTru1 = Double.parseDouble(thanhTienTru);
					double ThanhTienTruoc1 = Double.parseDouble(thanhTienTruoc);
					double ThueTruoc1 =Double.parseDouble(thueTruoc);
					
					double thueKQ = ThueTruoc1 - thanhTienTru1/10;
					double thanhTienTienKQ = ThanhTienTruoc1 - (thanhTienTru1+thanhTienTru1/10);
					String thueKQ1 = df.format(thueKQ);
					String thanhTienKQ = df.format(thanhTienTienKQ);
					txtThue.setText(thueKQ1);
					txtTongTien.setText(thanhTienKQ);
					modelDanhSachGioHang.removeRow(r); // xóa trong table model
					
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa!!");
				}
			}
		});
		btnXoaTatCa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dm = (DefaultTableModel) tableDanhSachGioHang.getModel();
				int rowCount = dm.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
				txtTongTien.setText("");
				txtThue.setText("");

			}
		});

		txtTienKhachTra.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				double tongTien = Double.parseDouble(txtTongTien.getText().toString().replace(",", ""));
				String tienKhachTra = txtTienKhachTra.getText();
				double tienKhachTra1 = Double.parseDouble(tienKhachTra);
				if (txtTienKhachTra.getText().length() != 0)
					if (tienKhachTra1 >= tongTien) {
						double tienThoiLai = tienKhachTra1 - tongTien;
						DecimalFormat df = new DecimalFormat("###,###");
						String dfTienThoi = df.format(tienThoiLai);

						txtTienThoiLai.setText(String.valueOf(dfTienThoi));
					} else {
						JOptionPane.showMessageDialog(null,
								"Tiền khách trả nhỏ hơn tổng tiền nên không thể thanh toán!!!");
					}
				else {
					JOptionPane.showMessageDialog(null, "Không được để trống tiền khách trả");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		btnTaoHoaDon.addMouseListener(new MouseAdapter() {
			HoaDon newhd = new HoaDon();

			@Override
			public void mouseClicked(MouseEvent e) {
				String sdt = txtSDT.getText();
				String tongTienKiemTra = txtTongTien.getText();
				String thueKiemTra = txtThue.getText();
				if((tongTienKiemTra.length()!=0)||(thueKiemTra.length()!=0)) {
				if (sdt.length() != 0) {
					try {
						String maHoaDon = txtMaHoaDon.getText();
						String maKH = txtMaKH.getText();
						String maNV = txtMaNV.getText();
						float thue = 10;
						KhachHang kh = new KhachHang(maKH);
						HoaDon hd = new HoaDon(maHoaDon, kh, nhanVien,
								Date.valueOf(DATE_FORMAT_SQL.format(dataChooserNgayLapHoaDon.getDate())), thue);
						HoaDon_Dao hd_dao = new HoaDon_Dao();
						hd_dao.themHoaDon(hd);
						int rowCount = tableDanhSachGioHang.getRowCount();
						ArrayList<ChiTietHoaDon> cthdlist = new ArrayList<>();
						for (int i = 0; i < rowCount; i++) {
							String maHD = tableDanhSachGioHang.getValueAt(i, 0).toString();
							String maLk = tableDanhSachGioHang.getValueAt(i, 1).toString();
							int soLuong = (int) tableDanhSachGioHang.getValueAt(i, 4);
							double donGia = Double
									.parseDouble(tableDanhSachGioHang.getValueAt(i, 5).toString().replace(",", ""));
							HoaDon hd1 = new HoaDon(maHD);
							LinhKien lk1 = new LinhKien(maLk);
							ChiTietHoaDon cthd1 = new ChiTietHoaDon(hd1, lk1, soLuong, donGia);
							cthdlist.add(cthd1);
						}
						ChiTietHoaDon_Dao cthd_dao = new ChiTietHoaDon_Dao();
						for (ChiTietHoaDon ct : cthdlist) {
							cthd_dao.themChiTietHoaDon(ct);
							cthd_dao.updateSoLuongTon(ct);
						}
						DefaultTableModel dm = (DefaultTableModel) tableDanhSachLinhKien.getModel();
						int rowCount1 = dm.getRowCount();
						for (int i = rowCount1 - 1; i >= 0; i--) {
							dm.removeRow(i);
						}
						DocLinhKienTuDBVaoModel();
						JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công !!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn !!");
						String maHoaDonHienTai = txtMaHoaDon.getText();
						HoaDon_Dao hd = new HoaDon_Dao();
						hd.xoaPhieuDatHang(maHoaDonHienTai);
						e1.printStackTrace();
					}
					XoaRong();
				} else {
					try {
						KhachHang khachVangLai = new KhachHang();
						KhachHang_dao kh_dao = new KhachHang_dao();
						khachVangLai = kh_dao.getKhachVangLai();
						String maHoaDon = txtMaHoaDon.getText();
						String maKH = khachVangLai.getMaKhachHang();
						String maNV = txtMaNV.getText();
						float thue = 10;
						KhachHang kh = new KhachHang(maKH);
						HoaDon hd = new HoaDon(maHoaDon, kh, nhanVien,
								Date.valueOf(DATE_FORMAT_SQL.format(dataChooserNgayLapHoaDon.getDate())), thue);
						HoaDon_Dao hd_dao = new HoaDon_Dao();
						hd_dao.themHoaDon(hd);
						int rowCount = tableDanhSachGioHang.getRowCount();
						ArrayList<ChiTietHoaDon> cthdlist = new ArrayList<>();
						for (int i = 0; i < rowCount; i++) {
							String maHD = tableDanhSachGioHang.getValueAt(i, 0).toString();
							String maLk = tableDanhSachGioHang.getValueAt(i, 1).toString();
							int soLuong = (int) tableDanhSachGioHang.getValueAt(i, 4);
							double donGia = Double
									.parseDouble(tableDanhSachGioHang.getValueAt(i, 5).toString().replace(",", ""));
							HoaDon hd1 = new HoaDon(maHD);
							LinhKien lk1 = new LinhKien(maLk);
							ChiTietHoaDon cthd1 = new ChiTietHoaDon(hd1, lk1, soLuong, donGia);
							cthdlist.add(cthd1);
						}
						ChiTietHoaDon_Dao cthd_dao = new ChiTietHoaDon_Dao();
						for (ChiTietHoaDon ct : cthdlist) {
							cthd_dao.themChiTietHoaDon(ct);
							cthd_dao.updateSoLuongTon(ct);
						}
						DefaultTableModel dm = (DefaultTableModel) tableDanhSachLinhKien.getModel();
						int rowCount1 = dm.getRowCount();
						for (int i = rowCount1 - 1; i >= 0; i--) {
							dm.removeRow(i);
						}
						DocLinhKienTuDBVaoModel();
						JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công !!");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn !!");
						String maHoaDonHienTai = txtMaHoaDon.getText();
						HoaDon_Dao hd = new HoaDon_Dao();
						hd.xoaPhieuDatHang(maHoaDonHienTai);
						ex.printStackTrace();
					}
					XoaRong();
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn khi giỏ hàng bị trống!!");
				}
			}
		});

		txtSDT.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtSDT.getText().length() != 0) {
					KhachHang_dao kh = new KhachHang_dao();
					String soDienThoai = "";
					Pattern pattern = Pattern.compile("^\\d{10}$");
					Matcher matcher = pattern.matcher(txtSDT.getText());
					boolean valid = matcher.find();
					if (!txtSDT.getText().equals("") && valid) {
						soDienThoai = txtSDT.getText();
						KhachHang khachHang = khachHang_dao.getKhachHangBySDT(soDienThoai);
						if (khachHang == null) {
							txtMaKH.setText("");
							txtTenKhachHang.setText("");
							txtDiaChi.setText("");
							txtSDT.requestFocus();
							txtSDT.selectAll();
							JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại", "Error Message",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						txtMaKH.setText(khachHang.getMaKhachHang());
						txtTenKhachHang.setText(khachHang.getTenKhachHang());
						txtDiaChi.setText(khachHang.getDiaChi());

						return;
					}
					if (txtSDT.getText().length() != 0 && valid == false) {
						txtMaKH.setText("");
						txtTenKhachHang.setText("");
						txtDiaChi.setText("");
						txtSDT.requestFocus();
						return;
					}
				}

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (txtSDT.getText().length() != 0) {
					KhachHang_dao kh = new KhachHang_dao();
					String soDienThoai = "";
					Pattern pattern = Pattern.compile("^\\d{10}$");
					Matcher matcher = pattern.matcher(txtSDT.getText());
					boolean valid = matcher.find();
					if (!txtSDT.getText().equals("") && valid) {
						soDienThoai = txtSDT.getText();
						KhachHang khachHang = kh.getKhachHangBySDT(soDienThoai);
						if (khachHang == null) {
							txtMaKH.setText("");
							txtTenKhachHang.setText("");
							txtDiaChi.setText("");
							txtSDT.requestFocus();
							txtSDT.selectAll();
							JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại", "Error Message",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						txtMaKH.setText(khachHang.getMaKhachHang());
						txtTenKhachHang.setText(khachHang.getTenKhachHang());
						txtDiaChi.setText(khachHang.getDiaChi());
						return;
					}
					if (txtSDT.getText().length() != 0 && valid == false) {
						txtMaKH.setText("");
						txtTenKhachHang.setText("");
						txtDiaChi.setText("");
						txtSDT.requestFocus();
						return;
					}
				}

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	// set gio
	private void updateClock(DateTimeFormatter timeFormat) {
		LocalDateTime now = LocalDateTime.now();
		lblTime.setText(timeFormat.format(now));
	}

	// set ngay thang nam
	public void date() {
		DateTimeFormatter dates = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		lbldate.setText(dates.format(now));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void XoaRong() {
		modelDanhSachGioHang.setRowCount(0);
		txtSDT.setText("");
		txtMaKH.setText("");
		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtThue.setText("");
		txtTongTien.setText("");
		txtTienKhachTra.setText("");
		txtTienThoiLai.setText("");
		HoaDon_Dao hd_dao = new HoaDon_Dao();
		int currentLength = hd_dao.laySoHoaDon();
		String maHoaDonUpDate = prefixMaHoaDon + (++currentLength);
		txtMaHoaDon.setText(maHoaDonUpDate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private void DocLinhKienTuDBVaoModel() {
		// TODO Auto-generated method stub
		ArrayList<LinhKien> listLinhKien = linhKien_DAO.getAllLinhKien();
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		for (LinhKien linhKien : listLinhKien) {
			// String[] header = {"Mã","Tên LK","Thương Hiệu", "Loại, "SL","Đơn
			// Giá","Bảo Hành"};
			Object[] obj = new Object[] { linhKien.getMaLinhKien(), linhKien.getTenLinhKien(),
					linhKien.getThuongHieu().getTenThuongHieu(), linhKien.getLoaiLinhKien().getTenLoaiLinhKien(),
					linhKien.getSoLuong(), decimalFormat.format(linhKien.getGiaBan()), linhKien.getBaoHanh() };
			modelDanhSachLinhKien.addRow(obj);
		}

	}

	private void capNhatModelLinhKien() {
		modelDanhSachLinhKien.getDataVector().removeAllElements();
		DocLinhKienTuDBVaoModel();
		// TODO Auto-generated method stub

	}
}
