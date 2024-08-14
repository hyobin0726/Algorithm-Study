n=int(input())
arr=list(map(int,input().split()))
countOne,countTwo=0,0
if sum(arr)%3!=0:
  print("NO")
else:
  for i in arr:
    countOne+=i%2
    countTwo+=i//2
  if countOne>countTwo:
    print("NO")
  else:
    if (countTwo-countOne)%3==0:
      print("YES")
    else:
      print("NO")