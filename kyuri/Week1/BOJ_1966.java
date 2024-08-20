/* 큐와 비슷하지만 인덱스 사용가능한 LinkedList 이용 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    int T = Integer.parseInt(br.readLine());
	    for (int t = 0; t < T; t++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int N = Integer.parseInt(st.nextToken());
	        int M = Integer.parseInt(st.nextToken());
	
	        LinkedList<int[]> q = new LinkedList<>();
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	            q.add(new int[] {i, Integer.parseInt(st.nextToken())});
	        }
	
	        int cnt = 0;
	        while (!q.isEmpty()) {
	            int[] f = q.poll();
	            boolean isMax = true;
	
	            for (int i = 0; i < q.size(); i++) {
	                if (f[1] < q.get(i)[1]) {
	                    q.add(f);
	                    for (int j = 0; j < i; j++) {
	                    	q.add(q.poll());
	                    }
	                    isMax = false;
	                    break;
	                }
	            }
	            
	            if (!isMax) { continue; }
	            
	            cnt++;
	            if (f[0] == M) { break; }
	        }
	        
	        System.out.println(cnt);
	    }
	
	}
}
