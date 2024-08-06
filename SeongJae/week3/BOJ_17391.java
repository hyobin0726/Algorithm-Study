import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_1303 {
    public static int n;
    public static int m;
    public static boolean[][] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        m=Integer.parseInt(st.nextToken());
        n=Integer.parseInt(st.nextToken());
        char[][] arr=new char[n][m];
        visited=new boolean[n][m];
        int wScore=0;
        int bScore=0;
        for (int i=0;i<n;i++){
            String line=br.readLine();
            for(int j=0;j<m;j++){
                arr[i][j]=line.charAt(j);
            }
        }
        for(int x=0;x<n;x++){
            for (int y = 0; y < m; y++) {
             if(!visited[x][y]){
                if(arr[x][y]=='W'){
                    wScore+=bfs(arr, visited,x,y,'W');
                }
                else{
                    bScore+=bfs(arr, visited,x,y,'B');
                }
             }
            }
        }
        System.out.println(wScore+" "+bScore);

    }

    public static int bfs(char[][] arr,boolean[][] visited,int x,int y,char team){
        int count=1;
        Queue<Point> queue=new LinkedList<>();
        int[] dx={1,-1,0,0};
        int[] dy={0,0,1,-1};
        queue.add(new Point(x,y));
        visited[x][y]=true;

        while (!queue.isEmpty()){
            Point p = queue.poll();
            for(int i=0;i<4;i++){
                int ax=p.x+dx[i];
                int ay=p.y+dy[i];
                if(0<=ax && ax<n && 0<=ay && ay<m && !visited[ax][ay] && arr[ax][ay]==team){
                    queue.add(new Point(ax,ay));
                    visited[ax][ay]=true;
                    count++;
                }
            }
        }
        return count*count;
    }
}
