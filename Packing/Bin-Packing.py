#encoding:utf-8

def valueDP(item,C):
	'''0-1背包 使用背包数N和总价值SumValue [2015-12-2 belanhd]

	Args:
		item: list[()] (v,c)
		C: 容量，整数

	Returns:
		(maxValue,[]) 最大值和取法list

	e.g.:
		item=[(5,3),(1,1),(7,6),(2,5),(19,9)]#物品0到N-1
		C=10
	'''
	#DP-i物品j价值需要的最小空间-不压缩
	#init
	N=len(item)
	SumValue=0
	for i in range(N):
		SumValue+=item[i][0]
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
			return (0,[])#用于不会在这返回，因为当不存在解时，Result=[-1,-1],循环直接退出
		elif S[i-1][j]==S[i][j]:
			continue
		else:
			Route.append((i,item[i]))
			j=j-item[i][0]
	
	return (Result[1],Route)


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


def valueDP_OneDimensionStore(item,C):
	'''0-1背包 使用背包数N和总价值SumValue 压缩一维存储 [2015-12-2 belanhd]

	Args:
		item: list[()] (v,c)
		C: 容量，整数

	Returns:
		maxValue 最大值

	e.g.:
		item=[(5,3),(1,1),(7,6),(2,5),(19,9)]#物品0到N-1
		C=10
	'''
	#DP-i物品j价值需要的最小空间-压缩
	#init
	N=len(item)
	SumValue=0
	for i in range(N):
		SumValue+=item[i][0]
	#init 
	S=[C+1 for x in range(SumValue+1)]#N行SumValue+1列
	for j in range(SumValue+1):
		if j==item[0][0]:
			S[j]=item[0][1]
	S[0]=0#物品0达到价值0所需空间为0
	Result = -1#value
	#Scan
	for i in range(1,N,1):#1~N-1
		for j in range(SumValue,-1,-1):#SumValue~0
			S[j]=S[j]
			if j-item[i][0]<0:
				S[j]=S[j]
			elif S[j]<S[j-item[i][0]]+item[i][1]:
				S[j]=S[j]
			else:
				S[j]=S[j-item[i][0]]+item[i][1]
			if S[j]<=C and j>Result:
				Result=j
	
	return (Result)	


def capacityDP_OneDimensionStore(item,C):
	'''0-1背包 使用背包数N和总容量C 压缩一维存储 [2015-12-2 belanhd]

	Args:
		item: list[()] (v,c)
		C: 容量，整数

	Returns:
		maxValue 最大值

	e.g.:
		item=[(5,3),(1,1),(7,6),(2,5),(19,9)]#物品0到N-1
		C=10
	'''
	#DP-i物品j空间达到最大价值-压缩
	#init
	N=len(item)
	#init 
	S=[0 for x in range(C+1)]#1行C+1列
	for j in range(C+1):
		if j>=item[0][1]:
			S[j]=item[0][0]
	#do
	#Scan
	for i in range(1,N,1):#1~N-1
		for j in range(C,-1,-1):#C~0
			if j-item[i][1]<0:
				S[j]=S[j]
			elif S[j-item[i][1]]+item[i][0]>S[j]:
				S[j]=S[j-item[i][1]]+item[i][0]
			else:
				S[j]=S[j]

	return (S[C])	


#test
item=[(5,3),(1,1),(7,3),(2,5),(9,7)]#物品0到N-1
C=0

print(valueDP(item,C))
print(capacityDP(item,C))
print(capacityDP_OneDimensionStore(item,C))
print(valueDP_OneDimensionStore(item,C))