package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_dao {
	public KhachHang_dao()
	{
		
	}
	// của đạt
	public KhachHang searchMaKhachHang(String ma){
    	KhachHang kh = new KhachHang();
    	try {
    		ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM KhachHang where maKhachHang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String MaKH = rs.getString(1);
            	String tenKH = rs.getString(2);
            	String soDienThoai = rs.getString(3);
            	String diaChi = rs.getString(4);
            	String email = rs.getString(5);
            	kh = new KhachHang(MaKH, tenKH, soDienThoai, diaChi, email);
            	return kh;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}
	
	public KhachHang getKhachVangLai() {
		KhachHang kh = new KhachHang();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM KhachHang where maKhachHang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "KH000001");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	String MaKH = rs.getString(1);
            	String tenKH = rs.getString(2);
            	String soDienThoai = rs.getString(3);
            	String diaChi = rs.getString(4);
            	String email = rs.getString(5);
            	kh = new KhachHang(MaKH, tenKH, soDienThoai, diaChi, email);
            	return kh;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
		}
	
	public KhachHang getKhachHangBySDT(String soDienThoai){
		KhachHang khachHang =null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KhachHang where sdt =?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, soDienThoai);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				String maKH= rs.getString("maKhachHang");
				String tenKH= rs.getString("tenKhachHang");
				String sdt = rs.getString("sdt");
				String diaChi=rs.getString("diaChi");
				String email = rs.getString("email");
				khachHang = new KhachHang(maKH, tenKH, sdt, diaChi, email);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}
	public ArrayList<KhachHang> getAllKhachHang(){
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		
		try {
			// tạo kết nối
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from KhachHang";
			PreparedStatement ps = con.prepareStatement(sql);
			// thực thi
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maKH = rs.getString("maKhachHang");
				String tenKH = rs.getString("tenKhachHang");
				String sdt = rs.getString("sdt");
				String diaChi = rs.getString("diaChi");
				String email =rs.getString("email");
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaChi, email);
				listKH.add(kh);
			}
			// xử lí
			System.out.println("Bạn đã truy vấn: " + sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listKH;
	}
	
	public int themKhachHang(KhachHang kh) {
		int ketQua = 0;
		try {
			// tạo kết nối
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "insert into KhachHang (tenKhachHang, sdt, diaChi, email) values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, kh.getTenKhachHang());
			ps.setString(2, kh.getSdt());
			ps.setString(3, kh.getDiaChi());
			ps.setString(4, kh.getEmail());
			// thục thi
			ketQua = ps.executeUpdate();
			// xử lí
			System.out.println("bạn đã thực thi: " + sql);
			System.out.println("có: " + ketQua + " dòng thay đổi");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}
	
	public int suaKhachHang(KhachHang kh) {
		int ketQua = 0;
		try {
			// tạo connect
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo câu lệnh
			String sql = "update KhachHang set tenKhachHang = ?, sdt = ?, diaChi = ?, email = ? where maKhachHang = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, kh.getTenKhachHang());
			ps.setString(2, kh.getSdt());
			ps.setString(3, kh.getDiaChi());
			ps.setString(4, kh.getEmail());
			ps.setString(5, kh.getMaKhachHang());
			
			// thực thi
			ketQua = ps.executeUpdate();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có: " + ketQua + " dòng thay đổi");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}
}
