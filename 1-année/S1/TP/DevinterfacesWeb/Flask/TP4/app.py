from flask import Flask, request, render_template, redirect, url_for, abort  # application WSGI

# (interface de serveur web python)
# comportements et méthodes d'un serveur web


app = Flask(__name__)    # instance de classe Flask (en paramètre le nom du module)

@app.route('/')
@app.route('/page1')
def show_page1():
     return render_template('TP1/page1.html')

@app.route('/page2')
def show_page2():
     return render_template('TP1/page2.html')

@app.route('/page3')
def show_page3():
     return render_template('TP1/page3.html')

if __name__ == '__main__':
    app.run(debug=True, port=5000)