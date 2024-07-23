package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_6198 {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());   //빌딩 번호
		
		int[] arr = new int[N];
		int[] cnt = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(arr[i] > arr[j]) {
					cnt[i]++;
				}else {
					break;
				}
			}
		}
		
		for (int i = 0; i < cnt.length; i++) {
			result += cnt[i];
		}
		
		System.out.println(result);
	}
}

