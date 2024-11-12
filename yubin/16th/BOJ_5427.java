import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427{
	/**
	 * BOJ 5427 G4 불
	 */
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int TC;
	static int N,M;
	static char[][] arr;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static Queue<Person> personQueue;
	static Queue<Fire> fireQueue;
	static boolean[][] check;
	
	static class Person{
		int x,y,count;

		public Person(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	static class Fire{
		int x,y;

		public Fire(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			arr = new char[N][M];
			personQueue = new LinkedList<>();
			fireQueue = new LinkedList<>();
			check = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < M; j++) {
					char tmp2 = tmp.charAt(j);
					arr[i][j] = tmp2;
					if(tmp2 == '*') {
						fireQueue.add(new Fire(i, j));
					}else if(tmp2 == '@') {
						personQueue.add(new Person(i,j,0));
						check[i][j] = true;
					}
				}
			}
			
			int result = BFS();
			if(result != -1) {
				sb.append(result).append("\n");
			}else {
				sb.append("IMPOSSIBLE").append("\n");
			}
			
		}
		System.out.println(sb);
	}
	
	static int BFS() {
	    while (!personQueue.isEmpty()) {
	        // 불 먼저 확산
	        int fireSize = fireQueue.size();
	        for (int f = 0; f < fireSize; f++) {
	            Fire fire = fireQueue.poll();
	            for (int i = 0; i < 4; i++) {
	                int nextX = fire.x + dx[i];
	                int nextY = fire.y + dy[i];

	                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
	                if (arr[nextX][nextY] == '*' || arr[nextX][nextY] == '#') continue;

	                if (arr[nextX][nextY] == '.' || arr[nextX][nextY] == '@') {
	                    arr[nextX][nextY] = '*';
	                    fireQueue.add(new Fire(nextX, nextY));
	                }
	            }
	        }

	        // 사람 이동
	        int personSize = personQueue.size();
	        for (int p = 0; p < personSize; p++) {
	            Person person = personQueue.poll();
	            for (int i = 0; i < 4; i++) {
	                int nextX = person.x + dx[i];
	                int nextY = person.y + dy[i];

	                // 탈출 성공 조건
	                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
	                    return person.count + 1;
	                }

	                if (arr[nextX][nextY] == '*' || arr[nextX][nextY] == '#') continue;
	                if (check[nextX][nextY]) continue;

	                if (arr[nextX][nextY] == '.') {
	                    check[nextX][nextY] = true;
	                    personQueue.add(new Person(nextX, nextY, person.count + 1));
	                }
	            }
	        }
	    }
	    return -1;
	}

}
