from flask import Flask, render_template, redirect, url_for, flash
from flask_bootstrap import Bootstrap
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField
from wtforms.validators import Required, Email
from werkzeug.security import generate_password_hash, check_password_hash
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager, UserMixin, login_required, logout_user, login_user



app = Flask(__name__)
app.config['SECRET_KEY']='thisissupposetobesecret!!'
bootstrap = Bootstrap(app)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////home/ankur/sem7/IS/app/sample.db'
db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.session_protection = 'strong'
login_manager.init_app(app)
login_manager.login_view = 'login'

################# models #############

class User(UserMixin, db.Model):
        __tablename__ = 'users'
        id = db.Column(db.Integer, primary_key = True)
        email = db.Column(db.String, unique=True, index=True)
        username = db.Column(db.String, unique=True, index=True)
        password_hash = db.Column(db.String)


        def __repr__(self):
                return '<User %r>' % self.username


################## FORMS ##############

class loginform(FlaskForm):
	name = StringField('enter your name', validators=[Required()])
	password = PasswordField('password', validators=[Required()])
	submit = SubmitField('login')

class registerform(FlaskForm):
	username = StringField('enter your username', validators=[Required()])
	email = StringField('enter your email', validators=[Required(), Email()])
	password = PasswordField('password', validators=[Required()])
	submit = SubmitField('Register')
	
################## VIEWS ##################3

@login_manager.user_loader
def load_user(user_id):
	return User.query.get(int(user_id))

@app.route('/', methods=['GET', 'POST'])
def index():
	return render_template('index.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
	form = loginform()
	if form.validate_on_submit() :
		user = User.query.filter_by(username=form.name.data).first()
		if user is not None:
			if check_password_hash(user.password_hash, form.password.data):
				login_user(user)
				return redirect(url_for('dashboard', username=user.username))
		else:	
			flash('invalid username or password!!')
			form.name.data =''
			return redirect(url_for('login'))
	return render_template('login.html', form=form)


@app.route('/register', methods=['GET','POST'])
def register():
	form = registerform()
	if form.validate_on_submit():
		new_user = User(email = form.email.data, username = form.username.data, password_hash=generate_password_hash(form.password.data))
		db.session.add(new_user)
		db.session.commit()
		return "User registered, You can login now!!" #redirect(url_for('login'))
	return render_template('register.html', form = form)

@app.route('/dashboard/<username>', methods=['GET', 'POST'])
@login_required
def dashboard(username):
	return render_template('dashboard.html', name=username)

if __name__ == '__main__':
	app.run(debug=True)
