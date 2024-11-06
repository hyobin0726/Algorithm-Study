import java.util.*;
import java.io.*;
public class Main {
    static int r,c;
    static char[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int ans;
    static Queue<int[]> water;
    static Queue<int[]> dochi;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        water = new LinkedList<>();
        dochi = new LinkedList<>();
        ans = Integer.MAX_VALUE;
        for(int i =0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    dochi.add(new int[]{i,j});
                }
                if(map[i][j] == '*'){
                    water.add(new int[]{i,j});
                }
            }
        }
        bfs();
        System.out.println(ans == Integer.MAX_VALUE ? "KAKTUS" : ans);
    }
    static void bfs(){
        int time = 0;
        while (!dochi.isEmpty()){
            time++;
            int waterSize = water.size();
            // 물이 먼저 퍼져야함
            for(int i=0; i<waterSize; i++){
                int[] w = water.poll();
                for(int j=0; j<4; j++){
                    int nx = w[0] + dx[j];
                    int ny = w[1] + dy[j];
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(map[nx][ny] == '.' ){
                        map[nx][ny] = '*';
                        water.add(new int[]{nx,ny});
                    }
                }
            }
            // 고슴도치 이동
            int dochiSize = dochi.size();
            for(int i=0; i<dochiSize; i++){
                int[] d = dochi.poll();
                for(int j=0; j<4; j++){
                    int nx = d[0] + dx[j];
                    int ny = d[1] + dy[j];
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(map[nx][ny] == 'D'){
                        ans = Math.min(ans,time);
                        return;
                    }
                    if(map[nx][ny] == '.'){
                        map[nx][ny] = 'S';
                        dochi.add(new int[]{nx,ny});
                    }
                }
            }
        }
    }

}
