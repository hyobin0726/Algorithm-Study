import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_17836 {
    public static int n;
    public static int m;
    public static int deadline;
    public static boolean[][] visited;
    static class yongsa{
        int x;
        int y;
        int count;

        public yongsa(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        deadline=Integer.parseInt(st.nextToken());
        int[][] arr=new int[n][m];
        visited=new boolean[n][m];
        for (int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        int result=bfs(arr,visited,0,0);
        if (result<=deadline && result!=-1){
            System.out.println(result);
        }
        else System.out.println("Fail");

    }

    public static int bfs(int[][] arr,boolean[][] visited,int x,int y){
        Queue<yongsa> queue=new LinkedList<yongsa>();
        int swordMaster=-1;
        int[] dx={1,-1,0,0};
        int[] dy={0,0,1,-1};
        queue.add(new yongsa(x,y,0));
        visited[x][y]=true;
        while (!queue.isEmpty()){
            yongsa yongsa = queue.poll();
            for(int i=0;i<4;i++){
                int ax=yongsa.x+dx[i];
                int ay=yongsa.y+dy[i];
                if (ax==n-1 && ay==m-1){
                    if (swordMaster!=-1 && yongsa.count+1>swordMaster) return swordMaster;
                    else return yongsa.count+1;
                }
                if(0<=ax && ax<n && 0<=ay && ay<m && !visited[ax][ay]){
                    visited[ax][ay]=true;
                    //☆전★서☆르★검☆//
                    if(arr[ax][ay]==2){
                        swordMaster= n-ax+m-ay+yongsa.count-1;
                    }
                    else if(arr[ax][ay]==0){
                        queue.add(new yongsa(ax,ay,yongsa.count+1));
                    }
                }
            }
        }
        if (swordMaster!=-1) return swordMaster;
        return -1;
    }
}
