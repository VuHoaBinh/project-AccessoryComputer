package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiLinhKien;
import entity.ThuongHieu;

public class ThuongHieu_DAO {
public ThuongHieu_DAO() {
		
	};

	public ArrayList<ThuongHieu> getAllThuongHieu() {
		ArrayList<ThuongHieu> listThuongHieu = new ArrayList<ThuongHieu>();
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from ThuongHieu";
			PreparedStatement st = con.prepareStatement(sql);
			// thực thi
			ResultSet rs = st.executeQuery();
			// xử lí
			System.out.println("bạn đã truy vấn: " + sql);
			while (rs.next()) {
				String maThuongHieu = rs.getString("maThuongHieu");
				String tenThuongHieu = rs.getString("tenThuongHieu");
				ThuongHieu thuongHieu = new ThuongHieu(maThuongHieu, tenThuongHieu);
				listThuongHieu.add(thuongHieu);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listThuongHieu;
	}
	
	public String getMaThuongHieuTheoTen(String tenThuongHieu) {
		String maThuongHieu = "";
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select maThuongHieu from ThuongHieu where tenThuongHieu = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenThuongHieu);
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				maThuongHieu = rs.getString("maThuongHieu");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maThuongHieu;
	}
}
