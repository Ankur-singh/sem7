from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////home/ankur/intern/flask/app/sample.db'
db = SQLAlchemy(app)

class Example(db.Model):
	__tablename__ = 'example2'
	id = db.Column(db.Integer, primary_key=True)

db.create_all()
