package SWEA;
import java.io.*;
import java.util.*;
public class SWEA_1952 {
    static int[] money;
    static int[] month;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        money = new int[4];
        month = new int[13];
        int t = Integer.parseInt(br.readLine());
        for (int test=1; test<t+1; test++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++){
                money[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<13; i++){
                month[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[13];
            for(int i=1; i<3;i++){
                if(money[1]/money[0]>month[i]){
                    dp[i] = dp[i-1]+(month[i]*money[0]);
                }
                else {
                    dp[i] = dp[i-1]+money[1];
                }
            }

            for(int i=3; i<13; i++){
               if(money[1]/money[0]>month[i]){
                   dp[i] = Math.min(dp[i-1]+(month[i]*money[0]),dp[i-3]+money[2]);
               }else {
                   dp[i] = Math.min(dp[i-1]+money[1],dp[i-3]+money[2]);
               }

            }
            if(dp[12]>money[3]){
                dp[12] =money[3];
            }
            System.out.println("#"+ test + " " +dp[12]);

        }
    }


}
