from models import app

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')
def index():
	name = None
	form = NameForm()
	if form.validate_on_submit():
		name= form.name.data
		form.name.data = ''
	return render_template('index.html', form=form, name=name)

if __name__ == '__main__':
	app.run(debug=True)

