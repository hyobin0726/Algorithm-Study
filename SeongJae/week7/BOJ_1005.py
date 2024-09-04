from collections import deque
import sys
input=sys.stdin.readline
def dfs():
    while queue:
        v,cost=queue.pop()
        for dv in graph[v]:
            chasoo[dv]-=1
            result[dv]=max(result[dv],cost+costarr[dv])
            if chasoo[dv]==0:
                queue.append((dv,result[dv]))

for _ in range(int(input())):
    queue=deque()
    n,k=map(int,input().split())
    costarr=[0]+list(map(int,input().split()))
    graph=[[] for _ in range(n+1)]
    result=[0]*(n+1)
    chasoo=[0]*(n+1)
    for node in range(k):
        start,end=map(int,input().split())
        graph[start].append(end)
        chasoo[end]+=1
    goal=int(input())
    for i in range(1,n+1):
        if chasoo[i]==0:
            queue.append((i,costarr[i]))
            result[i]=costarr[i]
    dfs()
    print(result[goal])
