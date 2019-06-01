package TextFields;/**
 * Created by zhivkogeorgiev on 4/30/17.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class TxtFields extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane pan = new Pane();
        HBox hb = new HBox();
        Button b = new Button("Save");
        b.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        UndoableTextField<String> utf = new UndoableTextField<>("OriginalText");
        hb.getChildren().addAll(utf, b);
        pan.getChildren().add(hb);



        b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                utf.setValue(utf.getText());
                b.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");

            }
        });
        utf.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e) {
                if(utf.isOriginal() == false){
                    b.setStyle("-fx-font: 22 arrial; -fx-base: #ff0000;");
                }
                else b.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            }

        });

        Scene scn = new Scene(pan);
        stage.setScene(scn);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
