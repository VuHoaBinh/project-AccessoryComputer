create database QLLinhKien
go
use QLLinhKien
--Tạo lệnh chạy trình tự mã
CREATE SEQUENCE dbo.SeqLinhKien
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    CYCLE;
CREATE SEQUENCE dbo.SeqKhachHang
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    CYCLE;

create sequence dbo.SeqNhanVien
	Start with 1
	increment by 1
	minvalue 1
	maxvalue 99999
	cycle;

CREATE SEQUENCE dbo.SeqHoaDon
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999
    CYCLE;

-- Linh kiện

CREATE TABLE LoaiLinhKien(
   maLoaiLinhKien  VARCHAR (10) primary key,
   tenLoaiLinhKien NVARCHAR (30) NOT NULL,     
);
CREATE TABLE ThuongHieu(
   maThuongHieu  VARCHAR (10) primary key,
   tenThuongHieu NVARCHAR (30) NOT NULL,     
);


INSERT INTO LoaiLinhKien (maLoaiLinhKien, tenLoaiLinhKien) VALUES
('LLK001', N'Chuột'),
('LLK002', N'Bàn phím'),
('LLK003', N'Màn hình'),
('LLK004', N'Tai nghe'),
('LLK005', N'CPU'),
('LLK006', N'RAM'),
('LLK007', N'VGA'),
('LLK008', N'SSD'),
('LLK009', N'Ổ cứng HDD'),
('LLK010', N'Mainboard'),
('LLK011', N'Nguồn máy tính'),
('LLK012', N'Case máy tính'),
('LLK013', N'Máy in'),
('LLK014', N'Máy scan'),
('LLK015', N'USB Flash Drive'),
('LLK016', N'Bộ lọc không khí máy tính'),
('LLK017', N'Cooling fan'),
('LLK018', N'Bộ điều khiển gamepad'),
('LLK019', N'Bộ loa máy tính'),
('LLK020', N'Microphone');


INSERT INTO ThuongHieu (maThuongHieu, tenThuongHieu) VALUES
('TH001', N'Dell'),
('TH002', N'Asus'),
('TH003', N'Baseus'),
('TH004', N'HOCO'),
('TH005', N'SamSung'),
('TH006', N'Logitech'),
('TH007', N'Lenovo'),
('TH008', N'HP'),
('TH009', N'Sony'),
('TH010', N'Apple'),
('TH011', N'Microsoft'),
('TH012', N'Panasonic'),
('TH013', N'Philips'),
('TH014', N'LG'),
('TH015', N'Acer'),
('TH016', N'Xiaomi'),
('TH017', N'Huawei'),
('TH018', N'Canon'),
('TH019', N'Epson'),
('TH020', N'JBL');


CREATE TABLE LinhKien (
    maLinhKien VARCHAR(10) PRIMARY KEY DEFAULT ('LK' + RIGHT('000000' + FORMAT(NEXT VALUE FOR dbo.SeqLinhKien, '000000'), 6)),
    tenLinhKien NVARCHAR(50) NOT NULL,
    soLuong INT NULL,
    giaNhap FLOAT NOT NULL,
    giaBan FLOAT NOT NULL,
    baoHanh INT NULL,
    moTa NVARCHAR(255) NULL,
    maLoaiLinhKien VARCHAR(10) REFERENCES LoaiLinhKien(maLoaiLinhKien),
    maThuongHieu VARCHAR(10) REFERENCES ThuongHieu(maThuongHieu),
    ngayNhap DATE
);

	INSERT INTO LinhKien (tenLinhKien, soLuong, giaNhap, giaBan, baoHanh, moTa, maLoaiLinhKien, maThuongHieu, ngayNhap) VALUES
