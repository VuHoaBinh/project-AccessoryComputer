package ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import dao.KhachHang_dao;
import dao.LinhKien_Dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;
import others.InPDF;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class JPanel_HoaDon extends JPanel {
	private JTextField txtTimKiemMaHoaDon;
	private JTable tableHoaDon;
	public static JTable tableQuanLyHoaDon;
	private DefaultTableModel modelQuanLyHoaDon;
	private JButton btnTim;
	private DefaultTableModel modelChiTietHoaDon;
	public static JTable tableChiTietHoaDon;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public JPanel_HoaDon()  {
		setLayout(null);
		
		JPanel pnl = new JPanel();
		pnl.setBackground(new Color(240, 240, 240));
		pnl.setBounds(0, 0, 1226, 656);
		pnl.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)), "Ho\u0301a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		add(pnl);
		pnl.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Theo Mã Hóa Đơn:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(24, 25, 212, 27);
		pnl.add(lblNewLabel_1);
		
		txtTimKiemMaHoaDon = new JTextField();
		txtTimKiemMaHoaDon.setBounds(241, 26, 385, 27);
		pnl.add(txtTimKiemMaHoaDon);
		txtTimKiemMaHoaDon.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)), "Danh sa\u0301ch ho\u0301a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		panel_1.setBounds(24, 62, 1179, 263);
		pnl.add(panel_1);
		panel_1.setLayout(null);
		
		String[] header = {"Mã hóa đơn","Mã khách hàng","Tên khách hàng","Mã nhân viên","Tên nhân viên","Ngày lập hóa đơn","Tổng tiền"};
	    modelQuanLyHoaDon = new DefaultTableModel(header,0) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
	    tableQuanLyHoaDon = new JTable(modelQuanLyHoaDon);
		JScrollPane scrollPane = new JScrollPane(tableQuanLyHoaDon);
		scrollPane.setBounds(10, 21, 1159, 233);
		panel_1.add(scrollPane);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 85, 170), new Color(0, 85, 170)), "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 85, 170)));
		panel_1_1.setBounds(24, 335, 1179, 263);
		pnl.add(panel_1_1);
		
		String[] header1 = {"Mã hóa đơn","Tên sản phẩm","Số lượng","Đơn giá","Thành tiền"};
	    modelChiTietHoaDon = new DefaultTableModel(header1,0) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		// TODO Auto-generated method stub
	    		return false;
	    	}
	    };
	    tableChiTietHoaDon= new JTable(modelChiTietHoaDon);
		JScrollPane scrollPane_1 = new JScrollPane(tableChiTietHoaDon);
		scrollPane_1.setBounds(10, 21, 1159, 233);
		panel_1_1.add(scrollPane_1);
		
		JButton btnXuatHoaDon = new FixButton("Xuất hóa đơn","img/xuathoadon.png",28,22);
		btnXuatHoaDon.setForeground(new Color(0, 0, 0));
		btnXuatHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXuatHoaDon.setBounds(873, 608, 221, 35);
		pnl.add(btnXuatHoaDon);
		
		btnTim = new FixButton("Tìm","img/tim.png",28,22);
		btnTim.setForeground(new Color(0, 0, 0));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTim.setBounds(640, 25, 135, 27);
		pnl.add(btnTim);
		
		JButton btnLamMoi = new FixButton("Làm mới danh sách","img/LamMoi.png",28,22);
		btnLamMoi.setForeground(new Color(0, 0, 0));
		btnLamMoi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLamMoi.setBounds(186, 608, 221, 35);
		pnl.add(btnLamMoi);
		DocLinhKienTuDBVaoModel();
		tableQuanLyHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelChiTietHoaDon.setRowCount(0);
				int row = tableQuanLyHoaDon.getSelectedRow();
				String maHD = tableQuanLyHoaDon.getModel().getValueAt(row, 0).toString();
				String maKH = tableQuanLyHoaDon.getModel().getValueAt(row, 1).toString();
				String maNV = tableQuanLyHoaDon.getModel().getValueAt(row, 2).toString();
				KhachHang_dao kh_dao = new KhachHang_dao();
				KhachHang kh = kh_dao.searchMaKhachHang(maKH);
				String tenKH = kh.getTenKhachHang();
				ArrayList<ChiTietHoaDon> dsCTHD;
				try {
					ChiTietHoaDon_Dao cthd_dao = new ChiTietHoaDon_Dao();
					dsCTHD = cthd_dao.timTheoMaCTHD(maHD);
					for(ChiTietHoaDon cthd : dsCTHD){
						DecimalFormat df = new DecimalFormat("###,###");
						int soLuong = cthd.getSoLuong();
						Double donGia =cthd.getDonGia();
						String dfDonGia = df.format(donGia);
						String maLK = cthd.getLinhKien().getMaLinhKien();
						LinhKien_Dao lk_dao = new LinhKien_Dao();
						LinhKien lk = lk_dao.timLinhKienTheoMa(maLK);
						String tenLK= lk.getTenLinhKien();
					
						double thanhTien = cthd.tinhThanhTien();
						
						String dfThanhTien = df.format(thanhTien);
						Object [] rowData = {maHD,tenLK,soLuong,dfDonGia,dfThanhTien};
						modelChiTietHoaDon.addRow(rowData);
						modelChiTietHoaDon.fireTableDataChanged();
					}
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
			
		});
		
		btnXuatHoaDon.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result =JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn không ?", "In hóa đơn :", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						new Frm_HoaDonSanPham();
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					InPDF.printComponent(Frm_HoaDonSanPham.textArea);
					String cmds[] = new String[] { "cmd", "/c", "D:\\HoaDon_test\\a.pdf" };
					try {
						Runtime.getRuntime().exec(cmds);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						Runtime.getRuntime().exec(cmds);
					} catch (Exception e1) {
						System.out.println(e1);
					}
					JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công");
					
				} else if (result == JOptionPane.NO_OPTION) {
					return;
				}
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dm= (DefaultTableModel) tableQuanLyHoaDon.getModel();
				int  rowCount1 =dm.getRowCount();
				for (int i = rowCount1 - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				DocLinhKienTuDBVaoModel();
			}
		});
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma = txtTimKiemMaHoaDon.getText();
				timTheoMa(ma);
				
			}
		});
	}
	private void timTheoMa(String ma) {
		String str = ma;
		if(str != null && str.trim().length()>0)
			try{
				{
					DefaultTableModel dm= (DefaultTableModel) tableQuanLyHoaDon.getModel();
					int  rowCount1 =dm.getRowCount();
					for (int i = rowCount1 - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					DecimalFormat df = new DecimalFormat("###,###");
					double tongTien =0;
					HoaDon_Dao hd_dao = new HoaDon_Dao();
					List<HoaDon> list = hd_dao.getHoaDonTheoMa(str);
					ChiTietHoaDon_Dao cthd_dao = new ChiTietHoaDon_Dao();
					for (HoaDon hd : list) {
						ArrayList<ChiTietHoaDon> chiTietHoaDons = cthd_dao.timTheoMaCTHD(str);
						hd.setListChiTietHoaDon(chiTietHoaDons);
						tongTien = hd.tinhTongTien();
						String tongTien1 = df.format(tongTien);
						Object[] obj = new Object[] { hd.getMaHoaDon(), hd.getMaKhachHang().getMaKhachHang(),hd.getMaKhachHang().getTenKhachHang(),
								hd.getMaNhanVien().getMaNhanVien(),hd.getMaNhanVien().getTenNhanVien(), hd.getNgayLap(), tongTien1};
						modelQuanLyHoaDon.addRow(obj);
					}
			}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(this, "Không tìm thấy mã");
			}
		
	}
	private void DocLinhKienTuDBVaoModel()  {
		try {
		// TODO Auto-generated method stub
		HoaDon_Dao hd_dao = new HoaDon_Dao();
		ArrayList<HoaDon> listHoaDon = hd_dao.layDsHoaDon();
		ChiTietHoaDon_Dao cthd_dao = new ChiTietHoaDon_Dao();
		DecimalFormat df = new DecimalFormat("###,###");
		double tongTien =0;
		for (HoaDon hoadon : listHoaDon) {
			ArrayList<ChiTietHoaDon> chiTietHoaDons = cthd_dao.timTheoMaCTHD(hoadon.getMaHoaDon());
			hoadon.setListChiTietHoaDon(chiTietHoaDons);
			tongTien = hoadon.tinhTongTien();
			String tongTien1 = df.format(tongTien);
			Object[] obj = new Object[] { hoadon.getMaHoaDon(), hoadon.getMaKhachHang().getMaKhachHang(),hoadon.getMaKhachHang().getTenKhachHang(),
					hoadon.getMaNhanVien().getMaNhanVien(),hoadon.getMaNhanVien().getTenNhanVien(), hoadon.getNgayLap(), tongTien1};
			modelQuanLyHoaDon.addRow(obj);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
