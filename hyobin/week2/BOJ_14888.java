import java.io.*;
import java.util.*;

public class BOJ_14888 {
	static int n;
	static int[] a ;
	static int[] opArr;
	static int count;
	static int total;
	static List<Integer> result = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new  StringTokenizer(br.readLine());
		a =  new int[n];
		for (int i =0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer str = new  StringTokenizer(br.readLine());
		opArr = new int[4];
		for (int i =0; i<4; i++) {
			opArr[i] = Integer.parseInt(str.nextToken());
		}
		int total = a[0];
	
		Operator(total,1,opArr[0],opArr[1],opArr[2],opArr[3]);
		
		System.out.println(Collections.max(result));
		System.out.print(Collections.min(result));
		
	}
	static void Operator(int total, int count, int p, int m, int x, int d) {
	
		if (count== n) {
			result.add(total);
			return;
		}
		if (p>0) {
			Operator(total+a[count], count+1, p-1, m, x, d);
		}
		if (m>0) {
			Operator(total-a[count], count+1, p, m-1, x, d);
		}
		if (x>0) {
			Operator(total*a[count], count+1, p, m, x-1, d);
		}
		if(d>0) {
			if (total>0) {
			Operator(total/a[count], count+1, p, m, x, d-1);
		} else if (total<0) {
			Operator(-(-total/a[count]), count+1, p, m, x, d-1);
		}
		}
	}
}
