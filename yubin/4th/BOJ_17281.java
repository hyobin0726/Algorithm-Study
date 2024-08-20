import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_17281 {
	static int N;  //이닝 수
	static int[][] arr;  //이닝
	static boolean[] visited = new boolean[10];
	static int[] players = new int[10];
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[4] = true;
		players[4] = 1;
		
		//순서 정하기
		order(2);
		
		System.out.println(answer);
		
	}
	
	//순서 정하기 백트래킹
	public static void order(int depth) {
		if(depth == 10) {
			//경기해보기
			int score = game();
			answer = Math.max(answer, score); 
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				players[i] = depth;
				order(depth + 1);
				visited[i] = false;
			}
		}        
	}
	
	public static int game() {
		int sum = 0;
		
		int idx = 1;
		for (int i = 0; i < N; i++) {
			int inningScore = 0;
			int out = 0;
			 boolean[] base = new boolean[4];
			 
			 while(out < 3) {
				 switch(arr[i][players[idx]]) {
				 case 0:
					 out++;
					 break;
				 case 1:
					 if(base[3]) {
						 inningScore++;
						 base[3] = false;
					 }
					 if(base[2]) {
						 base[2] = false;
						 base[3] = true;
					 }
					 if(base[1]) {
						 base[2] = true;
					 }
					 base[1] = true;
					 break;
					 
				 case 2:
					 if(base[3]) {
						 inningScore++;
						 base[3] = false;
					 }
					 if(base[2]) {
						 inningScore++;
					 }
					 if(base[1]) {
						 base[1] = false;
						 base[3] = true;
					 }
					 base[2] = true;
					 break;
					 
				 case 3:
					 if(base[3]) {
						 inningScore++;
					 }
					 if(base[2]) {
						 inningScore++;
						 base[2] = false;
					 }
					 if(base[1]) {
						 inningScore++;
						 base[1] = false;
					 }
					 base[3] = true;
					 break;
				 case 4:
					 if(base[3]) {
						 inningScore++;
						 base[3] = false;
					 }
					 if(base[2]) {
						 inningScore++;
						 base[2] = false;
					 }
					 if(base[1]) {
						 inningScore++;
						 base[1] = false;
					 }
					 inningScore++;
					 break;
			 }

			idx++;
			if(idx >= 10) {
				idx = 1;
			}
				}	
			sum += inningScore;
		}
		return sum;
	}
}
