import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_22683 {
    /**
     * SWEA 22683 D3 나무 베기
     * 풀이: BFS
     * 
     */
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
     
    static int TC;
    static int N, K;
    static char[][] arr;
    static boolean[][][] check;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
     
    static int[][] distance;
    static class Node implements Comparable<Node>{
        int x,y,breakCount, stepCount;
        int dir;
 
        public Node(int x, int y, int breakCount, int stepCount, int dir) {
            super();
            this.x = x;
            this.y = y;
            this.breakCount = breakCount;
            this.stepCount = stepCount;
            this.dir = dir;
        }
 
        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return this.stepCount - o.stepCount;
        }
         
    }
     
    static int result;
    static int startX;
    static int startY;
    static int endX;
    static int endY;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken()); //나무 벨 수 있는 갯수
            arr = new char[N][N];
            check = new boolean[N][N][K+1];
             
            for (int i = 0; i < N; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = tmp.charAt(j);
                    if(arr[i][j] == 'X') {
                        startX = i;
                        startY = j;
                    }else if(arr[i][j] == 'Y') {
                        endX = i;
                        endY = j;
                    }
                }
            }
             
            result = Integer.MAX_VALUE;
            BFS();
            if(result == Integer.MAX_VALUE) {
                sb.append("#").append(tc).append(" ").append(-1).append("\n");
            }else {
                sb.append("#").append(tc).append(" ").append(result).append("\n");              
            }
 
        }
        System.out.println(sb);
    }
    static void BFS() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startX, startY, 0,0,0));
        check[startX][startY][0] = true;
         
        while(!queue.isEmpty()) {
            Node now = queue.poll();
             
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                 
                //도착한 경우
                if(nextX == endX && nextY == endY) {
                    result = now.stepCount + 1;
                    return;
                }
                 
                //경계 벗어나는 경우
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                //이미 왔던 경우
                if(check[nextX][nextY][now.breakCount]) continue;
                 
                 
                //앞이 그냥 이동가능한 벽인 경우
                if(arr[nextX][nextY] == 'G') {
                    check[nextX][nextY][now.breakCount] = true;
                    if(now.dir == i) {
                        queue.add(new Node(nextX, nextY, now.breakCount, now.stepCount+1, i));      
                    }else {
                        if(i == (now.dir-1+4)%4 || i == (now.dir+1+4)%4) {
                            queue.add(new Node(nextX, nextY, now.breakCount, now.stepCount+2, i));                          
                        }else {
                            queue.add(new Node(nextX, nextY, now.breakCount, now.stepCount+3,i));
                        }
                    }
                     
                }else {
                    //앞이 벽인 경우
                    if(now.breakCount < K && arr[nextX][nextY] == 'T') {
                        if(now.dir == i) {
                            queue.add(new Node(nextX, nextY, now.breakCount+1, now.stepCount+1, i));        
                        }else {
                            if(i == (now.dir-1+4)%4 || i == (now.dir+1+4)%4) {
                                queue.add(new Node(nextX, nextY, now.breakCount+1, now.stepCount+2, i));                            
                            }else {
                                queue.add(new Node(nextX, nextY, now.breakCount+1, now.stepCount+3 ,i));
                            }
                        }
                        check[nextX][nextY][now.breakCount+1] = true;
                    }
                }
            }
        }
    }
}
