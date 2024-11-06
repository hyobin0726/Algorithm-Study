import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int x,y,d,g;
    static List<Integer> direction ;
    static boolean[][] map;
    static int[] dx = {0,-1,0,1};
    static int[] dy= {1,0,-1,0};
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new boolean[101][101];
        for(int dragon =0; dragon<n; dragon++){
            st = new StringTokenizer(br.readLine());
            x=Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d= Integer.parseInt(st.nextToken());
            g=Integer.parseInt(st.nextToken());
            direction = new ArrayList<Integer>();
            curve();
            map[y][x] = true;
            draw();
        }
        ans = 0;
        count();
        System.out.println(ans);


    }
    static void curve(){
    //g세대까지 반복
        //0세대 방향
        direction.add(d);
        for(int i = 0; i<g; i++){
            for(int j=direction.size()-1; j>=0; j--){
                direction.add((direction.get(j)+1)%4);
            }
        }
    }
    static void draw(){
        int nx = y;
        int ny = x;
//        System.out.println(direction);
        for(int i = 0; i<direction.size(); i++) {
            if (0 <= nx && nx < 101 && 0 <= ny && ny < 101) {
                nx = nx + dx[direction.get(i)];
                ny = ny + dy[direction.get(i)];
                map[nx][ny] = true;
            }
        }

    }
    static void count(){
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]){
                    ans++;
                }
            }
        }
    }
}
