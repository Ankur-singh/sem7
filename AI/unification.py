from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem import PorterStemmer

########## FUNCTIONS #############

def unify(ar1, ar2):
	global dict_sub
	subst = ""
	if ar1 != ar2:
		if isVariable(ar1):
			subst = ar2+'/'+ar1
			dict_sub[ar1]=ar2
		else:
			subst = ar1+'/'+ar2
			dict_sub[ar2]=ar1
	return subst
		

def isVariable(v):
	lenght  = False
	if len(v) == 1:
		lenght = True
	return v.islower() and v.isalpha and lenght


def unified_predicate(text1, dict_sub):
	text = text1
	for atom in dict_sub.keys():
		if atom in text1:
			text = text.replace(atom, dict_sub[atom])
		'''
		else:
			result.append(atom)
		'''
	return text
		

######### MAIN #########################

#text1 = raw_input("Enter the first sentence\t")
#text2 = raw_input("Enter the first sentence\t")
stop_words = set(stopwords.words('english'))
ps = PorterStemmer()

text1= "ankur is playing x"
text2 = "w plays football"

tokens = word_tokenize(text1)
l1 = []
for w in tokens:
	if w not in stop_words:
		l1.append(str(ps.stem(w)))

tokens = word_tokenize(text2)
l2 = []
for w in tokens:
	if w not in stop_words:
		l2.append(str(ps.stem(w)))

dict_sub = {}
result=[]

print "l1:\t"+str(l1)
print "l2:\t"+ str(l2)	

if len(l1)==len(l2): 
	for i in range(0, len(l1)):
		if len(unify(l1[i], l2[i])) != 0: 
			result.append(unify(l1[i], l2[i]))
	print "subst:\t"+str(result)	
	#print dict_sub
	print "result:\t"+str(unified_predicate(text1, dict_sub))
else:
	print "predicate didnt match or lenght not equal"
