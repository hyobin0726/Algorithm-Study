import sys
sys.setrecursionlimit(10 ** 9)
def findSet(a):
    if(parents[a]<0):
        return a
    parents[a]=findSet(parents[a])
    return parents[a]

def union(a,b):
    aRoot=findSet(a)
    bRoot=findSet(b)
    if (aRoot==bRoot):
        return False
    parents[aRoot]+=parents[bRoot]
    parents[bRoot]=aRoot
    return True

input=sys.stdin.readline
n,m=map(int,input().split())
parents=[-1]*(n+1)
arr=[list(map(int,input().split())) for _ in range(m)]
arr.sort(key= lambda x:x[2])
result=0
count=1
for i in range(m):
    if union(arr[i][0],arr[i][1]):
        result+=arr[i][2]
        count+=1
    if count==n:
        break
print(result)
