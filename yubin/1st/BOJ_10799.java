package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10799 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
				
		int result = 0 ;
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.add(str.charAt(i));
			}else {
				if(str.charAt(i - 1) == '(') {
					stack.pop();
					result += stack.size();
				}else {
					stack.pop();
					result += 1;
				}
			}
			
		}
		
		System.out.println(result);
		
		
	}
}
