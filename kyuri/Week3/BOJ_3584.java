/* BFS (큐) */ - 더 간단하게 코드 수정 필요
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	int N = Integer.parseInt(br.readLine());
        	
        	List<Integer>[] tree = new ArrayList[N+1];
        	for (int i = 0; i <= N; i++) {
        		tree[i] = new ArrayList<Integer>();
        	}
        	
        	for (int i = 0; i < (N-1); i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		tree[b].add(a);
        	}
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	int ans = 0;
        	List<Integer> n1_li = bfs(N, node1, tree);
        	List<Integer> n2_li = bfs(N, node2, tree);
        	int len1 = n1_li.size(), len2 = n2_li.size();
        	int min_len = Math.min(len1, len2);
        	for (int i = 1; i <= min_len; i++) {
        		if (n1_li.get(len1-i).equals(n2_li.get(len2-i))) {
        			ans = n1_li.get(len1-i);
        		}
        	}
        	System.out.println(ans);
        }

	}
    
    public static List<Integer> bfs(int N, int node, List<Integer>[] tree) {
    	List<Integer> result = new ArrayList<>();
    	int[] visited = new int[N+1];
    	Queue<Integer> q = new LinkedList<>();
    	q.add(node);
    	result.add(node);
    	while (!q.isEmpty()) {
    		int x = q.poll();
    		visited[x] = 1;
    		for (int next : tree[x]) {
    			if (visited[next] == 0) {
    				visited[next] = 1;
    				result.add(next);
    				q.add(next);
    			}
    		}
    	}
		return result;
    }
}
