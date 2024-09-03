import java.io.*;
import java.util.*;
public class BOJ_1976 {
    static int n;
    static int m;
    static int[] parents;
    static int[] city;

    static int get(int a){
        if(parents[a] == a) return a;
        return parents[a] = get(parents[a]);
    }
    static boolean find(int a, int b){
        int aroot = get(a);
        int broot = get(b);

        if(aroot==broot) return true;
        return false;
    }

    static void union(int a, int b){
        int aroot = get(a);
        int broot = get(b);

        if(a<b){
            parents[broot] = aroot;
        }else {
            parents[aroot] = broot;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        city = new int[m];
        StringTokenizer st;
        parents = new int[n+1];
        for (int i =1; i<=n ; i++){
            parents[i]=i;
        }
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n;j++){
                if(Integer.parseInt(st.nextToken()) == 1){
                    if(!find(i,j)) {
                        union(i, j);
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            city[i] = Integer.parseInt(st.nextToken());
        }
        boolean isflag = false;
        for(int i=0; i<m-1; i++){
            if(!find(city[i], city[i+1])){

                isflag = true;
                break;
            }
        }
        if(isflag){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }

    }
}
