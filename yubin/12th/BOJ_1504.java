import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	 static BufferedReader br;
	 static StringTokenizer st;
	 
	 static int N;  //정점 개수
	 static int M;  //간선 개수
	 static Node[] adjList;  //인접리스트
	 
	 static int pNum1;
	 static int pNum2;
	 
	 static class Node{
		 int vertex; //도착 정점
		 int weight;
		 Node nextNode;
		 
		 Node(int vertex, int weight, Node nextNode){
			 this.vertex = vertex;
			 this.weight = weight;
			 this.nextNode = nextNode;
		 }
	 }	
	 
	 public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];  //정점의 개수만큼 배열크기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[start] = new Node(end, weight, adjList[start]);
			adjList[end] = new Node(start, weight, adjList[end]);
		}
		
		st = new StringTokenizer(br.readLine());
		pNum1 = Integer.parseInt(st.nextToken());
		pNum2 = Integer.parseInt(st.nextToken());
		
		//1 -> pNum1 -> pNum2 - > N
		int sum1 = 0;
		int[] min1 = getMinDistance(1, adjList);
		int[] min2 = getMinDistance(pNum2, adjList);
		int[] min5 = getMinDistance(pNum1, adjList);
		if(min1[pNum1] != Integer.MAX_VALUE && min2[N] != Integer.MAX_VALUE && min5[pNum2] != Integer.MAX_VALUE) {
			sum1 += min1[pNum1];
			sum1 += min5[pNum2];
			sum1 += min2[N];
		}else {
			sum1 = Integer.MAX_VALUE;
		}
		
		int sum2 = 0;
		int[] min3 = getMinDistance(1, adjList);
		int[] min4 = getMinDistance(pNum1, adjList);
		int[] min6 = getMinDistance(pNum2, adjList);
		if(min1[pNum2] != Integer.MAX_VALUE && min2[N] != Integer.MAX_VALUE && min6[pNum1] != Integer.MAX_VALUE) {
			sum2 += min3[pNum2];
			sum2 += min6[pNum1];
			sum2 += min4[N];
		}else {
			sum2 = Integer.MAX_VALUE;
		}
		
		int result= Math.min(sum1, sum2);
		if(result == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(result);
		}
	}
	 
	 static int[] getMinDistance(int start,  Node[] adjList) {
		 final int V = adjList.length;
		 
		 int[] minDistance = new int[V];
		 Arrays.fill(minDistance, Integer.MAX_VALUE);
		 //출발지는 0
		 minDistance[start] = 0;
		 
		 //방문한 정점 관리
		 boolean[] visited = new boolean[V];
		 
		 for (int i = 1; i <V; i++) {
			 int min = Integer.MAX_VALUE;
			 int stopOver = -1;  //경유지
			 for (int j = 1; j < V; j++) {
				 //인접 정점이 아직 방문되지 않았고, 기존 거리보다 더 작은 경로가 발견되면
				if(!visited[j] && min > minDistance[j]) {
					min = minDistance[j];
					stopOver = j;
				}
			}
			 
			 if(stopOver == -1) break;
			 
			 visited[stopOver] = true;
			 
			 for (Node node = adjList[stopOver]; node != null; node = node.nextNode) {
				if(!visited[node.vertex] && minDistance[node.vertex] > min + node.weight) {
					minDistance[node.vertex] = min + node.weight;
				}
			}
		}
		 
		 return minDistance;
	 }
}
