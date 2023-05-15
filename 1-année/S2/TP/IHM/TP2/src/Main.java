import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.*;
public class Main extends Application {

    public ToggleGroup rbInfo;
    public ToggleGroup rbGeo;
    public ToggleGroup rbMath;
    public ToggleGroup rbAng;
    protected TextField tfAnglais;
    protected TextField tfMathematiques;
    protected TextField tfInformatique;
    protected TextField tfGeographie;
    protected TextField tfOption;
    protected RadioButton rbAng1;
    protected RadioButton rbAng2;
    protected RadioButton rbAng3;
    protected RadioButton rbMat1;
    protected RadioButton rbMat2;
    protected RadioButton rbMat3;
    protected RadioButton rbInfo1;
    protected RadioButton rbInfo2;
    protected RadioButton rbInfo3;
    protected RadioButton rbGeo1;
    protected RadioButton rbGeo2;
    protected RadioButton rbGeo3;
    protected ComboBox<String> listOption;
    protected Label lMoy;
    protected CheckBox cbCoeff;
    private Button btnMoyenne;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Application");
        initWidgets();
        addWidgetsToSceneV2();
        addListeners();

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(2));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(addWidgetsToSceneV2(), lMoy);

        primaryStage.setResizable(false);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addListeners() {
        btnMoyenne.setOnAction(new ControlButton(this));
    }


    private void initWidgets() {
        tfAnglais = new TextField();
        tfMathematiques = new TextField();
        tfInformatique = new TextField();
        tfGeographie = new TextField();
        tfOption = new TextField();
        rbAng1 = new RadioButton("1");
        rbAng2 = new RadioButton("2");
        rbAng3 = new RadioButton("3");
        rbMat1 = new RadioButton("1");
        rbMat2 = new RadioButton("2");
        rbMat3 = new RadioButton("3");
        rbInfo1 = new RadioButton("1");
        rbInfo2 = new RadioButton("2");
        rbInfo3 = new RadioButton("3");
        rbGeo1 = new RadioButton("1");
        rbGeo2 = new RadioButton("2");
        rbGeo3 = new RadioButton("3");
        listOption = new ComboBox<>();
        lMoy = new Label("Valeur");
        btnMoyenne = new Button("Moyenne");
    }

    private VBox addWidgetsToSceneV2() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(2));
        vbox.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lAnglais = new Label("Anglais :");
        Label lMathematiques = new Label("Mathématiques :");
        Label lInformatique = new Label("Informatique :");
        Label lGeographie = new Label("Géographie :");
        Label lOption = new Label("Option :");

        HBox hboxAnglais = createRadioButtonsHBox(rbAng1, rbAng2, rbAng3);
        HBox hboxMathematiques = createRadioButtonsHBox(rbMat1, rbMat2, rbMat3);
        HBox hboxInformatique = createRadioButtonsHBox(rbInfo1, rbInfo2, rbInfo3);
        HBox hboxGeographie = createRadioButtonsHBox(rbGeo1, rbGeo2, rbGeo3);

        gridPane.add(lAnglais, 0, 0);
        gridPane.add(tfAnglais, 1, 0);
        gridPane.add(hboxAnglais, 2, 0);

        gridPane.add(lMathematiques, 0, 1);
        gridPane.add(tfMathematiques, 1, 1);
        gridPane.add(hboxMathematiques, 2, 1);

        gridPane.add(lInformatique, 0, 2);
        gridPane.add(tfInformatique, 1, 2);
        gridPane.add(hboxInformatique, 2, 2);

        gridPane.add(lGeographie, 0, 3);
        gridPane.add(tfGeographie, 1, 3);
        gridPane.add(hboxGeographie, 2, 3);

        gridPane.add(lOption , 0, 4);
        gridPane.add(tfOption, 1, 4);
        gridPane.add(listOption, 2, 4);;

        listOption.getItems().addAll("Latin", "Grec", "Sport");
        listOption.getSelectionModel().selectFirst();

        HBox hboxMoyenne = new HBox(10);
        hboxMoyenne.getChildren().addAll(btnMoyenne,lMoy);
        hboxMoyenne.setAlignment(Pos.CENTER);

        cbCoeff = new CheckBox("Prendre en compte les coefficients");
        HBox hboxCb = new HBox(10);
        hboxCb.getChildren().add(cbCoeff);
        hboxCb.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(gridPane,hboxCb, hboxMoyenne);
        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
        return vbox;
    }

    private HBox createRadioButtonsHBox(RadioButton rb1, RadioButton rb2, RadioButton rb3) {
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        hbox.getChildren().addAll(rb1, rb2, rb3);
        return hbox;
    }

}