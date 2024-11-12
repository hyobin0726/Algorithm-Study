import java.util.*;
import java.io.*;
public class Main {
    static int w,h;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static int sx,sy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int test=0; test<t; test++){
            st = new StringTokenizer(br.readLine());
            w= Integer.parseInt(st.nextToken());
            h= Integer.parseInt(st.nextToken());

            map = new char[h][w];
            visited = new boolean[h][w];
            //좌표, 걸린 시간, 불인가 사람인가
            Queue<int[]> q = new LinkedList<int[]>();
            for(int i=0; i<h; i++){
                String str = br.readLine();
                for(int j=0; j<w; j++){
                    map[i][j] = str.charAt(j);

                    if (map[i][j] =='*'){
                        q.add(new int[]{i,j,0,1});
                    }
                    else if (map[i][j]== '@' ){
                       sx=i;
                       sy=j;
                       map[i][j] = '.';
                    }

                }
            }


            q.add(new int[] {sx,sy,0,0});
            visited[sx][sy] = true;
            boolean escape = false;
            while(!q.isEmpty() && !escape) {
                int[] temp = q.poll();
                int tx = temp[0];
                int ty = temp[1];
                int tcnt = temp[2];
                int is = temp[3];

//            System.out.println(tx + " " + ty + " "  +tcnt);

                for(int i=0; i<4; i++){
                    int nx = tx + dx[i];
                    int ny = ty + dy[i];
                    //탈출하나
                    if(is == 0) {
                        if (0 > nx || nx >= h || 0 > ny || ny >= w) {
                            System.out.println(tcnt+1);
                            escape = true;
                            break;
                        }
                    }
                    if (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] == '.') {
                        //불 확산
                        if (is == 1) {
                            map[nx][ny] = '*';
                            q.add(new int[]{nx, ny, tcnt + 1, 1});

                        } else {
                            if (!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny, tcnt + 1,0});
                            }
                        }
                    }
                }
//            for(int i=0; i<h;i++){
//                for(int j=0; j<w; j++){
//                    System.out.print(map[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            }
            if(!escape){
            System.out.println("IMPOSSIBLE");
            }
        }

    }

}
