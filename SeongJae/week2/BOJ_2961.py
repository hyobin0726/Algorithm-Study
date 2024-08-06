from collections import deque
def bfs(n,k,visited):
    queue=deque([(n,0)])
    count=0
    result=100001
    isFindResult=False
    while queue:
        loc,time=queue.popleft()
        visited[loc]=True
        if loc==k and (not isFindResult or time<=result):
            count+=1
            result=time
            isFindResult=True
        for des in loc-1,loc+1,2*loc:
            if 0<=des<=100000 and not visited[des] and (not isFindResult or time<result):
                queue.append((des,time+1))
    print(result)
    print(count)
#답을 찾으면 count가 넘어가면안됨 답을 못찾으면 무조건 통과
n,k=map(int,input().split())
visited=[False]*100001
bfs(n,k,visited)
