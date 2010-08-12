package almondbread;

import javafx.util.Math;

package class Complex {
    package var r = 0.0;
    package var i = 0.0;
    package def abs = Math.sqrt(r * r + i * i);

    package function add(other: Complex) {
        Complex {
            r: r + other.r
            i: i + other.i
        }
    }

    package function mul(other: Complex) {
        Complex {
            r: r * other.r - i * other.i
            i: i * other.r + r * other.i
        }
    }
}
