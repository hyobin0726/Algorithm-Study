import sys
from collections import deque
input=sys.stdin.readline
Triggerqueue=deque()
cheeseQueue=deque()
tmpQueue=deque()
countAir=1
MeltChessPoint=deque()
def Triggerbfs():
    global countAir
    #x좌표 , y좌표 ,0: 벽 아직 부수지 않음 1: 벽을 부숨 , 이동거리
    while Triggerqueue:
        x,y=Triggerqueue.popleft()
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if 0<=ax<n and 0<=ay<m:
                if arr[ax][ay]==0:
                    arr[ax][ay]=-1
                    countAir+=1
                    Triggerqueue.append((ax,ay))
                elif arr[ax][ay]==1:
                    #visited 역할
                    arr[ax][ay]=2
                    cheeseQueue.append((ax,ay))

def RemoveCheeseBFS():
    global countAir
    while cheeseQueue:
        x,y=cheeseQueue.popleft()
        countZero,countMinus=0,0
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if arr[ax][ay]==0:
                countZero+=1
            elif arr[ax][ay]==-1:
                countMinus+=1
        if countMinus>1:
            if countZero>0:
                Triggerqueue.append((x,y))
            MeltChessPoint.append((x,y))
            countAir+=1
        else:
            arr[x][y]=1

    
n,m=map(int,input().split())
arr=[list(map(int,input().split())) for _ in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]

visited=[[False] *m for _ in range(n)]
arr[0][0]=-1
Triggerqueue.append((0,0))
Triggerbfs()
day=0
while countAir<n*m:
    day+=1
    for x in range(n):
        for y in range(m):
            if arr[x][y]==1:
                cheeseQueue.append((x,y))
    RemoveCheeseBFS()
    while MeltChessPoint:
        x,y=MeltChessPoint.popleft()
        arr[x][y]=-1
    Triggerbfs()
print(day)
