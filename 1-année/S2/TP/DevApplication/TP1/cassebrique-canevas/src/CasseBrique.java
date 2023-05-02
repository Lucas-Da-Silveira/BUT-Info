import control.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class CasseBrique extends Application {

    private static int width;
    private static int height;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("program arguments: width [height]");
            System.err.println("if height not provided, considered to be equal to width");
        }
        try {
            width = Integer.parseInt(args[0]);
            if (args.length == 2) {
                height = Integer.parseInt(args[1]);
            }
            else {
                height = width;
            }
        }
        catch(NumberFormatException e) {
            System.err.println("program arguments: width [height]");
            System.err.println("if height not provided, considered to be equal to width");
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model(width, height, 6, 10);
        View view = new View(model);
        Controller control = new Controller(model,view);

        stage.setTitle("Casse brique");

        Scene scene = new Scene(view.getRootPane(), view.getWidth(), view.getHeight());

        stage.setScene(scene);
        stage.show();
        view.getRootPane().setFocusTraversable(true);
        view.getRootPane().requestFocus();

        control.initGame();

    }
}
