x1,y1,x2,y2=map(int,input().split())
x3,y3,x4,y4=map(int,input().split())
def ccw(xa,ya,xb,yb,xc,yc):
    s=xa*yb+xb*yc+xc*ya-(xb*ya+xc*yb+xa*yc)
    if s>0: return 1
    elif s==0: return 0
    else: return -1

if(ccw(x1,y1,x2,y2,x3,y3)*ccw(x1,y1,x2,y2,x4,y4)==0 and ccw(x3,y3,x4,y4,x1,y1)*ccw(x3,y3,x4,y4,x2,y2)==0):
    minLineX1=min(x1,x2)
    minLineY1=min(y1,y2)
    minLineX2=min(x3,x4)
    minLineY2=min(y3,y4)
    maxLineX1=max(x1,x2)
    maxLineY1=max(y1,y2)
    maxLineX2=max(x3,x4)
    maxLineY2=max(y3,y4)
    if minLineX1<=maxLineX2 and minLineX2<=maxLineX1 and minLineY1<=maxLineY2 and minLineY2<=maxLineY1:
        print(1)
        isOne=False
        result=[0,0]
        if(x1==x3 and y1==y3 and (x2-x1)*(x4-x1)<=0):
            isOne=True
            result[0]=x1
            result[1]=y1
        if(x1==x4 and y1==y4 and (x2-x1)*(x3-x1)<=0):
            isOne=True
            result[0]=x1
            result[1]=y1
        if(x2==x3 and y2==y3 and (x1-x2)*(x4-x2)<=0):
            isOne=True
            result[0]=x2
            result[1]=y2
        if(x2==x4 and y2==y4 and (x1-x2)*(x3-x2)<=0):
            isOne=True
            result[0]=x2
            result[1]=y2
        if(x1==x2):
            m1=100000009
        else:
            m1=(y2-y1)/(x2-x1)
        if (x3==x4):
            m2=100000009
        else:
            m2=(y4-y3)/(x4-x3)
        #기울기가 다를떄
        if(m1!=m2):
            if(x1==x3 and y1==y3):
                isOne=True
                result[0]=x1
                result[1]=y1
            elif(x1==x4 and y1==y4):
                isOne=True
                result[0]=x1
                result[1]=y1
            elif(x2==x3 and y2==y3):
                isOne=True
                result[0]=x2
                result[1]=y2
            elif(x2==x4 and y2==y4):
                isOne=True
                result[0]=x2
                result[1]=y2
        if isOne:
            print(result[0],result[1])
    else:
        print(0)
elif (ccw(x1,y1,x2,y2,x3,y3)*ccw(x1,y1,x2,y2,x4,y4)<=0 and ccw(x3,y3,x4,y4,x1,y1)*ccw(x3,y3,x4,y4,x2,y2)<=0):
    print(1)
    if x1==x2:
        m2=(y4-y3)/(x4-x3)
        b2=y3-m2*x3
        print(x1,m2*x1+b2)
    elif x3==x4:
        m1=(y2-y1)/(x2-x1)
        b1=y1-m1*x1
        print(x3,m1*x3+b1)
    else:
        m1=(y2-y1)/(x2-x1)
        b1=y1-m1*x1
        m2=(y4-y3)/(x4-x3)
        b2=y3-m2*x3
        print((b2-b1)/(m1-m2),m1*(b2-b1)/(m1-m2)+b1)
else:
    print(0)