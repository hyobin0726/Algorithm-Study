import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Arrays;
public class BOJ_11866 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        LinkedList arr=new LinkedList<>();
        for(int i=1;i<n+1;i++){
            arr.add(i);
        }
        pw.print("<");
        while(arr.size()>1){
            for(int i =0;i<k-1;i++){
            arr.addLast(arr.removeFirst());
            }
            pw.print(arr.removeFirst()+", ");
        }
        pw.print(arr.getFirst()+">");

        pw.flush();
        pw.close();
    }
}