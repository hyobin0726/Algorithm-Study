/* 스택 이용 */
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			Stack<String> stack = new Stack<>();
			String str = br.readLine();
			char[] arr = str.toCharArray();
			String ans = "YES";
			for (char s : arr) {
				if (s == '(') {
					stack.push("(");
				} else {
					if (stack.empty()) {
						ans = "NO";
						break;
					}
					stack.pop();
				}
			}
			
			if (ans.equals("YES") && !(stack.empty())) {
				ans = "NO";
			}
			System.out.println(ans);
		}
		
	}
}
