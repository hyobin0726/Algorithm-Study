import java.io.*;
import java.util.*;

public class BOJ_16637 {
    static int n;
    static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();
    static int total = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i =0; i <n; i++){
            char a = input.charAt(i);
            if ( i%2 == 0){
                arr.add(a - '0');
            }else {
                op.add(a);
            }
        }

        dfs(arr.get(0),0);
        System.out.println(total);
    }
    //백트래킹
    static void dfs(int temp,  int cnt){
        //연산자 모두 사용한 경우
        if (cnt == op.size()){
            total = Math.max(total,temp);
            return;
        }
        // 순서대로 계산 한 경우
        int resul1  = sum(temp, arr.get(cnt+1),op.get(cnt));
        dfs(resul1 ,cnt+1);
        // 다음 연산자(괄호 친 경우) 계산 한 경우
        if (cnt+1 <op.size()) {
            int resul2 = sum(arr.get(cnt+1), arr.get(cnt+2),op.get(cnt+1));
            int result3 = sum(temp, resul2, op.get(cnt));
            dfs(result3, cnt + 2);
        }
    }
    static int sum(int a,int b, char oper) {
        int temp = 0;
        switch (oper) {
            case '+' :
              temp= a+b;
               break;
            case '-' :
                temp= a-b;
                break;
            case '*':
                temp=a*b;
                break;
        }
        return temp;
    }
}
