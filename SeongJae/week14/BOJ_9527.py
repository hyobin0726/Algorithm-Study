def cal(n):
  result=0
  for i in range(n.bit_length()):
    result+=((n>>(i+1))*(2**i))
    if n&(1<<i)!=0:
      result+=(n&(2**(i)-1))+1
  return result
start,end=map(int,input().split())
print(cal(end)-cal(start-1))