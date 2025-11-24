package bst;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;

import java.io.File;
import java.io.FileInputStream;

public class FrequencyCounter {

    public static void main(String[] args) throws Exception {

        int minlen = 1;  // độ dài tối thiểu của từ để đếm

        System.setIn(new FileInputStream(new File("tale.txt")));
        ST<String, Integer> st = new ST<>();

        int distinct = 0, words = 0;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) continue;

            if (st.contains(word))
                st.put(word, st.get(word) + 1);
            else {
                st.put(word, 1);
                distinct++;
            }
            words++;
        }

        String max = "";
        st.put(max, 0);

        for (String w : st.keys()) {
            if (st.get(w) > st.get(max))
                max = w;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
