/* 재귀 */
import java.io.*;
import java.util.*;
 
class Solution {
    static int d, m, m3, y;
    static int[] plan;
    static int ans;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            m3 = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
 
            st = new StringTokenizer(br.readLine());
            plan = new int[12];
            for (int i = 0; i < 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }
 
            ans = y;
            dfs(0, 0);
            System.out.println("#" + t + " " + ans);
        }
    }
 
    public static void dfs(int mon, int cost) {
        if (mon >= 12) {
            ans = Math.min(ans, cost);
            return;
        }
        if (plan[mon] > 0) {
            dfs(mon + 1, cost + plan[mon] * d );
            dfs(mon + 1, cost + m);
            dfs(mon + 3, cost + m3);
        } else {
            dfs(mon + 1, cost);
        }
    }
}
