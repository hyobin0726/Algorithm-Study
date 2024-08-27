n,m=map(int,input().split())
arr=list(map(int,input().split()))
cha=[]
for i in range(n-1):
    cha.append(arr[i+1]-arr[i])
cha.sort()
print(sum(cha[:n-m]))