import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N;
	static int M;
	static boolean[][] visited;
	static int[][] map;
	public static void main(String[] args) throws Exception{	
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		//2
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = str.charAt(j) - '0'; //숫자로 만들기!
			}
		}
		
		
		//3
		visited = new boolean[N][M];
		visited[0][0] = true;
		
		
		//4
		bfs(0, 0);
		System.out.println(map[N-1][M-1]);
		
	}
	
	public static void bfs(int x, int y) {
		
		//4-1
		Queue<int[]> queue = new LinkedList<>();
		//4-2
		queue.add(new int[] {x,y});
		
		while (!queue.isEmpty()) {
			int now[] = queue.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new int[] {nextX, nextY});
				map[nextX][nextY] = map[nowX][nowY] + 1;
				visited[nextX][nextY] = true;
			}
		}
	}
}