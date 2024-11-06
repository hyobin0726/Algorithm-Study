import java.util.*;
import java.io.*;

public class Main {
    static int T, N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            bt(1, 1, 1, 0, "1");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bt(int now, int prev, int op, int sum, String str) {
        if (now == N) {
            sum += (prev * op);
            if (sum == 0) sb.append(str).append('\n');
            return;
        }

        bt(now + 1, prev * 10 + (now + 1), op, sum, str + " " + String.valueOf(now+1));
        bt(now + 1, now + 1, 1, sum + (prev * op), str + "+" + String.valueOf(now+1));
        bt(now + 1, now + 1, -1, sum + (prev * op), str + "-" + String.valueOf(now+1));
    }

}
