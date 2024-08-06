import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
    public static void main(String[] args) throws NumberFormatException, IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int n = Integer.parseInt(st.nextToken());
       int m = Integer.parseInt(st.nextToken());
       st = new StringTokenizer(br.readLine());
       int x = Integer.parseInt(st.nextToken());
       int y = Integer.parseInt(st.nextToken());
       int dst = Integer.parseInt(st.nextToken());
       int[][] graph= new int[n][m];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		graph[i][j]=Integer.parseInt(st.nextToken());
        	}
        }
        int dx[]= {-1,0,1,0};
        int dy[]= {0,1,0,-1};
        while(true) {
        	//1.현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
        	graph[x][y]=2;
        	
        	boolean isClear=true;
        	for(int i=3;i>-1;i--) {
        		int ax=x+dx[(dst+i)%4];
        		int ay=y+dy[(dst+i)%4];
        		// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
        		if(graph[ax][ay]==0) {
        			x=ax;
        			y=ay;
        			dst=(dst+i)%4;
        			isClear=false;
        			break;
        		}
        	}
        	//2.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        	if(isClear) {
        		int ax=x+dx[(dst+2)%4];
        		int ay=y+dy[(dst+2)%4];
        		if(graph[ax][ay]==1) {
        			break;
        		}
        		else {
        			x=ax;
        			y=ay;
        		}
        	}
        }
        int result=0;
        for (int i = 0; i < n; i++) {
        	for(int j=0;j<m;j++) {
        		if (graph[i][j]==2) result++;
        	}
        }
        System.out.print(result);
    }
}
