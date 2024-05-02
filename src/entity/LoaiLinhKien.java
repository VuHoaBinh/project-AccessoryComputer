package entity;

public class LoaiLinhKien {
	private String maLoaiLinhKien;
	private String tenLoaiLinhKien;
	
	public LoaiLinhKien() {	
	}
	
	public LoaiLinhKien(String maLoaiLinhKien) {
		this.maLoaiLinhKien = maLoaiLinhKien;
	}


	public LoaiLinhKien(String maLoaiLinhKien, String tenLoaiLinhKien) {
		this.maLoaiLinhKien = maLoaiLinhKien;
		this.tenLoaiLinhKien = tenLoaiLinhKien;
	}
	public String getMaLoaiLinhKien() {
		return maLoaiLinhKien;
	}
	public void setMaLoaiLinhKien(String maLoaiLinhKien) {
		this.maLoaiLinhKien = maLoaiLinhKien;
	}
	public String getTenLoaiLinhKien() {
		return tenLoaiLinhKien;
	}
	public void setTenLoaiLinhKien(String tenLoaiLinhKien) {
		this.tenLoaiLinhKien = tenLoaiLinhKien;
	}

	@Override
	public String toString() {
		return "LoaiLinhKien [maLoaiLinhKien=" + maLoaiLinhKien + ", tenLoaiLinhKien=" + tenLoaiLinhKien + "]";
	}
	
	
	
}
