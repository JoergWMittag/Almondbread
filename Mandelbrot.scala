package sandelbrot

object Mandelbrot {

  def main(args: Array[String]) {
    
    each_point(80, 20, (x, y, v) => {
        if (x==0) println
        if (v < 255) print("-") else print("#")
    })
  }
  
  def escapeTime(c: Complex) = {
    var step = 0
    var z = Complex()
    while (step < 256 && z.abs < 4) {
      z = (z * z) + c
      step += 1
    }
    step
  }
  
  def printMandelSet {
    for (i <- -1.0 to 1.0 by 2.0/20.0) {
      for (r <- -2.0 to 1.0 by 3.0/80.0)
        print(if (escapeTime(Complex(r,i)) < 255) '-' else '*')
      println
    }
  }
  // More Functional approach
  def each_point(width:Int, height:Int, func: (Int, Int, Int) => Unit) {
    val rs = 3.0/width
    val is = 2.0/height
    for ((ir, ii) <- (0 to height).map(y => (-1.0 + is * y, y)))
        for ((rr, ri) <- (0 to width).map(x => (-2.0 + rs * x, x)))
            func(ri, ii, escapeTime(Complex(rr, ir)))
  }

  // More Procedural
  def foreach_point(width:Int, height:Int, func: (Int, Int, Int) => Unit) {
    var r = -2.0
    var i = -1.0
    for (y <- 0 to height) {
        for (x <- 0 to width) {
            func(x, y, escapeTime(Complex(r, i)))
            r += (3.0/width)
        }
      r = -2.0
      i += (2.0/height)
    }
  }  


}
