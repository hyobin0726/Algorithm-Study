import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<int[]> egg;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        egg = new ArrayList<>();
        ans = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken()); 
            egg.add(new int[]{a, b});
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx) {
        if (idx == n) {
            int brokenEggs = 0;
            for (int[] e : egg) {
                if (e[0] <= 0) brokenEggs++;
            }
            ans = Math.max(ans, brokenEggs);
            return;
        }

        if (egg.get(idx)[0] <= 0 || allBrokenExcept(idx)) {
            dfs(idx + 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == idx || egg.get(i)[0] <= 0) continue;

            // 계란 치기
            egg.get(idx)[0] -= egg.get(i)[1];
            egg.get(i)[0] -= egg.get(idx)[1];

            dfs(idx + 1);

            // 상태 복구 (백트래킹)
            egg.get(idx)[0] += egg.get(i)[1];
            egg.get(i)[0] += egg.get(idx)[1];
        }
    }

    static boolean allBrokenExcept(int idx) {
        int brokenCount = 0;
        for (int i = 0; i < n; i++) {
            if (i != idx && egg.get(i)[0] <= 0) {
                brokenCount++;
            }
        }
        return brokenCount == n - 1;
    }
}
