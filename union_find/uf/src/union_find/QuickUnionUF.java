package union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickUnionUF {
    private int[] id;     // id[i] là cha của i
    private int count;    // số lượng nhóm (components)

    // Khởi tạo N site, mỗi site là một cây riêng biệt
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // Trả về số lượng nhóm hiện tại
    public int count() {
        return count;
    }

    // Kiểm tra hai phần tử có cùng nhóm hay không
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Tìm gốc của phần tử p (nút tự trỏ đến chính nó)
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    // Hợp nhất hai nhóm chứa p và q
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;  // nối gốc của p vào gốc của q
        count--;
    }

    // Chương trình chính đọc dữ liệu từ file tinyUF.txt
    public static void main(String[] args) {
        try {
            File file = new File("src/union_find/tinyUF.txt");
            Scanner sc = new Scanner(file);

            int N = sc.nextInt();
            QuickUnionUF uf = new QuickUnionUF(N);

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
