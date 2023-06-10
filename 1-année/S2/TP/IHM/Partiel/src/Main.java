import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View(model);
        ControlBouton controlBouton = new ControlBouton(model, view);
        ControlList controlList = new ControlList(model, view);
        view.display();
    }
}
