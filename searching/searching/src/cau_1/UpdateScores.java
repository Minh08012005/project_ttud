package cau_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class UpdateScores {

    public static void main(String[] args) {

        // ============================
        // 1. Đọc file sinh viên
        // ============================
        ST<String, Student> studentST = new ST<>();

        In svFile = new In("sinhvien.csv");
        svFile.readLine(); // bỏ dòng tiêu đề

        while (!svFile.isEmpty()) {
            String line = svFile.readLine();
            if (line == null || line.isEmpty()) continue;

            String[] parts = line.split(",");
            String id = parts[0];
            String name = parts[1];
            String lop = parts[2];

            Student stu = new Student(id, name, lop);
            studentST.put(id, stu);
        }

        // ============================
        // 2. Đọc các file điểm môn
        // args[] = danh sách file điểm
        // ============================
        for (String filename : args) {

            String subject = filename.replace(".csv", "");

            In in = new In(filename);

            while (!in.isEmpty()) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) continue;

                String[] row = line.split(",");
                String id = row[0];
                double score = Double.parseDouble(row[1]);

                // Nếu sinh viên tồn tại → ghi điểm
                if (studentST.contains(id)) {
                    studentST.get(id).addScore(subject, score);
                }
            }
        }

        // ============================
        // 3. In kết quả
        // ============================
        for (String id : studentST.keys()) {
            Student s = studentST.get(id);

            StdOut.println("===== " + s.id + " - " + s.name + " (" + s.lop + ") =====");

            // In điểm theo từng môn
            for (String subject : s.scores.keys()) {
                StdOut.println(subject + ": " + s.scores.get(subject));
            }

            StdOut.println();
        }
    }
}
