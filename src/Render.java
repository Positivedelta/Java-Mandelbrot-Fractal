//
// (c) Max van Daalen, April 2023
//

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Render extends ImageView
{
    private final int maxIterations;

    public Render(final Complex start, final Complex end, final int width, final int height, final int maxIterations)
    {
        this.maxIterations = maxIterations;

        final var plot = new WritableImage(width, height);
        final var writer = plot.getPixelWriter();

        final var range = end.minus(start);
        final var step = new Complex(range.real() / (double)width, range.imaginary() / (double)height);

        var base = new Complex(start);
        for (var y = 0; y < height; y++)
        {
            var c = new Complex(base);
            for (var x = 0; x < width; x++)
            {
                var iterations = maxIterations;
                var z = new Complex();
                while ((z.abs2() <= 4.0) && (iterations > 0))
                {
                    z = z.squared().plus(c);
                    //z = z.cubed().plus(c);
                    //z = z.quartic().plus(c);
                    iterations--;
                }

                writer.setArgb(x, y, getSmoothRgbSmooth(iterations));
                c = c.addToReal(step);
            }

            base = base.addToImaginary(step);
        }

        setImage(plot);
    }

    private final int getSmoothRgbSmooth(final int iterations)
    {
        final var t = (double)iterations / (double)maxIterations;

        // using a smooth bernstein polynomial to generate RGB
        //
        final var r = (int)(9 * (1 - t) * t * t * t * 255);
        final var g = (int)(15 * (1 - t) * (1 - t) * t * t * 255);
        final var b =  (int)(8.5 * (1 - t) * (1 - t) * (1 - t) * t * 255);

        return 0xff000000 | (r << 16) | (g << 8) | b;
    }
}
