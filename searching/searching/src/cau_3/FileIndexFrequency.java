package cau_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;

public class FileIndexFrequency {

    public static void main(String[] args) {

        // ST<word, ST<filename, frequency>>
        ST<String, ST<String, Integer>> index = new ST<>();

        // ==============================
        // ĐỌC FILE TỪ ARGS[]
        // ==============================
        for (String filename : args) {
            In in = new In(filename);

            while (!in.isEmpty()) {
                String word = in.readString();

                if (!index.contains(word))
                    index.put(word, new ST<String, Integer>());

                ST<String, Integer> freqMap = index.get(word);

                if (!freqMap.contains(filename))
                    freqMap.put(filename, 1);
                else
                    freqMap.put(filename, freqMap.get(filename) + 1);
            }
        }
        while (true) {
            StdOut.println();
            StdOut.println("===== FILE INDEX =====");
            StdOut.println("1. Tìm kiếm từ");
            StdOut.println("2. Thoát");
            StdOut.print("Chọn: ");

            // đọc dưới dạng string để tránh vỡ khi người dùng nhập chữ
            String choiceStr = StdIn.readString();

            // kiểm tra có phải số không
            if (!choiceStr.matches("\\d+")) {
                StdOut.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                continue; // quay lại menu
            }

            int choice = Integer.parseInt(choiceStr);

            // ==============================
            // 1. TÌM KIẾM TỪ
            // ==============================
            if (choice == 1) {
                StdOut.print("Nhập từ cần tìm: ");
                String query = StdIn.readString();

                if (!index.contains(query)) {
                    StdOut.println("Từ không tồn tại trong bất kỳ file nào.");
                    continue;
                }

                ST<String, Integer> freqMap = index.get(query);

                String[] files = new String[freqMap.size()];
                int i = 0;
                for (String f : freqMap.keys()) files[i++] = f;

                // sắp xếp giảm dần theo frequency
                for (int a = 0; a < files.length; a++) {
                    for (int b = a + 1; b < files.length; b++) {
                        if (freqMap.get(files[b]) > freqMap.get(files[a])) {
                            String temp = files[a];
                            files[a] = files[b];
                            files[b] = temp;
                        }
                    }
                }

                StdOut.println("File chứa từ \"" + query + "\" (giảm dần frequency):");
                for (String f : files)
                    StdOut.println("  " + f + " (" + freqMap.get(f) + ")");
            }

            // ==============================
            // 2. THOÁT CHƯƠNG TRÌNH
            // ==============================
            else if (choice == 2) {
                StdOut.println("Đã thoát chương trình.");
                break;
            }

            // ==============================
            // NHẬP SAI -> YÊU CẦU NHẬP LẠI
            // ==============================
            else {
                StdOut.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
        // ==============================
        // ===========================
    }
}
