import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {

    private TabPane tabPane;
    private ListView<String> listView;
    private ImageView imageView;
    private Button firstButton;
    private Button previousButton;
    private Button nextButton;
    private Button lastButton;
    private ProgressBar progressBar;

    public View(Model model) {
        tabPane = new TabPane();
        listView = new ListView<>();
        imageView = new ImageView();
        firstButton = new Button("First");
        previousButton = new Button("Previous");
        nextButton = new Button("Next");
        lastButton = new Button("Last");
        progressBar = new ProgressBar();
    }

    public Scene initialiseView(){
        BorderPane borderPane = new BorderPane();
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.getItems().addAll(listView, imageView);
        splitPane.setDividerPositions(0.3);

        VBox controlBox = new VBox();
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(firstButton, previousButton, nextButton, lastButton);
        controlBox.getChildren().addAll(buttonBox, progressBar);
        controlBox.setSpacing(10);

        Tab ListViewTab = new Tab("List View");
        ListViewTab.setContent(splitPane);
        Tab ControlTab = new Tab("Control");
        ControlTab.setContent(controlBox);

        tabPane.getTabs().addAll(ListViewTab, ControlTab);

        BorderPane.setMargin(tabPane, new javafx.geometry.Insets(10));
        borderPane.setCenter(tabPane);

        Scene scene = new Scene(borderPane, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        return scene;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Button getFirstButton() {
        return firstButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getLastButton() {
        return lastButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
