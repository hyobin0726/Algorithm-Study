import java.util.*;

/* 동적 계획법 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int mod = 1000000000;

        int[][] Dp = new int [N+1][10];
        for(int i = 0;i <= 9; i++)  Dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) Dp[i][j] = Dp[i-1][1] % mod;
                else if (j == 9) Dp[i][j] = Dp[i-1][8] % mod;
                else Dp[i][j] = (Dp[i-1][j-1] % mod+Dp[i-1][j+1] % mod) % mod;
            }
        }

        int sum = 0;
        for(int i = 1; i <= 9; i++) {
            sum += Dp[N][i];
            sum %= mod;
        }
        System.out.println(sum % mod);
    }
}
