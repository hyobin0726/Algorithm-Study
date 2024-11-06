import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,L,R;
	static int[][] A;
	static int[][] check;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());  //땅크기
		L = Integer.parseInt(st.nextToken());  //인구차이
		R = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		while(true) {
			if(!bfs()) break;
			result++;
		}
		System.out.println(result);
	}
	static boolean bfs() {
		boolean moveCheck = false;
		boolean[][] isSelected = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!isSelected[i][j]) {
					ArrayList<Node> selectedNodes = new ArrayList<>();
					Queue<Node> queue = new LinkedList<>();
					queue.add(new Node(i,j));
					isSelected[i][j] = true;
					selectedNodes.add(new Node(i,j));
					int count = 1;
					
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						
						for (int k = 0; k < 4; k++) {
							int nextX = now.x + dx[k];
							int nextY = now.y + dy[k];
							
							if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
							if(isSelected[nextX][nextY]) continue;
							
							int gap = Math.abs(A[now.x][now.y] - A[nextX][nextY]);
							if(gap >= L && gap <= R) {
								isSelected[nextX][nextY] = true;
								selectedNodes.add(new Node(nextX, nextY));
								count++;
								queue.add(new Node(nextX, nextY));
							}
						}
					}
					
					if(count > 1) {
						int sum = 0;
						moveCheck = true;
						for (Node node : selectedNodes) {
							sum += A[node.x][node.y];
						}
						int avg = sum / selectedNodes.size();
						for (Node node : selectedNodes) {
							A[node.x][node.y] = avg;
						}
					}
				}
			}
		}
		return moveCheck;
	}
}
