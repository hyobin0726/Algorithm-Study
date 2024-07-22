import java.io.*;
import java.util.*;

public class BOJ_11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pqm = new PriorityQueue<>();
		PriorityQueue<Integer> pqp = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if ((!pqm.isEmpty() && !pqp.isEmpty())) {
					if(pqm.peek() <= pqp.peek()) {
						System.out.println(-pqm.poll());
					}else {
						System.out.println(pqp.poll());
					}
				} else if ((!pqm.isEmpty() && pqp.isEmpty())) {
					System.out.println(-pqm.poll());
				} else if ((pqm.isEmpty() && !pqp.isEmpty())) {
					System.out.println(pqp.poll());
				} else if ((pqm.isEmpty() && pqp.isEmpty())) {
					System.out.println(0);
				}
				
			}else {
				if (x >0) {
					pqp.offer(x);
				}else {
					pqm.offer(-x);
				}
			}
		}
	}
}