/* 순열, 완전 탐색 (다른 방법으로 풀어보기) */
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] board;
    static int[][] change;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        change = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            change[i][0] = Integer.parseInt(st.nextToken()) - 1;
            change[i][1] = Integer.parseInt(st.nextToken()) - 1;
            change[i][2] = Integer.parseInt(st.nextToken());
        }

        perm(new int[K], 0, new int[K]);
        System.out.println(ans);
    }

    public static void perm(int[] line, int depth, int[] visited) {
        if (depth == K) {
            cycle(line);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i] != 1) {
                visited[i] = 1;
                line[depth] = i;
                perm(line, depth + 1, visited);
                visited[i] = 0;;
            }
        }
    }

    public static int[][] copy() {
        int[][] temp = new int[N][M];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }

    public static void cycle(int[] arr) {
        int[][] tmp = copy();
        for(int k = 0; k < K; k++) {
            int r = change[arr[k]][0];
            int c = change[arr[k]][1];
            int S = change[arr[k]][2];

            for(int s = 1; s <= S; s++) {
                int upTmp = tmp[r - s][c + s];
                for(int y = c + s; y > c - s; y--) {
                    tmp[r - s][y] = tmp[r - s][y - 1];
                }
                int rightTmp = tmp[r + s][c + s];
                for(int x = r + s; x > r - s; x--) {
                    tmp[x][c + s] = tmp[x - 1][c + s];
                }
                tmp[r - s + 1][c + s] = upTmp;
                int leftTmp = tmp[r + s][c - s];
                for(int y = c - s; y < c + s; y++) {
                    tmp[r + s][y] = tmp[r + s][y + 1];
                }
                tmp[r + s][c + s - 1] = rightTmp;
                for(int x = r - s; x < r + s; x++) {
                    tmp[x][c - s] = tmp[x + 1][c - s];
                }
                tmp[r + s - 1][c - s] = leftTmp;
            }
        }
        minAnswer(tmp);
    }

    public static void minAnswer(int[][] tmp) {
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int item : tmp[i]) {
                sum += item;
            }
            ans = Math.min(ans, sum);
        }
    }
}
