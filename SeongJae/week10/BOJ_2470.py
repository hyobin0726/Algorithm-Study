import sys
input=sys.stdin.readline
n=int(input())
arr=list(map(int,input().split()))
arr.sort()
start,end=0,n-1
minnum,maxnum=arr[0],arr[n-1]
result=20000000000
while start<end:
  doyong=arr[start]+arr[end]
  if abs(doyong)<result:
    minnum,maxnum=arr[start],arr[end]
    result=abs(doyong)
  if arr[start]<-arr[end]:
    start+=1
  else:
    end-=1
print(minnum,maxnum)