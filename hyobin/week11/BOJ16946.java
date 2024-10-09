import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static int[][] map;
    static int[][] result;
    static int[][] group;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static HashMap<Integer,Integer> hm = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        result = new int[n][m];
        group = new int[n][m];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j)-'0';

            }
        }
        int idx=1;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 0 && group[i][j] == 0){
                    hm.put(idx,bfs(i,j,idx));
                    idx++;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1){
                    HashSet<Integer> set = new HashSet<>();
                    int cnt = 1;
                    for(int k=0; k<4; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(nx>=0 && ny>=0 && nx<n && ny<m){
                            if(map[nx][ny] == 0 && !set.contains(group[nx][ny])){
                                set.add(group[nx][ny]);
                                cnt += hm.get(group[nx][ny]);
                            }
                        }
                    }

                    result[i][j] = cnt%10;
                }
            }
        }
       // for(int i=0; i<n; i++){
       //     for(int j=0; j<m; j++){
       //         System.out.print(result[i][j]);
       //     }
       //     System.out.println();
       // }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
    static int bfs(int x, int y, int idx){
        int cnt=1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        group[x][y] = idx;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];
//            System.out.println(tx + " " + ty);
            for(int i=0; i<4; i++){
                int nx = tx+dx[i];
                int ny = ty+dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<m){
                    if(map[nx][ny] == 0 && group[nx][ny] == 0){
                        cnt++;
                        group[nx][ny] = idx;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return cnt;
    }
}
