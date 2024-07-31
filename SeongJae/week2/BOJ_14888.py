n=int(input())
arr=list(map(int,input().split()))
a,b,c,d=map(int,input().split())
b+=a
c+=b
d+=c
rs=[]
max_result=-1000000001
min_result=1000000001
chk=[False]*(n-1)
def recur(deep):
    global max_result,min_result
    if deep==n-1:
        num=arr[0]
        for i in range(n-1):
            if rs[i]<a:
                num+=arr[i+1]
            elif rs[i]<b:
                num-=arr[i+1]
            elif rs[i]<c:
                num*=arr[i+1]
            else:
                if num<0:
                    num=-num
                    num=num//arr[i+1]
                    num=-num
                else:
                    num=num//arr[i+1]
        if max_result<num:
            max_result=num
        if min_result>num:
            min_result=num
        return 
    for i in range(n-1):
        if not chk[i]:
            chk[i]=True
            rs.append(i)
            recur(deep+1)
            rs.pop()
            chk[i]=False
recur(0)
print(max_result)
print(min_result)