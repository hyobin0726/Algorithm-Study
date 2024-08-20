/* 백트래킹 */
import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static int[] op;
    static int[] nums;
    static int max_ans, min_ans;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            op = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }
             
            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
             
            max_ans = Integer.MIN_VALUE;
            min_ans = Integer.MAX_VALUE;
            dfs(1, nums[0]);
            System.out.println("#" + t + " " + (max_ans - min_ans));
        }
 
    }
     
    public static void dfs(int idx, int ans) {
        if (idx == N) {
            max_ans = Math.max(max_ans, ans);
            min_ans = Math.min(min_ans, ans);
            return;
        }
         
        int[] cal = {ans + nums[idx], ans - nums[idx], ans * nums[idx], ans / nums[idx]};
        if (op[0] > 0) {
            op[0] -= 1;
            dfs(idx + 1, cal[0]);
            op[0] += 1;
        }
        if (op[1] > 0) {
            op[1] -= 1;
            dfs(idx + 1, cal[1]);
            op[1] += 1;
        }
        if (op[2] > 0) {
            op[2] -= 1;
            dfs(idx + 1, cal[2]);
            op[2] += 1;
        }
        if (op[3] > 0) {
            op[3] -= 1;
            dfs(idx + 1, cal[3]);
            op[3] += 1;
        }
    }
}
