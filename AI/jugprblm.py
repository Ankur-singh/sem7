from Queue import Queue
class state(object):
	def __init__(self):
		self.jug3 = 0
		self.full3 = None
		self.empty3 = None
		self.tranfer3 = None
		self.jug4 = 0
		self.full4 = None
		self.empty4 = None
		self.tranfer4 = None

class start(object):
	def __init__(self):
		self.root = state()
		self.current = None
		
	def isempty(self, node, jug):
		if(jug == 3):
			if node.jug3 == 0:
				 return True
			else:
				return False
		if(jug == 4):
			if node.jug4 == 0:
				 return True
			else:
				return False
			
	def isfull(self, node, jug):
		if(jug == 3):
			if node.jug3 == 3:
				 return True
			else:
				return False
		if(jug == 4):
			if node.jug4 == 4:
				 return True
			else:
				return False
		

	def full3(self, node):
		if(node.jug3 != 3):
			
			nod = state()
			nod.jug3 = 3
			nod.jug4 = node.jug4
			print "full3"
			print nod.jug3, nod.jug4
			self.current = nod
			return self.current
		else:
			return None

	def empty3(self, node):
		if(not(self.isempty(node, 3))):
			nod = state()
			nod.jug3 = 0
			nod.jug4 = node.jug4
			print "empty3"
			print nod.jug3, nod.jug4
			self.current = nod
			return self.current
		else:
			return None


	def transfer3(self, node):
		if(not(self.isempty(node, 3)) and not(self.isfull(node, 4))):
			nod = state()
			cap = 4-node.jug4
			if cap > node.jug3:
				cap = node.jug3
			nod.jug3 = node.jug3 - cap
			nod.jug4 = node.jug4 + cap
			res = self.check(nod)
			print "transfer3"
			print nod.jug3, nod.jug4
			if res == True:
				print "expected Output\n\n"
				return res
			self.current = nod
			return self.current
		else:
			return None
	

	def full4(self, node):
		if(node.jug4 != 4):
			nod = state()
			nod.jug4 = 4
			nod.jug3 = node.jug3
			print "full4"
			print nod.jug3, nod.jug4
			self.current = nod
			return self.current
		else:
			return None

	def empty4(self, node):
		nod = state()
		nod.jug4 = 0
		nod.jug3 = node.jug3
		print "empty4"
		print nod.jug3, nod.jug4
		self.current = nod
		return self.current

	def transfer4(self, node):
		if(not(self.isempty(node, 4)) and not(self.isfull(node, 3))):
			nod = state()
			cap = 3-node.jug3
			if cap > node.jug4:
				cap = node.jug4
			nod.jug4 = node.jug4 - cap
			nod.jug3 = node.jug3 + cap
			res = self.check(nod)
			print "transfer4"
			print nod.jug3, nod.jug4
			if res == True:
				print "expected Output\n\n"
				return res
			self.current = nod
			return self.current
		else :
			return None

	def check(self, node):
		if (node.jug4 == 2):
			return True
		else:
			return False

counter = 0
def recc(root):
	global counter, s
	if root is not None:
		one = s.full3(root)
		two = s.transfer3(root)
		three = s.full4(root)
		four = s.transfer4(root)
		return (one, two, three, four)

def filter(state):
	global s
	if state is not None:
		print (state.jug3, state.jug4)
		if state.jug3 != 3:
			q.put(s.full3(state))
		if state.jug3 != 0:
			q.put(s.empty3(state))
		if state.jug3 != 0:
			q.put(s.transfer3(state))
		if state.jug4 != 4:
			q.put(s.full4(state))
		if state.jug4 != 0:
			q.put(s.empty4(state))
		if state.jug4 != 0:
			q.put(s.transfer4(state))

q= Queue()
'''
s = start()
a = s.full3(s.root)
a = s.transfer3(a)
a = s.full3(a)
a = s.transfer3(a)
'''
s = start()
q.put(s.root)
count = 0
l = []
while count <= 70:
	count += 1
	try:
		a = q.get_nowait()
	except Exception as e:
		break
	if type(a) is state:
		if ((a.jug3, a.jug4) not in l):
			filter(a)
			l.append((a.jug3, a.jug4))
			print '\n'
print count
'''
a = recc(s.root)
print "\n"
print "\n"
for i in xrange(4):
	a = recc(a[i])
	print "\n"
	print "\n"

print list(q)
a = recc(a[0])
print "\n"
print "\n"
a = recc(a[0], a[1], a[2], a[3])
print "\n"
print "\n"
a = recc(a[0], a[1], a[2], a[3])

'''
