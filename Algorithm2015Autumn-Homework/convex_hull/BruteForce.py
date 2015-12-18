#encoding:utf-8
import sys
import random
import Tool
import time

#BruteForce
def BruteForce(pointList):
    if len(pointList)<=3:
        return pointList
    #去重
    pointList=list(set(pointList))
    #固定A点
    A = Tool.findIth(0,len(pointList)-1,pointList,1,lambda x,y:cmp(x[1],y[1]))
    pointList.remove(A)
    #枚举B,C,P
    while 1:
        dropList=[]
        for b in range(0,len(pointList)-1):
            for c in range(b+1,len(pointList)):
                for p in range(0,len(pointList)):
                    if p==b or p==c:
                        continue
                    B=pointList[b]
                    C=pointList[c]
                    P=pointList[p]
                    if Tool.isInTriangle(A,B,C,P)==1:
                        dropList.append(P)
        if len(dropList)==0:
            break
        #去重
        dropList=list(set(dropList))
        for p in dropList:
            pointList.remove(p)
    pointList.append(A)
    return pointList
    


#main()
#Get pointNum
if len(sys.argv)<2:
    print('需要序列长度')
    sys.exit(0)
point_Num = int(sys.argv[1])
pointList = []
Threshold = 100
#Get random Points
for w in range(point_Num):
    x = random.randint(0,Threshold)
    y = random.randint(0,Threshold)
    pointList.append((x,y))
#pointList = [(-1,0),(0,0),(0,1),(1,0)]
#pointList = [(1,2),(2,3),(-1,3),(-2,3),(-3,3),(3,3),(4,3),(1,5)]
#pointList = [(-2,9),(1,2),(2,3),(2,4),(1,5),(3,6),(-3,8),(-1,7),(-4,5)]
#pointList=[(0,1),(0,0),(-1,3)]
#Time Start
startTime = time.time()
print(BruteForce(pointList))
print('用时:%s' % (time.time()-startTime))
