/* 큐 이용 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    int N = Integer.parseInt(br.readLine());
	    int[][] board = new int[N][N];
	    
	    int K = Integer.parseInt(br.readLine());
	    for (int i = 0; i < K; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	board[a-1][b-1] = 1;
	    }
	    
	    int L = Integer.parseInt(br.readLine());
	    Queue<String[]> change = new LinkedList<>();
	    for (int i = 0; i < L; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	String a = st.nextToken();
	    	String b = st.nextToken();
	    	change.add(new String[] {a, b});
	    }
	    
	    int time = 0;
	    int d = 0;
	    Deque<int[]> snake = new ArrayDeque<>();
	    Map<Integer, int[]> dir = new HashMap<>();
	    dir.put(3, new int[] {-1, 0});
	    dir.put(1, new int[] {1, 0});
	    dir.put(2, new int[] {0, -1});
	    dir.put(0, new int[] {0, 1});
	    
	    snake.addFirst(new int[] {0, 0});
	    while (true) {
	    	time++;
	    	int nx = snake.peek()[0] + dir.get(d)[0];
	    	int ny = snake.peek()[1] + dir.get(d)[1];
	    	if (nx < 0 || nx >= N || ny < 0 || ny >= N) { break; } 
	    	
	    	boolean check = false;
	    	for (int[] elem : snake) {
	    	    if (elem[0] == nx && elem[1] == ny) {
	    	    	check = true;
	    	        break;
	    	    }
	    	}
	    	if (check) { break; }

	    	snake.addFirst(new int[] {nx, ny});
	    	if (board[nx][ny] == 1) {
	    		board[nx][ny] = 0;
	    	} else {
	    		snake.pollLast();
	    	}
	    	
	    	if (!change.isEmpty() && Integer.parseInt(change.peek()[0]) == time) {
	    		if (change.peek()[1].equals("L")) {
	    			d -= 1;
	    			if (d < 0) { d = 3; }
	    		} else if (change.peek()[1].equals("D")) {
	    			d += 1;
	    			d %= 4;
	    		}
	    		change.poll();
	    	}
	    }
	    System.out.println(time);
	}
}
