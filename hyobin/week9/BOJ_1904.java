import java.io.*;
import java.util.*;
public class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[1000001];
        dp[1]=1;
        dp[2] = 2;
        tile(n);
        System.out.println(dp[n]);

    }
    static void tile(int x){
        for(int i=3; i<=x; i++){
            dp[i] = (dp[i-1] + dp[i-2])%15746;
        }

    }

}
