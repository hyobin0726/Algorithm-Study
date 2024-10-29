import sys
input = sys.stdin.readline
n = int(input())
arr = [list(map(int,input().split())) for _ in range(n)]
for y in range(1,n):
    arr[y][0]+=arr[y-1][0]
    arr[y][y]+=arr[y-1][y-1]
    for x in range(1,y):
        arr[y][x]+=max(arr[y-1][x-1],arr[y-1][x])
print(max(arr[n-1]))