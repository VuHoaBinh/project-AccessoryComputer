package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChucVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;


public class ThongKe_Dao {
	
   public ThongKe_Dao()
   {
	   
   }
   public ArrayList<ChiTietHoaDon> layDsChiTietHoaDon() {
		ArrayList<ChiTietHoaDon> dscthd = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from ChiTietHoaDon");
			while (rs.next()) {
				HoaDon mahd = new HoaDon(rs.getString("maHoaDon"));
				LinhKien lk = new LinhKien(rs.getString("maLinhKien"));
				int soluong = rs.getInt("soLuong");
				double dongia = rs.getDouble("donGia");
				ChiTietHoaDon ct = new ChiTietHoaDon(mahd, lk, soluong, dongia);
				dscthd.add(ct);

		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dscthd;

	}   
   public List<ChiTietHoaDon> layDsChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
	    List<ChiTietHoaDon> listcthd = new ArrayList<ChiTietHoaDon>();

	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
	        
	        try (PreparedStatement statement = con.prepareStatement(sql)) {
	            statement.setString(1, maHoaDon);
	            
	            try (ResultSet rs = statement.executeQuery()) {
	                while (rs.next()) {
	                    HoaDon mahd = new HoaDon(rs.getString("maHoaDon"));
	                    LinhKien lk = new LinhKien(rs.getString("maLinhKien"));
	                    int soluong = rs.getInt("soLuong");
	                    double dongia = rs.getDouble("donGia");
	                    ChiTietHoaDon ct = new ChiTietHoaDon(mahd, lk, soluong, dongia);
	                    listcthd.add(ct);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listcthd;
	}
   public LinhKien timLinhKienTheoMa(String maLinhKien) {
		LinhKien lk = null;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from LinhKien where maLinhKien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLinhKien);
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				String tenLinhKien = rs.getString("tenLinhKien");
				lk = new LinhKien(maLinhKien, tenLinhKien, 0, 0, 0, 0, null, null, null, null);	
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return lk;
	}
   public ArrayList<HoaDon> layDsHoaDon() {
		ArrayList<HoaDon> dshd = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from HoaDon");
			while (rs.next()) {
				String maHD = rs.getString("maHoaDon");
				float thue = rs.getFloat("thue");
				Date ngayLapHD = rs.getDate("ngayLap");
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));
				NhanVien nv =new NhanVien(rs.getString("maNhanVien"));
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLapHD, thue);
				dshd.add(hd);

		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dshd;

	}
   public ChiTietHoaDon timTheoMaHoaDon(String maHoaDon) {
		ChiTietHoaDon cthd = null;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from ChiTietHoaDon where maHoaDon = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maHoaDon);
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHoaDon"));
				LinhKien lk = new LinhKien(rs.getString("maLinhKien"));
				int soLuuong = rs.getInt(3);
				Double donGia = rs.getDouble(4);
				cthd = new ChiTietHoaDon(hd, lk, soLuuong, donGia);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return cthd;
	}
   public KhachHang timKiemTheoMaKhachHang(KhachHang ma) {
		KhachHang kh = null;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from khachhang where makhachhang = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma.getMaKhachHang());
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				String MaKH = rs.getString(1);
            	String tenKH = rs.getString(2);
            	String soDienThoai = rs.getString(3);
            	String diaChi = rs.getString(4);
            	String email = rs.getString(5);
            	kh = new KhachHang(MaKH, tenKH, soDienThoai, diaChi, email);
            	return kh;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return kh;
	}
   public NhanVien searchMaNhanVien(NhanVien ma){
   	NhanVien nv = new NhanVien();
   	try {
   		ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien where maNhanVien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma.getMaNhanVien());
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
   	return nv;
	}
}
