import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class View extends Stage {
    private final Model model;
    private ControlBouton controlBouton;
    private ControlList controlList;
    Scene scene;
    BorderPane pane;
    SplitPane splitPane;
    ListView<Contact> listView;
    BorderPane addContactPane;
    HBox addContactButton;
    HBox FirstNameBox;
    HBox LastNameBox;
    HBox PhoneNumberBox;
    HBox UpdateDelete;
    VBox Formulaire;
    VBox AddContactAll;
    Button AddContact;
    Button UpdateContact;
    Button DeleteContact;
    Label FirstName;
    Label LastName;
    Label PhoneNumber;
    TextField FirstNameField;
    TextField LastNameField;
    TextField PhoneNumberField;


    public View(Model model) {
        this.model = model;
        initWidgets();
        initControleur();
        addWidget();
        this.setTitle("Contact");
        this.setScene(scene);
        this.show();
    }

    public void display() {
        this.show();
    }

    public void initWidgets() {
        pane = new BorderPane();
        scene = new Scene(pane, 500, 400);

        splitPane = new SplitPane();
        listView = new ListView<>();

        addContactPane = new BorderPane();

        Formulaire = new VBox();
        AddContactAll = new VBox();
        addContactButton = new HBox();
        FirstNameBox = new HBox();
        LastNameBox = new HBox();
        PhoneNumberBox = new HBox();
        UpdateDelete = new HBox();

        AddContact = new Button("Add New Contact");
        UpdateContact = new Button("Update");
        DeleteContact = new Button("Delete");

        FirstName = new Label("First Name");
        LastName = new Label("Last Name");
        PhoneNumber = new Label("Phone Number");

        FirstNameField = new TextField();
        LastNameField = new TextField();
        PhoneNumberField = new TextField();

    }

    public void addWidget() {
        splitPane.getItems().add(listView);
        splitPane.setDividerPositions(1);

        Formulaire.setSpacing(10);
        Formulaire.setPadding(new Insets(10, 10, 10, 10));
        Formulaire.setAlignment(Pos.CENTER);
        Formulaire.getChildren().addAll(FirstNameBox, LastNameBox, PhoneNumberBox);

        AddContactAll.setSpacing(10);
        AddContactAll.setPadding(new Insets(10, 10, 10, 10));
        AddContactAll.getChildren().addAll(addContactButton, Formulaire, UpdateDelete);

        addContactPane.setCenter(AddContactAll);

        addContactButton.getChildren().add(AddContact);
        addContactButton.setAlignment(Pos.CENTER);
        addContactButton.setPadding(new Insets(10, 10, 10, 10));
        addContactButton.setSpacing(10);

        FirstNameBox.getChildren().addAll(FirstName, FirstNameField);
        FirstNameBox.setAlignment(Pos.CENTER);
        FirstNameBox.setPadding(new Insets(10, 10, 10, 10));
        FirstNameBox.setSpacing(10);

        LastNameBox.getChildren().addAll(LastName, LastNameField);
        LastNameBox.setAlignment(Pos.CENTER);
        LastNameBox.setPadding(new Insets(10, 10, 10, 10));
        LastNameBox.setSpacing(10);

        PhoneNumberBox.getChildren().addAll(PhoneNumber, PhoneNumberField);
        PhoneNumberBox.setAlignment(Pos.CENTER);
        PhoneNumberBox.setPadding(new Insets(10, 10, 10, 10));
        PhoneNumberBox.setSpacing(10);

        UpdateDelete.getChildren().addAll(UpdateContact, DeleteContact);
        UpdateDelete.setAlignment(Pos.CENTER_LEFT);
        UpdateDelete.setPadding(new Insets(10, 10, 10, 10));
        UpdateDelete.setSpacing(30);

        pane.setCenter(splitPane);
        pane.setRight(addContactPane);
    }

    public void initControleur() {
        controlBouton = new ControlBouton(model, this);
        controlList = new ControlList(model, this);
        for (Node node : addContactButton.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setOnAction(controlBouton);
            }
        }
        for (Node node : UpdateDelete.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setOnAction(controlBouton);
            }
        }
    }
    public void setContact() {
        listView.getItems().clear();
        for (Contact contact : model.getContacts()) {
            listView.getItems().add(contact);
        }
    }
}
