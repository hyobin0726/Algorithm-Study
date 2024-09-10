from collections import deque
for TC in range(int(input())):
    n=int(input())
    queue=deque()
    graph=[[] for _ in range(n+1)]
    visited=[False]*(n+1)
    for i in range(1,n+1):
        s=input()
        if s[0]=='0':
            queue.append((i,1))
            visited[i]=True
        else:
            arr=list(map(int,s[2:].split()))
            for j in arr:
                graph[j].append(i)
    result=-1
    while queue:
        if result>n:
            result=-1
        x,count=queue.popleft()
        for ax in graph[x]:
            queue.append((ax,count+1))
            visited[ax]=True
        if count>result:
            result=count
    for i in range(1,n+1):
        if not visited[i]:
            result=-1
            break
    print(result)