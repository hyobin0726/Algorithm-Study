import sys
input=sys.stdin.readline
maxR,maxC=map(int,input().split())
arr=[list(input().strip()) for _ in range(maxR)]
cmd=['U','R','D','L']
dx=[-1,0,1,0]
dy=[0,1,0,-1]
parents=[[[-1,-1] for _ in range(maxC)] for _ in range(maxR)]
def dfs(x,y):
    visited[x][y]=True
    for i in range(4):
        if arr[x][y]==cmd[i]:
            ax=x+dx[i]
            ay=y+dy[i]
            if union(x,y,ax,ay):
                dfs(ax,ay)
def findSet(x,y):
    if(parents[x][y][0]<0):
        return [x,y]
    parents[x][y]=findSet(parents[x][y][0],parents[x][y][1])
    return parents[x][y]
def union(ax,ay,bx,by):
    aRoot=findSet(ax,ay)
    bRoot=findSet(bx,by)
    if(aRoot[0]==bRoot[0] and aRoot[1]==bRoot[1]):
        return False
    parents[aRoot[0]][aRoot[1]][0]+=parents[bRoot[0]][bRoot[1]][0]
    parents[aRoot[0]][aRoot[1]][1]+=parents[bRoot[0]][bRoot[1]][1]
    parents[bRoot[0]][bRoot[1]][0],parents[bRoot[0]][bRoot[1]][1]=aRoot[0],aRoot[1]
    return True
visited=[[False]*maxC for _ in range(maxR)]
for x in range(maxR):
    for y in range(maxC):
        if not visited[x][y]:
            dfs(x,y)
result=0
for x in range(maxR):
    for y in range(maxC):
        if parents[x][y][0]<0:
            result+=1
print(result)
