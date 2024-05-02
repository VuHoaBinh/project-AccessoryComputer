package ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.HoaDon_Dao;
import dao.ThongKe_Dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class JPanel_ThongKe extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableTopSanPhamBanChay;
	private JTable tableTopKhachHangTongTienCao;
	private JTable tableTopNhanVienCoDoanhThuCaoNhat;
	private DefaultTableModel modelTopSanPhamBanChay;
	private DefaultTableModel modelTopKhachHangTongTienCao;
	private DefaultTableModel modelTopNhanVienCoDoanhThuCaoNhat;
	private JComboBox comboBoxThang;
	private ButtonGroup btnGroup;
	private JCheckBox chckTatCa;
	private JCheckBox chckHomNay;
	private JCheckBox chckTheoThang;
	private JCheckBox chckTheoNam;
	private JComboBox comboBoxNam;
	private ThongKe_Dao dao;
	private HoaDon_Dao hoaDon_dao;
	private Map thongTinKhachHang;
	private JLabel lblDoanhThu;
	private JLabel lblSoHoaDon;

	/**
	 * Create the panel.
	 */
	public JPanel_ThongKe() {
		try {
			ConnectDB.getInstance().connect();
			dao = new ThongKe_Dao();
			hoaDon_dao = new HoaDon_Dao();
		} catch (Exception e) {
			System.out.println("Ket noi that bai");
		}
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
				"Th\u00F4\u0301ng K\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		panel.setBounds(0, 0, 1226, 656);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 187, 187));
		panel_1.setBounds(322, 11, 290, 137);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Doanh Thu");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 290, 73);
		String imgDT = "img/doanhthu.png";
        int setRongiconDT = 28; // Đặt chiều rộng mong muốn
        int setDaiiconDT = 22; // Đặt chiều cao mong muốn

        try {
            // Đọc hình ảnh từ tệp
            BufferedImage originalImage = ImageIO.read(new File(imgDT));

            // Thay đổi kích thước hình ảnh với giữ nguyên tỷ lệ khung hình
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (setDaiiconDT * aspectRatio);
            int scaledHeight = setDaiiconDT;

            if (scaledWidth > setRongiconDT) {
                scaledWidth = setRongiconDT;
                scaledHeight = (int) (setRongiconDT / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		panel_1.add(lblNewLabel);

		lblDoanhThu = new JLabel("");
		lblDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhThu.setBounds(0, 59, 290, 73);
		panel_1.add(lblDoanhThu);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(0, 187, 187));
		panel_1_1.setBounds(677, 11, 290, 137);
		panel.add(panel_1_1);

		JLabel lblSHoan = new JLabel("Số Hóa Đơn");
		lblSHoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblSHoan.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSHoan.setBounds(0, 0, 290, 73);
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
            lblSHoan.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
		panel_1_1.add(lblSHoan);

		lblSoHoaDon = new JLabel("");
		lblSoHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSoHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoHoaDon.setBounds(0, 58, 290, 73);
		panel_1_1.add(lblSoHoaDon);

		JPanel pnlTopSanPham = new JPanel();
		pnlTopSanPham.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Top sa\u0309n ph\u00E2\u0309m ba\u0301n cha\u0323y", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 85, 170)));
		pnlTopSanPham.setBounds(10, 321, 396, 305);
		panel.add(pnlTopSanPham);
		pnlTopSanPham.setLayout(null);

		String header[] = { "Top", "Mã LK", "Tên LK", "Số Lượng" };
		modelTopSanPhamBanChay = new DefaultTableModel(header, 0);
		tableTopSanPhamBanChay = new JTable(modelTopSanPhamBanChay);
		JScrollPane scrollPane = new JScrollPane(tableTopSanPhamBanChay);
		scrollPane.setBounds(10, 21, 376, 273);
		pnlTopSanPham.add(scrollPane);
		dao = new ThongKe_Dao();
		docDuLieuVaoTableLinhKien();

		JPanel pnlTopKhachHang = new JPanel();
		pnlTopKhachHang.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Top kha\u0301ch ha\u0300ng co\u0301 t\u00F4\u0309ng ti\u00EA\u0300n cao", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 85, 170)));
		pnlTopKhachHang.setBounds(416, 321, 396, 305);
		panel.add(pnlTopKhachHang);
		pnlTopKhachHang.setLayout(null);

		String header1[] = { "Top", "Mã KH", "Tên KH", "Tổng Tiền" };
		modelTopKhachHangTongTienCao = new DefaultTableModel(header1, 0);
		tableTopKhachHangTongTienCao = new JTable(modelTopKhachHangTongTienCao);
		JScrollPane scrollPane_1 = new JScrollPane(tableTopKhachHangTongTienCao);
		scrollPane_1.setBounds(10, 22, 376, 272);
		pnlTopKhachHang.add(scrollPane_1);
		docDuLieuVaoTableKhachHang();

		JPanel pnlTopNhanVien = new JPanel();
		pnlTopNhanVien.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Top nh\u00E2n vi\u00EAn co\u0301 doanh thu cao nh\u00E2\u0301t", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 85, 170)));
		pnlTopNhanVien.setBounds(820, 321, 396, 305);
		panel.add(pnlTopNhanVien);
		pnlTopNhanVien.setLayout(null);

		String header2[] = { "Top", "Mã NV", "Tên NV", "Doanh Thu" };
		modelTopNhanVienCoDoanhThuCaoNhat = new DefaultTableModel(header2, 0);
		tableTopNhanVienCoDoanhThuCaoNhat = new JTable(modelTopNhanVienCoDoanhThuCaoNhat);
		JScrollPane scrollPane_2 = new JScrollPane(tableTopNhanVienCoDoanhThuCaoNhat);
		scrollPane_2.setBounds(10, 22, 376, 272);
		pnlTopNhanVien.add(scrollPane_2);
		docDuLieuVaoTableNhanVien();

		btnGroup = new ButtonGroup();
		chckTatCa = new JCheckBox("Tất cả");
		chckTatCa.setSelected(true);
		chckTatCa.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					xoaDuLieutablesanpham();
					xoaDuLieutablekhachhang();
					xoaDuLieutablenhanvien();
					docDuLieuVaoTableNhanVien();
					docDuLieuVaoTableKhachHang();
					docDuLieuVaoTableLinhKien();
					tinhDoanhThu();
				}
			}
		});
		tinhDoanhThu();
		chckTatCa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		chckTatCa.setBounds(10, 181, 138, 23);
		btnGroup.add(chckTatCa);
		panel.add(chckTatCa);

		chckHomNay = new JCheckBox("Hôm nay");
		chckHomNay.setFont(new Font("Times New Roman", Font.BOLD, 15));
		chckHomNay.setBounds(10, 209, 138, 23);
		chckHomNay.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu được chọn, tính và hiển thị doanh thu hôm nay
		        	xoaDuLieutablesanpham();
					xoaDuLieutablekhachhang();
					xoaDuLieutablenhanvien();
		            hienThiSanPhamBanChayVaDoanhThuHomNay();
		            hienThiKhachHangvaDoanhThuHomNay();
		            hienThiNhanVienvaDoanhThuHomNay();
		        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
		            // Nếu không được chọn, có thể xử lý một cách khác nếu cần thiết
		        }
		    }
		});

		btnGroup.add(chckHomNay);
		panel.add(chckHomNay);


		chckTheoThang = new JCheckBox("Theo tháng");
		chckTheoThang.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu được chọn, tính và hiển thị doanh thu hôm nay
		        	xoaDuLieutablesanpham();
					xoaDuLieutablekhachhang();
					xoaDuLieutablenhanvien();
		            hienThiDoanhThuTheoThangCuaNhanVien();
		            hienThiDoanhThuTheoThangChoKhachHang();
		            hienThiSanPhamBanChayTheoThang();
		        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
		            // Nếu không được chọn, có thể xử lý một cách khác nếu cần thiết
		        }
		    }
		});
		chckTheoThang.setFont(new Font("Times New Roman", Font.BOLD, 15));
		chckTheoThang.setBounds(10, 241, 138, 23);
		btnGroup.add(chckTheoThang);
		panel.add(chckTheoThang);

		chckTheoNam = new JCheckBox("Theo năm");
		chckTheoNam.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu được chọn, tính và hiển thị doanh thu hôm nay
		        	xoaDuLieutablesanpham();
					xoaDuLieutablekhachhang();
					xoaDuLieutablenhanvien();
		            hienThiTopSanPhamVaDoanhThuTheoNam();
		            hienThiThongTinKhachHangVaDoanhThuTheoNam();
		            hienThiThongTinNhanVienVaDoanhThuTheoNam();
		        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
		            // Nếu không được chọn, có thể xử lý một cách khác nếu cần thiết
		        }
		    }
		});
		chckTheoNam.setFont(new Font("Times New Roman", Font.BOLD, 15));
		chckTheoNam.setBounds(10, 272, 138, 23);
		btnGroup.add(chckTheoNam);
		panel.add(chckTheoNam);

		String thang[] = { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
				"Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" };
		comboBoxThang = new JComboBox(thang);
		comboBoxThang.setBounds(170, 242, 132, 22);
		panel.add(comboBoxThang);

		String nam[] = {"2020","2021","2022","2023"};
		comboBoxNam = new JComboBox(nam);
		comboBoxNam.setBounds(170, 273, 132, 22);
		panel.add(comboBoxNam);

	}

	/**
	 * doc du lieu va xoa du lieu table linh kien , khach hang, nhan vien
	 */
	public void docDuLieuVaoTableLinhKien() {
	    ArrayList<ChiTietHoaDon> linhKien = dao.layDsChiTietHoaDon();
	    int i = 1;

	    // Sử dụng Map để lưu thông tin về số lượng bán của từng linh kiện
	    Map<String, Integer> soLuongBanCuaLinhKien = new HashMap<>();

	    for (ChiTietHoaDon ct : linhKien) {
	        String maLinhKien = ct.getLinhKien().getMaLinhKien();
	        int soLuong = ct.getSoLuong();

	        // Nếu mã linh kiện đã tồn tại trong Map, cộng thêm số lượng mới vào
	        soLuongBanCuaLinhKien.put(maLinhKien, soLuongBanCuaLinhKien.getOrDefault(maLinhKien, 0) + soLuong);
	    }

	    // Sắp xếp danh sách theo số lượng giảm dần
	    List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(soLuongBanCuaLinhKien.entrySet());
	    sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    // Hiển thị thông tin linh kiện vào bảng
	    DefaultTableModel modelTopSanPhamBanChay = (DefaultTableModel) tableTopSanPhamBanChay.getModel();
	    modelTopSanPhamBanChay.setRowCount(0);

	    for (Map.Entry<String, Integer> entry : sortedList) {
	        String maLinhKien = entry.getKey();
	        int tongSoLuong = entry.getValue();
	        LinhKien linhkien = dao.timLinhKienTheoMa(maLinhKien);
	        String tenLinhKien = linhkien.getTenLinhKien();

	        modelTopSanPhamBanChay.addRow(new Object[]{i++, maLinhKien, tenLinhKien, tongSoLuong});
	    }
	}


	public void xoaDuLieutablenhanvien() {
	    DefaultTableModel model = (DefaultTableModel) tableTopNhanVienCoDoanhThuCaoNhat.getModel();
	    while (model.getRowCount() > 0) {
	        model.removeRow(0);
	    }
	}
	public void xoaDuLieutablekhachhang() {
	    DefaultTableModel model = (DefaultTableModel) tableTopKhachHangTongTienCao.getModel();
	    while (model.getRowCount() > 0) {
	        model.removeRow(0);
	    }
	}
	public void xoaDuLieutablesanpham() {
	    DefaultTableModel model = (DefaultTableModel) tableTopSanPhamBanChay.getModel();
	    while (model.getRowCount() > 0) {
	        model.removeRow(0);
	    }
	}

	
	public void docDuLieuVaoTableKhachHang() {
	    ArrayList<HoaDon> khachhang = dao.layDsHoaDon();
	    int i = 1;
	    // Sử dụng Map để lưu trữ thông tin cho mỗi khách hàng
	    Map<String, Object[]> thongTinKhachHang = new HashMap<>();
	    DecimalFormat df = new DecimalFormat("###,###");

	    for (HoaDon hd : khachhang) {
	        String hoadon = hd.getMaHoaDon();
	        KhachHang makh = hd.getMaKhachHang();
	        KhachHang tekhachhang = dao.timKiemTheoMaKhachHang(makh);

	        // Assuming dao.timTheoMaHoaDon returns an ArrayList<ChiTietHoaDon>
	        List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(hoadon);

	        for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	            int soluong = cthd.getSoLuong();
	            Double donGia = cthd.getDonGia();
	            String dfDonGia = df.format(donGia);
	            double thanhTien = soluong * donGia;
	            String dfThanhTien = df.format(thanhTien);

	            if (thongTinKhachHang.containsKey(makh.getMaKhachHang())) {
	                // Nếu đã có, cập nhật thông tin
	                Object[] existingInfo = thongTinKhachHang.get(makh.getMaKhachHang());
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                // Cộng dồn số lượng và thành tiền
	                int tongSoLuong = soLuongCu + soluong;
	                double tongThanhTien = thanhTienCu + thanhTien;

	                // Cập nhật lại thông tin trong Map
	                thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{tongSoLuong, tongThanhTien, tekhachhang});
	            } else {
	                // Nếu chưa có, thêm mới thông tin vào Map
	                thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{soluong, thanhTien, tekhachhang});
	            }
	        }
	    }
		
		
		List<Map.Entry<String, Object[]>> sortedList = new ArrayList<>(thongTinKhachHang.entrySet());

		// Sử dụng Comparator để so sánh theo thành tiền giảm dần
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Object[]>>() {
			@Override
			public int compare(Map.Entry<String, Object[]> entry1, Map.Entry<String, Object[]> entry2) {
				double thanhTien1 = (double) entry1.getValue()[1];
				double thanhTien2 = (double) entry2.getValue()[1];
				// Sắp xếp giảm dần
				return Double.compare(thanhTien2, thanhTien1);
			}
		});
		// Đưa thông tin từ Map vào table
		for (Map.Entry<String, Object[]> entry : sortedList) {
			String maKhachHang = entry.getKey();
			Object[] info = entry.getValue();
			int tongSoLuong = (int) info[0];
			double tongThanhTien = (double) info[1];
			KhachHang tekhachhang = (KhachHang) info[2];

			modelTopKhachHangTongTienCao
					.addRow(new Object[] { i++, maKhachHang, tekhachhang.getTenKhachHang(), df.format(tongThanhTien) });
		}
	}

	
	
	public void docDuLieuVaoTableNhanVien() {
	    ArrayList<HoaDon> nhanvien = dao.layDsHoaDon();
	    int i = 1;
	    Map<String, Object[]> thongTinNhanVien = new HashMap<>();
	    DecimalFormat df = new DecimalFormat("###,###");
	    for (HoaDon hd : nhanvien) {
	        String hoadon = hd.getMaHoaDon();
	        NhanVien manv = hd.getMaNhanVien();
	        NhanVien tennv = dao.searchMaNhanVien(manv);
	        
	        // Assuming dao.timTheoMaHoaDon returns a List<ChiTietHoaDon>
	        List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(hoadon);

	        for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	            int soluong = cthd.getSoLuong();
	            Double donGia = cthd.getDonGia();
	            String dfDonGia = df.format(donGia);
	            double thanhTien = soluong * donGia;
	            String dfThanhTien = df.format(thanhTien);
	            if (thongTinNhanVien.containsKey(manv.getMaNhanVien())) {
	                // Nếu đã có, cập nhật thông tin
	                Object[] existingInfo = thongTinNhanVien.get(manv.getMaNhanVien());
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                // Cộng dồn số lượng và thành tiền
	                int tongSoLuong = soLuongCu + soluong;
	                double tongThanhTien = thanhTienCu + thanhTien;

	                // Cập nhật lại thông tin trong Map
	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{tongSoLuong, tongThanhTien, tennv});
	            } else {
	                // Nếu chưa có, thêm mới thông tin vào Map
	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{soluong, thanhTien, tennv});
	            }
	        }
	    }

	    List<Map.Entry<String, Object[]>> sortedList = new ArrayList<>(thongTinNhanVien.entrySet());

	    // Sử dụng Comparator để so sánh theo thành tiền giảm dần
	    Collections.sort(sortedList, new Comparator<Map.Entry<String, Object[]>>() {
	        @Override
	        public int compare(Map.Entry<String, Object[]> entry1, Map.Entry<String, Object[]> entry2) {
	            double thanhTien1 = (double) entry1.getValue()[1];
	            double thanhTien2 = (double) entry2.getValue()[1];
	            // Sắp xếp giảm dần
	            return Double.compare(thanhTien2, thanhTien1);
	        }
	    });
	    
	    DefaultTableModel model = (DefaultTableModel) modelTopNhanVienCoDoanhThuCaoNhat;
	    model.setRowCount(0);

	    for (Map.Entry<String, Object[]> entry : sortedList) {
	        String maNhanVien = entry.getKey();
	        Object[] info = entry.getValue();
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        NhanVien tennv = (NhanVien) info[2];

	        model.addRow(new Object[]{i++, maNhanVien, tennv.getTenNhanVien(), df.format(tongThanhTien)});
	    }
	}


	/**
	 * tinh doanh thu tat ca 
	 */
	
	public void tinhDoanhThu() {
	    ArrayList<ChiTietHoaDon> doanhthu = dao.layDsChiTietHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tong = 0;
	    Set<String> maHoaDonSet = new HashSet<>();

	    for (ChiTietHoaDon cthd : doanhthu) {
	        String maHoaDon = cthd.getHoaDon().getMaHoaDon();
	        int soluong = cthd.getSoLuong();
	        Double donGia = cthd.getDonGia();
	        String dfDonGia = df.format(donGia);
	        double thanhTien = soluong * donGia;
	        String dfThanhTien = df.format(thanhTien);
	        tong += thanhTien;

	        // Thêm mã hóa đơn vào Set nếu chưa tồn tại
	        maHoaDonSet.add(maHoaDon);
	    }

	    // Sử dụng kích thước của Set để lấy số lượng hóa đơn
	    int soLuongHoaDon = maHoaDonSet.size();

	    lblDoanhThu.setText(df.format(tong));
	    lblSoHoaDon.setText(String.valueOf(soLuongHoaDon));
	}
	
	
	/**
	 * tính doanh thu và hiển thị table sản phẩm , nhân viên , khách hàng trong hôm nay
	 */
	public void hienThiSanPhamBanChayVaDoanhThuHomNay() {
	    // Lấy danh sách hóa đơn trong ngày hôm nay
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");

	    // Dùng Map để lưu thông tin về số lượng bán của từng sản phẩm
	    Map<String, Integer> soLuongBanChay = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        // Tính doanh thu trong ngày hôm nay
	        if (ngayLap.equals(LocalDate.now())) {
	            String maHoaDon = hd.getMaHoaDon();

	            // Lấy danh sách chi tiết hóa đơn của hóa đơn hiện tại
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(maHoaDon);

	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                String maLinhKien = cthd.getLinhKien().getMaLinhKien();

	                // Cập nhật số lượng bán của từng sản phẩm
	                soLuongBanChay.put(maLinhKien, soLuongBanChay.getOrDefault(maLinhKien, 0) + cthd.getSoLuong());
	            }
	        }
	    }

	    // Hiển thị sản phẩm bán chạy vào bảng
	    List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(soLuongBanChay.entrySet());
	    sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    modelTopSanPhamBanChay.setRowCount(0);
	    int i = 1;
	    for (Map.Entry<String, Integer> entry : sortedList) {
	        String maLinhKien = entry.getKey();
	        int soLuongBan = entry.getValue();
	        LinhKien linhkien = dao.timLinhKienTheoMa(maLinhKien);

	        modelTopSanPhamBanChay.addRow(new Object[]{i++, maLinhKien, linhkien.getTenLinhKien(), soLuongBan});
	    }
	}




	public void hienThiKhachHangvaDoanhThuHomNay() {
	    // Lấy danh sách hóa đơn trong ngày hôm nay
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tongDoanhThuHomNay = 0;
	    int soLuongHoaDonHomNay = 0;

	    // Lấy ngày hôm nay
	    LocalDate homNay = LocalDate.now();

	    // Sử dụng Map để lưu trữ thông tin cho mỗi khách hàng
	    Map<String, Object[]> thongTinKhachHang = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        if (ngayLap.equals(homNay)) {
	            String maHoaDon = hd.getMaHoaDon();
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(maHoaDon);

	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                int soluong = cthd.getSoLuong();
	                double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;
	                tongDoanhThuHomNay += thanhTien;

	                // Tăng biến đếm khi gặp một hóa đơn trong ngày hôm nay
	                soLuongHoaDonHomNay++;

	                // Lấy thông tin khách hàng
	                KhachHang makh = hd.getMaKhachHang();
	                KhachHang tekhachhang = dao.timKiemTheoMaKhachHang(makh);

	                if (thongTinKhachHang.containsKey(makh.getMaKhachHang())) {
	                    // Nếu đã có, cập nhật thông tin
	                    Object[] existingInfo = thongTinKhachHang.get(makh.getMaKhachHang());
	                    int soLuongCu = (int) existingInfo[0];
	                    double thanhTienCu = (double) existingInfo[1];

	                    // Cộng dồn số lượng và thành tiền
	                    int tongSoLuong = soLuongCu + soluong;
	                    double tongThanhTien = thanhTienCu + thanhTien;

	                    // Cập nhật lại thông tin trong Map
	                    thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{tongSoLuong, tongThanhTien, tekhachhang});
	                } else {
	                    // Nếu chưa có, thêm mới thông tin vào Map
	                    thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{soluong, thanhTien, tekhachhang});
	                }
	            }
	        }
	    }

	    DefaultTableModel model = (DefaultTableModel) modelTopKhachHangTongTienCao;
	    model.setRowCount(0);

	    // Sắp xếp thông tin từ Map theo tổng tiền giảm dần
	    List<Object[]> sortedList = new ArrayList<>(thongTinKhachHang.values());
	    sortedList.sort((o1, o2) -> {
	        double thanhTien1 = (double) o1[1];
	        double thanhTien2 = (double) o2[1];
	        return Double.compare(thanhTien2, thanhTien1);
	    });

	    // Hiển thị thông tin từ Map vào table
	    int i = 1;
	    for (Object[] info : sortedList) {
	        String maKhachHang = ((KhachHang) info[2]).getMaKhachHang(); // Customer code
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        KhachHang tekhachhang = (KhachHang) info[2];

	        model.addRow(new Object[]{i++, maKhachHang, tekhachhang.getTenKhachHang(), df.format(tongThanhTien)});
	    }

	    // Tổng doanh thu từ table model
	    double tongDoanhThuTuTableModel = 0;
	    for (int row = 0; row < modelTopKhachHangTongTienCao.getRowCount(); row++) {
	        double thanhTien = Double.parseDouble(modelTopKhachHangTongTienCao.getValueAt(row, 3).toString().replace(",", ""));
	        tongDoanhThuTuTableModel += thanhTien;
	    }
	    lblDoanhThu.setText(df.format(tongDoanhThuTuTableModel));
	} 




	public void hienThiNhanVienvaDoanhThuHomNay() {
	    // Lấy danh sách hóa đơn trong ngày hôm nay
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tongDoanhThuHomNay = 0;
	    int soLuongHoaDonHomNay = 0;

	    // Lấy ngày hôm nay
	    LocalDate homNay = LocalDate.now();

	    // Sử dụng Map để lưu trữ thông tin cho mỗi nhân viên
	    Map<String, Object[]> thongTinNhanVien = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();
	        if (ngayLap.equals(homNay)) {
	            String maHoaDon = hd.getMaHoaDon();

	            // Lấy danh sách chi tiết hóa đơn của hóa đơn hiện tại
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(maHoaDon);

	            double tongDoanhThuHoaDon = 0;

	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                int soluong = cthd.getSoLuong();
	                Double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;
	                tongDoanhThuHoaDon += thanhTien;
	            }

	            // Tăng biến đếm khi gặp một hóa đơn trong ngày hôm nay
	            soLuongHoaDonHomNay++;

	            // Lấy thông tin nhân viên
	            NhanVien manv = hd.getMaNhanVien();
	            NhanVien tennv = dao.searchMaNhanVien(manv);

	            if (thongTinNhanVien.containsKey(manv.getMaNhanVien())) {
	                // Nếu đã có, cập nhật thông tin
	                Object[] existingInfo = thongTinNhanVien.get(manv.getMaNhanVien());
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                // Cộng dồn số lượng và thành tiền
	                int tongSoLuong = soLuongCu + chiTietHoaDonList.size();
	                double tongThanhTien = thanhTienCu + tongDoanhThuHoaDon;

	                // Cập nhật lại thông tin trong Map
	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{tongSoLuong, tongThanhTien, tennv});
	            } else {
	                // Nếu chưa có, thêm mới thông tin vào Map
	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{chiTietHoaDonList.size(), tongDoanhThuHoaDon, tennv});
	            }

	            // Accumulate total revenue for today
	            tongDoanhThuHomNay += tongDoanhThuHoaDon;
	        }
	    }

	    // Xóa dữ liệu hiện tại trong table
	    DefaultTableModel model = (DefaultTableModel) modelTopNhanVienCoDoanhThuCaoNhat;
	    model.setRowCount(0);

	    // Sắp xếp thông tin từ Map theo tổng tiền giảm dần
	    List<Object[]> sortedList = new ArrayList<>(thongTinNhanVien.values());
	    sortedList.sort((o1, o2) -> {
	        double thanhTien1 = (double) o1[1];
	        double thanhTien2 = (double) o2[1];
	        return Double.compare(thanhTien2, thanhTien1);
	    });

	    // Hiển thị thông tin từ sortedList vào table
	    int i = 1;
	    for (Object[] info : sortedList) {
	        String maNhanVien = ((NhanVien) info[2]).getMaNhanVien(); // Employee code
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        NhanVien tennv = (NhanVien) info[2];

	        model.addRow(new Object[]{i++, maNhanVien, tennv.getTenNhanVien(), df.format(tongThanhTien)});
	    }
	    
	    lblSoHoaDon.setText(String.valueOf(soLuongHoaDonHomNay));

	}




	/**
	 * tinh doanh thu theo thang va hien thi table san pham ban trong thang
	 */
	public void hienThiSanPhamBanChayTheoThang() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tong = 0;
	    int soLuongHoaDonTrongThang = 0;

	    int thangChon = comboBoxThang.getSelectedIndex() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1
	    int namHienTai = Year.now().getValue();

	    // Dùng Map để lưu thông tin về số lượng bán của từng sản phẩm
	    Map<String, Integer> soLuongBanChay = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        if (ngayLap.getMonthValue() == thangChon && ngayLap.getYear() == namHienTai) {
	            String maHoaDon = hd.getMaHoaDon();
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(maHoaDon);

	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                String maLinhKien = cthd.getLinhKien().getMaLinhKien();

	                // Cập nhật số lượng bán của sản phẩm
	                soLuongBanChay.put(maLinhKien, soLuongBanChay.getOrDefault(maLinhKien, 0) + cthd.getSoLuong());

	                // Tính tổng doanh thu và số lượng hóa đơn trong tháng
	                int soluong = cthd.getSoLuong();
	                Double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;
	                tong += thanhTien;
	                soLuongHoaDonTrongThang++;
	            }
	        }
	    }
	    
	    // Sắp xếp sản phẩm theo số lượng bán giảm dần
	    List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(soLuongBanChay.entrySet());
	    sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    // Đưa thông tin vào bảng
	    modelTopSanPhamBanChay.setRowCount(0);

	    int i = 1;
	    for (Map.Entry<String, Integer> entry : sortedList) {
	        String maLinhKien = entry.getKey();
	        int soLuongBan = entry.getValue();
	        LinhKien linhkien = dao.timLinhKienTheoMa(maLinhKien);

	        modelTopSanPhamBanChay.addRow(new Object[]{i++, maLinhKien, linhkien.getTenLinhKien(), soLuongBan});
	    }
	}


	/**
	 * tinh doanh thu theo thang va hien thi table khach hang co tong tien cao nhat trong thang
	 */
	public void hienThiDoanhThuTheoThangChoKhachHang() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tong = 0;
	    int soLuongHoaDonTrongThang = 0;

	    int thangChon = comboBoxThang.getSelectedIndex() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1
	    int namHienTai = Year.now().getValue();

	    Map<String, Object[]> thongTinKhachHang = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        if (ngayLap.getMonthValue() == thangChon && ngayLap.getYear() == namHienTai) {
	            String ma = hd.getMaHoaDon();
	            KhachHang makh = hd.getMaKhachHang();
	            KhachHang tekhachhang = dao.timKiemTheoMaKhachHang(makh);

	            // Reset the total for each new customer
	            double tongHoaDon = 0;

	            // Retrieve the list of ChiTietHoaDon for the current invoice
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(ma);

	            // Loop through the list of ChiTietHoaDon
	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                int soluong = cthd.getSoLuong();
	                Double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;

	                // Accumulate the total for each item in the invoice
	                tongHoaDon += thanhTien;
	                tong += thanhTien;
	            }

	            if (thongTinKhachHang.containsKey(makh.getMaKhachHang())) {
	                Object[] existingInfo = thongTinKhachHang.get(makh.getMaKhachHang());
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                int tongSoLuong = soLuongCu + 1; // Increment by 1 for each new invoice
	                double tongThanhTien = thanhTienCu + tongHoaDon;

	                thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{tongSoLuong, tongThanhTien, tekhachhang});
	            } else {
	                thongTinKhachHang.put(makh.getMaKhachHang(), new Object[]{1, tongHoaDon, tekhachhang}); // Start with 1 for a new customer
	            }

	            soLuongHoaDonTrongThang++;
	        }
	    }

	    List<Map.Entry<String, Object[]>> sortedList = new ArrayList<>(thongTinKhachHang.entrySet());

	    Collections.sort(sortedList, new Comparator<Map.Entry<String, Object[]>>() {
	        @Override
	        public int compare(Map.Entry<String, Object[]> entry1, Map.Entry<String, Object[]> entry2) {
	            double thanhTien1 = (double) entry1.getValue()[1];
	            double thanhTien2 = (double) entry2.getValue()[1];
	            return Double.compare(thanhTien2, thanhTien1);
	        }
	    });

	    modelTopKhachHangTongTienCao.setRowCount(0);

	    int i = 1;
	    for (Map.Entry<String, Object[]> entry : sortedList) {
	        String maKhachHang = entry.getKey();
	        Object[] info = entry.getValue();
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        KhachHang tekhachhang = (KhachHang) info[2];

	        modelTopKhachHangTongTienCao.addRow(new Object[]{i++, maKhachHang, tekhachhang.getTenKhachHang(), df.format(tongThanhTien)});
	    }

	    lblDoanhThu.setText(df.format(tong));
	    lblSoHoaDon.setText(df.format(soLuongHoaDonTrongThang));
	}



	/**
	 * tinh doanh thu theo thang va  hien thi trong table nhan vien co doanh thu cao nhat trong thang
	 * 
	 */
	public void hienThiDoanhThuTheoThangCuaNhanVien() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    int soLuongHoaDonTrongThang = 0;
	    double tong = 0;
	    int thangChon = comboBoxThang.getSelectedIndex() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1
	    int namHienTai = Year.now().getValue();

	    Map<String, Object[]> thongTinNhanVien = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        if (ngayLap.getMonthValue() == thangChon && ngayLap.getYear() == namHienTai) {
	            String ma = hd.getMaHoaDon();
	            NhanVien manv = hd.getMaNhanVien();
	            NhanVien tennv = dao.searchMaNhanVien(manv);

	            // Reset the total revenue for each new employee
	            double tongHoaDon = 0;

	            // Retrieve the list of ChiTietHoaDon for the current invoice
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(ma);

	            // Loop through the list of ChiTietHoaDon
	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                int soluong = cthd.getSoLuong();
	                Double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;

	                // Accumulate the total for each item in the invoice
	                tongHoaDon += thanhTien;
	            }

	            // Accumulate the total revenue for the current invoice
	            tong += tongHoaDon;

	            if (thongTinNhanVien.containsKey(manv.getMaNhanVien())) {
	                Object[] existingInfo = thongTinNhanVien.get(manv.getMaNhanVien());
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                int tongSoLuong = soLuongCu + chiTietHoaDonList.size();
	                double tongThanhTien = thanhTienCu + tongHoaDon;

	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{tongSoLuong, tongThanhTien, tennv});
	            } else {
	                thongTinNhanVien.put(manv.getMaNhanVien(), new Object[]{chiTietHoaDonList.size(), tongHoaDon, tennv});
	            }

	            // Increase the count when encountering an invoice in the selected month and year
	            soLuongHoaDonTrongThang += chiTietHoaDonList.size();
	        }
	    }

	    List<Map.Entry<String, Object[]>> sortedList = new ArrayList<>(thongTinNhanVien.entrySet());

	    Collections.sort(sortedList, new Comparator<Map.Entry<String, Object[]>>() {
	        @Override
	        public int compare(Map.Entry<String, Object[]> entry1, Map.Entry<String, Object[]> entry2) {
	            double thanhTien1 = (double) entry1.getValue()[1];
	            double thanhTien2 = (double) entry2.getValue()[1];
	            return Double.compare(thanhTien2, thanhTien1);
	        }
	    });

	    modelTopNhanVienCoDoanhThuCaoNhat.setRowCount(0);

	    int i = 1;
	    for (Map.Entry<String, Object[]> entry : sortedList) {
	        String maNhanVien = entry.getKey();
	        Object[] info = entry.getValue();
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        NhanVien tennv = (NhanVien) info[2];

	        modelTopNhanVienCoDoanhThuCaoNhat.addRow(new Object[]{i++, maNhanVien, tennv.getTenNhanVien(), df.format(tongThanhTien)});
	    }
	}



	
	
	
	/**
	 * tính doanh thu và hiển thị table sản phẩm , nhân viên , khách hàng trong năm
	 */
	public void hienThiTopSanPhamVaDoanhThuTheoNam() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tongDoanhThuTrongNam = 0;
	    int soLuongHoaDonTrongNam = 0;

	    int namChon = Integer.parseInt((String) comboBoxNam.getSelectedItem());

	    // Dùng Map để lưu thông tin về số lượng bán của từng sản phẩm
	    Map<String, Integer> soLuongBanChay = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        // Tính doanh thu trong năm được chọn
	        if (ngayLap.getYear() == namChon) {
	            String maHoaDon = hd.getMaHoaDon();
	            ChiTietHoaDon cthd = dao.timTheoMaHoaDon(maHoaDon);
	            String maLinhKien = cthd.getLinhKien().getMaLinhKien();

	            // Cập nhật số lượng bán của từng sản phẩm
	            soLuongBanChay.put(maLinhKien, soLuongBanChay.getOrDefault(maLinhKien, 0) + cthd.getSoLuong());

	            int soluong = cthd.getSoLuong();
	            Double donGia = cthd.getDonGia();
	            double thanhTien = soluong * donGia;
	            tongDoanhThuTrongNam += thanhTien;

	            // Tăng biến đếm khi gặp một hóa đơn trong năm được chọn
	            soLuongHoaDonTrongNam++;
	        }
	    }

	    // Hiển thị sản phẩm bán chạy vào bảng
	    List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(soLuongBanChay.entrySet());
	    sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    DefaultTableModel modelTopSanPhamBanChay = (DefaultTableModel) tableTopSanPhamBanChay.getModel();
	    modelTopSanPhamBanChay.setRowCount(0);

	    int i = 1;
	    for (Map.Entry<String, Integer> entry : sortedList) {
	        String maLinhKien = entry.getKey();
	        int soLuongBan = entry.getValue();
	        LinhKien linhkien = dao.timLinhKienTheoMa(maLinhKien);

	        modelTopSanPhamBanChay.addRow(new Object[]{i++, maLinhKien, linhkien.getTenLinhKien(), soLuongBan});
	    }
	}


	public void hienThiThongTinKhachHangVaDoanhThuTheoNam() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tongDoanhThuTrongNam = 0;
	    int soLuongHoaDonTrongNam = 0;

	    int namChon = Integer.parseInt((String) comboBoxNam.getSelectedItem());

	    // Sử dụng List để lưu thông tin cho mỗi khách hàng và sắp xếp giảm dần theo tổng tiền
	    List<Object[]> thongTinKhachHangList = new ArrayList<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        // Tính doanh thu trong năm được chọn
	        if (ngayLap.getYear() == namChon) {
	            String maHoaDon = hd.getMaHoaDon();
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(maHoaDon);
	            String maKhachHang = hd.getMaKhachHang().getMaKhachHang();
	            KhachHang teKhachHang = dao.timKiemTheoMaKhachHang(hd.getMaKhachHang());

	            double tongHoaDon = 0;

	            // Loop through the list of ChiTietHoaDon
	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                int soluong = cthd.getSoLuong();
	                Double donGia = cthd.getDonGia();
	                double thanhTien = soluong * donGia;

	                // Accumulate the total for each item in the invoice
	                tongHoaDon += thanhTien;
	                tongDoanhThuTrongNam += thanhTien;
	            }

	            // Tăng biến đếm khi gặp một hóa đơn trong năm được chọn
	            soLuongHoaDonTrongNam++;

	            boolean found = false;

	            // Tìm kiếm trong danh sách đã có thông tin của khách hàng hay chưa
	            for (Object[] info : thongTinKhachHangList) {
	                String existingMaKhachHang = (String) info[0];

	                if (existingMaKhachHang.equals(maKhachHang)) {
	                    // Nếu đã có, cập nhật thông tin
	                    int soLuongCu = (int) info[1];
	                    double thanhTienCu = (double) info[2];

	                    // Cộng dồn số lượng và thành tiền
	                    int tongSoLuong = soLuongCu + chiTietHoaDonList.size();
	                    double tongThanhTien = thanhTienCu + tongHoaDon;

	                    // Cập nhật lại thông tin trong danh sách
	                    info[1] = tongSoLuong;
	                    info[2] = tongThanhTien;

	                    found = true;
	                    break;
	                }
	            }

	            if (!found) {
	                // Nếu chưa có, thêm mới thông tin vào danh sách
	                thongTinKhachHangList.add(new Object[]{maKhachHang, chiTietHoaDonList.size(), tongHoaDon, teKhachHang});
	            }
	        }
	    }

	    // Sắp xếp danh sách giảm dần theo tổng tiền
	    thongTinKhachHangList.sort((o1, o2) -> {
	        double thanhTien1 = (double) o1[2];
	        double thanhTien2 = (double) o2[2];
	        return Double.compare(thanhTien2, thanhTien1);
	    });

	    // Hiển thị thông tin khách hàng vào bảng
	    int i = 1;
	    DefaultTableModel modelTopKhachHangTongTienCao = (DefaultTableModel) tableTopKhachHangTongTienCao.getModel();
	    modelTopKhachHangTongTienCao.setRowCount(0);
	    for (Object[] info : thongTinKhachHangList) {
	        String maKhachHang = (String) info[0];
	        int tongSoLuong = (int) info[1];
	        double tongThanhTien = (double) info[2];
	        KhachHang teKhachHang = (KhachHang) info[3];

	        modelTopKhachHangTongTienCao.addRow(new Object[]{i++, maKhachHang, teKhachHang.getTenKhachHang(), df.format(tongThanhTien)});
	    }

	    // Hiển thị doanh thu trong năm được chọn
	    lblDoanhThu.setText(df.format(tongDoanhThuTrongNam));
	    lblSoHoaDon.setText(String.valueOf(soLuongHoaDonTrongNam));
	}



	public void hienThiThongTinNhanVienVaDoanhThuTheoNam() {
	    ArrayList<HoaDon> doanhthu = dao.layDsHoaDon();
	    DecimalFormat df = new DecimalFormat("###,###");
	    double tongDoanhThuTrongNam = 0;
	    int soLuongHoaDonTrongNam = 0;

	    int namChon = Integer.parseInt((String) comboBoxNam.getSelectedItem());

	    // Sử dụng Map để lưu thông tin cho mỗi nhân viên
	    Map<String, Object[]> thongTinNhanVien = new HashMap<>();

	    for (HoaDon hd : doanhthu) {
	        // Lấy ngày lập hóa đơn
	        LocalDate ngayLap = hd.getNgayLap().toLocalDate();

	        // Tính doanh thu trong năm được chọn
	        if (ngayLap.getYear() == namChon) {
	            String maNhanVien = hd.getMaNhanVien().getMaNhanVien();
	            NhanVien teNhanVien = dao.searchMaNhanVien(hd.getMaNhanVien());

	            int soluong = 0;
	            double thanhTien = 0;

	            // Retrieve the list of ChiTietHoaDon for the current invoice
	            List<ChiTietHoaDon> chiTietHoaDonList = dao.layDsChiTietHoaDonTheoMaHoaDon(hd.getMaHoaDon());

	            // Loop through the list of ChiTietHoaDon
	            for (ChiTietHoaDon cthd : chiTietHoaDonList) {
	                soluong += cthd.getSoLuong();
	                thanhTien += cthd.getSoLuong() * cthd.getDonGia();
	            }

	            // Tăng biến đếm khi gặp một hóa đơn trong năm được chọn
	            soLuongHoaDonTrongNam++;

	            if (thongTinNhanVien.containsKey(maNhanVien)) {
	                // Nếu đã có, cập nhật thông tin
	                Object[] existingInfo = thongTinNhanVien.get(maNhanVien);
	                int soLuongCu = (int) existingInfo[0];
	                double thanhTienCu = (double) existingInfo[1];

	                // Cộng dồn số lượng và thành tiền
	                int tongSoLuong = soLuongCu + soluong;
	                double tongThanhTien = thanhTienCu + thanhTien;

	                // Cập nhật lại thông tin trong Map
	                thongTinNhanVien.put(maNhanVien, new Object[] { tongSoLuong, tongThanhTien, teNhanVien });
	            } else {
	                // Nếu chưa có, thêm mới thông tin vào Map
	                thongTinNhanVien.put(maNhanVien, new Object[] { soluong, thanhTien, teNhanVien });
	            }

	            tongDoanhThuTrongNam += thanhTien;
	        }
	    }

	    // Hiển thị thông tin nhân viên vào bảng
	    int i = 1;
	    DefaultTableModel modelTopNhanVienCoDoanhThuCaoNhat = (DefaultTableModel) tableTopNhanVienCoDoanhThuCaoNhat.getModel();
	    modelTopNhanVienCoDoanhThuCaoNhat.setRowCount(0);
	    List<Map.Entry<String, Object[]>> sortedList = new ArrayList<>(thongTinNhanVien.entrySet());

	    // Sử dụng Comparator để so sánh theo tổng tiền giảm dần
	    Collections.sort(sortedList, new Comparator<Map.Entry<String, Object[]>>() {
	        @Override
	        public int compare(Map.Entry<String, Object[]> entry1, Map.Entry<String, Object[]> entry2) {
	            double thanhTien1 = (double) entry1.getValue()[1];
	            double thanhTien2 = (double) entry2.getValue()[1];
	            // Sắp xếp giảm dần
	            return Double.compare(thanhTien2, thanhTien1);
	        }
	    });

	    for (Map.Entry<String, Object[]> entry : sortedList) {
	        String maNhanVien = entry.getKey();
	        Object[] info = entry.getValue();
	        int tongSoLuong = (int) info[0];
	        double tongThanhTien = (double) info[1];
	        NhanVien teNhanVien = (NhanVien) info[2];
	        modelTopNhanVienCoDoanhThuCaoNhat.addRow(new Object[]{i++, maNhanVien, teNhanVien.getTenNhanVien(), df.format(tongThanhTien)});
	    }
	}

}
