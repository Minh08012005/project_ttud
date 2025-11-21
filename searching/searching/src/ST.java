import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st = new TreeMap<>();

    public ST() { }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();
        if (val == null) st.remove(key);
        else st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return st.containsKey(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException();
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException();
        return k;
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException();
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException();
        return k;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        System.setIn(new FileInputStream(new File("tinyST.txt")));
        ST<String, Integer> st = new ST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
