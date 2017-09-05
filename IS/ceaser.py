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

def encry(text):
	cipher = ''
	for letter in text:
		cipher = cipher + chr((ord(letter)*3 +2)%256)
	return cipher

def decry(cipher):
	text = ""
	mod_inverse = findModInverse(3,256)
	for letter in cipher:
		text = text + chr((ord(letter) - 2)*mod_inverse % 256)
	return text

text = "ankursinghankitsinghgolus"
print "Input text : ", text
cipher = encry(text)
print "Cipher text : ", cipher
text = decry(cipher)
print "Plain text : ", text
