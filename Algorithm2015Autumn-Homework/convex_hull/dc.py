#encoding:utf-8
import sys
import random
import time

#a2和point在a1,a3同侧
def isLeftMove(a1,a2,a3,point):
    A=a1[1]-a3[1]
    B=a1[0]-a3[0]
    re1 = A*a2[0]-B*a2[1]+B*a3[1]-A*a3[0]
    re2 = A*point[0]-B*point[1]+B*a3[1]-A*a3[0]
    if(re1*re2)>=0:
        return 'right'
    return 'left'

    
#Merge point list
#list1,list2-[((a,b),c)...]
#list-[(a,b)...]
def merge(list1,list2):
    list = []
    while len(list1)>0 and len(list2)>0:
        if list1[0][1]<list2[0][1]:
            list.append(list1.pop(0)[0])
        else:
            list.append(list2.pop(0)[0])
    for w in list1:
        list.append(w[0])
    for w in list2:
        list.append(w[0])
    return list
    
#compare Point x
def comparePointX(p1,p2):
    if p1[0] > p2[0]:
        return 1
    elif p1[0] < p2[0]:
        return -1
    else:
        return 0
    
#Divid-Conquer
def convex_hull_dc(list):
    if len(list)==1:
        return list
    if len(list)<=0:
        return []
    
    listTemp = list[:]
    point = findIth(0,len(list)-1,list,len(list)/2,comparePointX)
    leftList=[]
    rightList=[]
    #如果从list横坐标第len(list)/2大到横坐标第len(list)大的点横坐标都一样，那么它们就都会被加入到leftList中，从而死循环
    #所以要处理一下
    maxX=findIth(0,len(list)-1,list,len(list),comparePointX)
    minX=findIth(0,len(list)-1,list,1,comparePointX)
    if minX[0]==maxX[0]:
        return list
    elif point[0]==maxX[0]:#至少一个点横坐标<maxX
        for w in list:
            if comparePointX(w,point)==0:
                rightList.append(w)
            else:
                leftList.append(w)
    else:
        for w in list:
            if comparePointX(w,point)>0:
                rightList.append(w)
            else:
                leftList.append(w)
    #print('leftList',leftList)
    leftList = convex_hull_dc(leftList)
    rightList = convex_hull_dc(rightList)
    #Merge
    L = leftList[0]
    ll = [[],[],[]]
    for w in leftList:
        if comparePointX(w,L)>0:
            ll[1].append((w,float(w[1]-L[1])/(w[0]-L[0])))
        elif w[1]>L[1]:
            ll[2].append((w,'+'))
        elif w[1]<L[1]:
            ll[0].append((w,'-'))
        else:
            pass
    rl = []
    maxrl = float(rightList[0][1]-L[1])/(rightList[0][0]-L[0])
    max_index = 0
    minrl = maxrl
    min_index = max_index
    for i in range(len(rightList)):
        w = rightList[i]
        xl = float(w[1]-L[1])/(w[0]-L[0])
        if xl>maxrl:
            maxrl = xl
            max_index=i
        if xl<minrl:
            minrl = xl
            min_index=i
        rl.append((w,xl))

    #print(rl,min_index,max_index)
    #clean rl
    #when min==max, the points in rl is in a line,  keep the farest one
    if minrl==maxrl:
        for i in range(len(rl)):
            w = rl[i]
            if ((w[0][0]-L[0])*(w[0][0]-L[0])+(w[0][1]-L[1])*(w[0][1]-L[1]))>((maxrl_point[0]-L[0])*(maxrl_point[0]-L[0])+(maxrl_point[1]-L[1])*(maxrl_point[1]-L[1])):#w is farther than maxrl_point
                maxrl_index=i
                maxrl=w[1]
        rl=[]
        #print('from',rl,'choose',maxrl_point)
        rl.append((rightList[maxrl],maxrl))
    #points with the same gradient, keep the farest one, min-max will solve it(needs min!=max).
    elif min_index<max_index:
        rl=rl[min_index:max_index+1]
    else:
        rl=rl[min_index:len(rl)]+rl[0:max_index+1]

    #print(L,rl)    
    #merge ll[0]-ll[1]&rl-ll[2]
    #print('L',L)
    #print('ll0',ll[0])
    #print('ll1',ll[1])
    #print('ll2',ll[2])
    #print('rl',rl)
    list = []
    list.append(L)
    for w in ll[0]:
        list.append(w[0])
    list.extend(merge(ll[1],rl))
    for w in ll[2]:
        list.append(w[0])
    #print('mergelist',list)
    #Graham-Scan 使用最左点
    stack = []
    if len(list)<3:
        return list
    for w in range(3):
        stack.append(list.pop(0))
    for w in range(len(list)):
        next = list.pop(0)
        while isLeftMove(stack[-2],stack[-1],next,L)=='right':
            stack.pop()
            if len(stack)<=2:
                break
        stack.append(next)
    return stack



    

#Find the ith
#It will change the order of list
#compareMethod:<-1 == 0 >1
def findIth(left,right,list,I,compareMethod):
    if left>right:
        return -1#坏处是一旦返回-1，findIth就不会报错，极有可能引起调用者后续代码出错，但是错误很难查找
    if right-left+1<I or I<1:
        return -1
    if left==right and I==1:
        return list[left]
    k=list[left]#radom select
    i=left+1
    j=right
    while i<j:
        while i<j and compareMethod(list[j],k)>=0:
            j=j-1
        while i<j and compareMethod(list[i],k)<0:
            i=i+1
        if i>=j:
            break
        exchange(list,i,j)
    if compareMethod(list[i],k)<0:
        exchange(list,left,i)
        if I==i-left+1:
            return k
        if I<i-left+1:
            return findIth(left,i-1,list,I,compareMethod)
        else:#I>i-left+1
            return findIth(i,right,list,I-(i-left),compareMethod)
    else:#list[i]>=k
        exchange(list,left,i-1)
        i=i-1
        if I==i-left+1:
            return k
        if I<i-left+1:
            return findIth(left,i,list,I,compareMethod)
        else:#I>i-left+1
            return findIth(i+1,right,list,I-(i-left+1),compareMethod)
#exchange list[i] list[j]
def exchange(list,i,j):
    temp = list[i]
    list[i] = list[j]
    list[j] = temp
#Numerical compare method
def compareMethod(i,j):
    if i>j:
        return 1
    elif i<j:
        return -1
    else:
        return 0


#main()
#设置最大递归次数为1000000-python默认的最大递归次数比较小，大概900多
#超出时抛出：RuntimeError: maximum recursion depth exceeded
sys.setrecursionlimit(1000000)
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
#pointList = [(-4,5),(-3,7),(-3,6),(-3,8),(-2,9),(1,2),(1,5),(2,3),(2,4)]
#pointList = [(1,2),(2,3),(-1,3),(-2,3),(-3,3),(3,3),(4,3),(1,5)]
startTime = time.time()
print(convex_hull_dc(pointList))
print('用时:%s' % (time.time()-startTime))
