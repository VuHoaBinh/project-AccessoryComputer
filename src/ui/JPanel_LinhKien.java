package ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.LinhKien_Dao;
import dao.LoaiLinhKien_DAO;
import dao.ThuongHieu_DAO;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.ThuongHieu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

public class JPanel_LinhKien extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaLinhKien;
	private JTextField txtSoLuong;
	private JTextField txtMoTa;
	private JTextField txtTenLinhKien;
	private JTextField txtGiaBan;
	private JTextField txtBaoHanh;
	private JTextField txtNgayNhap;
	private JTable tableLinhKien;
	private Frm_ChiTietLinhKien chiTietLinhKien;
	private JDateChooser dataChooserNgayNhap;
	private DefaultTableModel modelQuanLyLinhKien;
	private JTable tableQuanLyLinhKien;
	private JTextField txtTimTheoMa;
	private LinhKien_Dao linhKien_DAO;
	private JTextField txtGiaNhap;
	private JComboBox cBoBoxLoai;
	private JComboBox cBoBoxThuongHieu;
	private LoaiLinhKien_DAO loaiLinhKien_DAO;
	private ThuongHieu_DAO thuongHieu_DAO;

	/**
	 * Create the panel.
	 */
	public JPanel_LinhKien() {
		try {
			ConnectDB.getInstance().connect();
			
			System.out.println("Ket noi thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ket noi that bai");
		}
		linhKien_DAO = new LinhKien_Dao();
		loaiLinhKien_DAO = new LoaiLinhKien_DAO();
		thuongHieu_DAO = new ThuongHieu_DAO();
		// chiTietLinhKien = new Frm_ChiTietLinhKien(null);
		setLayout(null);
		// chiTietLinhKien.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Qua\u0309n ly\u0301 linh ki\u00EA\u0323n", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 85, 170)));
		panel.setBounds(0, 0, 1226, 656);
		add(panel);
		panel.setLayout(null);

		JLabel lblMaLinhKien = new JLabel("Mã Linh Kiện:");
		lblMaLinhKien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMaLinhKien.setBounds(33, 22, 121, 23);
		panel.add(lblMaLinhKien);

		txtMaLinhKien = new JTextField();
		txtMaLinhKien.setEditable(false);
		txtMaLinhKien.setBounds(171, 23, 366, 23);
		panel.add(txtMaLinhKien);
		txtMaLinhKien.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSoLuong.setBounds(33, 105, 121, 23);
		panel.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(171, 106, 366, 23);
		panel.add(txtSoLuong);

		JLabel lblMoTa = new JLabel("Mô Tả:");
		lblMoTa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMoTa.setBounds(33, 146, 121, 23);
		panel.add(lblMoTa);

		txtMoTa = new JTextField();
		txtMoTa.setBounds(171, 147, 366, 23);
		panel.add(txtMoTa);
		txtMoTa.setColumns(10);

		JLabel lblThuongHieu = new JLabel("Thương Hiệu:");
		lblThuongHieu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThuongHieu.setBounds(33, 187, 121, 23);
		panel.add(lblThuongHieu);

		JLabel lblTimTheoMa = new JLabel("Tìm Theo Mã:");
		lblTimTheoMa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTimTheoMa.setBounds(33, 229, 121, 23);
		panel.add(lblTimTheoMa);

		JLabel lblTenLinhKien = new JLabel("Tên Linh Kiện:");
		lblTenLinhKien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTenLinhKien.setBounds(684, 22, 121, 23);
		panel.add(lblTenLinhKien);

		JLabel lblGiaBan = new JLabel("Giá Bán:");
		lblGiaBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGiaBan.setBounds(684, 64, 121, 23);
		panel.add(lblGiaBan);

		JLabel lblNgayNhap = new JLabel("Ngày Nhập:");
		lblNgayNhap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgayNhap.setBounds(684, 188, 121, 23);
		panel.add(lblNgayNhap);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoai.setBounds(684, 105, 121, 23);
		panel.add(lblLoai);

		JLabel lblBaoHanh = new JLabel("Bảo Hành:");
		lblBaoHanh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBaoHanh.setBounds(684, 146, 121, 23);
		panel.add(lblBaoHanh);

		txtTenLinhKien = new JTextField();
		txtTenLinhKien.setColumns(10);
		txtTenLinhKien.setBounds(829, 22, 366, 23);
		panel.add(txtTenLinhKien);

		txtGiaBan = new JTextField();
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(829, 66, 366, 23);
		panel.add(txtGiaBan);

		txtBaoHanh = new JTextField();
		txtBaoHanh.setColumns(10);
		txtBaoHanh.setBounds(829, 148, 366, 23);
		panel.add(txtBaoHanh);

		dataChooserNgayNhap = new JDateChooser();
		dataChooserNgayNhap.setDateFormatString("yyyy-M-d");
		dataChooserNgayNhap.setBounds(829, 188, 366, 23);
		panel.add(dataChooserNgayNhap);

		JPanel pnl = new JPanel();
		pnl.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)),
						"Danh sa\u0301ch linh ki\u00EA\u0323n", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 255)));
		pnl.setBounds(10, 265, 1207, 283);
		panel.add(pnl);
		pnl.setLayout(null);

		String[] header = { "Mã LK", "Tên LK", "Mô Tả", "Giá Nhập", "Giá Bán", "Số Lượng", "Loại", "Thương Hiệu",
				"Ngày Nhập", "Bảo Hành" };
		modelQuanLyLinhKien = new DefaultTableModel(header, 0){
			 @Override
            public boolean isCellEditable(int row, int column) {
                
				 return false;
            }
		};
		tableQuanLyLinhKien = new JTable(modelQuanLyLinhKien);
		JScrollPane scrollPane = new JScrollPane(tableQuanLyLinhKien);

		// đọc dữ liệu lk từ db vào model
		DocLinhKienTuDBVaoModel();

		tableQuanLyLinhKien.addMouseListener(this);
		scrollPane.setBounds(10, 20, 1187, 252);
		pnl.add(scrollPane);

		JButton btnXemChiTiet = new FixButton("Xem Chi Tiết","img/XemChiTiet.png",28,22);
		btnXemChiTiet.setForeground(new Color(0, 0, 0));
		btnXemChiTiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					LinhKien linhKien = taoLinhKienTuField();
					System.out.println(linhKien);
					chiTietLinhKien = new Frm_ChiTietLinhKien(linhKien);
					chiTietLinhKien.setLocationRelativeTo(null);
					chiTietLinhKien.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Vui lòng chọn linh kiện để xem chi tiết");
				}

			}
		});
		btnXemChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemChiTiet.setBounds(33, 600, 154, 45);
		panel.add(btnXemChiTiet);

		JButton btnThem = new FixButton("Thêm","img/Thêm.png",28,22);
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidLinhKien()) {
					LinhKien linhKien = taoLinhKienTuField();
					System.out.println(linhKien);
					if (linhKien_DAO.themLinhKien(linhKien) > 0) {
						capNhatModelLinhKien();
						JOptionPane.showMessageDialog(null, "Thêm linh kiện thành công");
						xoaRongField();
					} else
						JOptionPane.showMessageDialog(null, "Thêm linh kiện thất bại");
				}
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnThem.setBounds(310, 600, 121, 45);
		panel.add(btnThem);

		JButton btnXoaRong = new FixButton("Xóa Rỗng","img/XoaRong.png",28,22);
		btnXoaRong.setForeground(new Color(0, 0, 0));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongField();
			}

			
		});
		btnXoaRong.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXoaRong.setBounds(548, 600, 130, 45);
		panel.add(btnXoaRong);

		JButton btnSua = new FixButton("Sửa","img/Sua.png",28,22);
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidLinhKien()) {
					LinhKien linhKien = taoLinhKienTuField();
					if (linhKien_DAO.suaLinhKien(linhKien) > 0) {
						JOptionPane.showMessageDialog(null, "Sửa thành công");
						capNhatModelLinhKien();
					} else
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
				}
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSua.setBounds(811, 600, 121, 45);
		panel.add(btnSua);

		JButton btnXoa = new FixButton("Xóa","img/Xoa.png",28,22);
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableQuanLyLinhKien.getSelectedRow();
				String maLinhKien = modelQuanLyLinhKien.getValueAt(row, 0).toString();
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận xóa",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					// Người dùng chọn "Xóa"
					if (linhKien_DAO.xoaLinhKienTheoMa(maLinhKien) > 0) {
						System.out.println("Xóa linh kiện thành công");
						capNhatModelLinhKien();
					} else
						System.out.println("Xóa linh kiện thất bại");
				} else {
					// Người dùng chọn "Không Xóa"
					System.out.println("Xóa không được thực hiện");
				}
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXoa.setBounds(1057, 600, 121, 45);
		panel.add(btnXoa);

		JLabel lblNewLabel_3_1 = new JLabel("1/2");
		lblNewLabel_3_1.setBounds(589, 558, 23, 20);
		panel.add(lblNewLabel_3_1);

		cBoBoxLoai = new JComboBox();

		// thêm loại linh kiện từ db vô cbobox
		ArrayList<LoaiLinhKien> listLoaiLinhKien = loaiLinhKien_DAO.getAllLoaiLinhKien();
		for (LoaiLinhKien loaiLinhKien : listLoaiLinhKien) {
			cBoBoxLoai.addItem(loaiLinhKien.getTenLoaiLinhKien());
		}

		cBoBoxLoai.setBounds(829, 106, 366, 23);
		panel.add(cBoBoxLoai);

		cBoBoxThuongHieu = new JComboBox();
		// thêm thương hiệu từ db vô cbobox
		ArrayList<ThuongHieu> listThuongHieu = thuongHieu_DAO.getAllThuongHieu();
		for (ThuongHieu thuongHieu : listThuongHieu) {
			cBoBoxThuongHieu.addItem(thuongHieu.getTenThuongHieu());
		}

		cBoBoxThuongHieu.setBounds(171, 188, 366, 23);
		panel.add(cBoBoxThuongHieu);

		txtTimTheoMa = new JTextField();
		txtTimTheoMa.setBounds(171, 231, 193, 23);
		panel.add(txtTimTheoMa);
		txtTimTheoMa.setColumns(10);

		JButton btnTimTheoMa = new FixButton("Tìm","img/tim.png",28,22);
		btnTimTheoMa.setForeground(new Color(0, 0, 0));
		btnTimTheoMa.setBounds(388, 230, 87, 23);
		btnTimTheoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maLinhKien = txtTimTheoMa.getText();

				LinhKien linhKien = linhKien_DAO.timLinhKienTheoMa(maLinhKien);
				if (!(linhKien == null)) {
					modelQuanLyLinhKien.getDataVector().removeAllElements();
					DecimalFormat decimalFormat = new DecimalFormat("###,###");
					Object[] obj = new Object[] { linhKien.getMaLinhKien(), linhKien.getTenLinhKien(),
							linhKien.getMoTa(), decimalFormat.format(linhKien.getGiaNhap()),
							decimalFormat.format(linhKien.getGiaBan()), linhKien.getSoLuong(),
							linhKien.getLoaiLinhKien().getTenLoaiLinhKien(),
							linhKien.getThuongHieu().getTenThuongHieu(), linhKien.getNgayNhap(),
							linhKien.getBaoHanh() };
					modelQuanLyLinhKien.addRow(obj);
				} else if (maLinhKien.equals("")) {
					capNhatModelLinhKien();
				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy linh kiện");

			}
		});
		panel.add(btnTimTheoMa);

		JLabel lblGiaNhap = new JLabel("Giá Nhập: ");
		lblGiaNhap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGiaNhap.setBounds(33, 63, 121, 23);
		panel.add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(171, 64, 366, 23);
		panel.add(txtGiaNhap);
		
		JButton btnTatCa = new FixButton("Tất Cả","img/LamMoi.png",28,22);
		btnTatCa.setForeground(new Color(0, 0, 0));
		btnTatCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatModelLinhKien();
			}
		});
		btnTatCa.setBounds(485, 230, 115, 23);
		panel.add(btnTatCa);
		
		JButton btnlen = new FixButton("","img/Len.png",20,18);
		btnlen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableQuanLyLinhKien.getSelectedRow();

		        if (selectedRow > 0) {
		            // Nếu dòng đã chọn không phải là dòng đầu tiên, giảm dòng được chọn
		        	tableQuanLyLinhKien.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);

		            // Scroll to make the newly selected row visible
		        	tableQuanLyLinhKien.scrollRectToVisible(tableQuanLyLinhKien.getCellRect(selectedRow - 1, 0, true));
		        }
		    }
		});
		btnlen.setBounds(548, 557, 39, 23);
		panel.add(btnlen);
		
		JButton btnxuong = new FixButton("","img/Xuong.png",20,18);;
		btnxuong.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableQuanLyLinhKien.getSelectedRow();
		        int rowCount = tableQuanLyLinhKien.getRowCount();

		        if (selectedRow < rowCount - 1) {
		            // Nếu dòng đã chọn không phải là dòng cuối cùng, tăng dòng được chọn
		        	tableQuanLyLinhKien.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);

		            // Scroll to make the newly selected row visible
		        	tableQuanLyLinhKien.scrollRectToVisible(tableQuanLyLinhKien.getCellRect(selectedRow + 1, 0, true));
		        }
		    }
		});
		btnxuong.setBounds(610, 557, 39, 23);
		panel.add(btnxuong);

	}

	private boolean isValidLinhKien() {
		// TODO Auto-generated method stub
		if (txtTenLinhKien.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không được để trống");
			txtTenLinhKien.requestFocus();
			return false;
		}
		if (txtGiaNhap.getText().length() > 0) {
			try {
				double giaNhap = Double.parseDouble(txtGiaNhap.getText());
				if (giaNhap <= 0) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương");
					txtGiaNhap.requestFocus();
					return false;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
				txtGiaNhap.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
			txtGiaNhap.requestFocus();
			return false;
		}
		if (txtGiaBan.getText().length() > 0) {
			try {
				double giaBan = Double.parseDouble(txtGiaBan.getText());
				if (giaBan <= 0) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương");
					txtGiaBan.requestFocus();
					return false;
				}
			} catch (Exception e3) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
				txtGiaBan.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
			txtGiaBan.requestFocus();
			return false;
		}

		if (txtSoLuong.getText().length() > 0) {
			try {
				int soLuong = Integer.parseInt(txtSoLuong.getText());
				if (soLuong <= 0) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương");
					txtSoLuong.requestFocus();
					return false;
				}
			} catch (Exception e4) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
				txtSoLuong.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
			txtSoLuong.requestFocus();
			return false;
		}

		if (txtMoTa.getText().length() <= 0) {
			JOptionPane.showMessageDialog(this, "Mô tả không được để trống");
			txtMoTa.requestFocus();
			return false;
		}

		if (txtBaoHanh.getText().length() > 0) {
			try {
				int baohanh = Integer.parseInt(txtBaoHanh.getText());
				if (baohanh <= 0) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương");
					txtBaoHanh.requestFocus();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
				txtBaoHanh.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
			txtBaoHanh.requestFocus();
			return false;
		}

		if (dataChooserNgayNhap.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày nhập không được để trống");
			dataChooserNgayNhap.requestFocus();
			return false;
		}
		return true;
	}

	private LinhKien taoLinhKienTuField() {
		String maLinhKien = txtMaLinhKien.getText();
		String tenLinhKien = txtTenLinhKien.getText();
		double giaNhap = Double.parseDouble(txtGiaNhap.getText().toString());
		double giaBan = Double.parseDouble(txtGiaBan.getText().toString());
		int soLuong = Integer.parseInt(txtSoLuong.getText().toString());

		String tenLoaiLinhKien = cBoBoxLoai.getSelectedItem().toString();
		LoaiLinhKien loaiLinhKien = new LoaiLinhKien();
		loaiLinhKien.setMaLoaiLinhKien(loaiLinhKien_DAO.getMaLoaiLinhKienTheoTen(tenLoaiLinhKien));
		loaiLinhKien.setTenLoaiLinhKien(tenLoaiLinhKien);

		String moTa = txtMoTa.getText();
		int baoHanh = Integer.parseInt(txtBaoHanh.getText().toString());

		String tenThuongHieu = cBoBoxThuongHieu.getSelectedItem().toString();
		ThuongHieu thuongHieu = new ThuongHieu();
		thuongHieu.setMaThuongHieu(thuongHieu_DAO.getMaThuongHieuTheoTen(tenThuongHieu));
		thuongHieu.setTenThuongHieu(tenThuongHieu);

		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date ngayNhap = dataChooserNgayNhap.getDate();
		// String formattedDate = dateFormat.format();
		java.sql.Date ngayNhapsql = new java.sql.Date(ngayNhap.getTime());
		LinhKien linhKien = new LinhKien(maLinhKien, tenLinhKien, soLuong, giaNhap, giaBan, baoHanh, moTa, loaiLinhKien,
				thuongHieu, ngayNhapsql);
		return linhKien;
	}

	private void capNhatModelLinhKien() {
		modelQuanLyLinhKien.getDataVector().removeAllElements();
		DocLinhKienTuDBVaoModel();
		// TODO Auto-generated method stub

	}

	private void DocLinhKienTuDBVaoModel() {
		// TODO Auto-generated method stub
		ArrayList<LinhKien> listLinhKien = linhKien_DAO.getAllLinhKien();
		DecimalFormat decimalFormat = new DecimalFormat("###,###");

		for (LinhKien linhKien : listLinhKien) {
			// String[] header = {"Mã LK","Tên LK","Mô Tả","Giá Nhập", "Giá Bán", "Số
			// Lượng","Loại","Thương Hiệu","Ngày Nhập","Bảo Hành"};
			Object[] obj = new Object[] { linhKien.getMaLinhKien(), linhKien.getTenLinhKien(), linhKien.getMoTa(),
					decimalFormat.format(linhKien.getGiaNhap()), decimalFormat.format(linhKien.getGiaBan()),
					linhKien.getSoLuong(), linhKien.getLoaiLinhKien().getTenLoaiLinhKien(),
					linhKien.getThuongHieu().getTenThuongHieu(), linhKien.getNgayNhap(), linhKien.getBaoHanh() };
			modelQuanLyLinhKien.addRow(obj);
		}

	}
	
	private void xoaRongField() {
		// TODO Auto-generated method stub
		txtMaLinhKien.setText("");
		txtTenLinhKien.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		cBoBoxLoai.setSelectedIndex(0);
		txtMoTa.setText("");
		txtBaoHanh.setText("");
		cBoBoxThuongHieu.setSelectedIndex(0);
		dataChooserNgayNhap.setDate(null);
		;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableQuanLyLinhKien.getSelectedRow();
		txtMaLinhKien.setText(modelQuanLyLinhKien.getValueAt(row, 0).toString());
		txtTenLinhKien.setText(modelQuanLyLinhKien.getValueAt(row, 1).toString());
		txtMoTa.setText(modelQuanLyLinhKien.getValueAt(row, 2).toString());
		txtGiaNhap.setText(modelQuanLyLinhKien.getValueAt(row, 3).toString().replace(",", ""));
		txtGiaBan.setText(modelQuanLyLinhKien.getValueAt(row, 4).toString().replace(",", ""));
		txtSoLuong.setText(modelQuanLyLinhKien.getValueAt(row, 5).toString());
		cBoBoxLoai.setSelectedItem(modelQuanLyLinhKien.getValueAt(row, 6));
		cBoBoxThuongHieu.setSelectedItem(modelQuanLyLinhKien.getValueAt(row, 7));
		dataChooserNgayNhap.setDate((Date) modelQuanLyLinhKien.getValueAt(row, 8));
		txtBaoHanh.setText(modelQuanLyLinhKien.getValueAt(row, 9).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

	}
}