(N'Chuột không dây', 100, 50000, 1200000, 12, N'Chuột không dây chất lượng cao', 'LLK001', 'TH001', '2023-01-01'),
(N'Bàn phím cơ', 80, 80000, 1500000, 24, N'Bàn phím cơ có đèn RGB', 'LLK002', 'TH002', '2023-01-02'),
(N'Màn hình 24 inch', 50, 1500000, 3000000, 36, N'Màn hình LED IPS 24 inch', 'LLK003', 'TH003', '2023-01-03'),
(N'Tai nghe Gaming', 120, 120000, 1800000, 18, N'Tai nghe chơi game cao cấp', 'LLK004', 'TH004', '2023-01-04'),
(N'CPU Intel i7', 30, 5000000, 7000000, 12, N'CPU Intel Core i7 10th gen', 'LLK005', 'TH005', '2023-01-05'),
(N'RAM DDR4 16GB', 60, 500000, 800000, 24, N'RAM DDR4 dung lượng 16GB', 'LLK006', 'TH006', '2023-01-06'),
(N'VGA NVIDIA GTX 1660', 40, 2000000, 3500000, 12, N'Card đồ họa NVIDIA GTX 1660', 'LLK007', 'TH007', '2023-01-07'),
(N'SSD 500GB', 70, 800000, 1200000, 36, N'Ổ cứng SSD dung lượng 500GB', 'LLK008', 'TH008', '2023-01-08'),
(N'Ổ cứng HDD 1TB', 50, 600000, 900000, 24, N'Ổ cứng HDD dung lượng 1TB', 'LLK009', 'TH009', '2023-01-09'),
(N'Mainboard ASUS', 25, 1500000, 2000000, 18, N'Mainboard ASUS chất lượng', 'LLK010', 'TH010', '2023-01-10'),
(N'Máy in Laser', 15, 4000000, 6000000, 24, N'Máy in Laser đen trắng', 'LLK011', 'TH011', '2023-01-11'),
(N'Máy quét scanner', 12, 3000000, 4500000, 18, N'Máy quét scanner màu', 'LLK012', 'TH012', '2023-01-12'),
(N'Máy ảnh DSLR', 8, 8000000, 12000000, 12, N'Máy ảnh DSLR chuyên nghiệp', 'LLK013', 'TH013', '2023-01-13'),
(N'USB Flash Drive 32GB', 40, 50000, 80000, 12, N'USB Flash Drive dung lượng 32GB', 'LLK014', 'TH014', '2023-01-14'),
(N'Bộ lọc không khí máy tính', 20, 150000, 250000, 12, N'Bộ lọc không khí máy tính', 'LLK015', 'TH015', '2023-01-15'),
(N'Cooling fan RGB', 30, 100000, 180000, 18, N'Cooling fan RGB', 'LLK016', 'TH016', '2023-01-16'),
(N'Bộ điều khiển gamepad', 25, 200000, 350000, 24, N'Bộ điều khiển gamepad', 'LLK017', 'TH017', '2023-01-17'),
(N'Bộ loa máy tính', 18, 300000, 500000, 12, N'Bộ loa máy tính', 'LLK018', 'TH018', '2023-01-18'),
(N'Microphone chuyên nghiệp', 10, 700000, 1000000, 12, N'Microphone chuyên nghiệp', 'LLK019', 'TH019', '2023-01-19'),
(N'Màn hình cảm ứng', 8, 3000000, 5000000, 24, N'Màn hình cảm ứng', 'LLK020', 'TH020', '2023-01-20');

-- khách hàng

CREATE TABLE KhachHang(
   maKhachHang  VARCHAR (10) primary key DEFAULT ('KH' + RIGHT('000000' + FORMAT(NEXT VALUE FOR dbo.SeqKhachHang, '000000'), 6)),
   tenKhachHang NVARCHAR (50) NOT NULL,
   sdt NVARCHAR(11) not null,
   diaChi Nvarchar(150) not null,
   email Nvarchar(50) not null,
);
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Khách vãng lai',N'Không có',N'Không có',N'Không có')
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Nguyễn Văn Hoàng','0321354213',N'12 Nguyễn Văn Nghi, Gò Vấp, Tp.HCM','hoang@gmail.com')
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Nguyễn Lê Hoàng','0912521372',N'34 Nguyễn Văn Bảo, Gò Vấp, TP.HCM','lehoang@gmail.com')
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Lê Văn Phong','0932137321',N'22 Nguyễn Duy Phương, Quận 10, Tp.HCM','phong@gmail.com')
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Đinh Văn Trường','0923172832',N'33 Mai Chí Thọ, Quận 9, TP.HCM','truong@gmail.com')
insert KhachHang ([tenKhachHang],[sdt],[diaChi],[email]) values (N'Lê Văn Đỉnh','0393472182',N'21 Nguyễn Thái Sơn, Gò Vấp, TP.HCM','dinh@gmail.com')

-- nhân viên

create table ChucVu(
	maChucVu	varchar(10) primary key, 
	tenChucVu	nvarchar(40) not null
);
--insert chức vụ
insert into ChucVu(maChucVu, tenChucVu) values
('NVQL', N'Nhân Viên Quản Lý'),
('NVBH', N'Nhân Viên Bán Hàng');


