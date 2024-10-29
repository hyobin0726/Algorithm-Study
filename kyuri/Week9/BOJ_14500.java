import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] tet = {
                {{0, 1}, {0, 2}, {0, 3}}, // ㅣ(회전)
                {{1, 0}, {2, 0}, {3, 0}}, // ㅣ(회전)
                {{0, 1}, {1, 0}, {1, 1}}, // ㅁ
                {{1, 0}, {2, 0}, {2, 1}}, // ㄴ(회전)
                {{0, 1}, {0, 2}, {1, 0}}, // ㄴ(회전)
                {{0, 1}, {1, 1}, {2, 1}}, // ㄴ(회전)
                {{0, 1}, {0, 2}, {-1, 2}}, // ㄴ(회전)
                {{0, 1}, {-1, 1}, {-2, 1}}, // ㄴ(회전)
                {{1, 0}, {1, 1}, {1, 2}}, // ㄴ대칭(회전)
                {{0, 1}, {1, 0}, {2, 0}}, // ㄴ대칭(회전)
                {{0, 1}, {0, 2}, {1, 2}}, // ㄴ대칭(회전)
                {{1, 0}, {1, 1}, {2, 1}}, // ㄹ(회전)
                {{0, 1}, {-1, 1}, {-1, 2}}, // ㄹ(회전)
                {{1, 0}, {0, 1}, {-1, 1}}, // ㄹ대칭(회전)
                {{0, 1}, {1, 1}, {1, 2}}, // ㄹ대칭(회전)
                {{0, 1}, {0, 2}, {1, 1}}, // ㅏ(회전)
                {{-1, 1}, {0, 1}, {1, 1}}, // ㅏ(회전)
                {{0, 1}, {0, 2}, {-1, 1}}, // ㅏ(회전)
                {{1, 0}, {2, 0}, {1, 1}}  // ㅏ(회전)
        };

        int total = 0;
        boolean chk = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < tet.length ; k++) {
                    total = board[i][j];
                    chk = true;
                    for (int d = 0; d < 3; d++) {
                        int nx = i + tet[k][d][0];
                        int ny = j + tet[k][d][1];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                            total += board[nx][ny];
                        } else {
                            chk = false;
                            break;
                        }
                    }
                    if (chk) ans = Math.max(ans, total);
                }

            }
        }

        System.out.println(ans);
    }
}
