import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int ans=0;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for(int i=0; i<5; i++){
            String str= br.readLine();
            for(int j=0; j<5; j++){
                map[i][j] = str.charAt(j);
            }
        }
        int[] friend = new int[7];
        dfs(0,friend,0);

        System.out.println(ans);
    }
    static void dfs(int idx, int[] friend, int depth){
        if(idx == 7){
            if(bfs(friend)){
                ans++;
            }
//            System.out.println(Arrays.toString(friend));
            return;
        }
        for(int i=depth; i<25; i++){
            friend[idx] = i;
            dfs(idx+1, friend, i+1);
        }

    }

    static boolean bfs(int[] friend){
        Queue<int[] > q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        visited[friend[0]/5][friend[0]%5] = true;
        q.add(new int[] {friend[0]/5,friend[0]%5});
        int cnt = 1;
        int s =0;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int tx= temp[0];
            int ty = temp[1];
            if(map[tx][ty] == 'S'){
                s++;
            }

            for(int i=0; i<4; i++){
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                int t = nx*5 +ny;
                if(0<= nx && nx<5 && 0<= ny && ny<5 && !visited[nx][ny] ){
                    for(int j=0; j<7; j++){
                        if(friend[j] ==t ){
                            visited[nx][ny] = true;
                            cnt++;

                            q.add(new int[] {nx,ny});
                        }
                    }
                }
            }
        }
        if(cnt == 7 && s >=4){
            return true;
        }else {
            return false;
        }
    }
}
