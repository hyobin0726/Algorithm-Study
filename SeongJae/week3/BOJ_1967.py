#틀린 이유 이진트리가 아님
import sys
sys.setrecursionlimit(10**6)

def dfs(loc,dst,start):
    global node,value
    if len(graph[loc])==1 and not loc==start:
        if value<dst:
            node,value=loc,dst
    else:
        for i in graph[loc]:
            if not visited[i[0]]:
                a=i[0]
                v=i[1]
                visited[a]=True
                dfs(a,dst+v,start)

input=sys.stdin.readline
n=int(input())
if n==2:
    p,c,v=map(int,input().split())
    print(v)
else:
#1차 최대거리 찾기
    graph=[[] for i in range(n+1)]
    visited=[False]*(n+1)
    for _ in range(n-1):
        p,c,v=map(int,input().split())
        graph[p].append([c,v])
        graph[c].append([p,v])
    visited[1]=True
    node,value=0,0
    dfs(1,0,1)
    visited=[False]*(n+1)
    visited[node]=True
    graph[node].append([node,0])
    value=0
    dfs(node,0,node)
    print(value)