/* 스택, StringBuilder */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();
        int len = bomb.length;

        Stack<Character> s = new Stack<>();
        for (char value : str) {
            s.add(value);
            if (s.size() >= len) {
                boolean check = true;
                for (int j = 0; j < len; j++) {
                    if (s.get(s.size() - len + j) != bomb[j]) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    for (int j = 0; j < len; j++) {
                        s.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : s) {
            sb.append(c);
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
