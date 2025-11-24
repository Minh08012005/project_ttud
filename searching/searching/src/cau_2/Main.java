package cau_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Student, BangDiem> map = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("sinhvien.csv"));

            br.readLine(); // bỏ dòng tiêu đề

            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");

                String maSV = arr[0];
                String tenSV = arr[1];
                String ngaySinh = arr[2];

                Student s = new Student(maSV, tenSV, ngaySinh);

                // điểm
                s.setDiem("Toan", Double.parseDouble(arr[3]));
                s.setDiem("Ly",   Double.parseDouble(arr[4]));
                s.setDiem("Hoa",  Double.parseDouble(arr[5]));
                s.setDiem("Van",  Double.parseDouble(arr[6]));

                map.put(s, s.getBangDiem());
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // In TBC từng sinh viên
        for (Student s : map.keySet()) {
            System.out.println(s + " | TBC = " + s.tinhTBC());
        }
    }
}

