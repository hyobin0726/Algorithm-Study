import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[][] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m=Integer.parseInt(br.readLine());
        node = new int[n+1][n+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(i==j) {
                    node[i][j] = 0;

                }else {
                    node[i][j] = 100000001;
                }
            }
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            node[s][e] = Math.min(node[s][e], c);
        }

        ArrayList<Integer>[][] list = new ArrayList[n+1][n+1];
        for(int i=0; i<=n ; i++){
            for(int j=0; j<=n; j++){
                list[i][j] = new ArrayList<>();
            }
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(node[i][j] > node[i][k] + node[k][j]){
                        node[i][j]= node[i][k] + node[k][j];
                        list[i][j].clear();
                        for(int a=0; a<list[i][k].size(); a++){
                            list[i][j].add(list[i][k].get(a));
                        }
                        list[i][j].add(k);
                        for(int a=0; a<list[k][j].size(); a++){
                            list[i][j].add(list[k][j].get(a));
                        }
                    }

                }
            }
        }
        for(int i=1; i<=n; i++){
            for(int j =1; j<=n; j++){
                if(node[i][j] ==100000001 ){
                    node[i][j] =0;
                }
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                System.out.print(node[i][j] + " ");
            }
            System.out.println();
        }
        for(int i=1; i<=n; i++){
           for(int j=1; j<=n ; j++){
               if(i==j || node[i][j] ==0 ){
                   System.out.println(0);
               }else {
                   System.out.print(list[i][j].size()+2 + " ");
                   System.out.print(i + " ");
                   for(int k=0; k<list[i][j].size(); k++){
                       System.out.print(list[i][j].get(k) + " ");
                   }
                   System.out.println(j);
               }
           }
        }


    }
}
