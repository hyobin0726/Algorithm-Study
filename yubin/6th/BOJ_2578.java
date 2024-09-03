import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2578 {
    /*
 * 1. 입력 케이스로 빙고판 만든다.
 * 2. 사회자가 부르는 수마다
 * 		2.1 불린 숫자를 체크하는 배열에다가 true로 넣는다.
 * 		2.2 한 줄이 될만한 조건이 확인한다.
 * 		2.3 채워진 줄 수가 3개 이상일 경우 true로 리턴한다.
 * 		2.4 3줄 이상일 경우 해당 숫자가 불린 번째를 출력하고 반복문을 break한다.
 * 		2.5 아직 빙고가 될 상황이 아닐 경우 반복한다.
 */
	static int[][] arr;
	static boolean[][] checkedArr;
	
	static boolean[] col;
	static boolean[] row;
	static boolean[] dea;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		arr = new int[5][5];
		checkedArr = new boolean[5][5];
		
		row = new boolean[5];
		col = new boolean[5];
		dea = new boolean[2];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		loopa:
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				if(check(Integer.parseInt(st.nextToken()))) {
					sb.append(i*5 + j + 1);
					break loopa;
				}
				
			}
		}
		
		System.out.println(sb);
	}
	
	static boolean check(int checkNum) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(arr[i][j] == checkNum) {
					checkedArr[i][j] = true;;
				}
			}
		}
		
		for (int i = 0; i < 5; i++) {
			int sum1 = 0;
			int sum2 = 0;
			for (int j = 0; j < 5; j++) {
				if(checkedArr[i][j] == true){
					sum1++;
				}
				if(checkedArr[j][i] == true){
					sum2++;
				}
			}
			
			if(sum1 == 5) {
				row[i] = true;
			}
			if(sum2 == 5) {
				col[i] = true;
			}
			
		}
		
		if(checkedArr[0][0] == true && checkedArr[1][1] == true & checkedArr[2][2] == true & checkedArr[3][3] == true & checkedArr[4][4]) {
			dea[0] = true;
		}
		
		if(checkedArr[4][0] == true && checkedArr[3][1] == true & checkedArr[2][2] == true & checkedArr[1][3] == true & checkedArr[0][4]) {
			dea[1] = true;
		}
		
		int resultSum = 0;
		for (int i = 0; i < 5; i++) {
			if(row[i] == true) {
				resultSum++;
			}
			if(col[i] == true) {
				resultSum++;
			}
		}
		if(dea[0] == true) {
			resultSum++;
		}
		if(dea[1]== true) {
			resultSum++;
		}
		
		if(resultSum >= 3) {
			return true;
		}else {
			return false;
		}
	}
	

}
