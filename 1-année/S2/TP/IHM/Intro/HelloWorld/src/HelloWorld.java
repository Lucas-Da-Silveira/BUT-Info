import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!"); //titre de la fenêtre
        Label label = new Label("Hello World"); //créer un label
        StackPane root = new StackPane(); //créer un conteneur
        root.getChildren().add(label);      //ajouter le bouton au conteneur
        primaryStage.setScene(new Scene(root, 300, 250)); //mettre le conteneur comme contenu de la fenêtre.
        primaryStage.show();
    }
}