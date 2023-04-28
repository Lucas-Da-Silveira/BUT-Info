import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;




public class ControlButton extends Main implements EventHandler<ActionEvent> {
    Main app;
    public ControlButton(Main app) {
        this.app = app;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (Integer.parseInt(app.textFieldAnglais.getText()) > 20 || Integer.parseInt(app.textFieldAnglais.getText()) < 0) {
            app.textFieldAnglais.setText("Erreur");
        }
        else if (Integer.parseInt(app.textFieldMathématiques.getText()) > 20 || Integer.parseInt(app.textFieldMathématiques.getText()) < 0) {
            app.textFieldMathématiques.setText("Erreur");
        }
        else if (Integer.parseInt(app.textFieldInformatique.getText()) > 20 || Integer.parseInt(app.textFieldInformatique.getText()) < 0) {
            app.textFieldInformatique.setText("Erreur");
        }
        else if (Integer.parseInt(app.textFieldGéographie.getText()) > 20 || Integer.parseInt(app.textFieldGéographie.getText()) < 0) {
            app.textFieldGéographie.setText("Erreur");
        }
        else if (Integer.parseInt(app.textFieldOptionnel.getText()) > 20 || Integer.parseInt(app.textFieldOptionnel.getText()) < 0) {
            app.textFieldOptionnel.setText("Erreur");
        }
        else if (app.textFieldAnglais.getText().equals("Erreur") || app.textFieldMathématiques.getText().equals("Erreur") || app.textFieldInformatique.getText().equals("Erreur") || app.textFieldGéographie.getText().equals("Erreur") || app.textFieldOptionnel.getText().equals("Erreur")) {
            app.textFieldMoyenne.setText("Erreur");
        } else {
            app.textFieldMoyenne.setText(String.valueOf((Integer.parseInt(app.textFieldAnglais.getText()) + Integer.parseInt(app.textFieldMathématiques.getText()) + Integer.parseInt(app.textFieldInformatique.getText()) + Integer.parseInt(app.textFieldGéographie.getText()) + Integer.parseInt(app.textFieldOptionnel.getText())) / 5));
        }
    }
}

