package student_app;
import index_max_min_pq.IndexMaxPQ;
import java.io.*;
import java.util.*;
public class TopMStudent {
    public static void main(String[] args) throws Exception {
        int M = 5;

        List<Student> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("sorting/src/student_app/students.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;  // bỏ dòng trống

            String[] p = line.split(",");
            if (p.length < 4) continue;  // bỏ dòng sai format

            list.add(new Student(
                    p[0].trim(),
                    p[1].trim(),
                    p[2].trim(),
                    Double.parseDouble(p[3].trim())
            ));
        }


        IndexMaxPQ<Student> pq = new IndexMaxPQ<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            pq.insert(i, list.get(i));
        }

        System.out.println("==== TOP " + M + " STUDENTS ====");
        for (int k = 0; k < M && !pq.isEmpty(); k++) {
            int idx = pq.delMax();
            System.out.println(list.get(idx));
        }
    }
}