package BOJ.두포인터;
import java.io.*;
import java.util.*;
public class 두용액 {
    static int n;
    static int[] arr;
    static long min ;
    static int ans;
    static int ans1 ;
    static int ans2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st =new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        min = Long.MAX_VALUE;
        ans =0;
        ans1=0;
        ans2=0;
        for(int i=0; i<n-2;i++){
            two(i,i+1);
        }
        int[] result = {ans, ans1, ans2};
        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);

     }
     static void two(int x, int start){
         int s = start;
         int e = n-1;

         while (s< e){
             long temp =  (arr[x] + arr[s] + arr[e]);
             if(Math.abs(temp)<min){
                 min = Math.abs(temp);
                 ans = arr[x];
                 ans1 = arr[s];
                 ans2 = arr[e];
             }
             if(arr[x] + arr[s]+arr[e]==0){
//                 System.out.println(ans + " " + ans1 + " " + ans2);
                 return;
             }
             else if(arr[x] + arr[s]+arr[e]<0){
                 s++;
             }else {
                 e--;
             }
         }
     }

}
