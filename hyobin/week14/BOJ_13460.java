import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static char[][] map;
    static int[] dx= {-1,1,0,0};
    static int[] dy= {0,0,-1,1};
    static class point{
        int redx,redy, bluex,bluey, cnt;

        public point(int redx, int redy, int bluex, int bluey, int cnt) {
            this.redx = redx;
            this.redy = redy;
            this.bluex = bluex;
            this.bluey = bluey;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        point p = new point(0,0,0,0,0);
        map = new char[n][m];
        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    p.redx=i;
                    p.redy=j;
                }
                if(map[i][j] == 'B'){
                    p.bluex=i;
                    p.bluey=j;
                }
            }
        }


        System.out.println(bfs(p));
    }
    static int bfs(point p){
        Queue<point> q = new LinkedList<>();
        q.add(p);

        while (!q.isEmpty()){
            point temp = q.poll();
            if(temp.cnt == 10){
                continue;
            }

            for(int i=0; i<4; i++){
                int tredx = temp.redx;
                int tredy = temp.redy;
                int tbluex = temp.bluex;
                int tbluey = temp.bluey;
                int tcnt = temp.cnt;
                boolean redhole = false;
                boolean bluehole = false;

                while (true){
                    int nx = tredx + dx[i];
                    int ny = tredy + dy[i];

                    if(0<=nx && nx<n && 0<=ny && ny<m){
                        if(map[nx][ny] == '#'){
                            break;
                        }
                    }
                    if(map[nx][ny] == 'O'){
                        redhole = true;
                        break;
                    }
                    tredx = nx;
                    tredy = ny;
                }

                while (true){
                    int nx = tbluex + dx[i];
                    int ny = tbluey + dy[i];

                    if(0<=nx && nx<n && 0<=ny && ny<m){
                        if(map[nx][ny] == '#'){
                            break;
                        }
                    }
                    if(map[nx][ny] == 'O'){
                        bluehole = true;
                        break;
                    }
                    tbluex = nx;
                    tbluey = ny;
                }

                if(bluehole){
                    continue;
                }else if(redhole){
                    return tcnt+1;
                }

                if(tredx==tbluex && tredy==tbluey){
                    if(i==0){
                        if(temp.redx<temp.bluex){
                            tbluex++;
                        }else {
                            tredx++;
                        }
                    }
                    if(i==1){
                        if(temp.redx<temp.bluex){
                            tredx--;
                        }else {
                            tbluex--;
                        }
                    }
                    if(i==2){
                        if(temp.redy<temp.bluey){
                            tbluey++;
                        }else {
                            tredy++;
                        }
                    }

                    if(i==3){
                        if(temp.redy<temp.bluey){
                            tredy--;
                        }else {
                            tbluey--;
                        }
                    }
                }

                q.add(new point(tredx,tredy,tbluex,tbluey,tcnt+1));
            }

        }
        return -1;
    }

}
