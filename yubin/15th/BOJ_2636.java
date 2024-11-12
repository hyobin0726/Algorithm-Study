import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2636 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M;
    static int[][] arr;
    static ArrayList<Integer> cheeseNum = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            boolean[][] isOutside = new boolean[N][M];
            findOutside(isOutside);

            if (!removeCheese(isOutside)) break;

            time++;
        }

        System.out.println(time);
        System.out.println(cheeseNum.get(cheeseNum.size() - 2));
    }

    //외부 영역 찾기
    static void findOutside(boolean[][] isOutside) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        isOutside[0][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    if (!isOutside[nextX][nextY] && arr[nextX][nextY] == 0) {
                        isOutside[nextX][nextY] = true;
                        queue.add(new Node(nextX, nextY));
                    }
                }
            }
        }
    }

    // 바깥 치즈 녹이기
    static boolean removeCheese(boolean[][] isOutside) {
        boolean check = false;
        ArrayList<Node> cheeseList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) { // 치즈가 있는 칸
                    check = true;
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];
                        if (isOutside[nextX][nextY]) { // 외부 공기와 접촉한 치즈
                            cheeseList.add(new Node(i, j));
                            break;
                        }
                    }
                }
            }
        }

        // 녹은 치즈 업데이트
        for (Node cheese : cheeseList) {
            arr[cheese.x][cheese.y] = 0;
        }
        cheeseNum.add(cheeseList.size());
        return check;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
