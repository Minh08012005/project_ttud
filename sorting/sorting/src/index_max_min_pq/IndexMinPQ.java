package index_max_min_pq;

import java.util.NoSuchElementException;

/**
 * An indexed min priority queue: each key has an associated index in [0..maxN-1].
 * Supports insert(index, key) and delMin() — remove & return index of minimum key.
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;         // maximum number of indices
    private int N;            // number of elements in pq
    private int[] pq;         // binary heap: pq[1..N] holds indices
    private int[] qp;         // inverse: qp[i] = position of index i in pq, or -1 if not present
    private Key[] keys;       // keys[i] = key associated with index i


    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        keys = (Key[]) new Comparable[maxN];
        pq = new int[maxN + 1];
        qp = new int[maxN];
        for (int i = 0; i < maxN; i++) qp[i] = -1;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("Index is already in the priority queue");
        keys[i] = key;
        pq[++N] = i;
        qp[i] = N;
        swim(N);
    }

    /**
     * Remove and return the index with the minimum key.
     */
    public int delMin() {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int minIndex = pq[1];
        exch(1, N--);
        sink(1);
        qp[minIndex] = -1;      // mark as removed
        keys[minIndex] = null;  // to avoid loitering
        pq[N + 1] = -1;
        return minIndex;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // For testing
    public Key keyOf(int i) {
        if (!contains(i)) throw new NoSuchElementException("Index is not in pq");
        return keys[i];
    }

    public static void main(String[] args) {
        // dãy chuỗi ví dụ
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        // tạo indexed min priority queue
        IndexMinPQ<String> pq = new IndexMinPQ<>(strings.length);

        // chèn mỗi chuỗi với chỉ số tương ứng
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // xoá dần phần tử nhỏ nhất và in ra (index + key)
        while (!pq.isEmpty()) {
            int i = pq.delMin();          // chỉ số có key nhỏ nhất
            String key = strings[i];      // hoặc pq.keyOf(i)
            System.out.println(i + " " + key);
        }
    }
}
