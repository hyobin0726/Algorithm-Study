from collections import deque
n=int(input())
arr=deque()
for i in range(n):
    b=input()
    for j in range(n):
        arr.append(b[j])

while n>1:
    for i in range(n//2):# n-> 1/2n-> 1/4n-> 1/8n식으로 접근해야함
        for j in range(n//2):
            x=i*2#세로줄
            y=j*2#가로줄
            if(arr[x*n+y]==arr[x*n+y+1]==arr[(x+1)*n+y]==arr[(x+1)*n+y+1]):#0:왼쪽 위 1:오른쪽 위 2: 왼쪽 아래 3: 오른쪽 아래
                if(arr[x*n+y]=='0'or arr[x*n+y]=='1'):
                    arr.append(arr[x*n+y])
                else:
                    arr.append(str("("+arr[x*n+y]+arr[x*n+y+1]+arr[(x+1)*n+y]+arr[(x+1)*n+y+1]+")"))
            else:
                arr.append(str("("+arr[x*n+y]+arr[x*n+y+1]+arr[(x+1)*n+y]+arr[(x+1)*n+y+1]+")"))
    for i in range(n*n):
        arr.popleft()
    n=n//2
print(arr[0])
