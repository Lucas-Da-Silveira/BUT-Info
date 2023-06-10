import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class View extends Stage {
    private final Model model;
    int displayVersion = 2;
    Scene scene;
    BorderPane pane;
    TextField noteAnglais;
    TextField noteMaths;
    TextField noteInfo;
    TextField noteGeographie;
    ToggleGroup rbGroupAnglais;
    ToggleGroup rbGroupMaths;
    ToggleGroup rbGroupInfo;
    ToggleGroup rbGroupGeo;

    ComboBox matOpt;
    TextField noteOpt;

    Button moyenneBtn;
    Label moyenne;


    MenuItem version1;
    MenuItem version2;
    private MenuItem ccm;
    private MenuItem apropos;
    private Stage stage;
    MenuBar menuBar;
    CheckBox coeffsCheckBox;


    public int getCoeffAnglais() {
        return Integer.parseInt(((RadioButton) rbGroupAnglais.getSelectedToggle()).getText());
    }

    public int getCoeffMaths() {
        return Integer.parseInt(((RadioButton) rbGroupMaths.getSelectedToggle()).getText());
    }

    public int getCoeffGeo() {
        return Integer.parseInt(((RadioButton) rbGroupGeo.getSelectedToggle()).getText());
    }

    public int getCoeffInfo() {
        return Integer.parseInt(((RadioButton) rbGroupInfo.getSelectedToggle()).getText());
    }

    public View(Model model) {
        this.model = model;
        initWidgets();
        addMenu(stage);

        stage.setTitle("Calcule Moyenne"); //titre de la fenêtre
        stage.setScene(scene); //mettre le conteneur comme contenu de la fenêtre.
        stage.show();
    }

    public void display() {
        this.stage = new Stage();


        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icone-fenetre.gif"))));

        if (displayVersion == 1) addWidgetsToSceneV1();
        else if (displayVersion == 2) addWidgetsToSceneV2();
    }

    private void initWidgets() {
        this.stage = new Stage();
        this.pane = new BorderPane();
        this.scene = new Scene(pane, 450, 350);


        this.noteAnglais = new TextField();
        this.noteMaths = new TextField();
        this.noteGeographie = new TextField();
        this.noteInfo = new TextField();

        this.rbGroupAnglais = new ToggleGroup();
        this.rbGroupMaths = new ToggleGroup();
        this.rbGroupGeo = new ToggleGroup();
        this.rbGroupInfo = new ToggleGroup();

        ObservableList<Matiere> Matieres = FXCollections.observableArrayList();
        Matieres.addAll(
                new Matiere(2, "Latin"),
                new Matiere(1, "Sport"),
                new Matiere(2, "Grec")
        );
        this.matOpt = new ComboBox<>(Matieres);
        this.matOpt.getSelectionModel().select(0);
        this.noteOpt = new TextField();

        this.coeffsCheckBox = new CheckBox("Prendre en considération les coefficients");

        this.moyenneBtn = new Button("Calculer la moyenne");
        this.moyenne = new Label();
    }

    private void addMenu(Stage primaryStage) {
        Menu options = new Menu("Options");

        version1 = new MenuItem("Version 1");
        version2 = new MenuItem("Version 2");
        Menu aide = new Menu("aide");
        ccm = new MenuItem("Comment ça marche ?");
        apropos = new MenuItem("À propos");
        aide.getItems().addAll(ccm, apropos);

        version1.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icone-menu-version1.gif")))));
        version2.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icone-menu-version2.gif")))));
        options.getItems().addAll(version1, version2, aide);

        menuBar = new MenuBar();
        menuBar.getMenus().add(options);

        this.pane.setTop(menuBar);
    }

    public void addMenuController(EventHandler<ActionEvent> controlMenu) {
        version1.setOnAction(controlMenu);
        version2.setOnAction(controlMenu);
    }

    private void addWidgetsToSceneV1() {
        VBox root = new VBox();
        root.setSpacing(10);

        FlowPane paneAnglais = GetSimpleFlowPane();

        Label labAnglais = new Label("Anglais");
        RadioButton rbAnglais1 = new RadioButton("1");
        rbAnglais1.setSelected(true);
        rbAnglais1.setToggleGroup(rbGroupAnglais);
        RadioButton rbAnglais2 = new RadioButton("2");
        rbAnglais2.setToggleGroup(rbGroupAnglais);
        RadioButton rbAnglais3 = new RadioButton("3");
        rbAnglais3.setToggleGroup(rbGroupAnglais);
        HBox rbAnglais = new HBox(rbAnglais1, rbAnglais2, rbAnglais3);
        rbAnglais.setSpacing(10);

        paneAnglais.getChildren().addAll(labAnglais, noteAnglais, rbAnglais);

        FlowPane paneMaths = GetSimpleFlowPane();

        Label labMaths = new Label("Mathématiques");
        RadioButton rbMaths1 = new RadioButton("1");
        rbMaths1.setSelected(true);
        rbMaths1.setToggleGroup(rbGroupMaths);
        RadioButton rbMaths2 = new RadioButton("2");
        rbMaths2.setToggleGroup(rbGroupMaths);
        RadioButton rbMaths3 = new RadioButton("3");
        rbMaths3.setToggleGroup(rbGroupMaths);
        HBox rbMaths = new HBox(rbMaths1, rbMaths2, rbMaths3);
        rbMaths.setSpacing(10);

        paneMaths.getChildren().addAll(labMaths, noteMaths, rbMaths);

        FlowPane paneGeo = GetSimpleFlowPane();

        Label labGeographie = new Label("Géographie");
        RadioButton rbGeographie1 = new RadioButton("1");
        rbGeographie1.setSelected(true);
        rbGeographie1.setToggleGroup(rbGroupGeo);
        RadioButton rbGeographie2 = new RadioButton("2");
        rbGeographie2.setToggleGroup(rbGroupGeo);
        RadioButton rbGeographie3 = new RadioButton("3");
        rbGeographie3.setToggleGroup(rbGroupGeo);
        HBox rbGeographie = new HBox(rbGeographie1, rbGeographie2, rbGeographie3);
        rbGeographie.setSpacing(10);

        paneGeo.getChildren().addAll(labGeographie, noteGeographie, rbGeographie);

        FlowPane paneInfo = GetSimpleFlowPane();

        Label labInfo = new Label("Informatique");
        RadioButton rbInfo1 = new RadioButton("1");
        rbInfo1.setSelected(true);
        rbInfo1.setToggleGroup(rbGroupInfo);
        RadioButton rbInfo2 = new RadioButton("2");
        rbInfo2.setToggleGroup(rbGroupInfo);
        RadioButton rbInfo3 = new RadioButton("3");
        rbInfo3.setToggleGroup(rbGroupInfo);
        HBox rbInfo = new HBox(rbInfo1, rbInfo2, rbInfo3);
        rbInfo.setSpacing(10);

        paneInfo.getChildren().addAll(labInfo, noteInfo, rbInfo);

        FlowPane paneOpt = GetSimpleFlowPane();

        Label optLabel = new Label("Optionnel");
        paneOpt.getChildren().addAll(matOpt, noteOpt, optLabel);

//        inputs.setAlignment(Pos.CENTER);


        HBox moyenneBox = new HBox();
        Label moyenneLabel = new Label("Valeur: ");
        moyenneBox.getChildren().addAll(moyenneBtn, moyenneLabel, moyenne);
        moyenneBox.setSpacing(10);

        moyenneBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(paneAnglais, paneMaths, paneGeo, paneInfo, paneOpt, coeffsCheckBox, moyenneBox);
        root.setAlignment(Pos.CENTER);


        this.pane.setCenter(root);
    }

    private void addWidgetsToSceneV2() {
        VBox root = new VBox();
        root.setSpacing(10);

        GridPane inputs = new GridPane();
        inputs.setVgap(10);
        inputs.setHgap(15);

        Label labAnglais = new Label("Anglais");
        RadioButton rbAnglais1 = new RadioButton("1");
        rbAnglais1.setSelected(true);
        rbAnglais1.setToggleGroup(rbGroupAnglais);
        RadioButton rbAnglais2 = new RadioButton("2");
        rbAnglais2.setToggleGroup(rbGroupAnglais);
        RadioButton rbAnglais3 = new RadioButton("3");
        rbAnglais3.setToggleGroup(rbGroupAnglais);
        HBox rbAnglais = new HBox(rbAnglais1, rbAnglais2, rbAnglais3);
        rbAnglais.setSpacing(10);

        inputs.add(labAnglais, 0, 0);
        inputs.add(noteAnglais, 1, 0);
        inputs.add(rbAnglais, 2, 0);

        Label labMaths = new Label("Mathématiques");
        RadioButton rbMaths1 = new RadioButton("1");
        rbMaths1.setSelected(true);
        rbMaths1.setToggleGroup(rbGroupMaths);
        RadioButton rbMaths2 = new RadioButton("2");
        rbMaths2.setToggleGroup(rbGroupMaths);
        RadioButton rbMaths3 = new RadioButton("3");
        rbMaths3.setToggleGroup(rbGroupMaths);
        HBox rbMaths = new HBox(rbMaths1, rbMaths2, rbMaths3);
        rbMaths.setSpacing(10);

        inputs.add(labMaths, 0, 1);
        inputs.add(noteMaths, 1, 1);
        inputs.add(rbMaths, 2, 1);

        Label labGeographie = new Label("Géographie");
        RadioButton rbGeographie1 = new RadioButton("1");
        rbGeographie1.setSelected(true);
        rbGeographie1.setToggleGroup(rbGroupGeo);
        RadioButton rbGeographie2 = new RadioButton("2");
        rbGeographie2.setToggleGroup(rbGroupGeo);
        RadioButton rbGeographie3 = new RadioButton("3");
        rbGeographie3.setToggleGroup(rbGroupGeo);
        HBox rbGeographie = new HBox(rbGeographie1, rbGeographie2, rbGeographie3);
        rbGeographie.setSpacing(10);


        inputs.add(labGeographie, 0, 2);
        inputs.add(noteGeographie, 1, 2);
        inputs.add(rbGeographie, 2, 2);

        Label labInfo = new Label("Informatique");
        RadioButton rbInfo1 = new RadioButton("1");
        rbInfo1.setSelected(true);
        rbInfo1.setToggleGroup(rbGroupInfo);
        RadioButton rbInfo2 = new RadioButton("2");
        rbInfo2.setToggleGroup(rbGroupInfo);
        RadioButton rbInfo3 = new RadioButton("3");
        rbInfo3.setToggleGroup(rbGroupInfo);
        HBox rbInfo = new HBox(rbInfo1, rbInfo2, rbInfo3);
        rbInfo.setSpacing(10);

        inputs.add(labInfo, 0, 3);
        inputs.add(noteInfo, 1, 3);
        inputs.add(rbInfo, 2, 3);

        Label optLabel = new Label("Optionnel");
        inputs.add(matOpt, 0, 4);
        inputs.add(noteOpt, 1, 4);
        inputs.add(optLabel, 2, 4);

        inputs.setAlignment(Pos.CENTER);

        HBox moyenneBox = new HBox();
        Label moyenneLabel = new Label("Valeur: ");
        moyenneBox.getChildren().addAll(moyenneBtn, moyenneLabel, moyenne);
        moyenneBox.setSpacing(10);

        moyenneBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(inputs, coeffsCheckBox, moyenneBox);
        root.setAlignment(Pos.CENTER);


        this.pane.setCenter(root);
    }

    private static FlowPane GetSimpleFlowPane() {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(5, 10, 5, 10));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        return pane;
    }
}

class Matiere {
    protected int coef;
    String label;

    public Matiere(int coef, String label) {
        this.coef = coef;
        this.label = label;
    }

    public int getCoef() {
        return coef;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return this.label;
    }
}
