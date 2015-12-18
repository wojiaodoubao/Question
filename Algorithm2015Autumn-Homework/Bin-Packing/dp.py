#encoding:utf-8
import sys
import random
import time

#DP Bin-Packing
#Input N,(Threshold of) v,w,c
N = int(sys.argv[1])
#Threshold of v,w,C
Threshold = []
for i in range(3):
	Threshold.append(int(sys.argv[i+2]))
Threshold[1]=Threshold[2]
#Get N items
item = []
for i in range(N):
	v = random.randint(0,Threshold[0])
	w = random.randint(0,Threshold[1])
	item.append((v,w))

'''
#Test Data
item=[(5,3),(1,1),(7,6),(2,5),(19,9)]
Threshold[2]=10
N=len(item)
'''

Threshold[2]=Threshold[2]+1


startTime=time.time()
#DP
#init
S = [0 for x in range(Threshold[2])]#1行C列,初始为0
Route = [[-1 for x in range(Threshold[2])] for x in range(N)]
for j in range(Threshold[2]):
	if j>=item[0][1]:
		S[j]=item[0][0]
		Route[0][j]=1
#Scan
for i in range(1,N,1):
	for j in range(Threshold[2]-1,-1,-1):
		if j-item[i][1]<0:
			S[j]=S[j]
		elif S[j-item[i][1]]+item[i][0]>S[j]:
			S[j]=S[j-item[i][1]]+item[i][0]
			Route[i][j]=1
		else:
			S[j]=S[j]

Route = capacityDP(item,Threshold[2])



Result=[]
#Print Value
print(S[Threshold[2]-1])
#Print Route
w=Threshold[2]-1
for n in range(N-1,-1,-1):
	if Route[n][w]==1:
		#print(n,item[n])
		Result.append((n,item[n]))
		w=w-item[n][1]
		n=n-1
	else:
		w=w
		n=n-1

print(Result)
print('时间:%s' % (time.time()-startTime))



def capacityDP(item,C):
	'''0-1背包 使用背包数N和总容量C [2015-12-2 belanhd]

	Args:
		item: list[()] (v,c)
		C: 容量，整数

	Returns:
		(maxValue,[]) 最大值和取法list

	e.g.:
		item=[(5,3),(1,1),(7,6),(2,5),(19,9)]#物品0到N-1
		C=10
	'''
	#DP-i物品j空间达到最大价值-不压缩
	#init
	N=len(item)
	#init 
	S=[[0 for x in range(C+1)] for x in range(N)]#N行C+1列
	for j in range(C+1):
		if j>=item[0][1]:
			S[0][j]=item[0][0]
	#do
	#Scan
	for i in range(1,N,1):#1~N-1
		for j in range(C,-1,-1):#C~0
			if j-item[i][1]<0:
				S[i][j]=S[i-1][j]
			elif S[i-1][j-item[i][1]]+item[i][0]>S[i-1][j]:
				S[i][j]=S[i-1][j-item[i][1]]+item[i][0]
			else:
				S[i][j]=S[i-1][j]
	#Route
	Route=[]
	j=C
	for i in range(N-1,-1,-1):#N-1~0
		if i==0 and S[i][j]>0:
			Route.append((i,item[i]))
		elif S[i][j]==S[i-1][j]:
			continue
		else:
			Route.append((i,item[i]))
			j=j-item[i][1]
	return (S[N-1][C],Route)