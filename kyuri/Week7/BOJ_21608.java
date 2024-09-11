import java.io.*;
import java.util.*;

/* 단순 구현 */
class Info implements Comparable<Info> {
    int x, y, emptyCnt, likeCnt;
    public Info(int x, int y, int emptyCnt, int likeCnt) {
        super();
        this.x = x;
        this.y = y;
        this.emptyCnt = emptyCnt;
        this.likeCnt = likeCnt;
    }

    @Override
    public int compareTo(Info o) {
        if (this.likeCnt != o.likeCnt) return o.likeCnt - this.likeCnt;
        if (this.emptyCnt != o.emptyCnt) return o.emptyCnt - this.emptyCnt;
        if (this.x != o.x) return this.x - o.x;
        return this.y - o.y;
    }
}

public class Main {
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int[][] map;
    static HashMap<Integer, HashSet<Integer>> friends; // HashSet의 contains로 값을 더 빨리 찾기 위함
    static int[] Students;
    static int N, empty, like;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        friends = new HashMap<>();
        map = new int[N][N];
        Students = new int[N*N];
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            Students[i] = a;
            HashSet<Integer> li = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                li.add(Integer.parseInt(st.nextToken()));
            }
            friends.put(a, li);
        }

        for (int i = 0; i < N * N; i++) {
            Info info = find(Students[i]);
            map[info.x][info.y] = Students[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Count(i, j, map[i][j]);
                if (like > 0) sum += Math.pow(10, like - 1);
            }
        }
        System.out.println(sum);
    }

    // 앉을 수 있는 좌석 찾기
    public static Info find(int num) {
        Info info = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    Count(i, j, num);
                    Info now = new Info(i, j, empty, like);
                    if (info == null) {
                        info = now;
                        continue;
                    }
                    if (info.compareTo(now) > 0) info = now;
                }
            }
        }
        return info;
    }

    // 해당 좌석 주변 정보 구하기
    public static void Count(int x, int y, int num) {
        empty = 0; like = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                int now = map[nx][ny];
                if (now == 0) empty++;
                else if (friends.get(num).contains(now)) like++;
            }
        }
    }

}
