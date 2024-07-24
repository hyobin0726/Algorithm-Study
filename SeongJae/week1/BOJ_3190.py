from collections import deque
n=int(input())
k=int(input())
arr=[[0]*n for _ in range(n)]
#명령 리스트
cmdlist=deque()
for _ in range(k):
    col,row=map(int,input().split())
    #사과는 2
    arr[col-1][row-1]=2
l=int(input())
for _ in range(l):
    time,cmd=input().split()
    time=int(time)
    cmdlist.append((time,cmd))

#x위치,y위치,방향,크기
x,y,dst,size=0,0,0,1
bam=deque()
bam.append((x,y))
dx=[0,1,0,-1]
dy=[1,0,-1,0]
time=0
count=0
while True:
    time+=1
    ax=x+dx[dst]
    ay=y+dy[dst]

    #밖에 나가거나 뱀을 만날경우
    if not (0<=ax<n and 0<=ay<n) or arr[ax][ay]==1 :
        break
    else:
        x,y=ax,ay
        #사과를 먹을경우
        if arr[x][y]==2:
            size+=1
            bam.append((x,y))
            arr[x][y]=1
        #아무것도 아님
        else:
            ax,ay=bam.popleft()
            arr[ax][ay]=0
            bam.append((x,y))
            arr[x][y]=1
    #명령어 시간이면
    if len(cmdlist)>0 and cmdlist[0][0]==time:
        if cmdlist[0][1]=='D':
            dst=(dst+1)%4
        else:
            dst=(dst-1)%4
        cmdlist.popleft()
print(time)