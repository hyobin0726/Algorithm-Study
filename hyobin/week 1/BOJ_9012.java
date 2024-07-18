
import java.io.*;
import java.util.*;

public class BOJ_9012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i =0; i <t; i++ ) {
			String answer = "YES";
			Stack<Character> stack = new Stack<>();
			String str = br.readLine();
			
			for (int j =0; j<str.length(); j++) {
				if (str.charAt(j) == '(') {
					stack.push(str.charAt(j));
				}else {
					if(stack.isEmpty()) {
						answer= "NO";
						break;
					}else {
						stack.pop();
					}
				}
			}
			if(!stack.isEmpty()) {
				answer= "NO";
			}
			System.out.println(answer);
		}
		
	}
}
