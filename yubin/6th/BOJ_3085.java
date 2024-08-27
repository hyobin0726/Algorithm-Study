import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3085 {

	/*
	 * 인접한 사탕끼리 색을 바꿔주기
	 * 가장 긴 수열 찾기(행, 열 체크)
	 * 바꿔준 사탕의 색 돌려주기
	 */
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static char[][] arr;
	static int N;
	
	static int resultMax = 0;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				swap(i,j, i, j+1);
				check1();
				swap(i,j + 1, i, j);
				
				swap(j,i, j+1,i);
				check1();
				swap(j+1,i, j,i);
			}
		}
		
		
		System.out.println(resultMax);
	}
	
	static void swap(int x1, int y1, int x2, int y2) {
		char tmp = arr[x1][y1];
		arr[x1][y1] = arr[x2][y2];
		arr[x2][y2] = tmp;
	}
	
	
	
	static void check1() {
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			int max = 1;
			for (int j = 0; j < N-1; j++) {
				if(arr[i][j] == arr[i][j+1]) {
					max++;
				}else {
					if(tmp < max) {
						tmp = max;
					}
					max = 1;
				}
			}
			
			if(tmp < max) {
				tmp = max;
			}

		}
		if(resultMax < tmp) {
			resultMax = tmp;
		}
		
		
		tmp = 0;
		for (int i = 0; i < N; i++) {
			int max = 1;
			for (int j = 0; j < N-1; j++) {
				if(arr[j][i] == arr[j+1][i]) {
					max++;
				}else {
					if(tmp < max) {
						tmp = max;
					}
					max = 1;
				}
			}
			
			if(tmp < max) {
				tmp = max;
			}
		}
		
		if(resultMax < tmp) {
			resultMax = tmp;
		}

	}
	
		
}
