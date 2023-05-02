package control;

import javafx.animation.AnimationTimer;
import model.Model;
import view.View;

public class ControllerAnimation {

    protected Model model;
    protected View view;
    protected Controller control;
    protected AnimationTimer animator;

    public ControllerAnimation(Model model, View view, Controller control) {
        this.model = model;
        this.view = view;
        this.control = control;

        animator = new AnimationTimer(){

            @Override
            public void handle(long time) {

                if (model.getLastFrame() == -1) {
                    model.setLastFrame(time);
                }
                // if not enough time has passed from the previous call, do nothing
                else if ((time-model.getLastFrame()) < model.getFrameGap()) {
                    return;
                }
                model.setLastFrame(time);
                control.step();
            }
        };
    }

    public void start() {
        animator.start();
    }
    public void stop() {
        animator.stop();
    }
}
