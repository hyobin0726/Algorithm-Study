def recur(deep,start):
    if deep==n//2:
        hap=0
        for i in clist:
            for j in clist:
                hap+=arr[i][j]
        result.append(hap)
        return
    for i in range(start,n):
        clist.append(i)
        recur(deep+1,i+1)
        clist.pop()

for TC in range(int(input())):
    n=int(input())
    arr=[list(map(int,input().split())) for _ in range(n)]
    clist=[]
    result=[]
    recur(0,0)
    jungDap=100000000000000
    for i in range(len(result)//2):
        if jungDap>abs(result[i]-result[-i-1]):
            jungDap=abs(result[i]-result[-i-1])
    print(f"#{TC+1} {jungDap}")