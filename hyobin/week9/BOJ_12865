import java.io.*;
import java.util.*;
public class Main {
    static int n,k;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k+1][n+1];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if(x<=k) {
            for(int i=x; i<=k; i++) {
                dp[i][1] = y;
            }
        }
        for(int t = 2; t<=n; t++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            for(int j=1; j<=k; j++) {
                if(j<x) {
                    dp[j][t] = dp[j][t-1];
                }else {
                    dp[j][t] = Math.max(dp[j][t-1],dp[j-x][t-1]+y);
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}
