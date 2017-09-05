from Queue import Queue
class state(object):
	def __init__(self, mis, can, side):
		self.mis = mis
		self.can = can
		self.side = side
		self.cc = None
		self.mm = None
		self.cm = None
		self.c = None
		self.m = None

class start(object):
	def __init__(self):
		self.root = state(3,3,0)
		self.current = None
		
	def permutations(self, node):
		l = []
		if node.side == 0:
			if node.mis >= 2:
				if self.safe(node, 'mm'):
					l.append('mm')
			if node.mis >= 1:
				if self.safe(node, 'm'):
					l.append('m')
			if node.can >= 2:
				if self.safe(node, 'cc'):
					l.append('cc')
			if node.can >= 1:
				if self.safe(node, 'c'):
					l.append('c')
			if node.can >= 1 and node.mis >= 1:
				if self.safe(node, 'cm'):
					l.append('cm')
		else:
			if (3 - node.mis >= 2):			
				#print 'inside mis >= 2'	
				if self.safe(node, 'mm'):
					l.append('mm')
			if 3-node.mis >= 1:
				if self.safe(node, 'm'):
					l.append('m')
			if (3 - node.can >= 2):
				if self.safe(node, 'cc'):
					l.append('cc')
			if 3-node.can >= 1:
				if self.safe(node, 'c'):
					l.append('c')
			if (3 - node.can >= 1) and (3 - node.mis >= 1):
				if self.safe(node, 'cm'):
					l.append('cm')


			
		return l
	
	def convert(self, move):
		if move == 'mm':
			t = (2,0)
		elif move == 'm':	
			t = (1,0)
		elif move == 'cc':
			t = (0,2)
		elif move == 'c':
			t = (0,1)
		elif move == 'cm':
			t = (1,1)
			
		else:
			print "not a valid move"
		
		return t		


	def safe(self, node, move):
		t = self.convert(move)
		if node.side == 0:
			if node.can - t[1] > node.mis - t[0] or (3-node.can+t[1] > 3-node.mis+t[0] and 3-node.mis != 0):
				return False
			else:
				return True
		else:
			if (node.can + t[1] > node.mis + t[0] and node.mis+t[0] != 0) or (3-node.can-t[1] > 3-node.mis-t[0] and 3-node.mis-t[0] != 0):
				return False
			else:
				return True

	def swim(self, node, move):
		t = self.convert(move)
		if node.side == 0:
			nod = state(node.mis - t[0], node.can - t[1], 1)
		else :
			nod = state(node.mis + t[0], node.can + t[1], 0)
		res = self.check(nod)
		print move
		print nod.mis, nod.can, nod.side
		if res :
			print "expected output"
			return res
		
		self.current = nod
		return self.current
		

	def check(self, node):
		if (node.mis == 0 and node.can == 0 and node.side == 1):
			return True
		else:
			return False

def filter(state):
	global s
	if state is not None:
		print (state.mis, state.can, state.side)
		l = s.permutations(state)
		for move in l:
			q.put(s.swim(state, move))


q= Queue()
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
		if ((a.mis, a.can, a.side) not in l):
			filter(a)
			l.append((a.mis, a.can, a.side))
			print '\n'
print count
