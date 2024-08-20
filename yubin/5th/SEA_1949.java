import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
 
public class SEA_1949 {
 
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuffer sb;
 
    static int TC;
    static int N;
    static int K;
    static int[][] map;
    static boolean[][] visited;
    static int result;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
 
    static class Node{
        int x;
        int y;
        int value;
        public Node(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuffer();
 
        TC = Integer.parseInt(br.readLine());
 
        for (int i = 1; i <= TC; i++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[N][N];
            List<Node> nodeList = new ArrayList<>();
            int nodeMax = 0;
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < N; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                    if(nodeMax < map[j][k]){
                        nodeList.clear();
                        nodeList.add(new Node(j, k, map[j][k]));
                        nodeMax = map[j][k];
                    } else if (nodeMax == map[j][k]) {
                        nodeList.add(new Node(j, k, map[j][k]));
                    }
                }
            }
 
 
            result = 0;
 
            for (Node node :
                    nodeList) {
                visited = new boolean[N][N];
                visited[node.x][node.y] = true;
                dfs(node.x, node.y, node.value, 1, false);
 
            }
 
            System.out.println("#" + i + " " + result);
 
        }
 
    }
 
   public static void dfs(int x, int y, int value, int length, boolean check){
        result = Math.max(result, length);
 
       for (int i = 0; i < 4; i++) {
           int nx = x + dx[i];
           int ny = y + dy[i];
 
           if(nx < 0 || ny < 0 || nx >= N || ny >=  N || visited[nx][ny]){
               continue;
           }
 
           //공사 없이 이동 가능한 경우
           if(map[nx][ny] < value){
               visited[nx][ny] = true;
               dfs(nx, ny, map[nx][ny], length + 1, check);
               visited[nx][ny] = false;
           } else if (!check && map[nx][ny] - K < value) {
               int originalHeight = map[nx][ny];
               map[nx][ny] = value - 1;
               visited[nx][ny] = true;
               dfs(nx, ny, map[nx][ny], length + 1, true);
               visited[nx][ny] = false;
               map[nx][ny] = originalHeight; //공사 복구
           }
       }
   }
}
