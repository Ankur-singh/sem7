import os, sys

def gcd(a, b):
	while a != 0:
		a,b = b%a, a
	return b

def findModInverse(a, m):
	if gcd(a, m) != 1:
		return None # no mod inverse exists if a & m aren't relatively prime
	u1, u2, u3 = 1, 0, a
	v1, v2, v3 = 0, 1, m
	while v3 != 0:
		q = u3 // v3 # // is the integer division operator
		v1, v2, v3, u1, u2, u3 = (u1 - q * v1), (u2 - q * v2), (u3 - q * v3),v1, v2, v3
	return u1 % m

def encry(filename):
	if os.path.exists(filename):
		fd = open(filename, 'r')
		content = fd.read()
		fd.close()
		en_content = ''
		for letter in content:
			en_content = en_content + chr((ord(letter)*3 +2)%256)
		fd = open(filename+'.en', 'w')
		fd.write(en_content)
		fd.close()
		print 'file with name %s.en created' %filename
			
	else:
		print 'File %s does not exist' %filename
		#sys.exit()

def decry(filename):
	if os.path.exists(filename):
		fd = open(filename, 'r')
		content = fd.read()
		fd.close()
		de_content = ''
		mod_inverse = findModInverse(3, 256)
		for letter in content:
			de_content = de_content + chr((ord(letter)-2) * mod_inverse % 256)
		fd = open(filename, 'w')
		fd.write(de_content)
		fd.close()
		print 'file with name %s decrypted' %filename
			
	else:
		print 'File %s does not exist' %filename
		#sys.exit()

import argparse
parser = argparse.ArgumentParser()
parser.add_argument("-d", help="decrypt the input file", action="store_true")		
parser.add_argument("-e", help="encrypt the input file", action="store_true")		
parser.add_argument("inputfile")
parser.add_argument("-foo", help="number of time to encry/decry", action="store", default=1, type=int, choices=xrange(0,10))
args = parser.parse_args()

if args.d and args.inputfile:
	decry(args.inputfile)

if args.e and args.inputfile:
	encry(args.inputfile)


