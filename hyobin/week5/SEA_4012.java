//요리사
import java.io.*;
import java.util.*;
public class SEA_4012 {
	static int n;
	static Integer[] num;
	static int[][] graph;
	
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int test =1; test<t+1; test++ ) {
			n = Integer.parseInt(br.readLine());
			
			num = new Integer[n/2];
			 int ans = Integer.MAX_VALUE;
			graph = new int[n][n];
			result = new ArrayList<>();
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j ++) {
					graph[i][j] =Integer.parseInt(st.nextToken());
				}
			}
			number(0, num, 0);
			
			for(int i=0; i<result.size()/2; i++) {
				int temp = Math.abs(result.get(i) - result.get(result.size()-i-1));
				if(ans> temp) {
					ans =temp;
				}
				
			}
			System.out.println("#"+test+" "+ans);
		}
	}
	static void number(int cnt, Integer[] num, int idx) {
		if(cnt == n/2) {
			cook(num);
			return;
		}
		
		for(int i=idx; i<n; i++) {
			num[cnt] = i; 
			number(cnt+1, num, i+1);
		}
	}
	static void cook(Integer[] num) {
		int temp=0;
		
		for (int i =0; i<num.length; i++) {
			for(int j=0; j<num.length; j++) {
				temp+= graph[num[i]][num[j]];
			}
		}
		result.add(temp);
		
	}
	
	
}
