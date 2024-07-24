import heapq,sys
input=sys.stdin.readline
maxheap=[]
minheap=[]
n=int(input())
tmp=int(input())
heapq.heappush(maxheap,-1*tmp)
print(tmp)
for i in range(1,n):
    num=int(input())
    if i%2==0:
        tmp=heapq.heappop(minheap)
    else:
        tmp=heapq.heappop(maxheap)*-1
    if tmp > num:
        #입력값은 최대힙에 다른건 최소힙에
        heapq.heappush(maxheap,-num)
        heapq.heappush(minheap,tmp)        
    else:
        heapq.heappush(maxheap,-tmp)
        heapq.heappush(minheap,num)
    print(maxheap[0]*-1)
