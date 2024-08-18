for TC in range(1,int(input())+1):
     #그냥 전부 다 구해
     n,k=map(int,input().split())
     s1=input().strip()
     arr=set()
     for _ in range(n//4):
          s1=s1[-1]+s1[:-1]
          arr.add(int(s1[:n//4],16))
          arr.add(int(s1[n//4:n//2],16))
          arr.add(int(s1[n//2:n//4*3],16))
          arr.add(int(s1[n//4*3:],16))
     arr=sorted(arr,reverse=True)
     print(f"#{TC} {arr[k-1]}")
