import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] lst = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                lst[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            lst[i][0] += Math.min(lst[i-1][1], lst[i-1][2]);
            lst[i][1] += Math.min(lst[i-1][0], lst[i-1][2]);
            lst[i][2] += Math.min(lst[i-1][1], lst[i-1][0]);
        }

        System.out.println(Math.min(Math.min(lst[N-1][0], lst[N-1][1]), lst[N-1][2]));
    }
}
