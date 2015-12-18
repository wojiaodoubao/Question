#encoding:utf-8
import sys
import random
import Tool
import time

#Function
def Graham_Scan(pointlist):
	#构造有序list
	point = Tool.findIth(0,len(pointlist)-1,pointlist,1,comparePointX)#获取最左边点
	uplist = []
	downlist=[]
	otherlist=[]
	for w in pointlist:
		if w[0]==point[0] and w[1]==point[1]:
			continue
		elif w[0]==point[0] and w[1]>point[1]:
			uplist.append((w,'+'))
		elif w[0]==point[0] and w[1]<point[1]:
			downlist.append((w,'-'))
		else:
			otherlist.append((w,float(w[1]-point[1])/(w[0]-point[0])))
	#排序
	uplist.sort(cmp=lambda x,y:comparePointY(x[0],y[0]),reverse=True)#降序
	downlist.sort(cmp=lambda x,y:comparePointY(x[0],y[0]))#升序
	otherlist.sort(cmp=lambda x,y:cmp(x[1],y[1]))#按斜率升序
	#合并为一个有序list
	list=[]
	list.append(point)
	for w in downlist:
		list.append(w[0])
	for w in otherlist:
		list.append(w[0])
	for w in uplist:
		list.append(w[0])
	#Graham-Scan
	stack = []
	if len(list)<3:
	    return list
	for w in range(3):
	    stack.append(list.pop(0))
	for w in range(len(list)):
	    next = list.pop(0)
	    while isLeftMove(stack[-2],stack[-1],next,point)=='right':
	        stack.pop()
	        if len(stack)<=2:
	            break
	    stack.append(next)
	return stack

#compare Point x
def comparePointX(p1,p2):
    if p1[0] > p2[0]:
        return 1
    elif p1[0] < p2[0]:
        return -1
    else:
        return 0
#compare Point y
def comparePointY(p1,p2):
	if p1[1] > p2[1]:
		return 1
	elif p1[1] < p2[1]:
		return -1
	else:
		return 0
#a2和point在a1,a3同侧
def isLeftMove(a1,a2,a3,point):
    A=a1[1]-a3[1]
    B=a1[0]-a3[0]
    re1 = A*a2[0]-B*a2[1]+B*a3[1]-A*a3[0]
    re2 = A*point[0]-B*point[1]+B*a3[1]-A*a3[0]
    if(re1*re2)>=0:
        return 'right'
    return 'left'






#main()
#Get pointNum
if len(sys.argv)<2:
    print('需要序列长度')
    sys.exit(0)
point_Num = int(sys.argv[1])
pointlist = []
Threshold = 100
#Get random Points
for w in range(point_Num):
    x = random.randint(0,Threshold)
    y = random.randint(0,Threshold)
    pointlist.append((x,y))
#pointlist = [(-1,0),(0,0),(0,1),(1,0)]
#pointlist = [(1,2),(2,3),(-1,3),(-2,3),(-3,3),(3,3),(4,3),(1,5)]
#pointlist = [(-4,5),(-3,7),(-3,6),(-3,8),(-2,9),(1,2),(1,5),(2,3),(2,4)]
#pointlist = [(1,2),(2,3),(-1,3),(-2,3),(-3,3),(3,3),(4,3),(1,5)]
#pointlist = [(-4,5),(-3,7),(-3,6),(-3,8),(-2,9),(1,2),(1,5),(2,3),(2,4)]
startTime = time.time()
print(Graham_Scan(pointlist))
print('用时:%s' % (time.time()-startTime))