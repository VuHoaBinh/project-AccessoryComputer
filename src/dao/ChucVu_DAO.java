package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;

public class ChucVu_DAO {
	public ChucVu_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ChucVu> getAllChucVu(){
		ArrayList<ChucVu> dsChucVu = new ArrayList<ChucVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChucVu";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			System.out.println("Bạn đã truy vấn: "+ sql);
			while(rs.next()) {
				String maCV		= rs.getString("maChucVu");
				String tenCV	= rs.getString("tenChucVu");
				ChucVu chucVu	= new ChucVu(maCV, tenCV);
				dsChucVu.add(chucVu);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return dsChucVu;
	}
	public String getMaChucVuTheoTen(String tenChucVu) {
		String maChucVu = "";
		try {
			//tạo ket nối:
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			//tạo statement:
			String sql = "select maChucVu from ChucVu where tenChucVu = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenChucVu);
			//thực thi:
			ResultSet rs = ps.executeQuery();
			//xử lý: 
			System.out.println("Bạn đã thực thi: "+sql);
			while(rs.next()) {
				maChucVu = rs.getString("maChucVu");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return maChucVu;
	}
}
