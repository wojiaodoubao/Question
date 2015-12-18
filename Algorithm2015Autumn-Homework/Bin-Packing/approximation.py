#encoding:utf-8
import sys
import random
import time

#Approximation Bin-Packing
#Input N,(Threshold of) v,w,c
N = int(sys.argv[1])
#Threshold of v,w,C
Threshold = []
for i in range(3):
	Threshold.append(int(sys.argv[i+2]))
#Create N items & maxValue
maxValue=0
original_item = []
for i in range(N):
	v = random.randint(0,Threshold[0])
	w = random.randint(0,Threshold[1])
	original_item.append((v,w))
	if v>maxValue:
		maxValue=v

'''
#Test Data
item=[(5,3),(1,1),(7,6),(2,5),(3,9)]
Threshold[2]=10
maxValue=7
'''

startTime = time.time()

#approximation
#modify item
ratio = 10000#不使用浮点数，这样以后可以自动取flour
N=len(original_item)
K = N/ratio
item=[]
for i in range(N):
	item.append((K*original_item[i][0]/maxValue,original_item[i][1]))
#DP-i物品j价值需要的最小空间-不压缩
#init
C=Threshold[2]
SumValue=K*N
#init S[0][*]
S=[[C+1 for x in range(SumValue+1)] for x in range(N)]#N行SumValue+1列
for j in range(SumValue+1):
	if j==item[0][0]:
		S[0][j]=item[0][1]
S[0][0]=0#物品0达到价值0所需空间为0
Result = [-1,-1]#i,j j就是value
#Scan
for i in range(1,N,1):#1~N-1
	for j in range(SumValue,-1,-1):#SumValue~0
		S[i][j]=S[i][j]
		if j-item[i][0]<0:
			S[i][j]=S[i-1][j]
		elif S[i-1][j]<S[i-1][j-item[i][0]]+item[i][1]:
			S[i][j]=S[i-1][j]
		else:
			S[i][j]=S[i-1][j-item[i][0]]+item[i][1]
		if S[i][j]<=C and j>Result[1]:
			Result[0]=i
			Result[1]=j
#Route
Route=[]
j=Result[1]
for i in range(Result[0],-1,-1):#Result[0]~0
	if i==0 and j!=0:
		Route.append((i,item[i]))		
	elif S[i-1][j]>=C+1 and S[i-1][j-item[i][0]]>=C+1:#Sij只可能由Si-1j/Si-1j-v[i]到达，如果两者皆已超过C，则Sij必定不是可行解，说明问题没有可行解
		break#用于不会在这返回，因为当不存在解时，Result=[-1,-1],循环直接退出
	elif S[i-1][j]==S[i][j]:
		continue
	else:
		Route.append((i,item[i]))
		j=j-item[i][0]

Result=0
for i in range(len(Route)):
	Result+=original_item[Route[i][0]][0]

print(Result,Route)

print('时间:%s' % (time.time()-startTime))