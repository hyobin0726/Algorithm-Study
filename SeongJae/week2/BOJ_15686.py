n,m=map(int,input().split())
arr=[list(map(int,input().split()))for _ in range(n)]
houselocate=[]
chickenlocate=[]
distance=[]
for x in range(n):
    for y in range(n):
        if arr[x][y]==1:
            houselocate.append([x,y])
        elif arr[x][y]==2:
            chickenlocate.append([x,y])
for x1,y1 in houselocate:
    tmp=[]
    for x2,y2 in chickenlocate:
        tmp.append(abs(x1-x2)+abs(y1-y2))
    distance.append(tmp)
indexList=[]
chk=[False]*(len(chickenlocate)+1)
result=[]
#백트래킹 (N과 M (2))
def recur(num,start):
    if num==m:
        Value=0
        for i in range(len(houselocate)):
            minValue=1000
            for index in indexList:
                if minValue>distance[i][index]:
                    minValue=distance[i][index]
            Value+=minValue
        result.append(Value)
        return
    for i in range(start,len(chickenlocate)):
        if chk[i]==False:
            chk[i] = True
            indexList.append(i)
            recur(num+1,i)
            indexList.pop()
            chk[i]=False
recur(0,0)
print(min(result))