import sys
from collections import deque
input=sys.stdin.readline
queue=deque()

def bfs(x,y,brokeWallCount,moveCount):
    #x좌표 , y좌표 ,0: 벽 아직 부수지 않음 1: 벽을 부숨 , 이동거리
    queue.append((x,y,brokeWallCount,moveCount))
    visited[x][y][brokeWallCount]=1
    while queue:
        x,y,brokeWallCount,moveCount=queue.popleft()
        if x==n-1 and y==m-1:
            return moveCount
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if 0<=ax<n and 0<=ay<m:
                #벽이 아닐때
                if arr[ax][ay]==0:
                    #방문 하지 않았을떄
                    if visited[ax][ay][brokeWallCount] > moveCount+1:
                        queue.append((ax,ay,brokeWallCount,moveCount+1))
                        for j in range(brokeWallCount,k+1):
                            visited[ax][ay][j]=moveCount+1
                #벽일떄
                elif brokeWallCount<k and visited[ax][ay][brokeWallCount+1] > moveCount+1:
                    queue.append((ax,ay,brokeWallCount+1,moveCount+1))
                    for j in range(brokeWallCount+1,k+1):
                        visited[ax][ay][brokeWallCount+1]=moveCount+1
    return -1
    
n,m,k=map(int,input().split())
arr=[list(map(int,input().strip())) for _ in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]
visited=[[[1000*1000*2]*(k+1) for _ in range(m)] for _ in range(n)]
print(bfs(0,0,0,1))