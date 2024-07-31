import sys
input=sys.stdin.readline
arr=[]
n=int(input())
dp=[0]*n+[0]
for _ in range(n):
    t,p=map(int,input().split())
    arr.append((t,p))
for day in range(n):
    if day+arr[day][0]<n+1:
        dp[day+arr[day][0]]=max(dp[day+arr[day][0]],dp[day]+arr[day][1])
    dp[day+1]=max(dp[day+1],dp[day])
print(dp[-1])