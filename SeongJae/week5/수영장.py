for TC in range(1,int(input())+1):
     cost=list(map(int,input().split()))
     arr=list(map(int,input().split()))
     result=[0]*12
     for i in range(12):
          #일일 이용권,1달 이용권
          result[i]=min(result[i-1]+arr[i]*cost[0],result[i-1]+cost[1],result[i-3]+cost[2])
     print(f"#{TC} {min(cost[3],result[-1])}")