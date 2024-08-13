import java.io.*;
import java.util.*;

public class BOJ_16637 {

    static int N;;
    static int result = Integer.MIN_VALUE;
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<Character> operators = new ArrayList<>();

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            if(i % 2 == 0) nums.add(Character.getNumericValue(s.charAt(i)));
            else operators.add(s.charAt(i));
        }

        find(0, nums.get(0));
        System.out.println(result);

    }

    private static void find(int idx, int total){
        if(idx == operators.size()){
            result = Math.max(result, total);
            return;
        }

        int cal = method(total, nums.get(idx + 1), operators.get(idx));
        find(idx + 1, cal);

        if(idx + 2 <= nums.size() - 1){
            cal = method(total, method(nums.get(idx + 1), nums.get(idx + 2), operators.get(idx + 1)), operators.get(idx));
            find(idx + 2, cal);
        }
    }

    static int method(int a, int b, char m){
        if(m == '+'){
            return a + b;
        } else if (m == '-') {
            return a - b;
        }else{
            return a * b;
        }
    }

}

