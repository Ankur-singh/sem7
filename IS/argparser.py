import argparse
parser = argparse.ArgumentParser()
parser.add_argument("-d", help="decryption", action="store_true")
parser.add_argument("-e", help="encryption", action="store_true")
args = parser.parse_args()

if args.d:
	print "decryption"
