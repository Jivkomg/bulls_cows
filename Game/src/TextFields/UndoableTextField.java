package TextFields;

/**
 * Created by zhivkogeorgiev on 4/30/17.
 */
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class UndoableTextField<T> extends TextField implements IUndoableField {
    T undoValue = null;

    public UndoableTextField(T initValue){
        this();
        this.setValue(initValue);
    }


    public UndoableTextField () {
        this.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                if(e.getCode() == KeyCode.Z && e.isControlDown()){
                    undoValue();
                    e.consume();
                }
                if(e.getCode() == KeyCode.H && e.isControlDown() ){
                    System.out.println("Help");
                }
            }
        });

    }

    public void undoValue (){
        this.setValue(this.undoValue);
        this.positionCaret(this.getLength());
    }

    public void setValue(T value) {
        this.setText(value.toString());
        this.undoValue = value;
    }

    public boolean isOriginal(){
        if (this.getText().equals(this.undoValue.toString())){
            return true;
        }
        else return false;
    }



}