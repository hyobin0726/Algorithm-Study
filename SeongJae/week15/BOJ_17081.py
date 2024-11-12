def printEndMsg(msg):
  for i in range(n):
    print("".join(map(str,arr[i])))
  print(f"Passed Turns : {turnCount}")
  print(f"LV : {heroLevel}")
  print(f"HP : {max(0,heroHp)}/{(heroLevel+3)*5}")
  print(f"ATT : {heroAtk}+{heroMugi}")
  print(f"DEF : {heroDef}+{herobang}")
  print(f"EXP : {int(HeroExp)}/{heroLevel*5}")
  print(f"{msg}")


n,m=map(int,input().split())
arr=[list(input().strip()) for _ in range(n)]
cmds=list(input().strip())
info=[[False]*m for _ in range(n)]
ECount,BCount,turnCount=0,0,0
heroX,heroY,heroLevel,heroHp,heroAtk,heroDef,HeroExp=0,0,1,20,2,2,0
#영웅 장신구(HR,RE,CO,EX,DX,HU,CU)
isHR,isRE,isCO,isEX,isDX,isHU,isCU=False,False,False,False,False,False,False
heroJangCount=0
#영웅 무기,영웅 방어구
heroMugi,herobang=0,0
BossX,BossY=0,0
for x in range(n):
  for y in range(m):
    if arr[x][y]=='&':
      ECount+=1
    elif arr[x][y]=='B':
      BCount+=1
    elif arr[x][y]=='@':
      startX,startY=x,y
    elif arr[x][y]=='M':
      ECount+=1
      BossX,BossY=x,y
for _ in range(ECount):
  x,y,ename,eatk,edef,ehp,eexp=input().split()
  info[int(x)-1][int(y)-1]=[ename,int(eatk),int(edef),int(ehp),int(eexp)]
for _ in range(BCount):
  x,y,jangtype,jangname=input().split()
  info[int(x)-1][int(y)-1]=[jangtype,jangname]
