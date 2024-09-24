import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {
	static BufferedReader br;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int number = Integer.parseInt(br.readLine());
		int[] arr = new int[number+1];
		
		arr[0] = 0;
		arr[1] = 0;
		
		for (int i = 2; i <= number; i++) {
			arr[i] = arr[i-1]+1;
			if(i % 2 == 0) {
				arr[i] = Math.min(arr[i], arr[i/2] + 1);
			}
			if(i % 3 == 0) {
				arr[i] = Math.min(arr[i], arr[i/3] + 1);
			}
		}
		System.out.println(arr[number]);
	}
	
}
