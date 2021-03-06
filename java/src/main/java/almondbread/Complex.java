package almondbread;

final class Complex {
    final static Complex ZERO = new Complex();
    final double real, imag, abs;

    Complex(final double real, final double imag) {
        this.real = real;
        this.imag = imag;
        this.abs = Math.sqrt(real * real + imag * imag);
    }

    Complex() {
        this.real = 0;
        this.imag = 0;
        this.abs = 0;
    }

    final Complex add(final Complex other) {
        return new Complex(real + other.real, imag + other.imag);
    }

    final Complex mul(final Complex other) {
        return new Complex(real * other.real - imag * other.imag,
                imag * other.real + real * other.imag);
    }
}
