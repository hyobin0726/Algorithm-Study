import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	/**
	 * 연구소
	 * 풀이: 완탐으로 조합 3개 만든 후, BFS로 바이러스 퍼뜨리고 안전구역 갯수 결과값이랑 비교하기
	 */
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M;
	static int[][] arr;
	static ArrayList<Node> arrayList;
	static ArrayList<Node> virusList;
	static boolean[] visited;
	static int[] selected;
	static boolean[][] virusChecked;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		arrayList = new ArrayList<>();
		virusList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {
					arrayList.add(new Node(i, j));
				}else if(arr[i][j] == 2) {
					virusList.add(new Node(i,j));
				}
			}
		}
		
		visited = new boolean[arrayList.size()]; 
		selected = new int[3];
		result = Integer.MIN_VALUE;
		Combination(0,0);
		System.out.println(result);
		
	}
	static void Combination(int at, int depth) {
		if(depth == 3) {
//			for (int i = 0; i < 3; i++) {
//				System.out.print(selected[i] + " ");
//			}
//			System.out.println();
			virusChecked = new boolean[N][M];
			int[][] arr1 = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr1[i][j] = arr[i][j];
				}
			}
			bfs(selected, arr1);
			result  = Math.max(result, count(arr1));
			return;
		}
		for (int i = at; i < arrayList.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				Combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	static void bfs(int[] selelcted, int[][] arr1) {
		for (int i = 0; i < selelcted.length; i++) {
			Node node = arrayList.get(selected[i]);
			arr1[node.x][node.y] = 1;
		}
		
		Queue<Node> queue = new LinkedList<>();
		for (int i = 0; i < virusList.size(); i++) {
			Node node = virusList.get(i);
			queue.add(new Node(node.x, node.y));
			virusChecked[node.x][node.y] = true;
		}
		
		while(!queue.isEmpty()){
			Node node = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = node.x + dx[i];
				int nextY = node.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
				if(virusChecked[nextX][nextY]) continue;
				if(arr1[nextX][nextY] == 1) continue;
				
				virusChecked[nextX][nextY] = true;
				arr1[nextX][nextY] = 2;
				queue.add(new Node(nextX, nextY));
			}
		}
	}
	static int count(int[][] arr1) {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr1[i][j] == 0) sum++;
			}
		}
		
		return sum;
	}
}
