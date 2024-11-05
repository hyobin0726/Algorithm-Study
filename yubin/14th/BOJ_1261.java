import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {
    /**
     * BOJ 1261 G4 알고스팟
     * 풀이: bfs + 다익스트라
     */

    static BufferedReader br;
    static StringTokenizer st;

    static class Node implements Comparable<Node>{
        int x, y;  //현재 위치
        int cnt;  //벽을 부순 횟수

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static boolean[][] check;
    static int[][] arr;
    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        
        check = new boolean[N][M];
        int result = bfs(arr, check);;
        System.out.println(result);
    }

    static int bfs(int[][] arr, boolean[][] check){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0,0,0));
        check[0][0] = true;
        while (!queue.isEmpty()){
            Node now = queue.poll();
            if(now.x == N-1 && now.y == M-1){
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                if(check[nextX][nextY]) continue;

                if(arr[nextX][nextY] == 0){
                    queue.add(new Node(nextX, nextY, now.cnt));
                }else{
                    queue.add(new Node(nextX, nextY, now.cnt+1));
                }
                check[nextX][nextY] = true;
            }
        }
        return -1;
    }

}
