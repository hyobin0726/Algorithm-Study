for TC in range(int(input())):
     n=int(input())
     a,b,c,d=map(int,input().split())
     arr=list(map(int,input().split()))
     rs=[]
     max_result=-1000000001
     min_result=1000000001
     chk=[False]*(n-1)
     def recur(deep,a,b,c,d):
          global max_result,min_result
          if deep==n-1:
               num=arr[0]
               for i in range(n-1):
                    if rs[i]=='+':
                         num+=arr[i+1]
                    elif rs[i]=='-':
                         num-=arr[i+1]
                    elif rs[i]=='*':
                         num*=arr[i+1]
                    else:
                         if num<0:
                              num=-num
                              num=num//arr[i+1]
                              num=-num
                         else:
                              num=num//arr[i+1]
               if max_result<num:
                    max_result=num
               if min_result>num:
                    min_result=num
               return 
          if a>0:
               rs.append('+')
               a-=1
               recur(deep+1,a,b,c,d)
               a+=1
               rs.pop()
          if b>0:
               rs.append('-')
               b-=1
               recur(deep+1,a,b,c,d)
               b+=1
               rs.pop()
          if c>0:
               rs.append('*')
               c-=1
               recur(deep+1,a,b,c,d)
               c+=1
               rs.pop()
          if d>0 :
               rs.append('/')
               d-=1
               recur(deep+1,a,b,c,d)
               d+=1
               rs.pop()
     recur(0,a,b,c,d)
     print(f"#{TC+1} {max_result-min_result}")