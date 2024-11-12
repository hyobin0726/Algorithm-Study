import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N,M,R;
	static int[][] arr;
	static int[] runArr;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		runArr = new int[R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			
			switch(num) {
			case 1:
				arr = method1();
				break;
			case 2:
				arr = method2();
				break;
			case 3:
				arr = method3();
				int tmp = M;
				M = N;
				N = tmp;
				break;
			case 4:
				arr = method4();
				tmp = M;
				M = N;
				N = tmp;
				break;
			case 5:
				arr = method5();
				break;
			case 6:
				arr = method6();
				break;
			}
			
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	} 
	
	//1번 연산: 상하 반전 
	static int[][] method1() {
		int[][] cloneArr = new int[N][M];
		//그냥 뒤에서 읽기
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				cloneArr[(N-1)-i][j] = arr[i][j];
			}
		}
		return cloneArr;
	}

	//2번 연산: 좌우 반전
	static int[][] method2() {
		int[][] cloneArr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cloneArr[i][j] = arr[i][(M-1)-j];
			}
		}
		return cloneArr;
	}
	
	//3번 연산: 오른쪽 90도 회전
	static int[][] method3() {
		int[][] cloneArr = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				cloneArr[i][j] = arr[(N-1)-j][i];
			}
		}
		
		return cloneArr;
	}
	
	//4번 연산: 왼쪽 90도 회전
	static int[][] method4() {
		int[][] cloneArr = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				cloneArr[i][j] = arr[j][(M-1)-i];
			}
		}
		
		return cloneArr;
	}
	
	//5번 연산
	static int[][] method5() {
		int[][] cloneArr = new int[N][M];
		
		//1 -> 2
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				cloneArr[i][M/2+j] = arr[i][j];
			}
		}
		
		//2 -> 3
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				cloneArr[i+N/2][j] = arr[i][j];
			}
		}
		
		//3 -> 4
		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				cloneArr[i][j-M/2] = arr[i][j];
			}
		}
		
		//4 -> 1
		for (int i = N/2; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				cloneArr[i-N/2][j] = arr[i][j];
			}
		}
		
		return cloneArr;
	}
	
	//6번 연산
	static int[][] method6(){
		int[][] cloneArr = new int[N][M];
		
		//1 -> 4
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				cloneArr[i+N/2][j] = arr[i][j];
			}	
		}
		
		//2 -> 1
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				cloneArr[i][j-M/2] = arr[i][j];
			}
		}
		
		//3 -> 2
		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				cloneArr[i-N/2][j] = arr[i][j];
			}
		}
		
		//4 -> 3
		for (int i = N/2; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				cloneArr[i][j+M/2] = arr[i][j];
			}
		}
		
		return cloneArr;
	}
}
