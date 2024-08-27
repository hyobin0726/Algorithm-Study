import java.io.*;

//같은 행에 퀸을 두지 않는 방식
public class BOJ_9663 {
    static int n;
    static int[] col;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new int[n+1];
        setQueen(1);
        System.out.println(ans);


    }
    static void setQueen(int row) {
        if(!isPossible(row-1)) return;
        if (row >n) {
            ans++;
            return;
        }
        for (int i = 1; i < n+1; i++) {
            col[row] = i;
            setQueen(row+1);
        }
    }
    static boolean isPossible(int row) {
        for (int i = 1; i < row; i++) {
            // 같은 열에 있는지, 대각선에 있는지 확인
            if (col[i] == col[row] || Math.abs(col[i]-col[row]) == row-i) return false;
        }
        return true;
    }
}
