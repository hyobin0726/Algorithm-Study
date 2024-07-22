import sys
input=sys.stdin.readline
stack=[]
result=0
for _ in range(int(input())):
    n=int(input())
    while stack:
        tmp=stack.pop()
        if tmp>n:
            stack.append(tmp)
            break
    stack.append(n)
    result+=len(stack)-1
print(result)