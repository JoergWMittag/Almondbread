package almondbread;

final class Mandelbrot {
    private static final int width = 80, height = 20;

    public static final void main(final String... args) {
        eachPoint(width, height, new Action3IntIntShort() {
                public void apply(final int x, final int y, final short v) {
                    if (x == 0) {
                        System.out.println();
                    }
                    System.out.print(v < 255 ? "-" : "#");
                }
        });
        System.exit(0);
    }

    private static final short escapeTime(final Complex c) {
        return escapeTimeRecursive(c, Complex.ZERO, (short) 0);
    }

    private static final short escapeTimeRecursive(final Complex c, final Complex z, final short step) {
        if (step > 256 || z.abs > 4) {
            return step;
        }
        return escapeTimeRecursive(c, z.mul(z).add(c), (short) (step + 1));
    }

    static final void eachPoint(final int width, final int height, final Action3IntIntShort func) {
        double r = -2.0, i = -1.0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                func.apply(x, y, escapeTime(new Complex(r, i)));
                r += 3.0 / width;
            }
            r = -2.0;
            i += 2.0 / height;
        }
    }
}
