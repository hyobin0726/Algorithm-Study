import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11663 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;
	static int[] arr;
	static int[][] line;
	
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		line = new int[M][2];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			line[i][0] = Integer.parseInt(st.nextToken());
			line[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < M; i++) {
			System.out.println(binarySearch(line[i][0], line[i][1]));
		}
	}

	static int binarySearch(int start, int end) {
		int start1 = 0;
		int end1= N-1;
		
		int resultStart = N;
		while(start1 <= end1) {
			int mid = (start1 + end1) / 2;
			
			if(arr[mid] < start) {
				start1 = mid + 1;
			}else {
				resultStart =  mid;
				end1 = mid -1;
			}
		}
		
		int start2 = 0;
		int end2= N-1;
		
		int resultEnd = N;
		while(start2 <= end2) {
			int mid = (start2 + end2) / 2;
			
			if(arr[mid] <= end) {
				start2 = mid + 1;
			}else {
				resultEnd =  mid;
				end2 = mid - 1;
			}
		}
		
		return resultEnd-resultStart;
		
	}
}
