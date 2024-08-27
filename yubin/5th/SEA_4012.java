package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4012 {

	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int TC;
	static int N;
	static int[][] arr;
	static boolean[] isSelected;
	static int minResult = Integer.MAX_VALUE;
	static int[] ingradientArr;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		for (int i = 1; i <= TC; i++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					arr[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelected = new boolean[N];
			minResult = Integer.MAX_VALUE;
			
			combinationTest(0,0);
			
			sb.append("#").append(i).append(" ").append(minResult).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void combinationTest(int cnt, int at) {
		if(cnt == N / 2) {
			minResult = Math.min(minResult, calculate());	
			return;
		}
		
		for (int i = at; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			combinationTest(cnt + 1, i + 1);
			isSelected [i] = false;
		}
	}
	
	static int calculate() {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isSelected[i] && isSelected[j]) {
					sum1 += arr[i][j];
				} else if(!isSelected[i] && !isSelected[j]) {
					sum2 += arr[i][j];
				}
			}
		}
		
		return Math.abs(sum1 - sum2);
	}
}
