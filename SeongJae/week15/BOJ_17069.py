import sys
input=sys.stdin.readline
n=int(input())
graph=[list(map(int,input().split())) for j in range(n)]
result=[[[0,0,0] for j in range(n)] for i in range(n)] #0은 가로  1은 대각선 2는 세로
#0층을 탐방하자 OutofIndex방지하자
for x in range(1,n):
    if graph[0][x]==0:
        result[0][x][0]=1
    else:
        break
for y in range(1,n):
    for x in range(1,n):
        if graph[y][x]==0:
            result[y][x][0]=result[y][x-1][0]+result[y][x-1][1]#가로
            if graph[y-1][x]==0 and graph[y][x-1]==0:
                result[y][x][1]=result[y-1][x-1][0]+result[y-1][x-1][1]+result[y-1][x-1][2]#대각선
            result[y][x][2]=result[y-1][x][1]+result[y-1][x][2]#세로
print(result[-1][-1][0]+result[-1][-1][1]+result[-1][-1][2])
