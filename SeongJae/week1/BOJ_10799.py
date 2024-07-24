n=input().rstrip()
count=0
result=0
for i in range(len(n)):
    if n[i]=='(':
        count+=1
    else:      
        count-=1
        if n[i-1]=='(':
            result+=count
        else:
            result+=1
print(result)