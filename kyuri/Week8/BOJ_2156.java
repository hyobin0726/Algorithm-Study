import java.io.*;
import java.util.*;

/* 동적 계획법 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+2];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N+2];
        dp[1] = arr[1];
        dp[2] = dp[1] + arr[2];
        for (int i = 3; i < N + 1; i++) {
            dp[i] = Math.max(Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]), dp[i-1]);
        }

        System.out.println(dp[N]);
    }

}
