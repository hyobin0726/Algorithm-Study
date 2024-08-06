import sys
from collections import deque
sentence=sys.stdin.readline().rstrip()
bomb=sys.stdin.readline().rstrip()
stack=[]
txt=""
if len(bomb)==1:
    txt=""
    for i in sentence:
        if i!=bomb[0]:
            txt+=i
else:
    for i in sentence:
        #첫글자 이면
        if i==bomb[0]:
            stack.append(txt)
            txt=bomb[0]
        #끝이면
        elif i==bomb[-1]:
            txt+=i
            if txt==bomb:
                if len(stack)>0:
                    txt=stack[-1]
                    stack.pop()
        else:
            txt+=i
if txt!="":
    stack.append(txt)
if stack:
    for i in stack:
        print(i,end="")
else:
    print("FRULA")