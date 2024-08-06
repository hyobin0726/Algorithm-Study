package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
				
		Stack<Character> stack = new Stack<>();
		
		
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			if(stack.size() >= bomb.length()) {
				for (int j = 0; j < bomb.length(); j++) {
					if(stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						break;
					}
					if(j == bomb.length() - 1) {
						for (int j2 = 0; j2 < bomb.length(); j2++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}
		
		System.out.println(sb);

		
	}
}
