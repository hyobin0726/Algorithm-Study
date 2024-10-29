import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[][] map;
    static boolean[][] visited;
    static int island;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0, 1,-1};
    static ArrayList<Edge> list;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());
        map= new int[n][m];
        visited = new boolean[n][m];
        island = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //섬 분리
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    island++;
                    bfs(i,j);
                }
            }
        }
        list = new ArrayList<Edge>();

        //가능한 다리 연결
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] >0 ){
                    connect(i,j);
                }
            }
        }
        Collections.sort(list);
        parent = new int[island+1];
        for(int i=1; i<=island; i++){
            parent[i] = -1;
        }
        int cnt = 0;
        int answer = 0;
        for(Edge e : list){

            if(union(e.from, e.to)){
                answer += e.len;
                cnt++;
            }
            if(cnt == island-1) break;

        }
        if(cnt == island-1){
            System.out.println(answer);
        }else{
            System.out.println(-1);
        }


    }
    static void bfs(int x, int y){
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y});
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int tx= temp[0];
            int ty = temp[1];
            map[tx][ty] = island;

            for(int i=0; i<4; i++){
                int nx = tx + dx[i];
                int ny = ty + dy[i];

                if(0<= nx && nx<n && 0<=ny && ny<m){
                    if(!visited[nx][ny] && map[nx][ny]==1){
                        visited[nx][ny] = true;
                        map[nx][ny] = island;
                        q.add(new int[] {nx,ny});
                    }
                }
            }
        }

    }
    static class Edge implements Comparable<Edge>{
        int from, to, len;

        public Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }

        @Override
        public int compareTo(Edge o) {
            return this.len-o.len;
        }
    }
    static void connect(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<4; i++){
            //좌표, 길이, 방향
            q.add(new int[] {x,y,0,i});
            while (!q.isEmpty()){
                int[] temp = q.poll();
                int tx = temp[0];
                int ty = temp[1];
                int len = temp[2];
                int d = temp[3];
                int nx = tx + dx[d];
                int ny = ty + dy[d];

                if(0<=nx && nx<n && 0<=ny && ny<m){
                    if(map[nx][ny] == 0){
                        q.add(new int[] {nx,ny,len+1,d});
                    }else if(map[nx][ny] != map[x][y]){
                        if(len>1){
                            list.add(new Edge(map[x][y], map[nx][ny], len));
                        }
                    }
                }
            }
        }
    }
    //mst
    static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int x, int y){
        int xroot = find(x);
        int yroot = find(y);
        if(xroot==yroot) return false;
        parent[yroot] = xroot;
        return true;
    }

}
