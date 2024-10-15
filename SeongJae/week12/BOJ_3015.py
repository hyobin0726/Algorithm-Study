import sys
input=sys.stdin.readline
stack=[]
count=0
for inputIndex in range(int(input())):
    inputHeight=int(input())
    if len(stack)==0:
        stack.append((inputHeight,1))
    else:
        while stack:
            h,hCount=stack[-1][0],stack[-1][1]
            #낮은 건물이 입력받음
            if(h>inputHeight):
                stack.append((inputHeight,1))
                count+=1
                break
            elif(h==inputHeight):
                stack.pop()
                stack.append((h,hCount+1))
                if(len(stack)==1):
                    count+=hCount
                else:
                    count+=hCount+1
                break
            #높은 건물이 입력받음
            else:
                stack.pop()
                count+=hCount
                continue
        if (len(stack)==0):
            stack.append((inputHeight,1))
print(count)