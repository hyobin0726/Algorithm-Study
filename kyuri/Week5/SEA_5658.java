/* TreeSet */
import java.io.*;
import java.util.*;
 
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String str = br.readLine();
 
            TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder());
            for (int i = 0; i < N/4; i++) {
                str = str.substring(N-1) + str.substring(0, N-1);
                for (int j = 0; j < N; j += N/4) {
                    ts.add(str.substring(j, j + N/4));
                }
            }
 
            String[] ans = ts.toArray(new String[ts.size()]);
            System.out.println("#" + t + " " + Integer.parseInt(ans[K-1], 16));
        }
    }
}
