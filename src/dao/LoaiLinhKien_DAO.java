package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiLinhKien;

public class LoaiLinhKien_DAO {
	public LoaiLinhKien_DAO() {

	};

	public ArrayList<LoaiLinhKien> getAllLoaiLinhKien() {
		ArrayList<LoaiLinhKien> listLoaiLinhKien = new ArrayList<LoaiLinhKien>();
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from LoaiLinhKien";
			PreparedStatement st = con.prepareStatement(sql);
			// thực thi
			ResultSet rs = st.executeQuery();
			// xử lí
			System.out.println("bạn đã truy vấn: " + sql);
			while (rs.next()) {
				String maLoaiLinhKien = rs.getString("maLoaiLinhKien");
				String tenLoaiLinhKien = rs.getString("tenLoaiLinhKien");
				LoaiLinhKien loaiLinhKien = new LoaiLinhKien(maLoaiLinhKien, tenLoaiLinhKien);
				listLoaiLinhKien.add(loaiLinhKien);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listLoaiLinhKien;
	}

//	public String getTenLoaiLinhKienTheoMa(String maLoaiLinhKien) {
//		String tenLoaiLinhKien = "";
//		try {
//			// tạo kn
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnection();
//			// tạo statement
//			String sql = "select tenLoaiLinhKien from LoaiLinhKien where maLoaiLinhKien = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, maLoaiLinhKien);
//			// thực thi
//			ResultSet rs = ps.executeQuery();
//			// xử lí
//			System.out.println("Bạn đã thực thi: " + sql);
//			while (rs.next()) {
//				tenLoaiLinhKien = rs.getString("tenLoaiLinhKien");
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return tenLoaiLinhKien;
//	}
	
	public String getMaLoaiLinhKienTheoTen(String tenLoaiLinhKien) {
		String maLoaiLinhKien = "";
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select maLoaiLinhKien from LoaiLinhKien where tenLoaiLinhKien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenLoaiLinhKien);
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				maLoaiLinhKien = rs.getString("maLoaiLinhKien");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maLoaiLinhKien;
	}

//	public static void main(String[] args) {
//		try {
//			ConnectDB.getInstance().connect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LoaiLinhKien_DAO ld = new LoaiLinhKien_DAO();
//		System.out.println(ld.getTenLoaiLinhKienTheoMa("LLK001"));
//	}

}
