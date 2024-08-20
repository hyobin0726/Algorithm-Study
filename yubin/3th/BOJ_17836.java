import java.io.*;
import java.util.*;

public class BOJ_17836 {
	static int N, M, T;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] cnt;
	static Queue<Node> q;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean check;
	static int gramCheck = Integer.MAX_VALUE;
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		cnt = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		visited[0][0] = true;
		q = new LinkedList<>();
		q.add(new Node(0,0));
		
		bfs();
		
		
		if(cnt[N-1][M-1] == 0) {
			if(gramCheck > T) {
				System.out.println("Fail");
			}else {				
				System.out.println(gramCheck);
			}
			return;
		}
		
		if(gramCheck < cnt[N-1][M-1]) {
			result = gramCheck;
		}else {
			result = cnt[N-1][M-1];
		}
		
		if(result > T) {
			System.out.println("Fail");
		}else {			
			System.out.println(result);
		}

	}

	static class Node{
		int x;
		int y;
		
		Node(int x, int y){
			this.x= x;
			this.y = y;
		}
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			if(arr[n.x][n.y] == 2) {
				gramCheck = cnt[n.x][n.y] + (N - n.x - 1) + (M - n.y - 1);
			}
						
			for (int i = 0; i < 4; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if(visited[nextX][nextY]) {
					continue;
				}
				
				
				if(arr[nextX][nextY] == 1) {
					continue;
				}
				
				q.add(new Node(nextX, nextY));
				visited[nextX][nextY] = true;
				cnt[nextX][nextY] = (cnt[n.x][n.y] + 1);
			}
		}
	}
	
}

