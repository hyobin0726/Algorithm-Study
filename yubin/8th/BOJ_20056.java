import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int arr[][];
	static int N,M,K;
	
	static ArrayList<FireBall> fireballList;
	
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	static int answer;
	
	static class FireBall{
		int x,y, m,s,d;

		public FireBall(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());  //배열 크기
		arr = new int[N][N];
		M = Integer.parseInt(st.nextToken()); //파이어볼 갯수
		fireballList = new ArrayList<>();
		K = Integer.parseInt(st.nextToken());  //명령 갯수
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fireballList.add(new FireBall(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		//마법사 상어가 이동을 K번 명령한 후
		for (int i = 0; i < K; i++) {
			//모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
			moveFireBall();
			divideFireBall();
		}
		answer = sumM();
		System.out.println(answer);
	}
	//모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
	static void moveFireBall() {
		for (int[] is : arr) {
			Arrays.fill(is, 0);
		}
		for (FireBall fireBall : fireballList) {
			fireBall.x = (N + fireBall.x + dx[fireBall.d] * (fireBall.s % N)) % N;
			fireBall.y = (N + fireBall.y + dy[fireBall.d] * (fireBall.s % N)) % N;
//			이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
			arr[fireBall.x][fireBall.y]++;
		}
	}
	//이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
	static void divideFireBall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//파이어볼 2개 이상인 경우
				if(arr[i][j] >= 2) {
					//같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
					int mSum = 0;  //질량의 합
					int sSum = 0;  //속력의 합
					int direction = 0;
					boolean resultDirection = true;
					int idx = 0;
					for (FireBall fireBall : fireballList) {
						if(fireBall.x == i && fireBall.y == j) {
							mSum += fireBall.m;
							sSum += fireBall.s;
							if(idx == 0) {
								direction = fireBall.d % 2;
								idx++;
 							}else{
								if(direction != fireBall.d % 2) resultDirection = false;
							}
						}
					}
					
					for (int k = 0; k < fireballList.size(); k++) {
						if(fireballList.get(k).x == i && fireballList.get(k).y == j) {
							fireballList.remove(k);
							k--;
						}
					}
					
					//파이어볼은 4개의 파이어볼로 나누어진다.
					//질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
					int m = mSum/5;
					//속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
					int s = sSum/arr[i][j];
					//합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
					if(resultDirection) {
						if(m != 0) {
							for (int k = 0; k < 8; k = k+2) {
								fireballList.add(new FireBall(i, j, m, s, k));
							}
						}
					}else {
						if(m != 0) {
							for (int k = 1; k < 8; k = k+2) {
								fireballList.add(new FireBall(i, j, m, s, k));
							}
						}
					}
					arr[i][j] = 0;
				}
			}
		}
	}

	static int sumM() {
		int sum = 0;
		for (FireBall fireball : fireballList) {
			sum += fireball.m;
		}
		return sum;
	}
}
