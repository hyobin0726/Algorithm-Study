import java.io.*;
import java.util.*;

public class Main {
    static int l,c;
    static char[] mo = {'a','e','i', 'o','u'};
    static String[] input;
    static String[] ans;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l= Integer.parseInt(st.nextToken());
        c= Integer.parseInt(st.nextToken());
        input=new String[c];
        ans = new String[l];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++){
            input[i] = st.nextToken();
        }
        Arrays.sort(input);
        dfs(0,ans, 0);
//        System.out.println(Arrays.toString(input));

    }
    static void dfs(int idx, String[] ans,int depth){
        if(idx == l){
            int temp=0;
            sb= new StringBuilder();
            for(int i=0; i<idx; i++){
                if(ans[i].equals("a") || ans[i].equals("e") || ans[i].equals("i") || ans[i].equals("o") ||ans[i].equals("u")){
                    temp++;
                }
            }
            if(temp>0 && temp<=l-2){
                for(int i=0; i<idx; i++){
                    sb.append(ans[i]);
                }
                System.out.println(sb);
            }
            return;
        }
        for(int i=depth; i<c; i++){
            ans[idx] = input[i];
            dfs(idx+1, ans, i+1);
        }
    }
}
