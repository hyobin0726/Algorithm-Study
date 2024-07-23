package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1966 {
	public static int TC;
	
	public static int N;
	public static int M;
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int result = 1;
			
			LinkedList<Integer> list = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			

			System.out.println(list);
			
			while(list.peek() != M) {
				
			}
			int peek = list.pop();
			
			System.out.println(peek);
			
			int size = list.size();
			
			
			
			for (int j = 0; j < size; j++) {
				if(peek < list.get(j)) {
					list.add(peek);
				}else {
					list.pop();
					result++;
				}
				
			}
		}
		
		
	}
}

