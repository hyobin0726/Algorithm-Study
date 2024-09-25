from bisect import bisect_right
n=int(input())
arr=[0]+list(map(int,input().split()))

countarr=[0]
indexarr=[0]
for i in range(1,n+1):
     count=0
     num=bisect_right(countarr,arr[i])
     if num==len(countarr): 
          if not countarr[-1]==arr[i]:
               countarr.append(arr[i])
     elif countarr[num]>arr[i] and countarr[num-1]<arr[i]:
         countarr[num]=arr[i]
print(len(countarr)-1)