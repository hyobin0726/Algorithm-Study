import sys
input=sys.stdin.readline
def findSet(a):
    if(parents[a]<0):
        return a
    
    parents[a] = findSet(parents[a])
    return parents[a]

def union(a,b):
    aRoot=findSet(a)
    bRoot=findSet(b)
    if(aRoot==bRoot):
        return False
    #집합 크기 관리
    #편의상 a집합에 b집합을 붙임
    parents[aRoot]+=parents[bRoot]
    parents[bRoot]=aRoot
    return True
def ccw(xa,ya,xb,yb,xc,yc):
    s=xa*yb+xb*yc+xc*ya-(xb*ya+xc*yb+xa*yc)
    if s>0: return 1
    elif s==0: return 0
    else: return -1
n=int(input())
parents=[-1]*(n)
lines=[list(map(int,input().split())) for _ in range(n)]
for i in range(n-1):
    for j in range(i+1 ,n):
        x1,y1,x2,y2=lines[i][0],lines[i][1],lines[i][2],lines[i][3]
        x3,y3,x4,y4=lines[j][0],lines[j][1],lines[j][2],lines[j][3]

        if(ccw(x1,y1,x2,y2,x3,y3)*ccw(x1,y1,x2,y2,x4,y4)==0 and ccw(x3,y3,x4,y4,x1,y1)*ccw(x3,y3,x4,y4,x2,y2)==0):
            minLineX1=min(x1,x2)
            minLineY1=min(y1,y2)
            minLineX2=min(x3,x4)
            minLineY2=min(y3,y4)
            maxLineX1=max(x1,x2)
            maxLineY1=max(y1,y2)
            maxLineX2=max(x3,x4)
            maxLineY2=max(y3,y4)
            if minLineX1<=maxLineX2 and minLineX2<=maxLineX1 and minLineY1<=maxLineY2 and minLineY2<=maxLineY1:
                union(i,j)
            else:
                continue
        elif (ccw(x1,y1,x2,y2,x3,y3)*ccw(x1,y1,x2,y2,x4,y4)<=0 and ccw(x3,y3,x4,y4,x1,y1)*ccw(x3,y3,x4,y4,x2,y2)<=0):
            union(i,j)
        else:
            continue
GroupCount=0
GroupLineCount=0
for i in parents:
    if i<0:
        GroupCount+=1
        if GroupLineCount>i:
            GroupLineCount=i
print(GroupCount)
print(-GroupLineCount)