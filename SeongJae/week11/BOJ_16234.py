from collections import deque
import sys
input=sys.stdin.readline
n,l,r=map(int,input().split())
arr=[list(map(int,input().split())) for _ in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]
result=0    #최종 답
queue=deque()
while True:
    isDifference=False #값이 변하는지 안변하는지 확인하는 변수
    visited=[[False for _ in range(n)] for _ in range(n)] 
    for x in range(n):
        for y in range(n):
            if not visited[x][y]:   #방문하지 않았다면
                visited[x][y]=True
                queue.append((x,y)) #bfs돌릴꺼다.
                team=[(x,y)]    #연합을 담는 리스트
                count=1 #현재 연합의 수
                teamSum=arr[x][y] #연합의 인구
                while queue:
                    qx,qy=queue.popleft()
                    for i in range(4):
                        ax=qx+dx[i]
                        ay=qy+dy[i]
                        if(0<=ax<n and 0<=ay<n and not visited[ax][ay] and l<=abs(arr[ax][ay]-arr[qx][qy])<=r):   #연합을 추가하는 조건
                            visited[ax][ay]=True
                            queue.append((ax,ay))
                            team.append((ax,ay)) 
                            count+=1  
                            teamSum+=arr[ax][ay]
                            if count>1: #연합이 2개이상면 인구이동이 반드시 일어난다.
                                isDifference=True
                for tx,ty in team:
                    arr[tx][ty]=teamSum//count
    if not isDifference:
        print(result)
        break
    else:
        result+=1