package entity;

public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private String sdt;
	private String diaChi;
	private String email;
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public KhachHang(String maKhachHang, String tenKhachHang, String sdt, String diaChi, String email) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.email = email;
	}
	public KhachHang(String maKhachHang, String tenKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
	}
	public KhachHang() {
		super();
	}
	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}
	
}
