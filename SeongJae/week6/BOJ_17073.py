n,w=map(int,input().split())
arr=[[] for _ in range(n+1)]
for i in range(0,n-1):
    a,b=map(int,input().split())
    arr[a].append(b)
    arr[b].append(a)
cnt=0
for i in range(2,n+1):
    if(len(arr[i])==1):
        cnt+=1
print(w/cnt)