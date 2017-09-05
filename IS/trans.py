def create_matrix(text):
	char = list(text)
	chunks = [char[x:x+5] for x in xrange(0, len(text), 5)]
	return chunks

def create_cipher(chunks):
	cipher = ""
	for i in xrange(len(chunks[0])-1, -1 , -1):
		for j in xrange(0, len(chunks),1):
			cipher = cipher + chunks[j][i]
		
	return cipher

def create_text(chunks):
	text = ""
	for i in xrange(0, len(chunks), 1):
		for j in xrange(len(chunks[0])-1, -1, -1):
			text = text + chunks[j][i]

	return text

def encry(text):
	c = create_matrix(text)
	return create_cipher(c)

def decry(cipher):
	c = create_matrix(cipher)
	return create_text(c)


text = "ankursinghankitsinghgolus"
print "Input text : ", text
cipher = encry(text)
print "Cipher text : ", cipher
text = decry(cipher)
print "Plain text : ", text
