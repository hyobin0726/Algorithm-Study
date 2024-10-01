import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N; //나무 수
	static int M; //가져가려는 나무 높이
	
	static int[] arr;
	
	static int result;
	
    public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		result = Integer.MIN_VALUE;
		
		binarySearch();
		
		System.out.println(result);
	}
    
    public static long check(int num) {
    	long sum = 0;
    	
    	for (int i = 0; i < N; i++) {
			if(arr[i] > num) sum += (arr[i] - num);
		}
    	
    	if(sum >= M) {
    		result = Math.max(num, result);
    	}
    	
    	return sum;
    }
    
    public static void binarySearch() {
    	int start = 0;
    	int end = arr[N-1];
    	
    	
    	while(start <= end) {
    		int mid = (start + end ) / 2;
    		
    		long tmp = check(mid);
    		
    		if(tmp > M) {
    			start = mid + 1;
    		}else {
    			end = mid - 1;
    		}
    		
    	}
    }
}
