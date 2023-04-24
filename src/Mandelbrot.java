//
// (c) Max van Daalen, April 2023
//

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Mandelbrot extends Application
{
    private final int PLOT_WIDTH = 1200;
    private final int PLOT_HEIGHT = 800;
    private final double ASPECT = PLOT_WIDTH / PLOT_HEIGHT;

    @Override
    public void start(final Stage primaryStage) throws Exception
    {
        // co-ordinates for the full mandelbrot set
        //
        final var topLeft = new Complex(-2.2, 1.2);
        final var bottomRight = new Complex(1.2, -1.2);
        final var maxIterations = 70;
        final var plot = new Render(topLeft, bottomRight, PLOT_WIDTH, PLOT_HEIGHT, maxIterations);

/*      final var x = 0.2929859127507;
        final var y = 0.6117848324958;
        final var radius = 4.4e-11 / 16.0;
        final var maxIterations = 10000;

        //final var x = -0.04524074130409;
        //final var y = 0.9868162207157852;
        //final var radius = 6.8e-14;
        //final var maxIterations = 900;

        //final var x = -0.04524074130409;
        //final var y = 0.9868162207157838;
        //final var radius = 2.3e-12;
        //final var maxIterations = 1400;

        final var s = new Complex(-(radius * ASPECT) + x, radius + y);
        final var e = new Complex((radius * ASPECT) + x, -radius + y);
        final var plot = new Render(s, e, PLOT_WIDTH, PLOT_HEIGHT, maxIterations); */
        final var root = new Pane();
        root.getChildren().add(plot);

        primaryStage.setTitle("Mandelbrot Set");
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
    }

    public static final void main(final String[] args)
    {
        Application.launch(args);
    }
}
