package ui;


import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChucVu_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.ImageIcon;



public class JPanel_NhanVien extends JPanel implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTable tableQuanLyNhanVien;
    private JDateChooser dataChooserNgaySinh;
	private DefaultTableModel modelQuanLyNhanVien;
	private ChucVu_DAO ChucVu_DAO;
	private NhanVien_DAO NhanVien_DAO;
	private JComboBox cBoBoxChucVu;
	private JComboBox cBoBoxGioiTinh;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JButton btnlen;
	private JButton btnxuong;

	/**
	 * Create the panel.
	 */
	public JPanel_NhanVien() {
		try {
			ConnectDB.getInstance().connect();
			ChucVu_DAO = new ChucVu_DAO();
			NhanVien_DAO = new NhanVien_DAO();
		} catch(Exception e) {
			System.out.println("Ket noi that bai");
		}
		
		NhanVien_DAO 	= new NhanVien_DAO();
		ChucVu_DAO 		= new ChucVu_DAO();
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)), "Qua\u0309n ly\u0301 nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		panel.setBounds(0, 0, 1226, 656);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMaNV.setBounds(55, 25, 128, 23);
		panel.add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setBounds(203, 26, 290, 23);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JLabel lblTenNv = new JLabel("Tên NV:");
		lblTenNv.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTenNv.setBounds(55, 68, 128, 23);
		panel.add(lblTenNv);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(203, 67, 290, 23);
		panel.add(txtTenNV);
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSDT.setBounds(55, 114, 128, 23);
		panel.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(203, 108, 290, 23);
		panel.add(txtSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDiaChi.setBounds(735, 155, 128, 23);
		panel.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(873, 156, 290, 23);
		panel.add(txtDiaChi);
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblChucVu.setBounds(55, 156, 128, 23);
		panel.add(lblChucVu);
		
		cBoBoxChucVu = new JComboBox();
		cBoBoxChucVu.setBounds(203, 156, 290, 23);
		panel.add(cBoBoxChucVu);
		ArrayList<ChucVu> listCV = ChucVu_DAO.getAllChucVu();
		for (ChucVu cv : listCV) {
			cBoBoxChucVu.addItem(cv.getTenChucVu());
		}
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail.setBounds(735, 30, 128, 23);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(873, 31, 290, 23);
		panel.add(txtEmail);
		
		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGioiTinh.setBounds(735, 68, 128, 23);
		panel.add(lblGioiTinh);
		
		cBoBoxGioiTinh = new JComboBox();
		cBoBoxGioiTinh.addItem("Nam");
		cBoBoxGioiTinh.addItem("Nữ");
		cBoBoxGioiTinh.setBounds(873, 69, 290, 23);
		panel.add(cBoBoxGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNgaySinh.setBounds(735, 114, 128, 23);
		panel.add(lblNgaySinh);
		
		dataChooserNgaySinh = new JDateChooser();
		dataChooserNgaySinh.setDateFormatString("yyyy-M-d");
		dataChooserNgaySinh.setBounds(873, 114, 290, 23);
		panel.add(dataChooserNgaySinh);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)), "Danh sa\u0301ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		panel_2.setBounds(28, 200, 1173, 342);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		String[] header = {"Mã NV","Tên NV","Địa Chỉ","SĐT","Email","Tên Chức Vụ","Ngày Sinh","Giới Tính"};
	    modelQuanLyNhanVien = new DefaultTableModel(header,0);
	    tableQuanLyNhanVien = new JTable(modelQuanLyNhanVien);
		JScrollPane scrollPane = new JScrollPane(tableQuanLyNhanVien);
		scrollPane.setBounds(10, 21, 1143, 309);
		panel_2.add(scrollPane);
		
		DocNhanVienTuDB();
		
		
		btnThem = new FixButton("Thêm","img/Thêm.png",28,22);
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(110, 587, 141, 47);
		panel.add(btnThem);
		
		btnSua = new FixButton("Sửa","img/Sua.png",28,22);
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBounds(394, 587, 141, 47);
		panel.add(btnSua);
		
		btnXoa = new FixButton("Xóa","img/Xoa.png",28,22);
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(699, 587, 141, 47);
		panel.add(btnXoa);
		
		btnXoaRong = new FixButton("Xóa Rỗng","img/XoaRong.png",28,22);
		btnXoaRong.setForeground(new Color(0, 0, 0));
		btnXoaRong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaRong.setBounds(995, 587, 141, 47);
		panel.add(btnXoaRong);
		
		JLabel lblNewLabel_3_1 = new JLabel("1/2");
		lblNewLabel_3_1.setBounds(602, 553, 24, 20);
		panel.add(lblNewLabel_3_1);
		
		btnlen = new FixButton("","img/Len.png",20,18);
		btnlen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableQuanLyNhanVien.getSelectedRow();

		        if (selectedRow > 0) {
		            // Nếu dòng đã chọn không phải là dòng đầu tiên, giảm dòng được chọn
		        	tableQuanLyNhanVien.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);

		            // Scroll to make the newly selected row visible
		        	tableQuanLyNhanVien.scrollRectToVisible(tableQuanLyNhanVien.getCellRect(selectedRow - 1, 0, true));
		        }
		    }
		});
		btnlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnlen.setBounds(556, 553, 40, 23);
		panel.add(btnlen);
		
		btnxuong = new FixButton("","img/Xuong.png",20,18);
		btnxuong.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableQuanLyNhanVien.getSelectedRow();
		        int rowCount = tableQuanLyNhanVien.getRowCount();

		        if (selectedRow < rowCount - 1) {
		            // Nếu dòng đã chọn không phải là dòng cuối cùng, tăng dòng được chọn
		        	tableQuanLyNhanVien.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);

		            // Scroll to make the newly selected row visible
		        	tableQuanLyNhanVien.scrollRectToVisible(tableQuanLyNhanVien.getCellRect(selectedRow + 1, 0, true));
		        }
		    }
		});

		btnxuong.setBounds(624, 552, 40, 23);
		panel.add(btnxuong);
		
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		tableQuanLyNhanVien.addMouseListener(this);
	}
	
	private void DocNhanVienTuDB() {
		ArrayList<NhanVien> dsNV = new NhanVien_DAO().getAllTableNhanVien();
		for (NhanVien nv : dsNV) {
			Object[] obj = new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
					nv.getSdt(), nv.getEmail(), nv.getChucVu().getTenChucVu(),nv.getNgaySinh(), nv.isGioiTinh()?"nữ":"nam"};
			modelQuanLyNhanVien.addRow(obj);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o == btnXoa) {
			xoaNV();
		}
		if(o== btnXoaRong) {
			xoaTrangField();
		}
		if(o == btnThem) {
			themNV();
		}
		if(o.equals(btnSua)) {
			suaNV();
		}
	}
	
	private void capNhatModelNhanVien() {
		modelQuanLyNhanVien.getDataVector().removeAllElements();
		DocNhanVienTuDB();
	}
	
	//Thêm nhân viên vào model;
	private void themNhanVienVaoModel(NhanVien nv) {
		Object[] obj = new Object[] {
			nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
			nv.getSdt(), nv.getEmail(), nv.getChucVu().getTenChucVu(),
			nv.getNgaySinh(), nv.isGioiTinh()?"Nam":"Nữ"
		};
		modelQuanLyNhanVien.addRow(obj);
	}
	
	//Tạo nhân viên từ field:
	private NhanVien taoNhanVienTuFeild() {
		String maNV 	= txtMaNV.getText();
		String tenNV	= txtTenNV.getText();
		String diaChi	= txtDiaChi.getText();
		String SDT		= txtSDT.getText();
		String email 	= txtEmail.getText();
		String tenCV 	= cBoBoxChucVu.getSelectedItem().toString();
		ChucVu cv		= new ChucVu();
		cv.setMaChucVu(ChucVu_DAO.getMaChucVuTheoTen(tenCV));
		cv.setTenChucVu(tenCV);
		Date ngaySinh	= dataChooserNgaySinh.getDate();
		java.sql.Date ngaySinhSQL = new java.sql.Date(ngaySinh.getTime());

		boolean phai 	= false;
		String gioiTinh = (String) cBoBoxGioiTinh.getSelectedItem();
		if("Nam".equals(gioiTinh)) {
			phai = false;
		} else {
			phai = true;
		}
		NhanVien nv 	= new NhanVien(maNV, tenNV, diaChi, SDT, email, cv, ngaySinhSQL, phai);
		return nv;
	}
	
	public void xoaNV() {
		int row		= tableQuanLyNhanVien.getSelectedRow();
		String maNV	= modelQuanLyNhanVien.getValueAt(row, 0).toString();
		int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá?", "xác nhận xoá!", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION) {
			if(NhanVien_DAO.xoaNhanVienTheoMa(maNV)>0) {
				System.out.println("Xoá nhân viên thành công");
				capNhatModelNhanVien();
			}else {
				System.out.println("Xoá nhân viên thất bại.");
			}
		}else {
			System.out.println("Xoá không được thực hiện");
		}
	}
	public void xoaTrangField() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		cBoBoxChucVu.setSelectedIndex(0);
		cBoBoxGioiTinh.setSelectedIndex(0);
		dataChooserNgaySinh.setDate(null);
	}
	private boolean isValidNhanVien() {
		
		String tenNV = txtTenNV.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt	 = txtSDT.getText().trim();
		String diaChi= txtDiaChi.getText().trim();
		
		if (!(tenNV.length() > 0 && tenNV.matches("^[\\p{Lu}][\\p{L}]+([\\s][\\p{Lu}][\\p{L}]+)*$"))) {
		    JOptionPane.showMessageDialog(this, "Tên nhân viên không hợp lệ. Hãy viết hoa chữ cái đầu và có thể có dấu.");
		    txtTenNV.requestFocus();
		    return false;
		}
		if(!(email.length()>0 && email.matches("^([a-z0-9_\\.-]+)@([a-z0-9_\\.]+)\\.([a-z]{2,6})$"))) {
			JOptionPane.showMessageDialog(this, "Email phải đúng định dạng chuẩn của google.");
			txtEmail.requestFocus();
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("^0\\d{9}$"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số");
			txtSDT.requestFocus();
			return false;
		}
		if(!(diaChi.length()>0)) {
			JOptionPane.showMessageDialog(this, "Địa chỉ nhân viên không được để trống.");
			txtDiaChi.requestFocus();
			return false;
		}
		
		
		
		
//		if(txtTenNV.getText().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)+$")) {
//			JOptionPane.showMessageDialog(this, "Tên nhân viên không viết dấu, in hoa các chữ cái đầu");
//			txtTenNV.requestFocus();
//			return false;
//		}
//		if(txtEmail.getText().matches("^([a-z0-9_\\.-]+)@([a-z0-9_\\.]+)\\.([a-z]{2,6})$")) {
//			JOptionPane.showMessageDialog(this, "Email phải đúng định dạng chuẩn của google.");
//			txtEmail.requestFocus();
//			return false;
//		}
//		if(txtSDT.getText().matches("^(84|0[1|2|3|5|7|9])+([0-9]{8,9})$")) {
//			JOptionPane.showMessageDialog(this, "Số điện thoại nhân viên phải đúng 10 số của Việt Nam");
//			txtSDT.requestFocus();
//			return false;
//		}
//		if(txtDiaChi.getText().matches("^([\\w]+[/\\w,]+)(\\s[\\w,]+)+$")) {
//			JOptionPane.showMessageDialog(this, "Địa chỉ nhân viên phải có dấu phẩy và dấu cách trước mỗi thông tin.");
//			txtDiaChi.requestFocus();
//			return false;
//		}
		return true;
	}
	public void themNV() {
		if(isValidNhanVien()) {
			NhanVien nv = taoNhanVienTuFeild();
			System.out.println(nv);
			if(NhanVien_DAO.themNhanVien(nv)>0) {
				capNhatModelNhanVien();
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
				xoaTrangField();
				
			}else
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
		}
	}
	
	public void suaNV() {
		if(isValidNhanVien()) {
			NhanVien nv = taoNhanVienTuFeild();
			if(NhanVien_DAO.suaNhanVien(nv)>0) {
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				capNhatModelNhanVien();
			}else {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableQuanLyNhanVien.getSelectedRow();
		txtMaNV.setText(modelQuanLyNhanVien.getValueAt(row, 0).toString());
		txtTenNV.setText(modelQuanLyNhanVien.getValueAt(row, 1).toString());
		txtDiaChi.setText(modelQuanLyNhanVien.getValueAt(row, 2).toString());
		txtSDT.setText(modelQuanLyNhanVien.getValueAt(row, 3).toString());
		txtEmail.setText(modelQuanLyNhanVien.getValueAt(row, 4).toString());
		cBoBoxChucVu.setSelectedItem(modelQuanLyNhanVien.getValueAt(row, 5));
		dataChooserNgaySinh.setDate((Date) modelQuanLyNhanVien.getValueAt(row, 6));
		cBoBoxGioiTinh.setSelectedItem(modelQuanLyNhanVien.getValueAt(row, 6));
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
	
	
}
