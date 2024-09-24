import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        arr=new int[n+1];
        dp=new int[n+1];
        st= new StringTokenizer(br.readLine());
        for(int i=1; i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1]=1;
        int max = dp[1];
        for(int i=2; i<=n; i++){
            //자기 자신의 경로
            dp[i]=1;
            for(int j=1;j<i;j++){
                if(arr[i]>arr[j] && dp[i]<=dp[j]){
                    dp[i] = dp[j]+1;
                }
            }
            if(max<dp[i]){
                max= dp[i];
            }

        }
        System.out.println(max);
    }
}
