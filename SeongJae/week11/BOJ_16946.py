import sys
from collections import deque
input=sys.stdin.readline
n,m=map(int,input().split())
arr=[list(map(int,input().strip())) for _ in range(n)]
visited=[[False]*m for _ in range(n)]
cntArr=[[0]*m for _ in range(n)]
result=[[0]*m for _ in range(n)]
group=[[0]*m for _ in range(n)]
queue=deque()
dx=[1,-1,0,0,1,1,-1,-1]
dy=[0,0,1,-1,1,-1,1,-1]
def bfs():
    cnt=1
    while queue:
        x,y=queue.popleft()
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if (0<=ax<n and 0<=ay<m and not visited[ax][ay] and arr[ax][ay]==0):
                cnt+=1
                tmp.append([ax,ay])
                queue.append((ax,ay))
                visited[ax][ay]=True
    return cnt
groupcount=1
for x in range(n):
    for y in range(m):
        if arr[x][y]==1:
            visited[x][y]=True
        else:
            if not visited[x][y]:
                visited[x][y]=True
                tmp=[[x,y]]
                queue.append((x,y))
                count=bfs()
                for point in tmp:
                    cntArr[point[0]][point[1]]=count
                    group[point[0]][point[1]]=groupcount
                groupcount+=1
for x in range(n):
    for y in range(m):
        if arr[x][y]==1:
            count=1
            tmp=[]
            for i in range(4):
                ax=x+dx[i]
                ay=y+dy[i]
                if (0<=ax<n and 0<=ay<m):
                    if len(tmp)==0:
                        count+=cntArr[ax][ay]
                        tmp.append(group[ax][ay])
                    else:
                        isdistinct=False
                        for num in tmp:
                            if num==group[ax][ay]:
                                isdistinct=True
                        if not isdistinct:
                            count+=cntArr[ax][ay]
                            tmp.append(group[ax][ay])
            result[x][y]=count%10
for i in range(n):
    print("".join(map(str,result[i])))

