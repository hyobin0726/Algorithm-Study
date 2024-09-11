import java.io.*;
import java.util.*;
public class Main {
	static int[][] map;
	static int ans;
	static int[] paper = {0,5,5,5,5,5};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		ans = Integer.MAX_VALUE;
		for(int i =0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<10; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		System.out.println(ans ==Integer.MAX_VALUE ? -1: ans );
	}
	static void dfs(int x, int y, int cnt) {
		//x,y 과 9이면 종료
		//ans의 최소값 찾기
		if(x >=9 && y >9) {
			ans = Math.min(ans,cnt);
			return;
		}
		
		//cnt가 ans보다 크다면 종료
		if(ans <cnt) {
			return;
		}
		
		//y범위를 넘어가면 다음 열로 x증가
		if(y>9) {
			x+=1;
			y=0;
		}
		
		//5부터 확인하면 붙였다 떼기
		if (map[x][y] == 1) {
			for(int i=5; i >0; i--) {
				if(paper[i] > 0 && ispossible(x, y, i)) {
					paper[i] -=1;
					attach(x,y,i,0);
					dfs(x,y+1, cnt+1);
					attach(x,y,i,1);
					paper[i] +=1;
					
				}
			}
		}else {
			dfs(x,y+1, cnt);
		}
		
	}
	//붙일 수 있는가 확인
	static boolean ispossible (int a, int b, int size) {
		if (a + size > 10 || b + size > 10) {
            return false;
        }
		for(int i =a; i< a+size; i++) {
			for(int j =b; j< b+size; j++) {
				if(map[i][j]==1) {
					continue;
				}
				else {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//붙이기,떼기 실행
	static void attach(int a, int b, int size, int value) {
		for(int i =a; i< a+size; i++) {
			for(int j =b; j< b+size; j++) {
				map[i][j] = value;
			}
		}
	}
}
