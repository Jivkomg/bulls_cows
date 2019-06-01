/**
 * Created by zhivkogeorgiev on 3/12/17.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;


public class BezierCurve extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        double  x0 = 100, y0 = 100,  // start point
                x1 = 400, y1 = 100,  // end point
                cx = 150, cy = 200;  // control point

        // #1: Create two properties to hold mouse coordinates
        DoubleProperty msx = new SimpleDoubleProperty ();
        DoubleProperty msy = new SimpleDoubleProperty ();

        // Main pane
        Pane pan = new Pane();

        // #2: Create a Quadratic Bezier curve
        QuadCurve q1 = new QuadCurve (x0, y0, cx, cy, x1, y1);
        Line l1 = new Line(x0, y0, cx, cy);
        Line l2 = new Line(x1, y1, cx, cy);

        l1.setStroke(Color.GREEN);
        l2.setStroke(Color.GREEN);

        q1.setFill (Color.gray (0.8));
        q1.setStrokeWidth (2);
        q1.setStroke (Color.RED);

        // #3: Bind the control point's coordinates to mouse position
        q1.controlXProperty().bind (msx);
        q1.controlYProperty().bind (msy);

        l1.endXProperty().bind(msx);
        l1.endYProperty().bind(msy);

        l2.endXProperty().bind(msx);
        l2.endYProperty().bind(msy);

        // #4: Observe the main panel for mouse movements and
        //     update msx/msy properties

        pan.setOnMouseMoved (new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent evt) {
                msx.setValue (evt.getSceneX());
                msy.setValue (evt.getSceneY());
            }
        });

        pan.getChildren().addAll (q1, l1, l2);
        Scene scn = new Scene (pan, 500, 500);
        stage.setScene (scn);
        stage.show();

    }
}
