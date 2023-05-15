import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private FlowPane flow;
    private HBox hBox,hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9, hBox10, hBox11, hBox12, hBox13, hBox14, hBox15, hBox16, hBox17;
    private VBox vBox;
    private StackPane root;
    private Label anglais, Mathématiques, Informatique, Géographie,Optionnel, Valider;
    TextField textFieldAnglais;
    TextField textFieldMathématiques;
    TextField textFieldInformatique;
    TextField textFieldGéographie;
    TextField textFieldOptionnel;

    TextField textFieldMoyenne;
    protected RadioButton radioButton1Anglais, radioButton2Anglais, radioButton3Anglais, radioButton1Mathématiques, radioButton2Mathématiques, radioButton3Mathématiques, radioButton1Informatique, radioButton2Informatique, radioButton3Informatique, radioButton1Géographie, radioButton2Géographie, radioButton3Géographie;
    private ComboBox<String> combo;
    private CheckBox checkBox;
    Button button;
    private GridPane gridPane1 , gridPane2, gridPane3, gridPane4, gridPane5, gridPane6, gridPane7, gridPane8, gridPane9, gridPane10, gridPane11;

    @Override
    public void start(Stage primaryStage){
        //addWidgetsToSceneV1();
        addWidgetsToSceneV2();
        addListeners();
        primaryStage.setTitle("Calcul de moyenne");
        //primaryStage.setScene(new Scene(vBox));
        primaryStage.setScene(new Scene(vBox, 500, 200));
        primaryStage.show();
    }

    private void addListeners(){

        button.setOnAction(new ControlButton(this));
    }

    public void initWidget(){
        flow = new FlowPane();
        hBox = new HBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
        hBox5 = new HBox();
        hBox6 = new HBox();
        hBox7 = new HBox();
        hBox8 = new HBox();
        hBox9 = new HBox();
        hBox10 = new HBox();
        hBox11 = new HBox();
        hBox12 = new HBox();
        hBox13 = new HBox();
        hBox14 = new HBox();
        hBox15 = new HBox();
        hBox16 = new HBox();
        hBox17 = new HBox();
        vBox = new VBox();
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        gridPane3 = new GridPane();
        gridPane4 = new GridPane();
        gridPane5 = new GridPane();
        gridPane6 = new GridPane();
        gridPane7 = new GridPane();
        gridPane8 = new GridPane();
        gridPane9 = new GridPane();
        gridPane10 = new GridPane();
        gridPane11 = new GridPane();
        anglais = new Label("Anglais");
        Mathématiques = new Label("Mathématiques");
        Informatique = new Label("Informatique");
        Géographie = new Label("Géographie");
        textFieldAnglais = new TextField();
        textFieldMathématiques = new TextField();
        textFieldInformatique = new TextField();
        textFieldGéographie = new TextField();
        textFieldOptionnel = new TextField();
        textFieldMoyenne = new TextField();
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();
        ToggleGroup group4 = new ToggleGroup();
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
        combo = new ComboBox<>(FXCollections.observableArrayList("Latin", "Grec", "Sport"));
        combo.getSelectionModel().select(0);
        checkBox = new CheckBox( " Prendre en considération les coefficients");
        button = new Button("Moyenne");
        Optionnel = new Label("Optionnel");
        Valider = new Label("Valider");

        radioButton1Anglais.setToggleGroup(group1);
        radioButton2Anglais.setToggleGroup(group1);
        radioButton3Anglais.setToggleGroup(group1);
        radioButton1Mathématiques.setToggleGroup(group2);
        radioButton2Mathématiques.setToggleGroup(group2);
        radioButton3Mathématiques.setToggleGroup(group2);
        radioButton1Informatique.setToggleGroup(group3);
        radioButton2Informatique.setToggleGroup(group3);
        radioButton3Informatique.setToggleGroup(group3);
        radioButton1Géographie.setToggleGroup(group4);
        radioButton2Géographie.setToggleGroup(group4);
        radioButton3Géographie.setToggleGroup(group4);
    }

    public void addWidgetsToSceneV1(){
        initWidget();
        hBox1.getChildren().addAll(anglais, textFieldAnglais,radioButton1Anglais, radioButton2Anglais, radioButton3Anglais);
        hBox2.getChildren().addAll(Mathématiques, textFieldMathématiques,radioButton1Mathématiques, radioButton2Mathématiques, radioButton3Mathématiques );
        hBox3.getChildren().addAll(Informatique, textFieldInformatique,radioButton1Informatique, radioButton2Informatique, radioButton3Informatique );
        hBox4.getChildren().addAll(Géographie, textFieldGéographie,radioButton1Géographie, radioButton2Géographie, radioButton3Géographie );
        hBox5.getChildren().addAll(combo,textFieldOptionnel, Optionnel);
        hBox6.getChildren().addAll(checkBox);
        hBox7.getChildren().addAll(button, Valider);
        hBox1.setAlignment(javafx.geometry.Pos.CENTER);
        hBox2.setAlignment(javafx.geometry.Pos.CENTER);
        hBox3.setAlignment(javafx.geometry.Pos.CENTER);
        hBox4.setAlignment(javafx.geometry.Pos.CENTER);
        hBox5.setAlignment(javafx.geometry.Pos.CENTER);
        hBox6.setAlignment(javafx.geometry.Pos.CENTER);
        hBox7.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7);

    }

    public void addWidgetsToSceneV2(){
        initWidget();
        hBox1.getChildren().addAll(anglais, textFieldAnglais);
        hBox2.getChildren().addAll(radioButton1Anglais, radioButton2Anglais, radioButton3Anglais);
        hBox3.getChildren().addAll(Mathématiques, textFieldMathématiques);
        hBox4.getChildren().addAll(radioButton1Mathématiques, radioButton2Mathématiques, radioButton3Mathématiques );
        hBox5.getChildren().addAll(Informatique, textFieldInformatique);
        hBox6.getChildren().addAll(radioButton1Informatique, radioButton2Informatique, radioButton3Informatique );
        hBox7.getChildren().addAll(Géographie, textFieldGéographie);
        hBox8.getChildren().addAll(radioButton1Géographie, radioButton2Géographie, radioButton3Géographie );
        hBox9.getChildren().addAll(combo,textFieldOptionnel, Optionnel);
        hBox10.getChildren().addAll(checkBox);
        hBox11.getChildren().addAll(button, Valider, textFieldMoyenne);
        gridPane1.getChildren().addAll(hBox1);
        gridPane2.getChildren().addAll(hBox2);
        gridPane3.getChildren().addAll(hBox3);
        gridPane4.getChildren().addAll(hBox4);
        gridPane5.getChildren().addAll(hBox5);
        gridPane6.getChildren().addAll(hBox6);
        gridPane7.getChildren().addAll(hBox7);
        gridPane8.getChildren().addAll(hBox8);
        gridPane9.getChildren().addAll(hBox9);
        gridPane10.getChildren().addAll(hBox10);
        gridPane11.getChildren().addAll(hBox11);
        hBox.getChildren().addAll(gridPane1, gridPane2);
        hBox12.getChildren().addAll(gridPane3, gridPane4);
        hBox13.getChildren().addAll(gridPane5, gridPane6);
        hBox14.getChildren().addAll(gridPane7, gridPane8);
        hBox15.getChildren().addAll(gridPane9);
        hBox16.getChildren().addAll(gridPane10);
        hBox17.getChildren().addAll(gridPane11);
        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox12.setAlignment(javafx.geometry.Pos.CENTER);
        hBox13.setAlignment(javafx.geometry.Pos.CENTER);
        hBox14.setAlignment(javafx.geometry.Pos.CENTER);
        hBox15.setAlignment(javafx.geometry.Pos.CENTER);
        hBox16.setAlignment(javafx.geometry.Pos.CENTER);
        hBox17.setAlignment(javafx.geometry.Pos.CENTER);

        vBox.getChildren().addAll(hBox, hBox12, hBox13, hBox14, hBox15, hBox16, hBox17);

    }

}
