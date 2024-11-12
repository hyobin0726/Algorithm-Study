from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(start, fires, arr, result, is_visited, W, H):
    move_queue = deque([start])
    fire_queue = deque(fires)
    
    while move_queue:
        new_fires = deque()
        # Spread the fire
        while fire_queue:
            cx, cy = fire_queue.popleft()
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                if 1 <= nx <= H and 1 <= ny <= W and not is_visited[nx][ny] and arr[nx][ny] != '#':
                    is_visited[nx][ny] = True
                    new_fires.append((nx, ny))
        
        new_moves = deque()
        # Move the person
        while move_queue:
            cx, cy = move_queue.popleft()
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                if 0 <= nx <= H + 1 and 0 <= ny <= W + 1 and not is_visited[nx][ny] and arr[nx][ny] != '#' and arr[nx][ny] != '*':
                    is_visited[nx][ny] = True
                    result[nx][ny] = result[cx][cy] + 1
                    new_moves.append((nx, ny))
        
        if not new_moves:
            break
        
        fire_queue.extend(new_fires)
        move_queue.extend(new_moves)

def main():
    T = int(input())
    for _ in range(T):
        W, H = map(int, input().split())
        arr = [['.'] * (W + 2) for _ in range(H + 2)]
        result = [[float('inf')] * (W + 2) for _ in range(H + 2)]
        is_visited = [[False] * (W + 2) for _ in range(H + 2)]
        fire = []
        start = None

        for i in range(1, H + 1):
            line = input().strip()
            for j in range(1, W + 1):
                arr[i][j] = line[j - 1]
                if arr[i][j] == '*':  # Fire
                    fire.append((i, j))
                elif arr[i][j] == '@':  # Start
                    start = (i, j)
                elif arr[i][j] == '#':  # Wall
                    is_visited[i][j] = True

        # Initialize result for the start position
        result[start[0]][start[1]] = 0
        answer = float('inf')

        if start[0] == 0 or start[1] == 0 or start[0] == H + 1 or start[1] == W + 1:
            print("IMPOSSIBLE")
            continue
        
        bfs(start, fire, arr, result, is_visited, W, H)

        flag = False
        for i in range(H + 2):
            for j in range(W + 2):
                if (i == 0 or i == H + 1 or j == 0 or j == W + 1) and is_visited[i][j]:
                    flag = True
                    answer = min(answer, result[i][j])

        if flag:
            print(answer if answer != float('inf') else "IMPOSSIBLE")
        else:
            print("IMPOSSIBLE")

if __name__ == "__main__":
    main()
