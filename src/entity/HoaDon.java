package entity;

import java.sql.Date;
import java.util.ArrayList;

public class HoaDon {
	private String maHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Date ngayLap;
	private float thue;
	private ArrayList<ChiTietHoaDon> listChiTietHoaDon;
	
	public HoaDon() {
		this.listChiTietHoaDon = new ArrayList<>();
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	public HoaDon(String maHoaDon, KhachHang maKhachHang, NhanVien maNhanVien, Date ngayLap, float thue) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = maKhachHang;
		this.nhanVien = maNhanVien;
		this.ngayLap = ngayLap;
		this.thue = thue;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public KhachHang getMaKhachHang() {
		return khachHang;
	}
	public void setMaKhachHang(KhachHang maKhachHang) {
		this.khachHang = maKhachHang;
	}
	public NhanVien getMaNhanVien() {
		return nhanVien;
	}
	public void setMaNhanVien(NhanVien maNhanVien) {
		this.nhanVien = maNhanVien;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public float getThue() {
		return thue;
	}
	public void setThue(float thue) {
		this.thue = thue;
	}
	public double tinhTongTien() {
		double tongTien=0;
		for(ChiTietHoaDon ct : this.listChiTietHoaDon) {
			tongTien += ct.getSoLuong() * ct.getDonGia();
		}
		return tongTien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public double tinhThue() {
		return 0.1 * this.tinhTongTien();
	}	
	public void addItem(ChiTietHoaDon ct) {
		this.listChiTietHoaDon.add(ct);
	}
	public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
		return listChiTietHoaDon;
	}
	public void setListChiTietHoaDon(ArrayList<ChiTietHoaDon> listChiTietHoaDon) {
		this.listChiTietHoaDon = listChiTietHoaDon;
	}
}
