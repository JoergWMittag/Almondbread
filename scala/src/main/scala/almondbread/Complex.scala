package almondbread

case class Complex(protected val real: Double = 0, protected val imag: Double = 0) {
  lazy val abs = scala.math.sqrt(real*real + imag*imag)
  def +(that: Complex) = Complex(real + that.real, imag + that.imag)
  def *(that: Complex) = Complex(real*that.real - imag*that.imag,
                                 imag*that.real + real*that.imag)
}
