import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;

public class ControlButton implements EventHandler<ActionEvent> {
    Main main;
    public ControlButton(Main main){
        this.main = main;
    }
    @Override
    public void handle(ActionEvent event) {
    }
}

