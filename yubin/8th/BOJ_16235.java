import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    /**
     * 나무 재태크
     * 1. 인풋 받기(N,M,K,로봇정보, Tree 정보)
     *      1.1 양분 배열 int[][], 5로 채우기
     *      1.2 로봇 정보 받기
     *      1.3 나무 정보 arrayList[][]로 저장하기
     * 2. K년 동안, 사계절
     *      2.1 봄: 나무 정보 배열을 돌면서 양분을 자신의 나이랑 체크한뒤 먹을 수 있으면 먹고 나이 +1 시키기
     *          2.1,2 먹지 못한다면 나무 즉사시키기(나무 정보 업데이트)
     *      2.2 여룸: 반복문 돌면서 죽은 나무 일경우 (죽은 나무 나이)/2 해당 칸 양분 추가
     *      2.3 가을: 반복문 돌면서 헤당 나무의 나이가 %5 == 0인 경우 인접 8방에 나이가 1인 나무 +1, 경계 체크하기
     *      2.4 겨울: 배열 돌면서 로봇 배열 칸 양분 배열 플러스 시키기
     * 2.5 살아있는 나무 체크하기
     */
    static BufferedReader br;
    static StringTokenizer st;

    static int N,M,K;
    static int[][] arr;  //양분 배열
    static int[][] robot;  //로봇 양분 배열
    static ArrayList<Tree>[][] trees;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static class Tree implements Comparable<Tree>{
        int age;
        boolean alive;  //살아있으면 true, 죽었으면 false
        Tree(int age, boolean alive){
            this.age = age;
            this.alive = alive;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //1. 인풋 받기(N,M,K, Tree 정보)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //배열의 크기
        M = Integer.parseInt(st.nextToken());  //나무 갯수
        K = Integer.parseInt(st.nextToken());  //최종 연도

        //1.1 양분 배열 int[][], 5로 채우기
        arr = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                arr[i][j] = 5;
            }
        }

        //1.2 로봇 정보 받기
        robot = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1.3 나무 정보 arrayList[][]로 저장하기
        trees = new ArrayList[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            trees[i] = new ArrayList[N+1];
            for (int j = 1; j < N+1; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(age, true));
        }

        //2. K년 동안, 사계절
        for (int i = 0; i < K; i++) {
            season();
        }
        System.out.println(checkAliveTree());
    }
    static void season(){
        //2.1 봄: 나무 정보 배열을 돌면서 양분을 자신의 나이랑 체크한뒤 먹을 수 있으면 먹고 나이 +1 시키기
        //제일 어린 나무부터 양분 먹여야 해서 collections
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                //정렬 시킨뒤
                Collections.sort(trees[i][j]);
                //해당 리스트 돌면서 양분을 자신의 나이랑 체크한뒤 먹을 수 있으면 먹고 나이 +1 시키기, 못 먹으면 break
                for (int k = 0; k < trees[i][j].size(); k++) {
                    Tree tree = trees[i][j].get(k);
                    if(tree.age <= arr[i][j]){
                        //나이를 먹을 수 있는 경우
                        arr[i][j] -= tree.age;
                        tree.age++;
                    }else{
                        //나이를 먹을 수 없는 경우
                        ///2.1,2 먹지 못한다면 나무 즉사시키기(나무 정보 업데이트)
                        tree.alive = false;
                    }
                }
            }
        }

        //2.2 여룸: 반복문 돌면서 죽은 나무 일경우 (죽은 나무 나이)/2 해당 칸 양분 추가
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 0; k < trees[i][j].size(); k++) {
                    Tree tree = trees[i][j].get(k);
                    if(!tree.alive){
                        arr[i][j] += (tree.age) / 2;
                        trees[i][j].remove(tree);
                        k--;
                    }
                }
            }
        }
        //2.3 가을: 반복문 돌면서 헤당 나무의 나이가 %5 == 0인 경우 인접 8방에 나이가 1인 나무 +1, 경계 체크하기
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 0; k < trees[i][j].size(); k++) {
                    Tree tree = trees[i][j].get(k);
                    if(tree.age % 5 == 0){
                        for (int l = 0; l < 8; l++) {
                            int nextX = i + dx[l];
                            int nextY = j + dy[l];

                            //경계 체크
                            if(nextX < 1 || nextY < 1 || nextX >= N+1 || nextY >= N+1) continue;

                            trees[nextX][nextY].add(new Tree(1, true));
                        }
                    }
                }
            }
        }
        // 2.4 겨울: 배열 돌면서 로봇 배열 칸 양분 배열 플러스 시키기
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                arr[i][j] += robot[i][j];
            }
        }
    }
    static int checkAliveTree(){
        int sum = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                    sum += trees[i][j].size();
            }
        }
        return sum;
    }
}
