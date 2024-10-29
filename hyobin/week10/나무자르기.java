package BOJ.이분탐색;
import java.io.*;
import java.util.*;
public class 나무자르기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] tree = new long[n];
        st = new StringTokenizer(br.readLine());
        long max =0;
        for(int i=0; i<n; i++){
            tree[i] = Long.parseLong(st.nextToken());
            if(tree[i]>max){
                max=tree[i];
            }
        }

        long start =0;
        long end = max;
        long mid=0;
        long ans =0;

        while (start<= end){
            mid = (start+end)/2;
            long temp=0;
            for(int i=0; i<n; i++){
                if(mid<tree[i]){
                    temp+= (tree[i]-mid);
                }
            }
            if(temp>=m){
                ans=mid;
                start = mid +1;
            }
            else {
                end = mid-1;

            }
        }
        System.out.println(ans);
    }
}
