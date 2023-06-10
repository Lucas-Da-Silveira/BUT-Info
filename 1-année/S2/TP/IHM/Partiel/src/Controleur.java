import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

class ControlBouton implements EventHandler<ActionEvent>{
    View view;
    Model model;

    public ControlBouton(Model model, View view) {
        this.view = view;
        this.model = model;
        view.AddContact.setOnAction(this);
        view.UpdateContact.setOnAction(this);
        view.DeleteContact.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        Button btnSource = (Button) actionEvent.getSource();
        if (btnSource == view.AddContact){
            model.setFirstName(view.FirstName.getText());
            model.setLastName(view.LastName.getText());
            model.setPhoneNumber(view.PhoneNumber.getText());
            model.addContact(new Contact(model.getFirstName(), model.getLastName(), model.getPhoneNumber(), model.getContactList()));
        }
        else if (btnSource == view.UpdateContact){
            model.setFirstName(view.FirstName.getText());
            model.setLastName(view.LastName.getText());
            model.setPhoneNumber(view.PhoneNumber.getText());
            model.updateContact(new Contact(model.getFirstName(), model.getLastName(), model.getPhoneNumber(), model.getContactList()));
        }
        else if (btnSource == view.DeleteContact){
            model.setFirstName(view.FirstName.getText());
            model.setLastName(view.LastName.getText());
            model.setPhoneNumber(view.PhoneNumber.getText());
            model.removeContact(new Contact(model.getFirstName(), model.getLastName(), model.getPhoneNumber(), model.getContactList()).getId());
        }
    }
}

class ControlList implements EventHandler<ActionEvent>{
    View view;
    Model model;
    public ControlList(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Contact contact = (Contact) ((ListView) actionEvent.getSource()).getSelectionModel().getSelectedItems().get(0);
        model.setSelectedContact(contact);
        view.setContact();
    }

}

