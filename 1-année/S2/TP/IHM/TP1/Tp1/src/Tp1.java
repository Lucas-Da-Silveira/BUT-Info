import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Tp1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private FlowPane flow;
    private HBox hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7;
    private VBox vBox;
    private StackPane root;
    private Label anglais, Mathématiques, Informatique, Géographie,Optionnel, Valider;
    private TextField textFieldAnglais, textFieldMathématiques, textFieldInformatique, textFieldGéographie, textFieldOptionnel;
    private RadioButton radioButton1Anglais, radioButton2Anglais, radioButton3Anglais, radioButton1Mathématiques, radioButton2Mathématiques, radioButton3Mathématiques, radioButton1Informatique, radioButton2Informatique, radioButton3Informatique, radioButton1Géographie, radioButton2Géographie, radioButton3Géographie;
    private ComboBox<String> combo;
    private CheckBox checkBox;
    private Button button;

    @Override
    public void start(Stage primaryStage){
        addWidgetsToSceneV1();
        primaryStage.setTitle("Calcul de moyenne");
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
    }

    public void initWidget(){
        flow = new FlowPane();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
        hBox5 = new HBox();
        hBox6 = new HBox();
        hBox7 = new HBox();
        vBox = new VBox();
        anglais = new Label("Anglais");
        Mathématiques = new Label("Mathématiques");
        Informatique = new Label("Informatique");
        Géographie = new Label("Géographie");
        textFieldAnglais = new TextField();
        textFieldMathématiques = new TextField();
        textFieldInformatique = new TextField();
        textFieldGéographie = new TextField();
        textFieldOptionnel = new TextField();
        radioButton1Anglais = new RadioButton("1");
        radioButton2Anglais = new RadioButton("2");
        radioButton3Anglais = new RadioButton("3");
        radioButton1Mathématiques = new RadioButton("1");
        radioButton2Mathématiques = new RadioButton("2");
        radioButton3Mathématiques = new RadioButton("3");
        radioButton1Informatique = new RadioButton("1");
        radioButton2Informatique = new RadioButton("2");
        radioButton3Informatique = new RadioButton("3");
        radioButton1Géographie = new RadioButton("1");
        radioButton2Géographie = new RadioButton("2");
        radioButton3Géographie = new RadioButton("3");
        combo = new ComboBox<String>(FXCollections.observableArrayList("Latin", "Grec", "Sport"));
        combo.getSelectionModel().select(0);
        checkBox = new CheckBox( " Prendre en considération les coefficients");
        button = new Button("Moyenne");
        Optionnel = new Label("Optionnel");
        Valider = new Label("Valider");
    }

    public void addWidgetsToSceneV1(){
        initWidget();
        hBox1.getChildren().addAll(anglais, textFieldAnglais,radioButton1Anglais, radioButton2Anglais, radioButton3Anglais);
        hBox2.getChildren().addAll(Mathématiques, textFieldMathématiques,radioButton1Mathématiques, radioButton2Mathématiques, radioButton3Mathématiques );
        hBox3.getChildren().addAll(Informatique, textFieldInformatique,radioButton1Informatique, radioButton2Informatique, radioButton3Informatique );
        hBox4.getChildren().addAll(Géographie, textFieldGéographie,radioButton1Géographie, radioButton2Géographie, radioButton3Géographie );
        hBox5.getChildren().addAll(combo);
        hBox6.getChildren().addAll(checkBox,textFieldOptionnel, Optionnel);
        hBox7.getChildren().addAll(button, Valider);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7);

    }



}
