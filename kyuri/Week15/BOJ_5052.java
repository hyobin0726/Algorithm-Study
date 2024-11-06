import java.util.*;
import java.io.*;

class TrieNode {
    TrieNode[] children = new TrieNode[10];
    boolean isEnd = false;

    TrieNode() {
        isEnd = false;
        for (int i = 0; i < 10; i++) {
            children[i] = null;
        }
    }
}

public class Main {
    public static TrieNode root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            root = new TrieNode();
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
                insert(arr[i]);
            }

            boolean ans = true;
            for (int i = 0; i < N; i++) {
                if (!search(arr[i])) {
                    ans = false;
                    break;
                }
            }

            System.out.println(ans ? "YES" : "NO");
        }
    }

    // Trie 생성
    public static void insert(String num) {
        TrieNode t = root;
        for (int i = 0; i < num.length(); i++) {
            int idx = num.charAt(i) - '0';
            if (t.children[idx] == null) {
                t.children[idx] = new TrieNode();
            }
            t = t.children[idx];
        }
        t.isEnd = true;
    }

    public static boolean search(String num) {
        TrieNode t = root;
        for (int i = 0; i < num.length(); i++) {
            int idx = num.charAt(i) - '0';
            if (t.isEnd) return false;
            t = t.children[idx];
        }
        return true;
    }
}
