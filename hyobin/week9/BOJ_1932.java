import java.io.*;
import java.util.*;
public class Main {
    static int[][] tri;
    static int[][] dp;
    static int max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n= Integer.parseInt(br.readLine());
        tri=new int[501][501];
        dp = new int[501][501];
        max=0;
        for(int i=1;i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=i;j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = tri[1][1];
        pascal(n);
        for(int i=1; i<=n;i++){
            if(max<dp[n][i]){
                max=dp[n][i];
            }
        }System.out.println(max);
    }
    static void pascal(int x){
        for(int i=2; i<=x; i++){
            for(int j=1; j<=i; j++){
                if(j==1){
                    dp[i][1] = dp[i-1][1]+ tri[i][1];
                }
                else if(j==i){
                    dp[i][j] = dp[i-1][j]+ tri[i][j];
                }
                dp[i][j] = Math.max(dp[i-1][j-1]+ tri[i][j],dp[i-1][j]+ tri[i][j]);
            }
        }
    }

}
