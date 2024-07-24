import sys
input=sys.stdin.readline
n=int(input())
stack=[]
result=[]
for i,floor in enumerate(list(map(int,input().split()))):
    while stack:
        tmpi,tmpfloor=stack.pop()
        if tmpfloor>floor:
            stack.append((tmpi,tmpfloor))
            break
    stack.append((i,floor))
    if len(stack)==1:
        result.append(0)
    else:
        result.append(stack[-2][0]+1)
print(" ".join(map(str,result)))