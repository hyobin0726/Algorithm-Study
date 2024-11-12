import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /**
     * BOJ 2655 G4 미로 만들기
     * 풀이: BFS + 다익스트라
     */
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int result;

    static int n;
    static int[][] arr;
    static boolean[][] checked;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node implements Comparable<Node>{
        int x, y;
        int count; //흰방으로 바꿔야하는 검은방 개수

        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {  //오름차순
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = tmp.charAt(j) - '0';
            }
        }
        result = 0;
        checked = new boolean[n][n];
        BFS();
        System.out.println(result);
    }


    static void BFS(){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(0,0,0));
        checked[0][0] = true;

        while(!priorityQueue.isEmpty()){
            Node now = priorityQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX == n-1 && nextY == n-1) {
                    result = now.count;
                    return;
                }

                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) continue;
                if(checked[nextX][nextY]) continue;

                checked[nextX][nextY] = true;
                if(arr[nextX][nextY] == 1){
                    priorityQueue.add(new Node(nextX, nextY, now.count));
                }else{
                    priorityQueue.add(new Node(nextX, nextY, now.count+1));
                }
            }
        }

    }
}
