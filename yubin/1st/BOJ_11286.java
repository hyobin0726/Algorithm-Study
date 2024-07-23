package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_11286 {
	public static int N;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Q> queue = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(queue.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					Q q = queue.poll();
					if(q.minus == true) {
						sb.append(-(q.number)).append("\n");
					}else {
						sb.append(q.number).append("\n");
					}
				}
			}else {
				boolean b = num > 0 ? false : true;
				int abs = num > 0 ? num : -(num);
				queue.add(new Q(abs, b));
			}
		}
		System.out.println(sb);

	}
}

class Q implements Comparable<Q>{
	public int number;
	public boolean minus;
	
	Q(int number, boolean minus){
		this.number = number;
		this.minus = minus;
	}

	@Override
	public int compareTo(Q o) {
		if (this.number == o.number) {
            return Boolean.compare(o.minus, this.minus);
        }
        return this.number - o.number;
	}
}
