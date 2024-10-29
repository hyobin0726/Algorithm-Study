import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**9)
b=int(input())
b+=1
c=15746
if b==0:
    print(0)
else:
    a=[[1,1],[1,0]]
    def mulMatrix(m1,m2):
        res=[[0,0],[0,0]]
        for i in range(2):
            for j in range(2):
                for z in range(2):
                    res[i][j]+=m1[i][z]*m2[z][j]%c
        return res

    def fpow(a,b):
        if b==1:
            return a
        else:
            tmp = fpow(a,b//2)
            if b%2==0:
                return mulMatrix(tmp,tmp)
            else:
                return mulMatrix(mulMatrix(tmp,tmp),a)
    result=fpow(a,b)
    print(result[0][1]%c)