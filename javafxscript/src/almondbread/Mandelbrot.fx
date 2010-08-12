package almondbread;

def width  = 80;
def height = 20;

function escapeTime(c: Complex) {
    var escapeTimeRec: function (:Complex, :Complex, :Integer): Integer;
    escapeTimeRec = function (c: Complex, z: Complex, s): Integer {
        if (s > 256 or z.abs > 4) return s;
        escapeTimeRec(c, z.mul(z).add(c), s + 1)
    }
    escapeTimeRec(c, Complex {}, 0)
}

package function eachPoint(width, height, func: function (:Integer, :Integer, :Integer)) {
    def rs = 3.0 / width;
    def is = 2.0 / height;
    for (y in [0..height-1])
        for (x in [0..width-1])
            func(x, y, escapeTime(Complex { r: -2.0 + rs*x; i: -1.0 + is*y}))
}

function run() {
    eachPoint(width, height, function (x, _, v: Integer) {
            if (x == 0) println("");
            if (v < 255) print("-")
            else print("#")
        })
}
