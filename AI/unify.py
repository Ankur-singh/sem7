########## FUNCTIONS #############

def unify(ar1, ar2):
	global dict_sub
	subst = ""
	if ar1 != ar2 and (not isinstance(ar1, list)) and (not isinstance(ar2, list)):
		if isVariable(ar1):
			subst = ar2+'/'+ar1
			dict_sub[ar1]=ar2
		else:
			subst = ar1+'/'+ar2
			dict_sub[ar2]=ar1
	
	elif isinstance(ar1, list) and isinstance(ar2, list):
		main(ar1, ar2)
		
	return subst
		

def isVariable(v):
	lenght  = False
	if len(v) == 1:
		lenght = True
	return v.islower() and v.isalpha and lenght


def unified_predicate(l1):
	global dict_sub
	result = []
	for atom in l1:
		if atom in dict_sub.keys():
			atom = dict_sub[atom]
			result.append(atom)
		else:
			result.append(atom)
	return result
		

def main(l1, l2):
	global dict_sub, result
	if len(l1)==len(l2) and l1[0]==l2[0]:
		for i in range(1, len(l1)):
			if len(unify(l1[i], l2[i])) != 0: 
				result.append(unify(l1[i], l2[i]))
		print "l1:\t"+str(l1)
		print "l2:\t"+ str(l2)	
		print "subst:\t"+str(result)	
		#print dict_sub
		print "result:\t"+str(unified_predicate(l1))
	else:
		print "predicate didnt match or lenght not equal"


######### MAIN #########################

dict_sub = {}
l1= ['hate', ['dog','spot'], 'ceasar']
l2= ['hate', ['dog','y'], 'x']

main(l1, l2)
