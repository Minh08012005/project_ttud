package max_min_pq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// =================== Lớp MaxPQ ===================
class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // Mảng lưu trữ heap (đánh số từ 1)
    private int N = 0;  // Số phần tử hiện có

    // Khởi tạo hàng đợi ưu tiên rỗng với kích thước cho trước
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    // Thêm phần tử mới vào hàng đợi
    public void insert(Key v) {
        pq[++N] = v;   // thêm vào cuối mảng
        swim(N);       // điều chỉnh vị trí (bơi lên)
    }

    // Xóa và trả về phần tử lớn nhất
    public Key delMax() {
        Key max = pq[1];          // phần tử lớn nhất ở gốc
        exch(1, N--);             // đưa phần tử cuối lên gốc
        pq[N+1] = null;           // dọn ô nhớ cuối
        sink(1);                  // cho phần tử ở gốc “chìm xuống”
        return max;
    }

    // --------- Các hàm hỗ trợ nội bộ ---------
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }

    // Cho phần tử ở vị trí k “bơi lên” nếu lớn hơn cha
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    // Cho phần tử ở vị trí k “chìm xuống” nếu nhỏ hơn con
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++; // chọn con lớn hơn
            if (!less(k, j)) break;         // nếu cha ≥ con, dừng
            exch(k, j);
            k = j;
        }
    }
}

// =================== Chương trình kiểm thử ===================
public class TestMaxPQ {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("sorting/src/max_min_pq/tinyPQ.txt"));
        MaxPQ<String> pq = new MaxPQ<>(100);

        while (sc.hasNext()) {
            String item = sc.next();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) System.out.print(pq.delMax() + " ");
        }
        System.out.println("(" + pq.size() + " left on pq)");
    }
}

