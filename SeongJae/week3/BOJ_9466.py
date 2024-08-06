import sys
input=sys.stdin.readline
for _ in range(int(input())):
    n=int(input())
    arr=[0]+list(map(int,input().split()))
    visited=[False]*(n+1)
    assa=0
    inssa=0
    for i in range(1,n+1):
        loc=i
        #방문안했어 ㅠㅠ
        if not visited[loc]:
            visitnum=[]
            while True:
                if not visited[loc]:
                    visitnum.append(loc)
                    visited[loc]=True
                    loc=arr[loc]
                else:
                    visitnum.append(loc)
                    key=visitnum[-1]
                    count=0
                    for i in visitnum:
                        count+=1
                        if i==key:
                            assa+=count-1
                            inssa+=len(visitnum)-count
                            break
                    break
    print(assa)
