package dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;

public class NhanVien_DAO {
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<NhanVien> getAllTableNhanVien(){
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			//tạo kn:
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			//Tạo statement
			String sql = "select * from NhanVien join ChucVu on NhanVien.maChucVu = ChucVu.maChucVu";
			PreparedStatement st = con.prepareStatement(sql);
			//thực thi:
			ResultSet rs = st.executeQuery();
			System.out.println("Bạn đã truy vấn: " + sql);
			while(rs.next()) {
				String maNV 	= rs.getString("maNhanVien");
				String tenNV	= rs.getString("tenNhanVien");
				String diaChiNV	= rs.getString("diaChi");
				String soDT		= rs.getString("sdt");
				String email 	= rs.getString("email");
				String maCV		= rs.getString("maChucVu");
				String tenCV	= rs.getString("tenChucVu");
				ChucVu chucVu	= new ChucVu(maCV, tenCV);
				Date ngaySinh 	= rs.getDate("ngaySinh");
				boolean gioiTinh= rs.getBoolean("gioiTinh");
				
				NhanVien nv 	= new NhanVien(maNV, tenNV, diaChiNV, soDT, email, chucVu, ngaySinh, gioiTinh);
				dsNhanVien.add(nv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public int themNhanVien(NhanVien nv) {
		int kq = 0;
		try {
			//tạo kết nối:
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			//Tạo câu lệnh:
			String sql = "insert into NhanVien(tenNhanVien, diaChi, sdt, email, maChucVu, ngaySinh, gioiTinh) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getTenNhanVien());
			ps.setString(2, nv.getDiaChi());
			ps.setString(3, nv.getSdt());
			ps.setString(4, nv.getEmail());
			ps.setString(5, nv.getChucVu().getMaChucVu());
			ps.setDate(6, nv.getNgaySinh());
			ps.setBoolean(7, nv.isGioiTinh());
			// thực hiện câu lệnh:
			kq = ps.executeUpdate();
			//xử lý:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Số dòng thay đổi" + kq);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public int xoaNhanVienTheoMa(String maNV) {
		int kq = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// Tạo câu lệnh:
			String sql = "delete from NhanVien where maNhanVien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNV);
			// thực thi: 
			kq = ps.executeUpdate();
			// xử lý: 
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Đã xoá: "+kq+" dòng");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public int suaNhanVien(NhanVien nv) {
		int kq = 0;
		try {
			// tạo kết nối:
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement:
			String sql = "update NhanVien set tenNhanVien = ?, diaChi = ?, sdt = ?, email = ?, maChucVu = ?, ngaySinh = ?, gioiTinh = ? where maNhanVien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getTenNhanVien());
			ps.setString(2, nv.getDiaChi());
			ps.setString(3, nv.getSdt());
			ps.setString(4, nv.getEmail());
			ps.setString(5, nv.getChucVu().getMaChucVu());
			ps.setDate(6, nv.getNgaySinh());
			ps.setBoolean(7, nv.isGioiTinh());
			ps.setString(8, nv.getMaNhanVien());
			//thực thi:
			kq = ps.executeUpdate();
			//xử lý:
			System.out.println("Bạn đã thực thi: "+sql);
			System.out.println("Có: " + kq + " dòng thay đổi");
		} catch(Exception e) {
			
		}
		return kq;
	}
	
	// của đạt
	public NhanVien searchMaNhanVien(String ma){
    	NhanVien nv = new NhanVien();
    	try {
    		ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien where maNhanVien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String MaNV = rs.getString(1);
            	String tenNV = rs.getString(2);
            	String diaChi = rs.getString(3);
            	String sdt = rs.getString(4);
            	String email = rs.getString(5);
            	ChucVu cv = new ChucVu(rs.getString("maChucVu"));
            	Date ngaySinh 	= rs.getDate("ngaySinh");
            	boolean gioiTinh= rs.getBoolean("gioiTinh");
            	nv = new NhanVien(MaNV, tenNV, diaChi, sdt, email, cv, ngaySinh, gioiTinh);
            	return nv;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}
}
