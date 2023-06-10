import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;

class ControlButton implements EventHandler<ActionEvent> {

    private Model model;
    private View view;
    private Alert alert;

    public ControlButton(Model model, View view) {

        this.model = model;
        this.view = view;

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");

        this.view.moyenneBtn.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        float noteOpt;
        try {
            model.setNoteAnglais(Float.parseFloat(view.noteAnglais.getText()));
            model.setNoteMaths(Float.parseFloat(view.noteMaths.getText()));
            model.setNoteGeo(Float.parseFloat(view.noteGeographie.getText()));
            model.setNoteInfo(Float.parseFloat(view.noteInfo.getText()));
            model.setCoeffAnglais(view.getCoeffAnglais());
            model.setCoeffMaths(view.getCoeffMaths());
            model.setCoeffGeo(view.getCoeffGeo());
            model.setCoeffInfo(view.getCoeffInfo());

            model.setCoeffActive(view.coeffsCheckBox.isSelected());
            try {
                noteOpt = Float.parseFloat(view.noteOpt.getText());
            } catch (NumberFormatException ex) {
                noteOpt = 0;
            }

            if (noteOpt != 0) {

                model.setNoteOption(noteOpt);
                model.setMatiereOption((Matiere) view.matOpt.getValue());

            } else {
                model.setNoteOption(noteOpt);
                model.setMatiereOption(null);
            }

        } catch (NoteOutOfBound | CoeffOutOfBound | NumOptionOutOfBound e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }

        view.moyenne.setText(Float.toString(model.calculerMoyenne()));
    }
}


class ControlMenu implements EventHandler<ActionEvent> {

    private View view;

    public ControlMenu(View view) {
        this.view = view;
        view.addMenuController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == view.version1) {
            view.displayVersion = 1;
            view.display();
            System.out.println("test V1");
        } else if (actionEvent.getSource() == view.version2) {
            view.displayVersion = 2;
            view.display();
            System.out.println("test V2");
        }
    }
}