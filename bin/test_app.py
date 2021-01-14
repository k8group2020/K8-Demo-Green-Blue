import flask

app = flask.Flask(__name__)
c = 0 
@app.route("/version")
def version():
    global c
    w = {"version":"1.0.3-edge+build.40b2","release":"BLUE"}
    x = {"version":"1.0.3-stable+build.40b2","release":"GREEN"}
    y = {"version":"1.0.4-edge+build.40b2","release":"BLUE"}
    z = {"version":"1.0.4-stable+build.40b2","release":"GREEN"} 
    c += 1
    op = w
    if c > 15 and c <= 30:
        op = x
    if c > 30 and c <= 45:
        op = y
    if c > 45 and c <= 60:
        op = z
    # print(op)
    return op
if __name__ == "__main__":
    app.run()