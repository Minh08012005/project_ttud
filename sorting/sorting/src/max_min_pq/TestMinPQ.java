package max_min_pq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*************************************************************************
 *  MinPQ với Binary Heap (Min-Heap).
 *  Mỗi lần delMin() sẽ xóa và trả về phần tử NHỎ NHẤT.
 *************************************************************************/

class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;    // mảng heap, đánh số từ 1
    private int N = 0;   // số phần tử

    // Khởi tạo hàng đợi ưu tiên rỗng với sức chứa cho trước
    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // Thêm một phần tử vào priority queue
    public void insert(Key v) {
        pq[++N] = v;   // thêm vào cuối
        swim(N);       // bơi lên nếu cần
    }

    // Xóa và trả về phần tử NHỎ NHẤT
    public Key delMin() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        Key min = pq[1];        // phần tử nhỏ nhất ở gốc
        exch(1, N--);           // đưa phần tử cuối lên gốc
        pq[N + 1] = null;       // tránh rò bộ nhớ
        if (!isEmpty()) sink(1); // cho phần tử ở gốc chìm xuống
        return min;
    }

    // ----------------- Các hàm hỗ trợ nội bộ -----------------

    // so sánh pq[i] > pq[j] ?
    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    // hoán đổi hai phần tử trong mảng
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    // cho phần tử ở vị trí k “bơi lên” nếu NHỎ hơn cha
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    // cho phần tử ở vị trí k “chìm xuống” nếu LỚN hơn con
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;          // con trái
            if (j < N && greater(j, j + 1)) j++; // chọn con NHỎ hơn
            if (!greater(k, j)) break;         // nếu cha <= con thì dừng
            exch(k, j);                         // hoán đổi cha–con
            k = j;                              // tiếp tục xuống tầng dưới
        }
    }
}

// ----------------- Chương trình kiểm thử -----------------
public class TestMinPQ {
    public static void main(String[] args) throws FileNotFoundException {
        // Đọc từ file tinyPQ.txt (cùng thư mục với MinPQ.java)
        File file = new File("sorting/src/max_min_pq/tinyPQ.txt");
        Scanner sc = new Scanner(file);

        MinPQ<String> pq = new MinPQ<>(100);

        while (sc.hasNext()) {
            String item = sc.next();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) System.out.print(pq.delMin() + " ");
        }

        System.out.println("(" + pq.size() + " left on pq)");
        sc.close();
    }
}

