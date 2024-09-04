arr=[[0]*4 for _ in range(4)]
dstArr=[[0]*4 for _ in range(4)]
dx=[5,-1,-1,0,1,1,1,0,-1]
dy=[5,0,-1,-1,-1,0,1,1,1]
sharkloc=[[0]*2 for _ in range(17)]

#입력 배열
for i in range(4):
    tmp=list(map(int,input().split()))
    for j in range(8):
        if (j%2==0):
            arr[i][j//2]=tmp[j]
        else:
            dstArr[i][j//2]=tmp[j]

#상어 위치를 sharkloc 리스트에 저장하는 코드
for x in range(4):
    for y in range(4):
        sharkloc[arr[x][y]][0],sharkloc[arr[x][y]][1]=x,y

sharkx,sharky,sharkdst=0,0,dstArr[0][0]
result=arr[0][0]
sharkloc[result][0],sharkloc[result][1]=-1,-1
arr[0][0]=-1
maxresult=result

# 물고기를 이동시키자
for goginum in range(1,17):
    #좌표를 불러와서
    x,y=sharkloc[goginum][0],sharkloc[goginum][1]
    if x!=-1 and y!=-1:
        dst=dstArr[x][y]
        for i in range(9):
            ax=x+dx[(dst+i)%9]
            ay=y+dy[(dst+i)%9]
            if(0<=ax<4 and 0<=ay<4 and arr[ax][ay]!=-1):
                dst=(dst+i)%9
                dstArr[x][y]=dst
                sharkloc[arr[x][y]][0],sharkloc[arr[x][y]][1],sharkloc[arr[ax][ay]][0],sharkloc[arr[ax][ay]][1]=sharkloc[arr[ax][ay]][0],sharkloc[arr[ax][ay]][1],sharkloc[arr[x][y]][0],sharkloc[arr[x][y]][1]
                dstArr[x][y],dstArr[ax][ay]=dstArr[ax][ay],dstArr[x][y]
                arr[x][y],arr[ax][ay]=arr[ax][ay],arr[x][y]
                break

def backTracking(arr,dstArr,sharkloc,sharkx,sharky,sharkdst,result,cmd):
    global maxresult
    for n in range(1,4):
        sharkax,sharkay=sharkx+dx[sharkdst]*n,sharky+dy[sharkdst]*n
        if 0<=sharkax<4 and 0<=sharkay<4 and arr[sharkax][sharkay]>0:
            newarr,newdstArr,newsharkloc=[[0]*4 for _ in range(4)],[[0]*4 for _ in range(4)],[[0]*2 for _ in range(17)]
            for x in range(4):
                for y in range(4):
                    newarr[x][y]=arr[x][y]
                    newdstArr[x][y]=dstArr[x][y]
            for i in range(17):
                for j in range(2):
                    newsharkloc[i][j]=sharkloc[i][j]
            tmpvalue=newarr[sharkax][sharkay]
            sharkadst=newdstArr[sharkax][sharkay]
            newsharkloc[newarr[sharkax][sharkay]][0],newsharkloc[newarr[sharkax][sharkay]][1]=-1,-1
            newarr[sharkx][sharky]=0
            newarr[sharkax][sharkay]=-1

            # 물고기를 이동시키자
            for goginum in range(1,17):
                #좌표를 불러와서
                x,y=newsharkloc[goginum][0],newsharkloc[goginum][1]
                if x!=-1 and y!=-1:
                    dst=newdstArr[x][y]
                    for i in range(9):
                        ax=x+dx[(dst+i)%9]
                        ay=y+dy[(dst+i)%9]
                        if(0<=ax<4 and 0<=ay<4 and newarr[ax][ay]!=-1):
                            dst=(dst+i)%9
                            newdstArr[x][y]=dst
                            if newarr[ax][ay]==0:
                                newsharkloc[newarr[x][y]][0],newsharkloc[newarr[x][y]][1],newsharkloc[newarr[ax][ay]][0],newsharkloc[newarr[ax][ay]][1]=ax,ay,newsharkloc[newarr[x][y]][0],newsharkloc[newarr[x][y]][1]
                            else:
                                newsharkloc[newarr[x][y]][0],newsharkloc[newarr[x][y]][1],newsharkloc[newarr[ax][ay]][0],newsharkloc[newarr[ax][ay]][1]=newsharkloc[newarr[ax][ay]][0],newsharkloc[newarr[ax][ay]][1],newsharkloc[newarr[x][y]][0],newsharkloc[newarr[x][y]][1]
                            newdstArr[x][y],newdstArr[ax][ay]=newdstArr[ax][ay],newdstArr[x][y]
                            newarr[x][y],newarr[ax][ay]=newarr[ax][ay],newarr[x][y]
                            break
            backTracking(newarr,newdstArr,newsharkloc,sharkax,sharkay,sharkadst,result+tmpvalue,cmd+" "+str(tmpvalue))
    if maxresult<result:
        maxresult=result

backTracking(arr,dstArr,sharkloc,sharkx,sharky,sharkdst,result,str(result))
print(maxresult)