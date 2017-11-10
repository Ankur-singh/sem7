from flask import Flask, render_template
from flask_socketio import SocketIO
from random import random as rn
import time

app = Flask(__name__)
app.config['SECRET_KEY'] = 'Secret!'
socketio = SocketIO(app)



def rand():
	while True:
		time.sleep(1)
		data = rn()*9 + 1
		emit('response', {'data': data})	



@socketio.on('connect')
def on_connect():
	print "server connected"
	rand()
	


@socketio.on('my event')
def on_my_event(json):
	print "client connected"
	print str(json)

@app.route('/')
def user():
	return render_template( "index2.html")


if __name__ == '__main__' :
	socketio.run(app, debug=True)
