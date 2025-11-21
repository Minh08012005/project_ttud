package cau_1;

import edu.princeton.cs.algs4.ST;

public class Student {
    String id;
    String name;
    String lop;

    // ST<subject, score>
    ST<String, Double> scores = new ST<>();

    public Student(String id, String name, String lop) {
        this.id = id;
        this.name = name;
        this.lop = lop;
    }

    public void addScore(String subject, double score) {
        scores.put(subject, score);
    }
}

