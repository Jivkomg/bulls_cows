/**
 * Created by zhivkogeorgiev on 3/11/17.
 * Bulls and Cows
 */
// CopyPaste Incorporated
// FINISHED
// borkata the best
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BullsAndCows extends Application {
    private static final int LIMIT = 4;
    private TextField guessNumberField = new TextField("");
    private String theNumber = "";
    private TextArea history = new TextArea("");
    private Label lblWinLose = new Label("");
    private int cnt = 10;
    private Label lifes = new Label("     Lifes: " + cnt);

    Scene scn;

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();

        Button btnGuess = new Button("Guess");
        btnGuess.setDisable(true);

        Button btnStart = new Button("Start");
        guessNumberField.setDisable(true);
        history.setEditable(false);

        guessNumberField.setOnKeyPressed(event -> {
            boolean keyIsDeleteOrBackspace = event.getCode().equals(KeyCode.DELETE)
                    || event.getCode().equals(KeyCode.BACK_SPACE);

            btnGuess.setDisable(!btnGuess.isDisabled() && keyIsDeleteOrBackspace);
        });

        guessNumberField.setOnKeyTyped(event -> {
            if (!event.getCharacter().isEmpty()) {
                char ch = event.getCharacter().charAt(0);
                if (!Character.isDigit(ch)){
                    event.consume();
                    return;
                }
            }

            if (guessNumberField.getLength() == LIMIT){
                event.consume();
            }
            else if (guessNumberField.getLength() + 1 == LIMIT){
                btnGuess.setDisable(false);
            }
        });
        EventHandler<MouseEvent> btnGuessPressed = new EventHandler<MouseEvent> (){
            public void handle(MouseEvent e){
                guessing();
                btnGuess.setDisable(true);
            }
        };

        btnGuess.addEventHandler(MouseEvent.MOUSE_CLICKED, btnGuessPressed);
        btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            history.clear();
            theNumber = GeneratingRandom();
//                System.out.println(theNumber);
            guessNumberField.clear();
            guessNumberField.setDisable(false);
            btnGuess.setDisable(true);
            lblWinLose.setText("");
            lblWinLose.setLayoutX(stage.getX()/2);
            lblWinLose.setVisible(false);
            guessNumberField.requestFocus();
            cnt = 10;
            lifes.setText("     Lifes: " + cnt);

        });
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            lblWinLose.setTranslateX(scn.getWidth() / 2 - (7 * 10) / 2);
        });

        HBox hb = new HBox();
        VBox vb = new VBox();

        root.getChildren().addAll(hb, vb);
        hb.getChildren().addAll(guessNumberField, btnGuess, btnStart, lifes);
        vb.getChildren().addAll(hb, history, lblWinLose);

        scn = new Scene(root, 400, 300);
        stage.setScene(scn);
        stage.setTitle("BULLS AND COWS");
        stage.setResizable(false);
        stage.show();
    }

    private void guessing() {
        cnt --;
        lifes.setText("     Lifes: " + cnt);
        String guessNumber = guessNumberField.getText();
        String result = "";
        int bulls = 0;
        int cows = 0;

        for(int i = 0; i < LIMIT; i++){
            for(int j = 0; j < LIMIT; j ++){
                if(theNumber.charAt(i) == guessNumber.charAt(j)){
                    if(j == i){
                        bulls ++;

                    }
                    else cows ++;
                }
            }
        }

        if(bulls != 4)
        {
            if(cnt == 0) {
                this.lblWinLose.setText("YOU LOSE \n    "  + theNumber);
                lblWinLose.setVisible(true);
                guessNumberField.setDisable(true);
            }
        }
        else {
            this.lblWinLose.setText("YOU WIN");
            lblWinLose.setVisible(true);
            guessNumberField.setDisable(true);
        }

        result += guessNumber + ": " + bulls + "B" + cows + "C" + "\n";
        history.setText(history.getText() + result);
        guessNumberField.clear();

    }

    private String GeneratingRandom()
    {
        List<Integer> digits = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            digits.add(i);
        }

        Collections.shuffle(digits);

        return digits.stream()
                .limit(4)
                .map(i -> i.toString())
                .reduce("", (s1, s2) -> s1 + s2);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
