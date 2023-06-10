import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class View extends Stage {
    private final Model model;
    Scene scene;
    BorderPane pane;
    MenuItem directoryBTN;
    MenuBar menuBar;
    TabPane tabPane;
    Tab diapoTab;
    Tab listTab;
    SplitPane splitPane;
    ListView<AnimalsImage> listView;
    ImageView imageViewList;
    ImageView imageViewDiapo;
    HBox buttonPane;
    Button btnFirst;
    Button btnLast;
    Button btnNext;
    Button btnPlay;
    Button btnPrevious;
    BorderPane diapoPane;
    Image image;
    FlowPane imageListPane;
    ProgressBar progressBar;
    ButtonController buttonController;
    private ListController listController;
    private DirectoryController directoryController;

    public View(Model model) {
        this.model = model;
        initWidgets();
        addMenu(this);
        addWidget();
        initController();
        setImage();

        this.setTitle("TP4 : Diaporama"); //titre de la fenêtre
        this.setScene(scene); //mettre le conteneur comme contenu de la fenêtre.
        this.setResizable(false);
        this.show();

        this.setOnCloseRequest(windowEvent -> {
            if (this.btnPlay.getText() == "Pause")
                buttonController.stopLoop();
        });
    }

    private void initWidgets() {
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 720);

        tabPane = new TabPane();
        listTab = new Tab();
        diapoTab = new Tab();
        imageViewList = new ImageView();
        imageViewDiapo = new ImageView();
        imageListPane = new FlowPane();

        splitPane = new SplitPane();
        listView = new ListView<AnimalsImage>();

        diapoPane = new BorderPane();
        progressBar = new ProgressBar();

        buttonPane = new HBox();
        btnFirst = new Button("First");
        btnPrevious = new Button("Previous");
        btnPlay = new Button("Play");
        btnNext = new Button("Next");
        btnLast = new Button("Last");

    }

    private void addMenu(Stage primarythis) {
        Menu files = new Menu("files");

        directoryBTN = new MenuItem("open directory");

        files.getItems().addAll(directoryBTN);

        menuBar = new MenuBar();
        menuBar.getMenus().add(files);

        pane.setTop(menuBar);
    }

    private void addWidget() {
        listTab.setText("Liste");
        listView.setItems(model.getImagesList());

        imageViewList.setFitWidth(960);
        imageViewList.setFitHeight(600);
        imageViewList.setPreserveRatio(true);
        imageViewDiapo.setFitWidth(1200);
        imageViewDiapo.setFitHeight(500);
        imageViewDiapo.setPreserveRatio(true);

        imageListPane.getChildren().add(imageViewList);
        imageListPane.setAlignment(Pos.CENTER);
        listView.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.2));
        splitPane.getItems().addAll(listView, imageListPane);
        splitPane.setDividerPosition(0, 0.2);
        listTab.setContent(splitPane);


        diapoTab.setText("Diaporama");
        buttonPane.setSpacing(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(btnFirst, btnPrevious, btnPlay, btnNext, btnLast);

        BorderPane.setAlignment(progressBar, Pos.CENTER);
        BorderPane.setMargin(progressBar, new Insets(15, 5, 5, 5));
        BorderPane.setMargin(imageViewDiapo, new Insets(5));
        BorderPane.setMargin(buttonPane, new Insets(5, 5, 15, 5));
        diapoPane.setTop(progressBar);
        diapoPane.setCenter(imageViewDiapo);
        diapoPane.setBottom(buttonPane);

        diapoTab.setContent(diapoPane);

        tabPane.getTabs().addAll(listTab, diapoTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        pane.setCenter(tabPane);
    }

    public void initController() {
        listController = new ListController(this,model);
        directoryController = new DirectoryController(this,model);

        buttonController = new ButtonController(this, model);
        for (Node node : buttonPane.getChildren()) {
            ((Button) node).setOnAction(buttonController);
        }
    }

    public void setImage() {
        image = new Image(model.getImage().getPath());
        imageViewList.setImage(image);
        imageViewDiapo.setImage(image);

        int ID = model.getImage().ID;
        listView.getSelectionModel().select(ID);
        progressBar.setProgress((double) ID / (model.images.size() - 1));
    }

    public void update(){
        listView.setItems(model.getImagesList());
        setImage();
    }
}
