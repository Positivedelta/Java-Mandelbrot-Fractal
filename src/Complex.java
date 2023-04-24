//
// (c) Max van Daalen, April 2023
//

// note, an incomplete implementation, but all that's needed to generate the mandelbrot set
//
public class Complex
{
    private final double real, imaginary;
    private final double realSquared, imaginarySquared;

    public Complex()
    {
        this.real = 0.0;
        this.realSquared = 0.0;
        this.imaginary = 0.0;
        this.imaginarySquared = 0.0;
    }

    public Complex(final Complex complex)
    {
        this.real = complex.real;
        this.realSquared =  complex.real *  complex.real;
        this.imaginary = complex.imaginary;
        this.imaginarySquared = complex.imaginary * complex.imaginary;
    }

    public Complex(final double real, final double imaginary)
    {
        this.real = real;
        this.realSquared =  real *  real;
        this.imaginary = imaginary;
        this.imaginarySquared = imaginary * imaginary;
    }

    public final Complex plus(final Complex c)
    {
        return new Complex(real + c.real, imaginary + c.imaginary);
    }

    public final Complex minus(final Complex c)
    {
        return new Complex(real - c.real, imaginary - c.imaginary);
    }

    public final Complex addToReal(final Complex c)
    {
        return new Complex(real + c.real, imaginary);
    }

    public final Complex addToImaginary(final Complex c)
    {
        return new Complex(real, imaginary + c.imaginary);
    }

    public final double real()
    {
        return real;
    }

    public final double imaginary()
    {
        return imaginary;
    }

    public final Complex squared()
    {
        return new Complex(realSquared - imaginarySquared, 2.0 * real * imaginary);
    }

    public final Complex cubed()
    {
        final var r = (real * realSquared) - (3.0 * real * imaginarySquared);
        final var i = (3.0 * realSquared * imaginary) - (imaginarySquared * imaginary);

        return new Complex(r, i);
    }

    public final Complex quartic()
    {
        final var r = (realSquared * realSquared) - (6.0 * realSquared * imaginarySquared) + (imaginarySquared * imaginarySquared);
        final var i = (4.0 * real * realSquared * imaginary) - (4.0 * real * imaginary *  imaginarySquared);

        return new Complex(r, i);
    }

    public final double abs()
    {
        return Math.sqrt(realSquared + imaginarySquared);
    }

    public final double abs2()
    {
        return realSquared + imaginarySquared;
    }

    public final String toString()
    {
        final var sb = new StringBuilder();
        sb.append("(");
        sb.append(real);
        sb.append(", ");
        sb.append(imaginary);
        sb.append(")");

        return sb.toString();
    }
}