create table NhanVien(
	maNhanVien			varchar(10) primary key default ('NV' + right('000000' + format(next value for dbo.SeqNhanVien, '000000'),6)),
	tenNhanVien			nvarchar(50) not null,
	diaChi				nvarchar(150) not null,
	sdt					varchar(11),
	email				varchar(50),
	maChucVu			varchar(10) references ChucVu(maChucVu),
	ngaySinh			Date,
	gioiTinh			bit not null,
);
insert into NhanVien(tenNhanVien, diaChi, sdt, email, maChucVu, ngaySinh, gioiTinh) 
values 
(N'Trần Trọng Huy', N'3 Đường số 10, hiệp bình chánh, thủ đức', '0379499255', 'fastdominicracing@gmail.com', 'NVQL', '2003-02-14',0),
(N'Võ Công Tuấn Anh', N'p11, Gò Vấp, TPHCM', '0123456781', 'employee1@example.com', 'NVQL', '1990-01-15', 0),
(N'Đinh Tiến Đạt', N'102/65 Lê Văn Thọ, Phường 10, quận Gò Vấp, TPHCM', '0123456782', 'employee2@example.com', 'NVQL', '1991-03-20', 0),
(N'Đỗ Tiến Lượng', N'1423/218 kp Vườn Dừa, Phước Tân, Biên Hoà', '0123456783', 'employee3@example.com', 'NVQL', '1992-05-25', 0),
(N'Nguyễn Thị Thu Hà', N'123 Đường ABC, Quận 1, TP.HCM', '0123456784', 'employee4@example.com', 'NVBH', '1995-08-10', 1),
(N'Lê Văn An', N'456 Đường XYZ, Quận 5, TP.HCM', '0123456785', 'employee5@example.com', 'NVBH', '1988-12-03', 1),
(N'Trần Ngọc Linh', N'789 Đường LMN, Quận 7, TP.HCM', '0123456786', 'employee6@example.com', 'NVBH', '1993-04-18', 1),
(N'Phạm Thị Kim Anh', N'101 Đường QWE, Quận 10, TP.HCM', '0123456787', 'employee7@example.com', 'NVBH', '1994-06-30', 1);


-- tài khoản

CREATE TABLE TaiKhoan (
    tenDangNhap VARCHAR(255) PRIMARY KEY,
    matKhau VARCHAR(255) NOT NULL,
    maNhanVien VARCHAR (10) NOT NULL references NhanVien(maNhanVien),
);

INSERT INTO TaiKhoan (tenDangNhap, matKhau, maNhanVien)
VALUES
    ('user1', '123456', 'NV000001'),
    ('user2', '123456', 'NV000002'),
    ('user3', '123456', 'NV000003'),
	('user4', '123456', 'NV000004'),
	('user5', '123456', 'NV000005')
	


-- hóa đơn

CREATE TABLE HoaDon(
   maHoaDon  VARCHAR (10) primary key check(maHoaDon like 'HD%'),
   ngayLap Date  NOT NULL,
   maKhachHang varchar(10) null,
   maNhanVien varchar(10) not null,
   thue real not null,
   Constraint F_HD_KH Foreign key(maKhachHang) references KhachHang(maKhachHang),
   Constraint F_HD_NV Foreign key(maNhanVien) references NhanVien(maNhanVien)
);


insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000001','2023-07-01','KH000001','NV000001','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000002','2023-08-02','KH000002','NV000002','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000003','2023-09-03','KH000003','NV000003','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000004','2023-06-06','KH000004','NV000004','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000005','2023-05-01','KH000005','NV000005','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000006','2023-06-03','KH000002','NV000002','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000007','2023-02-06','KH000001','NV000001','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000008','2023-01-03','KH000003','NV000003','10');
insert HoaDon ([maHoaDon],[ngayLap],[maKhachHang],[maNhanVien],[thue]) values ('HD000009','2023-06-08','KH000004','NV000002','10');

-- chi tiết hóa đơn

create table ChiTietHoaDon(
	maHoaDon varchar(10),
	maLinhKien varchar(10) not null,
	soLuong int not null,
	donGia float not null,
	Constraint F_CTHD_HD Foreign key(maHoaDon) references HoaDon(maHoaDon),
	-- tạo liên kết đến bảng linh kiện
    Constraint F_CTHD_LK Foreign key(maLinhKien) references LinhKien(maLinhKien)
);

insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000001','LK000001','20','40000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000002','LK000002','10','30000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000003','LK000003','15','20000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000004','LK000004','11','21000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000005','LK000005','31','50000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000006','LK000002','3','30000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000007','LK000002','1','30000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000008','LK000003','2','20000');
insert ChiTietHoaDon([maHoaDon],[maLinhKien],[soLuong],[donGia]) values ('HD000009','LK000004','7','21000');


select SUM (soLuong*donGia) AS tongTien
from ChiTietHoaDon join HoaDon on ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon
group by ChiTietHoaDon.maHoaDon

