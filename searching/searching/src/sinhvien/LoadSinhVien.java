package sinhvien;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class LoadSinhVien {

    public static void main(String[] args) {

        ST<String, SinhVien> st = new ST<>();

        In in = new In("students.csv");

        in.readLine();  // bỏ dòng tiêu đề

        while (!in.isEmpty()) {
            String line = in.readLine();
            if (line == null || line.isEmpty()) continue;

            String[] arr = line.split(",");

            String ma = arr[0];
            String ten = arr[1];
            String ngay = arr[2];

            SinhVien sv = new SinhVien(ma, ten, ngay);

            st.put(ma, sv);
        }

        // In thử
        for (String ma : st.keys()) {
            System.out.println(st.get(ma));
        }
    }
}
