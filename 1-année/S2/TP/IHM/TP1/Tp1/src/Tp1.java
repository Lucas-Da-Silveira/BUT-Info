import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Tp1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private FlowPane flow;
    private HBox hBox;
    private StackPane root;
    private Label anglais, Mathématiques, Informatique, Géographie, Latin, Grec, Sport;
    private TextField textFieldAnglais, textFieldMathématiques, textFieldInformatique, textFieldGéographie;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private ComboBox<String> combo;

    @Override
    public void start(Stage primaryStage){
        addWidgetsToSceneV1();
        primaryStage.setTitle("TP1");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public void initWidget(){
        flow = new FlowPane();
        hBox = new HBox();
        root = new StackPane();
        anglais = new Label("Anglais");
        Mathématiques = new Label("Mathématiques");
        Informatique = new Label("Informatique");
        Géographie = new Label("Géographie");
        Latin = new Label("Latin");
        Grec = new Label("Grec");
        Sport = new Label("Sport");
        textFieldAnglais = new TextField();
        textFieldMathématiques = new TextField();
        textFieldInformatique = new TextField();
        textFieldGéographie = new TextField();
        radioButton1 = new RadioButton("1");
        radioButton2 = new RadioButton("2");
        radioButton3 = new RadioButton("3");
        combo = new ComboBox<String>(FXCollections.observableArrayList("Latin", "Grec", "Sport"));
    }

    public void addWidgetsToSceneV1(){
        initWidget();
        hBox.getChildren().addAll(anglais, textFieldAnglais, radioButton1, radioButton2, radioButton3);


        root.getChildren().addAll(flow, hBox);
    }



}
