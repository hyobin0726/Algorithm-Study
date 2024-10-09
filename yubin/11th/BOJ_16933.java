import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16933 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M,K;
    static int[][] arr;
    static boolean[][][] check;
    static int result;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node{
        int x;
        int y;
        int sum;
        int breakNum;
        boolean night;

        public Node(int x, int y, int sum, int breakNum, boolean night){
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.breakNum = breakNum;
            this.night = night;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        check = new boolean[N][M][K+1];  //몇개 부순지 기록하기 위하여
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp.charAt(j) - '0';
            }
        }
        result = Integer.MAX_VALUE;
        BFS();

        if(result == Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(result);
        }
    }


    public static void BFS(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,1,0, false));
        check[0][0][0] = true;

        while (!queue.isEmpty()){
            Node node = queue.poll();

            if(node.x == N-1 && node.y == M-1){
                result = Math.min(result, node.sum);
                return;
            }

            if(node.sum > result) return;

            for (int i = 0; i < 4; i++) {
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;

                if(arr[nextX][nextY] == 0){
                    if(!check[nextX][nextY][node.breakNum]){
                        check[nextX][nextY][node.breakNum] = true;
                        queue.add(new Node(nextX, nextY, node.sum + 1, node.breakNum, !node.night));
                    }
                }else{

                    if(node.breakNum < K && !check[nextX][nextY][node.breakNum + 1]){
                        if(!node.night){
                            check[nextX][nextY][node.breakNum + 1] = true;
                            queue.add(new Node(nextX, nextY, node.sum + 1, node.breakNum +1, !node.night));
                        }else{
                            queue.add(new Node(node.x, node.y, node.sum + 1, node.breakNum , !node.night));
                        }
                    }
                }
            }
        }
    }
}
