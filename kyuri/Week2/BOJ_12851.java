/* BFS (큐) */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    
	    int[] arr = new int[100001];
	    Arrays.fill(arr, Integer.MAX_VALUE);
	    Queue<Integer> q = new LinkedList<>();
	    int cnt = 1;
	    
	    arr[N] = 0;
	    q.add(N);
	    while (!q.isEmpty()) {
	    	int x = q.poll();
	    	int[] next = {x-1, x+1, 2*x};
	    	for (int val : next) {
	    		if (val < 0 || val > 100000) { continue; }
	    		int time = arr[x] + 1;
	    		if (arr[K] < time) { continue; }
	    		if (arr[val] > time) {
	    			arr[val] = time;
	    			if (val == K) { cnt = 1; }
	    			q.add(val);
	    		} else if (arr[val] == time) {
	    			if (val == K) { cnt++; }
	    			q.add(val);
	    		}
	    	}
	    }
	    
	    System.out.println(arr[K]);
	    System.out.println(cnt);
	}
}
