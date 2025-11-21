package student_app;

public class Student implements Comparable<Student> {
    private String hoDem;
    private String ten;
    private String ngaySinh;
    private double tbc;

    public Student(String hoDem, String ten, String ngaySinh, double tbc) {
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.tbc = tbc;
    }

    public double getTbc() { return tbc; }

    @Override
    public int compareTo(Student that) {
        // So sánh theo TBC để phục vụ cho MaxPQ
        return Double.compare(this.tbc, that.tbc);
    }

    @Override
    public String toString() {
        return hoDem + " " + ten + " | " + ngaySinh + " | TBC=" + tbc;
    }
}
