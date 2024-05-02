package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private LinhKien linhKien;
	private int soLuong;
	private double donGia;
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	
	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDon, LinhKien linhKien, int soLuong, double donGia) {
		super();
		this.hoaDon = hoaDon;
		this.linhKien = linhKien;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public LinhKien getLinhKien() {
		return linhKien;
	}
	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double tinhThanhTien() {
		return (double)soLuong*donGia;
	}
	
}
