import java.util.*;
import java.io.*;

public class Main {
	static int n,m;
	static int[][] map;
	static int[][] temp;
	static boolean[][] visited;
	static int cnt;
	static int year;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n  = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
	
		cnt = 0;
		year = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		while(cnt<2) {
			year++;
			cnt = 0;
			temp = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					melt(i,j);
				}
			}
			map = new int[n][m];
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					map[i][j] = temp[i][j];
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(!visited[i][j] && map[i][j] >0) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			if(cnt ==0) {
				year=0;
				break;
			}
			
		}
		System.out.println(year);
		
	
	}
	static void melt(int x, int y) {
		int tcnt=0;
		for(int i=0; i<4; i++) {
			int nx= x + dx[i];
			int ny = y + dy[i];
			
			if(0<= nx && nx<n && 0<= ny && ny<m) {
				if(map[nx][ny] ==0) {
					tcnt++;
				}
			}
		}
		
		temp[x][y] = map[x][y]-tcnt;
		
		if(temp[x][y]<0) {
			temp[x][y]=0;
		}
	}
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] t= q.poll();
			
			int tx = t[0];
			int ty = t[1];
			for(int i=0; i<4; i++) {
				int nx= tx + dx[i];
				int ny = ty + dy[i];
				
				if(0<= nx && nx<n && 0<= ny && ny<m) {
					if(!visited[nx][ny] && map[nx][ny] >0) {
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny});
					}
				}
			}
			
			
		}
		
	}
}
