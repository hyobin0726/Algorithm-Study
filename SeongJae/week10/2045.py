arr=[list(map(int,input().split())) for _ in range(3)]
if arr[0][0]==arr[1][1]==arr[2][2]==0 or arr[2][0]==arr[1][1]==arr[0][2]==0:
    pivot=(sum(arr[0])+sum(arr[1])+sum(arr[2]))//2
else:
    pivot=0
    #가로 숫자 찾기
    for x in range(3):
        if arr[x][0]!=0 and arr[x][1]!=0 and arr[x][2]!=0:
            pivot=arr[x][0]+arr[x][1]+arr[x][2]
    #세로 숫자 찾기
    for y in range(3):
        if arr[0][y]!=0 and arr[1][y]!=0 and arr[2][y]!=0:
            pivot=arr[0][y]+arr[1][y]+arr[2][y]
    #대각선 숫자 찾기
    if arr[0][0]!=0 and arr[1][1]!=0 and arr[2][2]!=0:
        pivot=arr[0][0]+arr[1][1]+arr[2][2]
    #대각선 숫자 찾기2
    if arr[2][0]!=0 and arr[1][1]!=0 and arr[0][2]!=0:
        pivot=arr[2][0]+arr[1][1]+arr[0][2]
#가로 노가다 하자
for x in range(3):
    zeroCount=0
    sumArr=0
    for y in range(3):
        sumArr+=arr[x][y]
        if arr[x][y]==0:
            zeroCount+=1
    if zeroCount==1:
        if arr[x][0]==0:
            arr[x][0]=pivot-sumArr
        elif arr[x][1]==0:
            arr[x][1]=pivot-sumArr
        elif arr[x][2]==0:
            arr[x][2]=pivot-sumArr
#세로 노가다 하자
for y in range(3):
    zeroCount=0
    sumArr=0
    for x in range(3):
        sumArr+=arr[x][y]
        if arr[x][y]==0:
            zeroCount+=1
    if zeroCount==1:
        if arr[0][y]==0:
            arr[0][y]=pivot-sumArr
        elif arr[1][y]==0:
            arr[1][y]=pivot-sumArr
        elif arr[2][y]==0:
            arr[2][y]=pivot-sumArr
for a,b,c in arr:
    print(a,b,c)