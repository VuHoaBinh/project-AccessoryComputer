package entity;

import java.sql.Date;

public class LinhKien {
	private String maLinhKien;
	private String tenLinhKien;
	private int soLuong;
	private double giaNhap;
	private double giaBan;
	private int baoHanh;
	private String moTa;
	private LoaiLinhKien loaiLinhKien;
	private ThuongHieu thuongHieu;
	private Date ngayNhap;
	
	
	public LinhKien() {
		super();
	}
	
	public LinhKien(String maLinhKien) {
		super();
		this.maLinhKien = maLinhKien;
	}

	public LinhKien(String tenLinhKien, int soLuong, double giaNhap, double giaBan, int baoHanh, String moTa,
			LoaiLinhKien loaiLinhKien, ThuongHieu thuongHieu, Date ngayNhap) {
		super();
		this.tenLinhKien = tenLinhKien;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.baoHanh = baoHanh;
		this.moTa = moTa;
		this.loaiLinhKien = loaiLinhKien;
		this.thuongHieu = thuongHieu;
		this.ngayNhap = ngayNhap;
	}

	public LinhKien(String maLinhKien, String tenLinhKien, int soLuong, double giaNhap, double giaBan, int baoHanh,
			String moTa, LoaiLinhKien loaiLinhKien, ThuongHieu thuongHieu, Date ngayNhap) {
		super();
		this.maLinhKien = maLinhKien;
		this.tenLinhKien = tenLinhKien;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.baoHanh = baoHanh;
		this.moTa = moTa;
		this.loaiLinhKien = loaiLinhKien;
		this.thuongHieu = thuongHieu;
		this.ngayNhap = ngayNhap;
	}
	public String getMaLinhKien() {
		return maLinhKien;
	}
	public void setMaLinhKien(String maLinhKien) {
		this.maLinhKien = maLinhKien;
	}
	public String getTenLinhKien() {
		return tenLinhKien;
	}
	public void setTenLinhKien(String tenLinhKien) {
		this.tenLinhKien = tenLinhKien;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public int getBaoHanh() {
		return baoHanh;
	}
	public void setBaoHanh(int baoHanh) {
		this.baoHanh = baoHanh;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public LoaiLinhKien getLoaiLinhKien() {
		return loaiLinhKien;
	}
	public void setLoaiLinhKien(LoaiLinhKien loaiLinhKien) {
		this.loaiLinhKien = loaiLinhKien;
	}
	public ThuongHieu getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(ThuongHieu thuongHieu) {
		this.thuongHieu = thuongHieu;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	@Override
	public String toString() {
		return "LinhKien [maLinhKien=" + maLinhKien + ", tenLinhKien=" + tenLinhKien + ", soLuong=" + soLuong
				+ ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", baoHanh=" + baoHanh + ", moTa=" + moTa
				+ ", loaiLinhKien=" + loaiLinhKien + ", thuongHieu=" + thuongHieu + ", ngayNhap=" + ngayNhap + "]";
	}
	
}
