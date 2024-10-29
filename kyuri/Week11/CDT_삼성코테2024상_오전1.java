// 삼성 SW 역량테스트 2024 상반기 오전 1번 문제
import java.util.*;

public class Main {
    static int K, M;
    static int[][] board = new int[5][5];
    static ArrayDeque<Integer> pack = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] visited = new int[5][5];
    static List<int[]> changeIdx = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < M; i++) {
            pack.add(sc.nextInt());
        }

        for (int k = 0; k < K; k++) {
            int result = 0;
            if (maxGetMap()) {
                while (true) {
                    int kVal = getPack(board);
                    if (kVal == 0) break;
                    result += kVal;
                    changePack();
                }
                if (result > 0) {
                    System.out.print(result + " ");
                }
            } else {
                break;
            }
        }
        sc.close();
    }

    static int bfs(int[][] bMap, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = 1;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0], y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];

                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && visited[nx][ny] == 0) {
                    if (bMap[x][y] == bMap[nx][ny]) {
                        cnt++;
                        changeIdx.add(new int[]{nx, ny});
                        visited[nx][ny] = 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if (cnt == 2) changeIdx.remove(changeIdx.size() - 1);
        return cnt >= 3 ? cnt : 0;
    }

    static int getPack(int[][] bMap) {
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visited[i], 0);
        }
        changeIdx.clear();
        int ans = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j] == 0) {
                    int get = bfs(bMap, i, j);
                    if (get != 0) {
                        ans += get;
                        changeIdx.add(new int[]{i, j});
                    }
                }
            }
        }
        return ans;
    }

    static class RotateResult {
        int packValue;
        int[][] tempMap;

        RotateResult(int packValue, int[][] tempMap) {
            this.packValue = packValue;
            this.tempMap = tempMap;
        }
    }

    static RotateResult rotate(int sx, int sy, int caseNum) {
        int[][] tempMap = new int[5][5];

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                tempMap[x][y] = board[x][y];
            }
        }

        for (int x = sx; x < sx + 3; x++) {
            for (int y = sy; y < sy + 3; y++) {
                int ox = x - sx, oy = y - sy, rx = 0, ry = 0;
                if (caseNum == 0) {
                    rx = oy;
                    ry = 3 - ox - 1;
                } else if (caseNum == 1) {
                    rx = 3 - ox - 1;
                    ry = 3 - oy - 1;
                } else {
                    rx = 3 - oy - 1;
                    ry = ox;
                }
                tempMap[rx + sx][ry + sy] = board[x][y];
            }
        }
        return new RotateResult(getPack(tempMap), tempMap);
    }

    static boolean maxGetMap() {
        int maxVal = 0;
        int[][] newMap = new int[5][5];

        for (int caseNum = 0; caseNum < 3; caseNum++) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    RotateResult result = rotate(i, j, caseNum);
                    int val = result.packValue;
                    int[][] temp = result.tempMap;
                    if (val > maxVal) {
                        maxVal = val;
                        for (int x = 0; x < 5; x++) {
                            for (int y = 0; y < 5; y++) {
                                newMap[x][y] = temp[x][y];
                            }
                        }
                    }
                }
            }
        }

        if (maxVal == 0) {
            return false;
        } else {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    board[x][y] = newMap[x][y];
                }
            }
            return true;
        }
    }

    static void changePack() {
        changeIdx.sort((a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        for (int[] pos : changeIdx) {
            board[pos[0]][pos[1]] = pack.pollFirst();
        }
    }
}
