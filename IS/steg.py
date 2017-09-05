import numpy as np
import PIL 
from scipy import misc

def encry(text):
	face = np.asarray(PIL.Image.open('face.png'))
	shape = face.shape
	for i in range(len(text)):
		print (face[-1][i][0])
	print ('\n')
	face.setflags(write=1)
	text = list(text)
	for i in range(len(text)):
		face[-1][i][0] = ord(text[i])
		print (face[-1][i][0])
	print ('\n')
	#misc.imsave('face_en.png', face, format="png")
	#img = Image.fromarray(face, "RGB")
	facen = PIL.Image.fromarray(np.uint8(face))
	facen.save('facen.png')
	#face = misc.face('facen.png')
	'''
	for i in range(len(text)):
		print (facen[-1][i][0])
	print ('\n')
	'''


def decry(length):
	text = ''
	facen = np.asarray(PIL.Image.open('facen.png'))
	for i in range(length):
		text = text + chr(facen[-1][i][0])
	print (text)
