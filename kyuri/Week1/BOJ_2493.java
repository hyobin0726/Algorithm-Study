/* 스택 이용 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    int N = Integer.parseInt(br.readLine());
	    int[] arr = new int[N];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for (int i = 0; i < N; i++) {
	    	arr[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    Stack<int[]> s = new Stack<>();
	    int[] ans = new int[N];
	    for (int i = 0; i < N; i++) {	
	    	while (!s.isEmpty()) {
	    		if (arr[i] < s.peek()[1]) {
	    			ans[i] = s.peek()[0];
	    			s.push(new int[] {i+1, arr[i]});
	    			break;
	    		} else {
	    			s.pop();
	    		}
	    	}
	    		
    		if (s.isEmpty()) {
	    		s.push(new int[] {i+1, arr[i]});
	    		ans[i] = 0;
	    	}
	    }
	    
	    for (int a : ans) {
	    	System.out.print(a + " ");
	    }
	}
}
