package sinhvien;

public class SinhVien {
    private String maSV;
    private String tenSV;
    private String ngaySinh;

    public SinhVien(String maSV, String tenSV, String ngaySinh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.ngaySinh = ngaySinh;
    }

    public String getMaSV() { return maSV; }
    public String getTenSV() { return tenSV; }
    public String getNgaySinh() { return ngaySinh; }

    @Override
    public String toString() {
        return maSV + " - " + tenSV + " - " + ngaySinh;
    }
}
