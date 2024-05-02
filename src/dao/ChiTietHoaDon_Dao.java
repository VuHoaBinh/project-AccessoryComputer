package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LinhKien;

public class ChiTietHoaDon_Dao {
	private ArrayList<ChiTietHoaDon> ds;
	public ChiTietHoaDon_Dao (){
		
	}
	public ArrayList<ChiTietHoaDon> timTheoMaCTHD(String maCT) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon> ctdh = new ArrayList<ChiTietHoaDon>();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM ChiTietHoaDon where maHoaDon = ?");
		preparedStatement.setString(1, maCT);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			HoaDon hd = new HoaDon(rs.getString("maHoaDon"));
			LinhKien lk = new LinhKien(rs.getString("maLinhKien"));
			int soLuuong = rs.getInt(3);
			Double donGia = rs.getDouble(4);
			ChiTietHoaDon ct = new ChiTietHoaDon(hd, lk, soLuuong, donGia);
			ctdh.add(ct);
		}
		return ctdh;
	}
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?)");
		stmt.setString(1,cthd.getHoaDon().getMaHoaDon());
		stmt.setString(2,cthd.getLinhKien().getMaLinhKien());
		stmt.setInt(3, cthd.getSoLuong());
		stmt.setDouble(4, cthd.getDonGia());
		
		n= stmt.executeUpdate();
		return n>0;
	}
	public boolean updateSoLuongTon(ChiTietHoaDon ct) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		stmt = con.prepareStatement("UPDATE LinhKien \r\n"
				+ "SET LinhKien.soLuong = s.SoLuong - ct.SoLuong\r\n"
				+ "FROM LinhKien s \r\n"
				+ "JOIN ChiTietHoaDon ct ON s.maLinhKien = ct.maLinhKien \r\n"
				+ "JOIN HoaDon hd ON ct.maHoaDon = hd.maHoaDon\r\n"
				+ "WHERE s.maLinhKien = ? AND s.soLuong >= ? \r\n"
				+ "	AND ? = hd.maHoaDon");
		stmt.setString(1, ct.getLinhKien().getMaLinhKien());
		stmt.setInt(2, ct.getSoLuong());
		stmt.setString(3, ct.getHoaDon().getMaHoaDon());
		n = stmt.executeUpdate();
		
		return n > 0;
	}
}
