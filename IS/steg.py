import numpy as np
from PIL import Image
from scipy import misc

def encry(text):
	face = misc.face('face.png')
	shape = face.shape
	text = list(text)
	for i in range(len(text)):
		face[0][i][0] = ord(text[i])
		print (face[0][i][0])
	#misc.imsave('face_en.png', face, format="png")
	img = Image.fromarray(face, "RGB")
	img.save('face_en.png')
	face = misc.face('face_en.png')
	for i in range(len(text)):
		print (face[0][i][0])
	


def decry(length):
	text = ''
	face = misc.face('face_en.png')
	shape = face.shape
	for i in range(length):
		text = text + chr(face[0][i][0])
	print (text)
