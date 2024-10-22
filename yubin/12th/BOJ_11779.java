import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	 static BufferedReader br;
	 static StringTokenizer st;
	 static StringBuilder sb;
	 
	 static int N;  //정점 개수
	 static int M;  //간선 개수
	 static Node[] adjList;  //인접리스트
	 
	 static int start;
	 static int end;
	 
	 static ArrayList<Integer> arr;
	 
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
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjList = new Node[N+1];  //정점의 개수만큼 배열크기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[start] = new Node(end, weight, adjList[start]);
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		
		int[] min = getMinDistance(start, adjList);
		
		sb.append(min[end]).append("\n");
		sb.append(arr.size()).append("\n");
		for (int i = 0; i < arr.size(); i++) {
			sb.append(arr.get(i)).append(" ");
		}
		
		System.out.println(sb);
	}
	 
	 static int[] getMinDistance(int start, Node[] adjList) {
		 final int V = adjList.length;
		 
		 int[] minDistance = new int[V];
		 int[] path = new int[V];  // 경유지 저장할 배열
		 Arrays.fill(minDistance, Integer.MAX_VALUE);
		 Arrays.fill(path, -1);  // 초기값을 -1로 설정 (경로가 없을 때)
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
					path[node.vertex] = stopOver;  //경유지 갱신
				}
			}
			 
			 
		}
		 
		// 경로 역추적
		arr.clear();
		int current = end;
		while (current != -1) {
		     arr.add(0, current);  // 역순으로 저장
		     current = path[current];
		}
		    
		return minDistance;
	 }
}
