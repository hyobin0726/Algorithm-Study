import sys
from collections import deque
def bfs(arr,visited):
   queue=deque()
   queue.append((0,0,1))
   visited[0][0]=True
   while queue:
      x,y,count=queue.popleft()
      for i in range(4):
         ax=x+dx[i]
         ay=y+dy[i]
         if 0<=ax<m and 0<=ay<n and not visited[ay][ax] and arr[ay][ax]==1:
            queue.append((ax,ay,count+1))
            visited[ay][ax]=True
            if ay==n-1 and ax==m-1:
               return count+1

input=sys.stdin.readline
n,m=map(int,input().split())
arr=[list(map(int,input().rstrip())) for i in range(n)]
visited=[[False for i in range(m)] for j in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]
print(bfs(arr,visited))