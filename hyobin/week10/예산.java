package BOJ.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int end=0;
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(end<arr[i]){
                end = arr[i];
            }
        }
        int m = Integer.parseInt(br.readLine());

        int start =0;

        int ans =0;
        int mid;

        while(start <= end){
            mid = (start+end)/2;
            int temp =0;
            for (int i =0; i<n;i++){
                if(arr[i]<=mid){
                    temp+= arr[i];
                }else {
                    temp+= mid;
                }
            }
            if(temp == m ){
                ans=mid;
                System.out.println(ans);
                return;
            } else if (temp>m) {
                end = mid-1;
//                ans = mid;
            } else {
                start = mid+1;
                ans = mid;
            }

        }
        System.out.println(ans);
    }
}
