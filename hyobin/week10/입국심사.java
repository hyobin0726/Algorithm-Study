package BOJ.이분탐색;
import java.io.*;
import java.util.*;

public class 입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] time = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i=0; i<n;i++){
            time[i] = Integer.parseInt(br.readLine());
            if (min>time[i]){
                min = time[i];

            }
        }


        long start = 1;
        long end = (long) min*m;
        long mid =0;
        long ans =0;

        while (start<= end){
            mid = (start+end)/2;
            long temp = 0;
            for(int i=0; i<n;i++){
                temp += (mid/time[i]);
            }

            if(temp>= m){
                ans=mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        System.out.println(ans);
    }
}
