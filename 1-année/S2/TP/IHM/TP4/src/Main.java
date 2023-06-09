import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        Model model = new Model();
        View view = new View(model);
        ControlList controlList = new ControlList(model, view);
        stage.setScene(view.initialiseView());
        stage.show();


    }
}