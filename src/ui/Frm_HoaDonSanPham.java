package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_dao;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("unused")
public class Frm_HoaDonSanPham extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnHoaDon;
	public static JTextArea textArea;
		

	public Frm_HoaDonSanPham() throws SQLException {
		JScrollPane sc = new JScrollPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(425,600));
		String maNV = Frm_TrangChu.txtMaNV.getText();
		NhanVien_DAO nv_dao = new NhanVien_DAO();
		NhanVien nv = nv_dao.searchMaNhanVien(maNV);
		String tennv = nv.getTenNhanVien();
		Date dateChooser = Frm_TrangChu.dataChooserNgayLapHoaDon.getDate();
		
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(dateChooser);
		setLocationRelativeTo(null);
		setResizable(false);
		pnHoaDon = new JPanel();
		pnHoaDon.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnHoaDon.setLayout(null);
		textArea = new JTextArea();
		textArea.setEnabled(true);
		int viTri = JPanel_HoaDon.tableQuanLyHoaDon.getSelectedRow();
		String maKh = JPanel_HoaDon.tableQuanLyHoaDon.getValueAt(viTri, 1).toString();
		KhachHang_dao kh_dao = new KhachHang_dao();
		KhachHang kh = kh_dao.searchMaKhachHang(maKh);
		String tenKH = kh.getTenKhachHang();
		String sdt= kh.getSdt();
		textArea.setFont(new Font("Arial", Font.BOLD, 11));
		textArea.setBounds(5, 5, 400, 535);
		textArea.append("\n 		Cửa hàng bán linh kiện \n");
		textArea.append("\n 12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, Thành Phố Hồ Chí Minh \n");
		textArea.append("---------------------------------------------------------------------------------------------------\n");
		textArea.append("	 	HÓA ĐƠN BÁN HÀNG \n");
		textArea.append("  Ngày lập hóa đơn: " + formattedDate + "\n");	
		textArea.append("  Khách hàng:         " + tenKH + "\n");
		textArea.append("  Điện thoại:           " + sdt + "\n");
		textArea.append("  Nhân viên:            " + tennv + "\n");
		textArea.append("\n"+String.format("   |%-14s|%-16s|%-16s|\n", "    SL    ", "      ĐGiá    	  ", "	TTiền"));
		textArea.append(" --------------------------------------------------------------------------------------------------\n");

		int tableRowCount  = JPanel_HoaDon.tableChiTietHoaDon.getRowCount();
		String tenSanPham = null, soLuong, donGia;
		double tongtien =0;
		for(int i = 0; i<tableRowCount; i++) {
			tenSanPham = JPanel_HoaDon.tableChiTietHoaDon.getValueAt(i, 1).toString();
			soLuong = JPanel_HoaDon.tableChiTietHoaDon.getValueAt(i, 2).toString();
			donGia = JPanel_HoaDon.tableChiTietHoaDon.getValueAt(i, 3).toString();
			double thanhTien = Double.parseDouble(JPanel_HoaDon.tableChiTietHoaDon.getValueAt(i, 4).toString().replace(",", ""));
			DecimalFormat df = new DecimalFormat("#,###,###,###  VNĐ");
			String thanhTien1 = df.format(thanhTien);
			tongtien += thanhTien;
			tenSanPham = (tenSanPham.length() > 64) ? tenSanPham.substring(0,64) +"\n" +"  "+tenSanPham.substring(64,tenSanPham.length()) : tenSanPham ;
			textArea.append("  "+tenSanPham +"\n");
			textArea.append(String.format("   |%-14s|%-24s|%-16s|\n", "    "+soLuong+"    ", "      "+donGia+"    ", "	"+thanhTien1));
			textArea.append("  -------------------------------------------------------------------------------------------------\n");
		}
		DecimalFormat df = new DecimalFormat("#,###,###,###  VNĐ");
		double thue = tongtien/10;
		double tienTra = tongtien + thue;
		textArea.append(String.format("\n  Tổng tiền hàng:      %55s" ,df.format(tongtien).toString()));
		textArea.append(String.format("\n  Tiền thuế VAT 10%s:      %49s",'%', df.format(thue).toString()));
		textArea.append(String.format("\n  Tổng cộng tiền thanh toán:      %34s", df.format(tienTra).toString()));
		textArea.append("\n\n\n 	Cảm ơn quý khách và hẹn gặp lại! \n");
		pnHoaDon.add(textArea);
		textArea.getRows();
		add(pnHoaDon);
		setVisible(true);

	}
}