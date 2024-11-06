//같은 행에 퀸을 두지 않는 방식
public class BOJ_9663 {

  //
	static int N;
	static int[] col; //이차원으로 안 둠
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1];  //1행부터 시작하기 위해 크기 +1 함
		
		setQueens(1);
		System.out.println(ans);
	}
	static void setQueens(int rowNo) {
		
		//가지치기
		if(!isAvailable(rowNo-1)) return;
		
		if(rowNo > N) {
			//무조건 답
			ans++;
			return;
		}
		
		//유도 파트
		for (int c = 1; c <= N; c++) {
			col[rowNo] = c;
			setQueens(rowNo + 1);
		}
	}
	
	static boolean isAvailable(int rowNo) {
		//새로운 퀸을 놓기전에 조금 전 선택이 옳바른지 본다.
		for (int k = 1; k < rowNo; k++) {
			if(col[rowNo] == col[k] || rowNo-k == Math.abs(col[rowNo] - col[k])) {
				return false;
			}
		}
		
		return true;
	}
}
