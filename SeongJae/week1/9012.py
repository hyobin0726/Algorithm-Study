for _ in range(int(input())):
    count=0
    for i in input().rstrip():
        if i=="(":
            count+=1
        else:
            count-=1
        if count<0:
            break
    if count==0:
        print("YES")
    else:
        print("NO")
