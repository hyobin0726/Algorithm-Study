import sys
from collections import deque
input=sys.stdin.readline
queue=deque()

def bfs(x,y,IsBrokeWall,moveCount):
    #x좌표 , y좌표 ,0: 벽 아직 부수지 않음 1: 벽을 부숨 , 이동거리
    queue.append((x,y,IsBrokeWall,moveCount))
    visited[x][y][IsBrokeWall]=True
    while queue:
        x,y,IsBrokeWall,moveCount=queue.popleft()
        if x==n-1 and y==m-1:
            return moveCount
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if 0<=ax<n and 0<=ay<m:
                if arr[ax][ay]==0:
                    if not visited[ax][ay][IsBrokeWall]:
                        queue.append((ax,ay,IsBrokeWall,moveCount+1))
                        visited[ax][ay][IsBrokeWall]=True
                elif IsBrokeWall==0:
                    queue.append((ax,ay,1,moveCount+1))
                    visited[ax][ay][IsBrokeWall]=True
    return -1


    
n,m=map(int,input().split())
arr=[list(map(int,input().strip())) for _ in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]
visited=[[[False,False] for _ in range(m)] for _ in range(n)]
print(bfs(0,0,0,1))