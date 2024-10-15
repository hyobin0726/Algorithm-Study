import sys
input=sys.stdin.readline
n=int(input())
r,m=map(int,input().split())
arr=list(map(int,input().split()))
arr.sort()
arr.append(arr[0]+m)
tmp=[]
for j in range(2):
    for i in range(1,len(arr)):
        tmp.append(arr[i]-arr[i-1]-2*r)
result = 0
max_result = 0
for i in tmp:
    result+=i
    if result<0:
        result=0
    if max_result<result:
        max_result=result
print((max_result+1)//2)