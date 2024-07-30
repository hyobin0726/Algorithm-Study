package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int result = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		ArrayList<Where> arrayList = new ArrayList<>();
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					arrayList.add(new Where(i, j));
				}
			}
		}
		
		bfs(arrayList);
		
		int result = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				if(result < map[i][j]) result = map[i][j];
			}
		}
		System.out.println(result - 1);
	}
	
	public static void bfs(ArrayList<Where> arr) {
		Queue<int[]> queue = new LinkedList<>();
		
		for (Where w : arr) {
			queue.add(new int[] {w.x, w.y});
		}
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();

			int nowX = now[0];
			int nowY = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				if(map[nextX][nextY] >= 1 || map[nextX][nextY] == -1) {
					continue;
				}
				
				queue.add(new int[] {nextX, nextY});
				map[nextX][nextY] += (map[nowX][nowY]+1);
			}
			
		}
	}
	
	static class Where{
		int x;
		int y;
		
		public Where(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
