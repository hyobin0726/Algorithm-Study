import java.util.*;
import java.io.*;

public class Main {
    static int N, ans = 0;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        hit(0, 0);
        System.out.println(ans);
    }

    public static void hit(int idx, int cnt) {
        if (idx == N) {
            ans = Math.max(ans, cnt);
            return;
        }

        if (arr[idx][0] <= 0 || cnt == N - 1) {
            hit(idx + 1, cnt);
            return;
        }

        int nCnt = cnt;
        for (int i = 0; i < N; i++) {
            if (i == idx) continue;
            if (arr[i][0] <= 0) continue;

            arr[idx][0] -= arr[i][1];
            arr[i][0] -= arr[idx][1];
            if (arr[idx][0] <= 0) cnt++;
            if (arr[i][0] <= 0) cnt++;

            hit(idx + 1, cnt);

            arr[idx][0] += arr[i][1];
            arr[i][0] += arr[idx][1];
            cnt = nCnt;
        }
    }
}
