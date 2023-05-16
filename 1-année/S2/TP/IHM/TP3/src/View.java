import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class View {
    private GridPane gridPane;
    private TextField tfAnglais;
    private TextField tfMath;
    private TextField tfInfo;
    private TextField tfGeo;
    private TextField tfOption;
    private CheckBox cbCoefficients;
    private Label lblMoyenneValue;
    private VBox vboxLatin;
    private VBox vboxGrec;
    private VBox vboxSport;
    private Button btnMoyenne;
    private Model model;

    public View(Model model) {
        this.model = model;
        initializeComponents();
    }

    private void initializeComponents() {

        // Set the initial values from the model
        tfAnglais.setText(Double.toString(model.getAnglais()));
        tfMath.setText(Double.toString(model.getMath()));
        tfInfo.setText(Double.toString(model.getInfo()));
        tfGeo.setText(Double.toString(model.getGeo()));
        tfOption.setText(Double.toString(model.getOption()));
        cbCoefficients.setSelected(model.useCoefficients());

        // Set the control buttons and menus
        setControlBouton();
        setControlMenu();
    }

    public <ControlBouton> void setControlBouton() {
        ControlBouton controlBouton = new ControlBouton(model, this);
        btnMoyenne.setOnAction(controlBouton);
    }

    public void setControlMenu() {
        ControlMenu controlMenu = new ControlMenu(model, this);
        cbCoefficients.setOnAction(controlMenu);
    }
}

// Rest of the View class
// ...