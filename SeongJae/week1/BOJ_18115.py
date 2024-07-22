import sys
from collections import deque

input=sys.stdin.readline
card=deque()
n=int(input())
arr=list(map(int,input().split()))
cardnum=1
for i in range(n-1,-1,-1):
    if arr[i]==1:
        card.append(cardnum)
    elif arr[i]==2:
        tmp=card.pop()
        card.append(cardnum)
        card.append(tmp)
    else:
        card.appendleft(cardnum)
    cardnum+=1
print(' '.join(map(str, reversed(card))))
