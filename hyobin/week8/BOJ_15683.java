import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[][] map;
    static List<int[]> cctv = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        map=new int[n][m];
        for(int i =0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]>=1 && map[i][j]<=5){
                    cctv.add(new int[]{i,j,map[i][j]});
                }
            }
        }
        dfs(0);
        System.out.println(ans);
    }
    static void dfs(int idx){
        if(idx==cctv.size()){
            int temp = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j]==0) temp++;
                }
            }
            ans = Math.min(ans,temp);
            return;
        }
        int x = cctv.get(idx)[0];
        int y = cctv.get(idx)[1];
        int type = cctv.get(idx)[2];
        if(type == 1){
            for(int i=0;i<4;i++){
                watch(x,y,i,-1);
                dfs(idx+1);
                watch(x,y,i,+1);

            }
        }
        else if(type == 2){
            for(int i=0;i<2;i++){
                watch(x,y,i, -1);
                watch(x,y,i+2, -1);
                dfs(idx+1);
                watch(x,y,i, +1);
                watch(x,y,i+2, +1);

            }
        }
        else if(type == 3){
            for(int i=0;i<4;i++){
                watch(x,y,i,-1);
                watch(x,y,(i+1)%4,-1);
                dfs(idx+1);
                watch(x,y,i,+1);
                watch(x,y,(i+1)%4,+1);
            }
        }
        else if(type == 4){
            for(int i=0;i<4;i++){

                watch(x,y,i,-1);
                watch(x,y,(i+1)%4, -1);
                watch(x,y,(i+2)%4, -1);
                dfs(idx+1);
                watch(x,y,i,+1);
                watch(x,y,(i+1)%4, +1);
                watch(x,y,(i+2)%4, +1);

            }
        }
        else if(type == 5){

            watch(x,y,0,-1);
            watch(x,y,1,-1);
            watch(x,y,2,-1);
            watch(x,y,3,-1);
            dfs(idx+1);
            watch(x,y,0,+1);
            watch(x,y,1,+1);
            watch(x,y,2,+1);
            watch(x,y,3,+1);


        }

    }
    static void watch(int x, int y, int dir, int val){
        switch(dir){
            case 0:
                for(int i=x-1;i>=0;i--){
                    if(map[i][y]==6) break;
                    if(map[i][y]<=0) {
                        map[i][y] += val;
                    }
                }
                break;
            case 1:
                for(int i=y+1;i<m;i++){
                    if(map[x][i]==6) break;
                    if (map[x][i] <=0) {
                        map[x][i] += val;
                    }
                }
                break;
            case 2:
                for(int i=x+1;i<n;i++){
                    if(map[i][y]==6) break;
                    if (map[i][y]<=0) {
                        map[i][y] += val;
                    }
                }
                break;
            case 3:
                for(int i=y-1;i>=0;i--){
                    if(map[x][i]==6) break;
                    if (map[x][i]<=0) {
                        map[x][i] += val;
                    }
                }
                break;
        }
    }

}
