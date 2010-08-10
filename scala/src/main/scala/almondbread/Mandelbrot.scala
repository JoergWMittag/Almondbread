package almondbread

import scala.annotation.tailrec

object Mandelbrot {
  private val width = 80
  private val height = 20

  def main(args: Array[String]) {
    eachPoint(width, height, (x, y, v) => {
        if (x == 0) println
        print(if (v < 255) "-" else "#")
      })
  }

  private def escapeTime(c: Complex) = {
    var step = 0
    var z = Complex()
    while (step < 256 && z.abs < 4) {
      z = z*z + c
      step += 1
    }
    step
  }

  @tailrec
  def escapeTime2(c: Complex, z: Complex = Complex(), step: Int = 0): Int =
    if (step > 256 || z.abs > 4) step
    else escapeTime2(c, z*z + c, step + 1)

  private def printMandelSet(width: Int = width, height: Int = height) {
    for (i <- -1.0 to 1.0 by 2.0/height) {
      for (r <- -2.0 to 1.0 by 3.0/width)
        print(if (escapeTime2(Complex(r, i)) < 255) '-' else '*')
      println
    }
  }

  // More Functional approach
  def eachPoint(width: Int = width, height: Int = height, func: (Int, Int, Int) => Unit) {
    val rs = 3.0/width
    val is = 2.0/height
    for ((ir, ii) <- (0 until height).map(y => (-1.0 + is * y, y)))
      for ((rr, ri) <- (0 until width).map(x => (-2.0 + rs * x, x)))
        func(ri, ii, escapeTime2(Complex(rr, ir)))
  }

  // More Procedural
  def eachPoint2(width: Int = width, height: Int = height, func: (Int, Int, Int) => Unit) {
    var r = -2.0
    var i = -1.0
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        func(x, y, escapeTime2(Complex(r, i)))
        r += 3.0 / width
      }
      r = -2.0
      i += 2.0 / height
    }
  }

  // Nested Iteration
  def eachPoint3(width: Int = width, height: Int = height, func: (Int, Int, Int) => Unit) {
    val rs = 3.0 / width
    val is = 2.0 / height
    for (y <- 0 until height; x <- 0 until width)
      func(x, y, escapeTime2(Complex(-2.0 + rs*x, -1.0 + is*y)))
  }
}