x,y=startX,startY
arr[x][y]='.'
for cmd in cmds:
  if cmd=="L":
    ax,ay=x,y-1
  elif cmd=="R":
    ax,ay=x,y+1
  elif cmd=="U":
    ax,ay=x-1,y
  elif cmd=="D":
    ax,ay=x+1,y
  #움직일 수 있는가?
  if 0<=ax<n and 0<=ay<m and arr[ax][ay]!='#':
    x,y=ax,ay
  arr[x][y]+='@'
  turnCount+=1
  #아이템 상자
  if arr[x][y]=='B@':
    #장비가 무기일때(W)
    if info[x][y][0]=='W':
      heroMugi=int(info[x][y][1])
    #장비가 갑옷일때(A)
    elif info[x][y][0]=='A':
      herobang=int(info[x][y][1])
    #장비가 장신구일떄(O)
    else:
      #장신구가 이미 4개
      canEquip=False
      if heroJangCount>=4:
        canEquip=False
      else:
        if info[x][y][1]=="HR" and not isHR:
          canEquip,isHR=True,True
        elif info[x][y][1]=="RE" and not isRE:
          canEquip,isRE=True,True
        elif info[x][y][1]=="CO" and not isCO:
          canEquip,isCO=True,True
        elif info[x][y][1]=="EX" and not isEX:
          canEquip,isEX=True,True
        elif info[x][y][1]=="DX" and not isDX:
          canEquip,isDX=True,True
        elif info[x][y][1]=="HU" and not isHU:
          canEquip,isHU=True,True
        elif info[x][y][1]=="CU" and not isCU:
          canEquip,isCU=True,True
      #장비를 착용할 수 있으면
      if canEquip:
        heroJangCount+=1

    #지도와 장비 초기화    
    arr[x][y]='.'
    info[x][y]=False

  #가시함정
  elif arr[x][y]=='^@':
    if isDX:
      heroHp-=1
    else:
      heroHp-=5
    #용사가 죽으면##############################################
    if heroHp<1:
      if isRE:
        arr[x][y]='^'
        x,y=startX,startY
        isRE=False
        heroHp=(heroLevel+3)*5
        heroJangCount-=1
      else:
        arr[x][y]='^'
        printEndMsg("YOU HAVE BEEN KILLED BY SPIKE TRAP..")
        exit()
    else:
      arr[x][y]='^'
    
  #일반 몬스터 조우
  elif arr[x][y]=='&@':
    ename,eatk,edef,ehp,eexp=info[x][y][0],info[x][y][1],info[x][y][2],info[x][y][3],info[x][y][4]

    #첫턴 공격
    if isCO:
      if isDX:
        ehp-=max(1,(heroAtk+heroMugi)*3-edef)
      else:
        ehp-=max(1,(heroAtk+heroMugi)*2-edef)
    else:
      ehp-=max(1,(heroAtk+heroMugi)-edef)
    #적의 체력
    if ehp>0:
      heroHp-=max(1,eatk-(heroDef+herobang))

    #맞고라
    while heroHp>0 and ehp>0:
      ehp-=max(1,(heroAtk+heroMugi)-edef)
      if ehp>0:
        heroHp-=max(1,eatk-(heroDef+herobang))
    
    #용사가 승리하면
    if heroHp>0:
      #레벨업 처리
      if isEX:
        HeroExp+=int((eexp*1.2)//1)
      else:
        HeroExp+=eexp
      if HeroExp>=5*heroLevel:
        HeroExp=0
        heroLevel+=1
        heroHp=5*(heroLevel+3)
        heroAtk=2*heroLevel
        heroDef=2*heroLevel
      
      if isHR:
        heroHp=min(heroHp+3,5*(heroLevel+3))

      #지도와 몬스터 초기화
      arr[x][y]='.'
      info[x][y]=False
    #용사가 패배
    else:
      if isRE:
        arr[x][y]='&'
        x,y=startX,startY
        isRE=False
        heroHp=(3+heroLevel)*5
        heroJangCount-=1
      else:
        arr[x][y]='&'
        printEndMsg(f"YOU HAVE BEEN KILLED BY {ename}..")
        exit()

  #그냥 길
  elif arr[x][y]=='.@':
    arr[x][y]='.'
  
  #보스방
  elif arr[x][y]=='M@':
    ename,eatk,edef,ehp,eexp=info[x][y][0],info[x][y][1],info[x][y][2],info[x][y][3],info[x][y][4]

    #첫턴 공격
    if isCO:
      if isDX:
        ehp-=max(1,(heroAtk+heroMugi)*3-edef)
      else:
        ehp-=max(1,(heroAtk+heroMugi)*2-edef)
    else:
      ehp-=max(1,(heroAtk+heroMugi)-edef)
    #적의 체력
    if ehp>0:
      heroHp-=max(1,eatk-(heroDef+herobang))
    if isHU:
      heroHp=(heroLevel+3)*5
    #맞고라
    while heroHp>0 and ehp>0:
      ehp-=max(1,(heroAtk+heroMugi)-edef)
      if ehp>0:
        heroHp-=max(1,eatk-(heroDef+herobang))
    
    #용사가 승리하면
    if heroHp>0:
      #레벨업 처리
      if isEX:
        HeroExp+=(eexp*1.2)//1
      else:
        HeroExp+=eexp
      if HeroExp>=5*heroLevel:
        HeroExp=0
        heroLevel+=1
        heroHp=5*(heroLevel+3)
        heroAtk=2*heroLevel
        heroDef=2*heroLevel
      
      if isHR:
        heroHp=min(heroHp+3,5*(heroLevel+3))
      arr[x][y]='@'
      printEndMsg("YOU WIN!")
      exit()
    #용사가 패배
    else:
      if isRE:
        arr[x][y]='M'
        x,y=startX,startY
        isRE=False
        heroHp=(3+heroLevel)*5
        heroJangCount-=1
      else:
        arr[x][y]='M'
        printEndMsg(f"YOU HAVE BEEN KILLED BY {ename}..")
        exit()
arr[x][y]='@'
printEndMsg("Press any key to continue.")
