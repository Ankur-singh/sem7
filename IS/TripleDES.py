import os
import time
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
backend = default_backend()
key = os.urandom(16)
iv = os.urandom(8)
cipher = Cipher(algorithms.TripleDES(key), modes.CBC(iv), backend=backend)

encryptor = cipher.encryptor()
start = time.time()
ct = encryptor.update(b"aaa secret message jksajrulkejsljk jkjjsewiujhetsh kjhgytuoilkjhgf lkjhgfrtyuiopoi secret message jksajrulkejsljk jkjjsewiujhetsh kjhgytuoilkjhgf lkjhgfrtyuiopoia secret message jksajrulkejsljk jkjjsewiujhetsh kjhgytuoilkjhgf lkjhgfrtyuiopoi secret message jksajrulkejsljk jkjjsewiujhetsh kjhgytuoilkjhgf lkjhgfrtyuiopoi") + encryptor.finalize()
end = time.time()
#print ct
print "encryption execution time: "+ str(end-start)

decryptor = cipher.decryptor()
start = time.time()
decryptor.update(ct) + decryptor.finalize()
end = time.time()
print "Decryption execution time: "+ str(end-start)
