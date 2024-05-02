package entity;

public class ThuongHieu {
	private String maThuongHieu;
	private String tenThuongHieu;
	
	public ThuongHieu() {
		super();
	}

	public ThuongHieu(String maThuongHieu) {
		super();
		this.maThuongHieu = maThuongHieu;
	}

	public ThuongHieu(String maThuongHieu, String tenThuongHieu) {
		super();
		this.maThuongHieu = maThuongHieu;
		this.tenThuongHieu = tenThuongHieu;
	}

	public String getMaThuongHieu() {
		return maThuongHieu;
	}

	public void setMaThuongHieu(String maThuongHieu) {
		this.maThuongHieu = maThuongHieu;
	}

	public String getTenThuongHieu() {
		return tenThuongHieu;
	}

	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}

	@Override
	public String toString() {
		return "ThuongHieu [maThuongHieu=" + maThuongHieu + ", tenThuongHieu=" + tenThuongHieu + "]";
	}
	
}
