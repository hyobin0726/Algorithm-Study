import java.io.*;
import java.util.*;
public class SEA_4008 {
	static int n;
	static int[] op;
	static int[] num;
	static int mini = Integer.MAX_VALUE;
	static int maxi = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int test=1; test<t+1; test++) {
		mini = Integer.MAX_VALUE;
		maxi = Integer.MIN_VALUE;
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		op = new int[4];
		num = new int[n];
		for (int i=0; i<4; i++) {
			op[i]= Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		math(1,num[0],0,0,0,0);
		System.out.println("#"+test+" "+ (maxi-mini));
		}
	}
	static void math(int cnt, int total, int p, int m, int x, int d) {
		if(cnt==n) {
			if (total<mini) {
				mini = total;
			}
			if(total>maxi) {
				maxi =total;
			}
			return;
		}
		
		if(op[0]>p) {
			math(cnt+1, total+num[cnt], p+1,m,x,d);
			
		}
		if(op[1]>m) {
			math(cnt+1, total-num[cnt], p,m+1,x,d);
		}
		if(op[2]>x) {
			math(cnt+1, total*num[cnt], p,m,x+1,d);	
		}
		if(op[3]>d) {
			math(cnt+1, total/num[cnt], p,m,x,d+1);
		}
	}

}
