import sys
from collections import deque
input=sys.stdin.readline
queue=deque()
n,m=map(int,input().split())
dx=[1,0,-1,0]
dy=[0,1,0,-1]
ans=[0]

def bfs(count):
    dst=0
    while queue:
        x,y=queue.popleft()
        for i in range(4):
            #남 동 북 서
            if bin(arr[x][y]+16)[3+i]=='0':
                ax=x+dx[i]
                ay=y+dy[i]
                if (0<=ax<m and 0<=ay<n and not visited[ax][ay]):
                    visited[ax][ay]=True
                    maps[ax][ay]=count
                    queue.append((ax,ay))
                    dst+=1
            else:
                continue
    ans.append(dst+1)

def nearSearchBFS(Team):
    while queue:
        x,y=queue.popleft()
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if (0<=ax<m and 0<=ay<n and not visited[ax][ay]):
                if maps[ax][ay]==Team:
                    visited[ax][ay]=True
                    queue.append((ax,ay))
                else:
                    near[Team].add(maps[ax][ay])
                    near[maps[ax][ay]].add(Team)

maps=[[0]*n for _ in range(m)]
arr= [list(map(int,input().split())) for _ in range(m)]
visited=[[False]*n for _ in range(m)]
count=1
for x in range(m):
    for y in range(n):
        if not visited[x][y]:
            queue.append((x,y))
            visited[x][y]=True
            maps[x][y]=count
            bfs(count)
            count+=1
print(len(ans)-1)
print(max(ans))
near=[set([]) for _ in range(len(ans))]
visited=[[False]*n for _ in range(m)]
for x in range(m):
    for y in range(n):
        if not visited[x][y]:
            queue.append((x,y))
            visited[x][y]=True
            nearSearchBFS(maps[x][y])
max_num=0
for i in range(1,len(ans)):
    for num in near[i]:
        tmp=ans[i]+ans[num]
        if tmp>max_num:
            max_num=tmp
print(max_num)