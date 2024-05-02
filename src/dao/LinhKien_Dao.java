package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.ThuongHieu;

public class LinhKien_Dao {

	// LoaiLinhKien_DAO llk_dao = new LoaiLinhKien_DAO();
	public LinhKien_Dao() {
	}

	public ArrayList<LinhKien> getAllLinhKien() {
		ArrayList<LinhKien> listLinhKien = new ArrayList<LinhKien>();
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from LinhKien join LoaiLinhKien on LinhKien.maLoaiLinhKien = LoaiLinhKien.maLoaiLinhKien join ThuongHieu on LinhKien.maThuongHieu = ThuongHieu.maThuongHieu";
			PreparedStatement st = con.prepareStatement(sql);
			// thực thi
			ResultSet rs = st.executeQuery();
			// xử lí
			System.out.println("bạn đã truy vấn: " + sql);
			while (rs.next()) {
				String maLinhKien = rs.getString("maLinhKien");
				String tenLinhKien = rs.getString("tenLinhKien");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				int baoHanh = rs.getInt("baoHanh");
				String moTa = rs.getString("moTa");
				String maLoaiLinhKien = rs.getString("maLoaiLinhKien");
				String tenLoaiLinhKien = rs.getString("tenLoaiLinhKien");
				LoaiLinhKien loaiLinhKien = new LoaiLinhKien(maLoaiLinhKien, tenLoaiLinhKien);
				String maThuongHieu = rs.getString("maThuongHieu");
				String tenThuongHieu = rs.getString("tenThuongHieu");
				ThuongHieu thuongHieu = new ThuongHieu(maThuongHieu, tenThuongHieu);
				Date ngayNhap = rs.getDate("ngayNhap");
				LinhKien linhKien = new LinhKien(maLinhKien, tenLinhKien, soLuong, giaNhap, giaBan, baoHanh, moTa,
						loaiLinhKien, thuongHieu, ngayNhap);
				listLinhKien.add(linhKien);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listLinhKien;
	}

	public int themLinhKien(LinhKien linhKien) {
		int ketQua = 0;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo câu lệnh
			String sql = "INSERT INTO LinhKien (tenLinhKien, soLuong, giaNhap, giaBan, baoHanh, moTa, maLoaiLinhKien, maThuongHieu, ngayNhap) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, linhKien.getTenLinhKien());
			ps.setInt(2, linhKien.getSoLuong());
			ps.setDouble(3, linhKien.getGiaNhap());
			ps.setDouble(4, linhKien.getGiaBan());
			ps.setInt(5, linhKien.getBaoHanh());
			ps.setString(6, linhKien.getMoTa());
			ps.setString(7, linhKien.getLoaiLinhKien().getMaLoaiLinhKien());
			ps.setString(8, linhKien.getThuongHieu().getMaThuongHieu());
			ps.setDate(9, linhKien.getNgayNhap());
			// thực thi câu lệnh
			ketQua = ps.executeUpdate();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Số dòng thay đổi: " + ketQua);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;

	}

	public int xoaLinhKienTheoMa(String maLinhKien) {
		int ketQua = 0;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo câu lệnh
			String sql = "DELETE FROM LinhKien where maLinhKien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLinhKien);
			// thực thi
			ketQua = ps.executeUpdate();
			// xử lí kq
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Đã xóa: " + ketQua + " dòng");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;

	}

	public int suaLinhKien(LinhKien linhKien) {
		int ketQua = 0;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "UPDATE LinhKien SET tenLinhKien = ?, soLuong = ?, giaNhap = ?, giaBan = ?, baoHanh = ?, moTa = ?, maLoaiLinhKien = ?, maThuongHieu = ?, ngayNhap = ? WHERE maLinhKien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, linhKien.getTenLinhKien());
			ps.setInt(2, linhKien.getSoLuong());
			ps.setDouble(3, linhKien.getGiaNhap());
			ps.setDouble(4, linhKien.getGiaBan());
			ps.setInt(5, linhKien.getBaoHanh());
			ps.setString(6, linhKien.getMoTa());
			ps.setString(7, linhKien.getLoaiLinhKien().getMaLoaiLinhKien());
			ps.setString(8, linhKien.getThuongHieu().getMaThuongHieu());
			ps.setDate(9, linhKien.getNgayNhap());
			ps.setString(10, linhKien.getMaLinhKien());
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

	public LinhKien timLinhKienTheoMa(String maLinhKien) {
		LinhKien linhKien = null;
		try {
			// tạo kn
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			// tạo statement
			String sql = "select * from LinhKien join LoaiLinhKien on LinhKien.maLoaiLinhKien = LoaiLinhKien.maLoaiLinhKien join ThuongHieu on LinhKien.maThuongHieu = ThuongHieu.maThuongHieu where maLinhKien = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLinhKien);
			// thực thi
			ResultSet rs = ps.executeQuery();
			// xử lí
			System.out.println("Bạn đã thực thi: " + sql);
			while (rs.next()) {
				String tenLinhKien = rs.getString("tenLinhKien");
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				int baoHanh = rs.getInt("baoHanh");
				String moTa = rs.getString("moTa");
				String maLoaiLinhKien = rs.getString("maLoaiLinhKien");
				String tenLoaiLinhKien = rs.getString("tenLoaiLinhKien");
				LoaiLinhKien loaiLinhKien = new LoaiLinhKien(maLoaiLinhKien, tenLoaiLinhKien);
				String maThuongHieu = rs.getString("maThuongHieu");
				String tenThuongHieu = rs.getString("tenThuongHieu");
				ThuongHieu thuongHieu = new ThuongHieu(maThuongHieu, tenThuongHieu);
				System.out.println(thuongHieu);
				Date ngayNhap = rs.getDate("ngayNhap");
				linhKien = new LinhKien(maLinhKien, tenLinhKien, soLuong, giaNhap, giaBan, baoHanh, moTa, loaiLinhKien,
						thuongHieu, ngayNhap);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return linhKien;

	}

}
