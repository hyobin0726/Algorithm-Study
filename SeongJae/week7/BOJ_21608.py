import sys
input=sys.stdin.readline
n=int(input())
studentInfo=[list(map(int,input().split())) for _ in range(n*n)]
classMap=[[0]*n for _ in range(n)]
dx=[1,-1,0,0]
dy=[0,0,1,-1]
result=0
for sNo,a,b,c,d in studentInfo:
    resultx,resulty,resultFavoriveCount,resultEmptyCount=0,0,-1,-1
    for x in range(n):
        for y in range(n):
            if classMap[x][y]==0:
                favoriteCount,emptyCount=0,0
                for i in range(4):
                    ax=x+dx[i]
                    ay=y+dy[i]
                    if(0<=ax<n and 0<=ay<n):
                        if(classMap[ax][ay]==0):
                            emptyCount+=1
                        for bestFriend in [a,b,c,d]:
                            if(classMap[ax][ay]==bestFriend):
                                favoriteCount+=1
                if resultFavoriveCount<favoriteCount:
                    resultFavoriveCount=favoriteCount
                    resultEmptyCount=emptyCount
                    resultx,resulty=x,y
                elif resultFavoriveCount==favoriteCount and resultEmptyCount<emptyCount:
                    resultEmptyCount=emptyCount
                    resultx,resulty=x,y
    classMap[resultx][resulty]=sNo
studentInfo.sort()

for x in range(n):
    for y in range(n):
        favoriteCount=0
        for i in range(4):
            ax=x+dx[i]
            ay=y+dy[i]
            if(0<=ax<n and 0<=ay<n):
                for bestFriend in studentInfo[classMap[x][y]-1][1:]:
                    if(classMap[ax][ay]==bestFriend):
                        favoriteCount+=1
        if favoriteCount>0:
            result+=10**(favoriteCount-1)
print(result)