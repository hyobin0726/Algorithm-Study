/* 재귀, 완전 탐색 */
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> num = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) num.add(str.charAt(i)-'0');
            else op.add(str.charAt(i));
        }

        getResult(0, num.get(0));
        System.out.println(ans);
    }

    public static void getResult(int idx, int sum) {
        if (idx >= op.size()) {
            ans = Math.max(ans, sum);
            return;
        }
        // 괄호X
        int tmp = cal(idx, sum, num.get(idx + 1));
        dfs(idx + 1, tmp);
        // 괄호O
        if(idx + 1 < op.size()) {
            tmp = cal(idx + 1, num.get(idx + 1), num.get(idx + 2));
            int tmp2 = cal(idx, sum, tmp);
            dfs(idx + 2, tmp2);
        }
    }

    public static int cal(int op_idx, int a, int b) {
        if (op.get(op_idx).equals('+')) return a + b;
        else if (op.get(op_idx).equals('-')) return a - b;
        else return a * b;
    }
}
