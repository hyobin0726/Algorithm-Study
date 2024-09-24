import sys
input=sys.stdin.readline

n,k=map(int,input().split())
dp=[[0]*(k+1) for _ in range(n+1)]
items=[[0,0]]+[list(map(int,input().split())) for _ in range(n)]
for x in range(1,n+1):
    for y in range(1,k+1):
        w,v=items[x][0],items[x][1]
        if y>=w:
            dp[x][y]=max(dp[x-1][y],v+dp[x-1][y-w])
        else:
            dp[x][y]=dp[x-1][y]
print(dp[n][k])