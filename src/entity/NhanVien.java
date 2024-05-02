package entity;

import java.sql.Date;

public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private String diaChi;
	private String sdt;
	private String email;
	private ChucVu chucVu;
	private Date ngaySinh;
	private boolean gioiTinh;
	
	
	public NhanVien() {
		super();
	}
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	public NhanVien(String maNhanVien, String tenNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
	}
	public NhanVien(String maNhanVien, String tenNhanVien, String diaChi, String soDienThoai, String email,
			ChucVu chucVu, Date ngaySinh, boolean gioiTinh) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.diaChi = diaChi;
		this.sdt = soDienThoai;
		this.email = email;
		this.chucVu = chucVu;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String soDienThoai) {
		this.sdt = soDienThoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
}
