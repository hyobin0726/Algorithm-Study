import java.util.*;
import java.io.*;
public class BOJ_1717 {
    //유니온파인드 (합집합 찾기)
    static int n;
    static int m;
    static int[] parents;

    static void make(){
        parents = new int[n+1];
        for (int i=0; i<=n; i++){
            parents[i] = i;
        }
    }
    static int find (int a){
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }
    static void union (int a, int b){
        int aroot = find(a);
        int broot = find(b);

        if(a<b){
            parents[broot] = aroot;
        }
        else{
            parents[aroot] = broot;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        make();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 합집합 연산
            if(op==0){
                union(a, b);
            }
            // 같은 집합인지 확인
            else{
                if(find(a)==find(b)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
        }

    }
}
