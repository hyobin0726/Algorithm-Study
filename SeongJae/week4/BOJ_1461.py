n,m=map(int,input().split())
arr=list(map(int,input().split()))
arr.sort()
result=0
#가장큰것수가 음수이면
if arr[-1]<=0:
    for i in range(0,n,m):
        result-=arr[i]*2
    result+=arr[0]
else:
    #양수로 변환되는 시점을 찾자
    positiveStart=0
    for j in range(n):
        if arr[j]>0:
            positiveStart=j
            break
    #가장 작은수가 양수
    if positiveStart==0:
        for i in range(n-1,-1,-m):
            result+=arr[i]*2
        result-=arr[-1]
    else:
        for i in range(0,positiveStart,m):
            result-=arr[i]*2
        for i in range(n-1,positiveStart-1,-m):
            result+=arr[i]*2
        #절대값이 가장 작은값 -99 > 1 가장 큰값
        if -arr[0]>arr[-1]:
            result+=arr[0]
        else:
            result-=arr[-1]
print(result)