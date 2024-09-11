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

n=int(input())
m=int(input())
parents=[-1]*(n+1)
arr=[list(map(int,input().split())) for _ in range(n)]
for i in range(1,n+1):
     for j in range(i,n+1):
         if arr[i-1][j-1]==1:
             union(i,j)
arr=list(map(int,input().split()))
isYes=True
for i in range(m-1):
     if(findSet(arr[i])==findSet(arr[i+1])):
          continue
     else:
          isYes=False
if isYes:
    print("YES")
else:
    print("NO")