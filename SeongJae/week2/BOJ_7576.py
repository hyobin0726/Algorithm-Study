from collections import  deque
def bfs(queue):
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if 0<=ax<m and 0<=ay<n:
                if arr[ax][ay]==0 or arr[ax][ay]>arr[x][y]+1:
                    arr[ax][ay]=arr[x][y]+1
                    queue.append((ax,ay))

queue=deque([])
dx=[1,-1,0,0]
dy=[0,0,1,-1]
n,m=map(int,input().split())
arr=[list(map(int,input().split())) for _ in range(m)]
for row in range(m):
    for col in range(n):
        if arr[row][col]==1:
            queue.append((row,col))
bfs(queue)
isMinus=False
result=1
for row in range(m):
    for col in range(n):
        if arr[row][col]==0:
            isMinus=True
        elif result<arr[row][col]:
            result=arr[row][col]
if isMinus:
    print(-1)
else:
    print(result-1)