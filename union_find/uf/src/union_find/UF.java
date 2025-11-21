package union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UF {
    private int[] id;
    private int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
        count--;
    }

    public static void main(String[] args) {
        try {
            // Đọc từ file tinyUF.txt (nằm cùng thư mục project)
            File file = new File("src/union_find/tinyUF.txt");
            Scanner sc = new Scanner(file);

            int N = sc.nextInt();
            UF uf = new UF(N);

            while (sc.hasNextInt()) {
                int p = sc.nextInt();
                int q = sc.nextInt();
                if (uf.connected(p, q)) continue;
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
            System.out.println(uf.count() + " components");

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file input!");
        }
    }
}
