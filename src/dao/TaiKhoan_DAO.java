package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public TaiKhoan_DAO() {
		
	}
	
	public TaiKhoan getTaiKhoanTuTenDangNhap(String tenDangNhap, String matKhau) {
		TaiKhoan ketQua = null;
		try {
			// tạo kết nối
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo câu lệnh
			String sql = "select * from TaiKhoan join NhanVien on TaiKhoan.maNhanVien = NhanVien.maNhanVien join ChucVu on NhanVien.maChucVu = ChucVu.maChucVu where tenDangNhap = ? and matKhau = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenDangNhap);
			ps.setString(2, matKhau);
			// thực thi
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String matKhauDB = rs.getString("matKhau");
				String maNhanVien = rs.getString("maNhanVien");
				System.out.println(maNhanVien);
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String sdt = rs.getString("sdt");
				String email = rs.getString("email");
				String maChucVu = rs.getString("maChucVu");
				String tenChucVu = rs.getString("tenChucVu");
				ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);
				Date ngaySinh = rs.getDate("ngaySinh");
				Boolean gioiTinh = rs.getBoolean("gioiTinh");
				
				NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, diaChi, sdt, email, chucVu, ngaySinh, gioiTinh);
				ketQua = new TaiKhoan(tenDangNhap, matKhauDB, nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
		}
		return ketQua;
		
	}
}
