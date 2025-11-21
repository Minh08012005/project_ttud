package union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeightedQuickUnionUF {
    private int[] id;   // id[i] là cha của i
    private int[] sz;   // sz[i] là kích thước cây có gốc i
    private int count;  // số lượng nhóm (components)

    // Khởi tạo N site, mỗi site là một cây riêng biệt
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1; // mỗi cây ban đầu có kích thước 1
        }
    }

    // Trả về số nhóm hiện tại
    public int count() {
        return count;
    }

    // Tìm gốc của phần tử p
    private int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    // Kiểm tra hai phần tử có cùng nhóm không
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Hợp nhất hai nhóm theo trọng số
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // Gắn cây nhỏ hơn vào cây lớn hơn
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    // Đọc dữ liệu từ file và thực hiện
    public static void main(String[] args) {
        try {
            File file = new File("src/union_find/tinyUF.txt");
            Scanner sc = new Scanner(file);

            int N = sc.nextInt();
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);

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
