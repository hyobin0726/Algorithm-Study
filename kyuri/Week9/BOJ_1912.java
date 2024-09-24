import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] lst = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lst[i] = Integer.parseInt(st.nextToken());
        }

        int ans = lst[0];
        for (int i = 1; i < N; i++) {
            lst[i] = Math.max(lst[i], lst[i-1] + lst[i]);
            ans = Math.max(ans, lst[i]);
        }

        System.out.println(ans);
    }
}
