class side:
	def __init__(self, m, c):
		self.m = m
		self.c = c

	def permutation(self):
		per = []
		if self.m >= 2:
			t = (2,0)
			per.append(t)

		if self.c >= 2:
			t = (0,2)
			per.append(t)
		
		if self.m >= 1 and self.c >= 1:
			t = (1,1)
			per.append(t)
		
		if self.m >= 1 or self.c >= 1:
			t = (1,0)
			per.append(t)
			t = (0,1)
			per.append(t)

		return per

	def check(self):
		if self.c > self.m :
			return 0;
		else:
			return 1;


class boat:
	flag = 0

	def move(self, t, left, right):
		left.m = left.m - t[0]
		left.c = left.c - t[1]
		right.m = right.c + t[0]
		right.c = right.c + t[0] 
		if self.flag == 0:
			self.flag = 1
		else:
			self.flag = 0

	def safemove(self, t, left, right):
		if (left.c - t[1] > left.m - t[0] or right.c + t[1] > right.m + t[0]):
			return False
		else:
			return True
	

class miscan:
	left = side(3,3)
	right = side(0,0)
	nav = boat()

	def output(self):
		a = (self.left.m, self.left.c)
		print tuple(a),
		if self.nav.flag == 0:
			print "\__/\t\t",
		else:
			print "\t\t\__/",
		a = (self.right.m, self.right.c)
		print tuple(a)

	def finish(self):
		if self.right.m == self.right.c == 3:
			return True
		else:
			return False

	def start(self):
		s = []
		self.output()
		if self.nav.flag == 0:
			l = self.left.permutation()
			print l
			for i in xrange(len(l)):
				if self.nav.safemove(l[i], self.left, self.right):
						s = s.append(l[i])
			self.nav.move(s[0], self.left, self.right)
			self.output()
			
