import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application {

    public void start(Stage stage) {
        Model model = new Model();
        View view = new View(model);
        ControlList controlList = new ControlList(model, view);
        ControlButton controlButton = new ControlButton(model, view);
        controlList.update();
        controlButton.update();
        stage.setScene(view.getScene());
        stage.setTitle("Diaporama");
        stage.show();


    }
}