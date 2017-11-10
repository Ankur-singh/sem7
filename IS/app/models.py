from views import app
from flask_sqlalchemy import SQLAlchemy 
from flask_login import UserMixin

#app = Flask(__name__)
#app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////home/ankur/intern/flask/app/sample.db'
db = SQLAlchemy(app)

class User(UserMixin, db.Model):
	__tablename__ = 'users'
	user_id = db.Column(db.Integer, primary_key = True)
	email = db.Column(db.String, unique=True, index=True)
	username = db.Column(db.String, unique=True, index=True)
	password_hash = db.Column(db.String)

	def __repr__(self):
		return '<User %r>' % self.username
	 
#if __name__ == '__main__':
#	app.run(debug=True)
