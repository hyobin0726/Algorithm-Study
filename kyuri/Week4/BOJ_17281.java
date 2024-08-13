/* 순열, 완전 탐색 */
import java.io.*;
import java.util.*;

public class Main {
    static int[] visited = new int[9];
    static int[] p_num = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    static int[] line = new int[9];
    static int N;
    static int[][] li;
    static int maxScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        li = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(line, 0);
        System.out.println(maxScore);
    }

    static void perm(int[] line, int depth) {
        if (depth == 9) {
            if (line[3] == 0) {
                calculateScore(line);
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i] != 1) {
                visited[i] = 1;
                line[depth] = p_num[i];
                perm(line, depth + 1);
                visited[i] = 0;;
            }
        }
    }

    public static int moveBases(boolean[] base, int result) {
        int runsScored = 0;
        switch (result) {
            case 1: // 1루타
                runsScored += base[2] ? 1 : 0;
                base[2] = base[1];
                base[1] = base[0];
                base[0] = true;
                break;
            case 2: // 2루타
                runsScored += base[2] ? 1 : 0;
                runsScored += base[1] ? 1 : 0;
                base[2] = base[0];
                base[1] = true;
                base[0] = false;
                break;
            case 3: // 3루타
                runsScored += base[2] ? 1 : 0;
                runsScored += base[1] ? 1 : 0;
                runsScored += base[0] ? 1 : 0;
                base[2] = true;
                base[1] = base[0] = false;
                break;
            case 4: // 홈런
                runsScored += base[2] ? 1 : 0;
                runsScored += base[1] ? 1 : 0;
                runsScored += base[0] ? 1 : 0;
                runsScored += 1;
                base[0] = base[1] = base[2] = false;
                break;
        }
        return runsScored;
    }

    public static void calculateScore(int[] lineup) {
        int score = 0;
        int curBatter = 0;
        for (int inning = 0; inning < N; inning++) {
            int outCnt = 0;
            boolean[] base = new boolean[3]; // 0: 1루, 1: 2루, 2: 3루

            while (outCnt < 3) {
                int result = li[inning][lineup[curBatter % 9]];
                if (result == 0) {
                    outCnt++;
                } else {
                    score += moveBases(base, result);
                }
                curBatter++;
            }
        }
        maxScore = Math.max(maxScore, score);
    }
}
