def findrow(x):
    result=1
    count=1
    char=arr[x][0]
    for y in range(1,n):
        #같으면
        if char==arr[x][y]:
            count+=1
            if result<count:
                result=count
        else:
            count=1
            char=arr[x][y]
    return result

def findcol(y):
    result=1
    count=1
    char=arr[0][y]
    for x in range(1,n):
        #같으면
        if char==arr[x][y]:
            count+=1
            if result<count:
                result=count
        else:
            count=1
            char=arr[x][y]
    return result

n=int(input())
arr=[list(input().strip()) for _ in range(n)]
result=0
for x in range(n):
    result=max(result,findrow(x))
for y in range(n):
    result=max(result,findcol(y))
for x in range(n-1):
    for y in range(n):
        arr[x][y],arr[x+1][y]=arr[x+1][y],arr[x][y]
        result=max(result,findrow(x),findrow(x+1),findcol(y))
        arr[x][y],arr[x+1][y]=arr[x+1][y],arr[x][y]
for x in range(n):
    for y in range(n-1):
        arr[x][y],arr[x][y+1]=arr[x][y+1],arr[x][y]
        result=max(result,findcol(y),findcol(y+1),findrow(x))
        arr[x][y],arr[x][y+1]=arr[x][y+1],arr[x][y]
print(result)