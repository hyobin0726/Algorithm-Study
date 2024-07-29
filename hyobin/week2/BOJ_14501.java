import java.util.*;
import java.io.*;

public class BOJ_14501 {
    static int n;
    static int[] t ;
    static int[] p ;
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];

        for (int i = 0 ; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        bfs(0,0);
        System.out.println(ans);
    }
    static void bfs(int day, int total){
        //종료조건
        if (day == n){
            if(ans <total){
                ans=total;
            }
            return ;
        }
        if (day>n) {
            return;
        }
        //상담 가능한 경우
        bfs( day + t[day], total+p[day]);
        //아닐 경우
        bfs(day+1, total);
    }
}
