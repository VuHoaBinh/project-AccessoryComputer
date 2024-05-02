package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_Dao {
	public HoaDon_Dao (){
		
	}
	public ArrayList<HoaDon> layDsHoaDon() {
		ArrayList<HoaDon> dshd = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from HoaDon join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang "
					+ "join NhanVien on HoaDon.maNhanVien = NhanVien.maNhanVien");
			while (rs.next()) {
				String maHD = rs.getString("maHoaDon");
				float thue = rs.getFloat("thue");
				Date ngayLapHD = rs.getDate("ngayLap");
				String tenKH = rs.getString("tenKhachHang");
				String maKH = rs.getString("maKhachHang");
				String tenNV = rs.getString("tenNhanVien");
				String maNV = rs.getString("maNhanVien");
				KhachHang kh = new KhachHang(maKH, tenKH);
				NhanVien nv =new NhanVien(maNV, tenNV);
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLapHD, thue);
				dshd.add(hd);
				
		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dshd;

	}
	public ArrayList<HoaDon> getHoaDonTheoMa(String id) {
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement =null;
		try {						
			String sql = "select * from HoaDon join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang join NhanVien on HoaDon.maNhanVien = NhanVien.maNhanVien where maHoaDon =?";
			statement=con.prepareStatement(sql);
			statement.setString(1, id);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String maHD = rs.getString("maHoaDon");
				float thue = rs.getFloat("thue");
				Date ngayLapHD = rs.getDate("ngayLap");
				String tenKH = rs.getString("tenKhachHang");
				String maKH = rs.getString("maKhachHang");
				String tenNV = rs.getString("tenNhanVien");
				String maNV = rs.getString("maNhanVien");
				KhachHang kh = new KhachHang(maKH, tenKH);
				NhanVien nv =new NhanVien(maNV, tenNV);
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLapHD, thue);
				dshd.add(hd);
			}
		} catch (SQLException e) {e.printStackTrace();				}
		finally {
			try {				statement.close();
			} catch (SQLException e) {				e.printStackTrace();		}		}
		return dshd;
	}

	public int laySoHoaDon() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int response = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select count(maHoaDon) from HoaDon");
			response =rs.getHoldability();
			while (rs.next())
				response =rs.getInt(1);
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return response;
	}
	public boolean themHoaDon(HoaDon p) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n=0;
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("insert into HoaDon values (?,?,?,?,?)");
			stmt.setString(1, p.getMaHoaDon());
			stmt.setDate(2,p.getNgayLap());
			stmt.setString(3,p.getMaKhachHang().getMaKhachHang());
			stmt.setString(4, p.getMaNhanVien().getMaNhanVien());
			stmt.setFloat(5, p.getThue());
			n= stmt.executeUpdate();
			return n>0;
	}
	public boolean xoaPhieuDatHang(String p){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		try {
			String sql = " delete from HoaDon where maHoaDon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
}
