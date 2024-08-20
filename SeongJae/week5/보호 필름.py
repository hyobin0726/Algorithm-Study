def test(arr):
     global isExit
     isPass=True
     for y in range(m):
          maxlength=1
          beforenum=arr[0][y]
          countlength=1
          for x in range(1,d):
               if arr[x][y]==beforenum:
                    countlength+=1
                    if maxlength<countlength:
                         maxlength=countlength
               else:
                    beforenum=arr[x][y]
                    countlength=1
          if maxlength<k:
               isPass=False
               return
     if isPass:
          print(f"#{TC} {len(yaklocs)}")
          isExit=True
          #exit 떄문에 전부 종료됨
          return

def yak(yakloc,arr,cmd):
     global isExit
     #10 11 00 01
     for i in range(len(cmd)):
          for y in range(m):
               arr[yakloc[i]][y]=int(cmd[i])
     test(arr)
     return

def rowC(r,depth,start):
     global isExit
     if depth==r:
          arr=[tmp[:] for tmp in originalArr]
          for tmpi in range(2**r):
               cmd=bin(tmpi+2**r)[3:]
               #순열을 짰으니 바꾸는걸 찾자
               yak(yaklocs,arr,cmd)
               if isExit:
                    break
          return
     for num in range(start,d):
          if not visitedC[num]:
               visitedC[num]=True
               yaklocs.append(num)
               rowC(r,depth+1,num)
               visitedC[num]=False
               yaklocs.pop()
               if isExit:
                    break
               
for TC in range(1,int(input())+1):
     d,m,k=map(int,input().split())
     originalArr=[list(map(int,input().split())) for _ in range(d)]
     isExit=False
     #만약 원본이 통과가 안된다면
     for r in range(d+1):
          visitedC=[False]*d
          yaklocs=[]
          rowC(r,0,0)
          if isExit:
               break
