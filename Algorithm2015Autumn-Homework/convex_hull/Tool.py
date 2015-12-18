#encoding:utf-8

def findIth(left,right,list,I,compareMethod):
    """Find the Ith element  [2015-12-2 belanhd]

    Find the Ith element from list[left:right+1],will change the order of list.
    It's O(n).
    e.g.:
        list=[1,2,3,4,5,6,7]
        t=findIth(0,len(list)-1,list,3,numericalCompareMethod) 

    Args:
        list: [object,...]
        left-right:list[left]-list[right]
        I:Ith
        compareMethod:function to compare object,return -1,0,1 represent [first] <,=,> [second]

    Returns:
        The Ith object

    Raises:
        InValidInputError
    """
    if left>right:
        raise InValidInputError('Invalid left,right')#抛出异常，方便调试
    if right-left+1<I or I<1:
        raise InValidInputError('Invalid I')
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

def exchange(list,i,j):
    """exchange list[i] list[j]
    """
    temp = list[i]
    list[i] = list[j]
    list[j] = temp

def numericalCompareMethod(i,j):
    '''Numerical compare method
    '''
    if i>j:
        return 1
    elif i<j:
        return -1
    else:
        return 0

class InValidInputError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)




#####################################################################
#Is P in triangle ABC
#2015-11-9
#P!=A,B,C,A!=B,A!=C,B!=C
def isInTriangle(A,B,C,P):
    """Is point P in triangle ABC

    判断点P是否在三角形ABC中

    Args:
        A,B,C,P 二维平面点，A[0]表示X,A[1]表示Y
    Returns:
        1 在
        0 其他（不在，ABC不是三角形...）
    Raises:
        Unknown
    """
    if P==A or P==B or P==C or A==B or A==C or B==C:
        return -1
    #f:X,Y,P在一条直线上返回0
    f=lambda X,Y,P:cmp((X[1]-P[1])*(P[0]-Y[0]),(P[1]-Y[1])*(X[0]-P[0]))
    #A,B,C没构成三角形<==>A,B,C在一条直线上
    if f(A,B,C)==0:
        return 0
    #P,A同侧;P,B同侧;P,C同侧
    if isTheSameSide(A,B,C,P)=='same' and isTheSameSide(A,C,B,P)=='same' and isTheSameSide(B,C,A,P)=='same':
        return 1
    #P在A-B or P在A-C or P在B-C
    ff=lambda X,Y,P:(X-P)*(P-Y)
    fff = lambda X,Y,P:f(X,Y,P)==0 and ff(X[0],Y[0],P[0])>=0 and ff(X[1],Y[1],P[1])>=0
    if fff(A,B,P) or fff(B,C,P) or fff(A,C,P):
        return 1
    return 0

#Determine if X,P are in the same side of L-R
def isTheSameSide(L,R,X,P):
    T1=L[0]-R[0]
    T2=L[1]-R[1]
    f=lambda T1,T2,L,P:T1*P[1]-T2*P[0]-T1*L[1]+T2*L[0]
    if f(T1,T2,L,X)*f(T1,T2,L,P)>0:
        return 'same'
    return 'different'

