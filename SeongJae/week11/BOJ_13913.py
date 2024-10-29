from collections import deque
def bfs(n,k,visited):
    queue=deque([(n,0,str(n))])
    while queue:
        loc,time,road=queue.popleft()
        visited[loc]=True
        if loc==k:
            print(time)
            print(road)
            break
        for des in loc-1,loc+1,2*loc:
            if 0<=des<=max_area and not visited[des]:
                queue.append((des,time+1,road+" "+str(des)))
n,k=map(int,input().split())
if (k<n):
    print(n-k)
    for i in range(n,k-1,-1):
        print(i,end=" ")
else:    
    max_area=100000
    visited=[False]*(max_area+1)
    bfs(n,k,visited)
