import java.io.*;
import java.util.*;
public class BOJ_1303 {
	static Character[][] arr;
	static int n;
	static int m;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int wcnt;
	static int bcnt;
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new Character[m][n];
		visited = new boolean[m][n];
		
		for (int i =0; i <m; i ++) {
			String input = br.readLine();
			for (int j =0; j <n; j++) {
				arr[i][j] = input.charAt(j); 
			}
		}
		
		for (int i =0; i <m; i ++) {
			for (int j =0; j <n; j++) {
				if (arr[i][j].equals('W')  && !visited[i][j]) {
					visited[i][j] = true;
					wbfs(i,j);
				} else if (arr[i][j].equals('B')  && !visited[i][j]) {
					visited[i][j] = true;
					bbfs(i,j);
				}
			}
		
		}
		System.out.print(wcnt + " ");
		System.out.print(bcnt);
	}
	static void wbfs(int a, int b) {
		int cnt=1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {a,b});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x= temp[0];
			int y = temp[1];
			
			
			for (int i =0; i< 4; i++) {
				int nx = x + dx[i]; 
				int ny = y + dy[i];
				
				if (0<= nx && nx<m && 0<= ny && ny<n) {
					if (!visited[nx][ny] && arr[nx][ny].equals('W') ) {
						visited[nx][ny] = true;
						cnt+=1;
						queue.add(new int[] {nx,ny});
						
					}
				}
	
			}
			
		}
		wcnt += cnt*cnt;
	}
	static void bbfs(int a, int b) {
		int cnt=1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {a,b});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x= temp[0];
			int y = temp[1];
			
			
			for (int i =0; i< 4; i++) {
				int nx = x + dx[i]; 
				int ny = y + dy[i];
				
				if (0<= nx && nx<m && 0<= ny && ny<n) {
					if (!visited[nx][ny] && arr[nx][ny].equals('B') ) {
						visited[nx][ny] = true;
						cnt+=1;
						queue.add(new int[] {nx,ny});
						
					}
				}
	
			}
			
		}
		bcnt += cnt*cnt;
	}
}
