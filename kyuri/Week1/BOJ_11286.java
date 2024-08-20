/* 우선순위 큐 이용, 기준 Override */
import java.io.*;
import java.util.*;

class SortComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		if (Math.abs(o1) == Math.abs(o2)) {
			return o1 - o2;
		} else {
			return Math.abs(o1) - Math.abs(o2);
		}
	}
}

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	
	    PriorityQueue<Integer> pq = new PriorityQueue<>(new SortComparator());
	    for (int t = 0; t < T; t++) {
	    	int n = Integer.parseInt(br.readLine());
	    	if (n == 0) {
	    		if (pq.isEmpty()) {
	    			System.out.println(0);
	    		} else {
	    			System.out.println(pq.poll());
	    		}
	    		continue;
	    	}
	    	
	    	pq.add(n);
	    }
	
	}
}
