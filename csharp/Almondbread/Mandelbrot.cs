using System;
using System.Numerics;
using System.Linq;

namespace Almondbread
{
    internal sealed class Mandelbrot
    {
        private const int width = 80;
        private const int height = 20;

        private static void Main(string[] args)
        {
            EachPoint(width, height, (x, y, v) =>
            {
                if (x == 0)
                {
                    Console.WriteLine();
                }
                Console.Write(v < 255 ? "-" : "#");
            });
        }

        private static short EscapeTime(Complex cc)
        {
            Func<Complex, Complex, short, short> escapeTime = null;
            escapeTime = (c, z, step) =>
            {
                if (step > 256 || Complex.Abs(z) > 4)
                {
                    return step;
                }
                return escapeTime(c, z * z + c, (short)(step + 1));
            };
            return escapeTime(cc, Complex.Zero, 0);
        }

        internal static void EachPoint(int width, int height, Action<int, int, short> func)
        {
            var r = 3.0 / width;
            var i = 2.0 / height;
            foreach (var t in from y in Enumerable.Range(0, height)
                              from x in Enumerable.Range(0, width)
                              select new { ir = -1.0 + i * y, ii = y, rr = -2.0 + r * x, ri = x })
            {
                func(t.ri, t.ii, EscapeTime(new Complex(t.rr, t.ir)));
            }
        }
    }
}
