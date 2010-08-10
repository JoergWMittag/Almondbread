package almondbread

import scala.annotation.tailrec

object Mandelbrot {
  private val width = 80
  private val height = 20

  def main(args: Array[String]) {
    eachPoint(width, height) { (x, _, v) => {
        if (x == 0) println
        print(if (v < 255) "-" else "#")
      }}
  }

  @tailrec
  def escapeTime(c: Complex, z: Complex = Complex(), step: Int = 0): Int =
    if (step > 256 || z.abs > 4) step
    else escapeTime(c, z*z + c, step + 1)

  def eachPoint(width: Int = width, height: Int = height)(func: (Int, Int, Int) => Unit) {
    val rs = 3.0 / width
    val is = 2.0 / height
    for (y <- 0 until height; x <- 0 until width)
      func(x, y, escapeTime(Complex(-2.0 + rs*x, -1.0 + is*y)))
  }
}
