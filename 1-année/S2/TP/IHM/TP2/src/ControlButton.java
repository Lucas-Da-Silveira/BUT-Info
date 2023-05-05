import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControlButton implements EventHandler<ActionEvent> {
    Main main;

    public ControlButton(Main main){
        this.main = main;
    }
    @Override
    public void handle(ActionEvent event) {
        double noteAnglais = Double.parseDouble(main.textFieldAnglais.getText());
        double noteMathématiques = Double.parseDouble(main.textFieldMathématiques.getText());
        double noteInformatique = Double.parseDouble(main.textFieldInformatique.getText());
        double noteGéographie = Double.parseDouble(main.textFieldGéographie.getText());
        double noteOptionnel = Double.parseDouble(main.textFieldOptionnel.getText());

        int coeffAnglias = Integer.parseInt(main.radioButton1Anglais.getText());
        int coeffMathématiques = Integer.parseInt(main.radioButton1Mathématiques.getText());
        int coeffInformatique = Integer.parseInt(main.radioButton1Informatique.getText());
        int coeffGéographie = Integer.parseInt(main.radioButton1Géographie.getText());

        double moyenne = (noteAnglais * coeffAnglias + noteMathématiques * coeffMathématiques + noteInformatique * coeffInformatique + noteGéographie * coeffGéographie + noteOptionnel) / (coeffAnglias + coeffMathématiques + coeffInformatique + coeffGéographie);
        main.textFieldMoyenne.setText(String.valueOf(moyenne));
    }
}

