import java.util.*;
import java.io.*;

public class Main {
    static final int MAX_N = 51;
    static final int MAX_P = 31;
    static int n, m, p, c, d;
    static int[] points = new int[MAX_P];
    static Map<Integer, Pair> pos = new HashMap<>();
    static Pair rudolf = new Pair(0, 0);

    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[] is_live = new boolean[MAX_P];
    static int[] stun = new int[MAX_P];

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Tuple implements Comparable<Tuple> {
        int first, second, third;

        Tuple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public int compareTo(Tuple other) {
            if (this.first != other.first) {
                return Integer.compare(this.first, other.first);
            }
            if (this.second != other.second) {
                return Integer.compare(this.second, other.second);
            }
            return Integer.compare(this.third, other.third);
        }
    }

    static boolean is_inrange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rudolf.first = Integer.parseInt(st.nextToken());
        rudolf.second = Integer.parseInt(st.nextToken());
        board[rudolf.first][rudolf.second] = -1;

        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos.put(id, new Pair(x, y));
            board[x][y] = id;
            is_live[id] = true;
        }

        for(int t = 1; t <= m; t++) {
            int closestX = 10000;
            int closestY = 10000;
            int closestIdx = 0;

            // 가장 가까운 산타
            for(int i = 1; i <= p; i++) {
                if(!is_live[i]) continue;

                Tuple currentBest = new Tuple((closestX - rudolf.first) * (closestX - rudolf.first) + (closestY - rudolf.second) * (closestY - rudolf.second), -closestX, -closestY);
                Tuple currentValue = new Tuple((pos.get(i).first - rudolf.first) * (pos.get(i).first - rudolf.first) + (pos.get(i).second - rudolf.second) * (pos.get(i).second - rudolf.second), -pos.get(i).first, -pos.get(i).second);

                if(currentValue.compareTo(currentBest) < 0) {
                    closestX = pos.get(i).first;
                    closestY = pos.get(i).second;
                    closestIdx = i;
                }
            }

            // 루돌프 이동
            if(closestIdx != 0) {
                Pair prevRudolf = new Pair(rudolf.first, rudolf.second);
                int moveX = 0;
                if(closestX > rudolf.first) moveX = 1;
                else if(closestX < rudolf.first) moveX = -1;

                int moveY = 0;
                if(closestY > rudolf.second) moveY = 1;
                else if(closestY < rudolf.second) moveY = -1;

                rudolf.first += moveX;
                rudolf.second += moveY;
                board[prevRudolf.first][prevRudolf.second] = 0;

                // 루돌프의 이동으로 충돌한 경우
                if(rudolf.first == closestX && rudolf.second == closestY) {
                    int firstX = closestX + moveX * c;
                    int firstY = closestY + moveY * c;
                    int lastX = firstX;
                    int lastY = firstY;

                    stun[closestIdx] = t + 1;

                    // 연쇄적으로 이동
                    while(is_inrange(lastX, lastY) && board[lastX][lastY] > 0) {
                        lastX += moveX;
                        lastY += moveY;
                    }

                    while(!(lastX == firstX && lastY == firstY)) {
                        int beforeX = lastX - moveX;
                        int beforeY = lastY - moveY;

                        if(!is_inrange(beforeX, beforeY)) break;

                        int idx = board[beforeX][beforeY];
                        if(!is_inrange(lastX, lastY)) {
                            is_live[idx] = false;
                        } else {
                            board[lastX][lastY] = board[beforeX][beforeY];
                            pos.put(idx, new Pair(lastX, lastY));
                        }

                        lastX = beforeX;
                        lastY = beforeY;
                    }

                    points[closestIdx] += c;
                    pos.put(closestIdx, new Pair(firstX, firstY));
                    if(is_inrange(firstX, firstY)) {
                        board[firstX][firstY] = closestIdx;
                    } else {
                        is_live[closestIdx] = false;
                    }
                }
            }

            board[rudolf.first][rudolf.second] = -1;

            // 각 산타들은 한칸 이동
            for (int i = 1; i <= p; i++) {
                if (!is_live[i] || stun[i] >= t) continue;

                int minDist = (pos.get(i).first - rudolf.first) * (pos.get(i).first - rudolf.first) + (pos.get(i).second - rudolf.second) * (pos.get(i).second - rudolf.second);
                int moveDir = -1;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = pos.get(i).first + dx[dir];
                    int ny = pos.get(i).second + dy[dir];

                    if (!is_inrange(nx, ny) || board[nx][ny] > 0) continue;

                    int dist = (nx - rudolf.first) * (nx - rudolf.first) + (ny - rudolf.second) * (ny - rudolf.second);
                    if (dist < minDist) {
                        minDist = dist;
                        moveDir = dir;
                    }
                }

                if (moveDir != -1) {
                    int nx = pos.get(i).first + dx[moveDir];
                    int ny = pos.get(i).second + dy[moveDir];

                    if (nx == rudolf.first && ny == rudolf.second) {
                        stun[i] = t + 1;

                        int moveX = -dx[moveDir];
                        int moveY = -dy[moveDir];

                        int firstX = nx + moveX * d;
                        int firstY = ny + moveY * d;
                        int lastX = firstX;
                        int lastY = firstY;

                        if (d == 1) {
                            points[i] += d;
                        } else {
                            while (is_inrange(lastX, lastY) && board[lastX][lastY] > 0) {
                                lastX += moveX;
                                lastY += moveY;
                            }

                            while (!(lastX == firstX && lastY == firstY)) {
                                int beforeX = lastX - moveX;
                                int beforeY = lastY - moveY;

                                if (!is_inrange(beforeX, beforeY)) break;

                                int idx = board[beforeX][beforeY];

                                if (!is_inrange(lastX, lastY)) {
                                    is_live[idx] = false;
                                } else {
                                    board[lastX][lastY] = board[beforeX][beforeY];
                                    pos.put(idx, new Pair(lastX, lastY));
                                }

                                lastX = beforeX;
                                lastY = beforeY;
                            }

                            points[i] += d;
                            board[pos.get(i).first][pos.get(i).second] = 0;
                            pos.put(i, new Pair(firstX, firstY));
                            if (is_inrange(firstX, firstY)) {
                                board[firstX][firstY] = i;
                            } else {
                                is_live[i] = false;
                            }
                        }
                    } else {
                        board[pos.get(i).first][pos.get(i).second] = 0;
                        pos.put(i, new Pair(nx, ny));
                        board[nx][ny] = i;
                    }
                }
            }
            
            for (int i = 1; i <= p; i++) {
                if (is_live[i]) points[i]++;
            }
        }

        for (int i = 1; i <= p; i++)
            System.out.print(points[i] + " ");
    }
}
