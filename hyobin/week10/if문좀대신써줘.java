package BOJ.이분탐색;
import java.util.*;
import java.io.*;
public class if문좀대신써줘 {
    static int n,m;
    static String[] str;
    static int[] num;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        str = new String[n+1];
        num = new int[n+1];
        for(int i=1;i<=n; i++){
            st = new StringTokenizer(br.readLine());
            str[i] = st.nextToken();
            num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<m; i++){
            int temp = Integer.parseInt(br.readLine());
            int t = binary(temp);
            System.out.println(str[t]);

        }


    }
    static int binary(int x){

        int start =1;
        int end = n+1;
        int mid =0;
        int ans =0;
        while (start<= end){
            mid = (start+end)/2;
            if( x<=num[mid]){
//                ans = end;
                end = mid-1;

//                System.out.println(end + "end");
            } else if (x>num[mid]) {
                start = mid+1;
                ans = start;
//                System.out.println(start + "start");
            }
        }
//        System.out.println("---");

        return ans;
    }

}
