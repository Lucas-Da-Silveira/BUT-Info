import javafx.application.Application;
import javafx.stage.Stage;

public class Appli extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View(model);
        ControlButton controlButton = new ControlButton(model, view);
        ControlMenu controlMenu = new ControlMenu(view);
        view.display();
    }
}