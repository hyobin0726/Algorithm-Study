package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
	public static int N;
	public static int M;
	public static int[][] arr;
	public static ArrayList<Where> home;
	public static ArrayList<Where> chicken;
	public static boolean[] visited;
	public static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) {
					home.add(new Where(i,j));
				}else if(arr[i][j] == 2) {
					chicken.add(new Where(i,j));
				}
			}
		}
		
		visited = new boolean[chicken.size()];
		
		backTracking(0, 0);
		System.out.println(min);
	}
	
	public static void backTracking(int level, int start) {
		if(level == M) {
			int total = 0;
			
			for (int i = 0; i < home.size(); i++) {
				int sum = Integer.MAX_VALUE;
				for (int j = 0; j < chicken.size(); j++) {
					 if(visited[j] == true) {
						 int diff = Math.abs(home.get(i).x - chicken.get(j).x) + 
								 Math.abs(home.get(i).y - chicken.get(j).y);
						 
						 sum = Math.min(sum, diff);
					 }					
				}
				total += sum;
			}
			
			if(total < min) min = total;
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			if(visited[i] == false) {
				visited[i] = true;
				backTracking(level+1, i + 1);
				visited[i] = false;
			}
		}
	}

}
class Where{
	int x;
	int y;
	
	public Where(int x, int y) {
		this.x = x;
		this.y = y;
	}
}