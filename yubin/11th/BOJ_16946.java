import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16946 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,m;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] group;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        group = new int[n][m];
        int index = 1;  //그룹 번호

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == '0' && group[i][j] == 0){
                    hashMap.put(index, bfs(i,j,index));
                    index++;
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(mapCount(i,j));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    static int bfs(int x, int y, int groupNum){
        int count = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x,y));
        group[x][y] = groupNum;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                if(map[nextX][nextY] == '0' && group[nextX][nextY] == 0){
                    queue.offer(new Node(nextX, nextY));
                    group[nextX][nextY] = groupNum;
                    count++;
                }
            }
        }
        return count;
    }
    static class Node{
        int x,y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static  int mapCount(int x, int y){
        int sum = 1;
        HashSet<Integer> hashSet = new HashSet<>();
        if(map[x][y] == '0'){
            return 0;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

            if(group[nextX][nextY] == 0) continue;

            if(map[nextX][nextY] == '0'){
                hashSet.add(group[nextX][nextY]);
            }
        }

        for (int idx :
                hashSet) {
            sum  += hashMap.get(idx);
        }

        return sum % 10;
    }
}
