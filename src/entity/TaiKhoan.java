package entity;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private NhanVien nhanVien;
	public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String tenDangNhap) {
		super();
		this.tenDangNhap = tenDangNhap;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
}
