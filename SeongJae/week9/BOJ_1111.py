n=int(input())
arr=list(map(int,input().split()))
if(n>2):
    if(arr[1]==arr[0]):
        a=0.0
        b=float(arr[1])
    else:
        a=(arr[2]-arr[1])/(arr[1]-arr[0])
        b=arr[1]-arr[0]*a
    if(a.is_integer() and b.is_integer()):#모두 정수일때
        i=1
        while arr[i+1]==arr[i]*a+b and i<n-2:
            i+=1
        if(i==n-2 and arr[i+1]==arr[i]*a+b):
            result=int(arr[-1]*a+b)
        else:
            result="B"
    else:
        result="B"
elif(n==2):
    if(arr[0]==arr[1]):
        result=arr[0]
    else:
        result="A"
else:
    result="A"
print(result)
