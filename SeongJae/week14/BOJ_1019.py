resultBruto=[0]*10
n=int(input())
result=[0]*10
for i in range(1,10):
    stringN=str(n)
    for tmp in range(len(stringN)):
        if int(stringN[-tmp-1])>i:
            result[i]+=10**tmp*(n//(10**(tmp+1))+1)
        elif int(stringN[-tmp-1])<i:
            result[i]+=10**tmp*(n//(10**(tmp+1)))
        else:
            result[i]+=(10**tmp*(n//(10**(tmp+1)))+n%(10**tmp)+1)
for i in [0]:
    stringN=str(n)
    for tmp in range(len(stringN)-1):
        if int(stringN[-tmp-1])>i:
            result[i]+=10**tmp*(n//(10**(tmp+1)))
        else:
            result[i]+=(10**tmp*(n//(10**(tmp+1))-1)+n%(10**tmp)+1)
print(" ".join(map(str,result)))
