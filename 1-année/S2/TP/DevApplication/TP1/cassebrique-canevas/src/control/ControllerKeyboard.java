package control;

import javafx.event.*;
import javafx.scene.input.*;
import model.Model;
import view.View;

public class ControllerKeyboard implements EventHandler<KeyEvent> {

    protected Model model;
    protected View view;
    protected Controller control;

    public ControllerKeyboard(Model model, View view, Controller control) {
        this.model = model;
        this.view = view;
        this.control = control;
        // Attach KeyEvent listening to a Node that has focus.
        view.getRootPane().setFocusTraversable(true);
        view.getRootPane().requestFocus();
        view.getRootPane().setOnKeyPressed(this);

    }

    public void handle(KeyEvent arg0) {

        //System.out.println("kecode = "+arg0.getCode());

        if (arg0.getCode() == KeyCode.BACK_SPACE ) {
            control.stopAnimation();
            control.step();
        }
        else if (arg0.getCode() == KeyCode.LEFT ) {
            control.pushRacketLeft();
        }
        else if (arg0.getCode() == KeyCode.RIGHT ) {
            control.pushRacketRight();
        }
        else if (arg0.getCode() == KeyCode.SPACE ) {
            control.togglePauseGame();
        }
    }
}
