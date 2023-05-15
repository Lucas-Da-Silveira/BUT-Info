import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class ControlButton implements EventHandler<ActionEvent> {
    private Main main;

    public ControlButton(Main main) {
        this.main = main;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Clic sur le bouton");

        // Récupération des notes
        String anglaisStr = main.tfAnglais.getText();
        String mathStr = main.tfMathematiques.getText();
        String infoStr = main.tfInformatique.getText();
        String geoStr = main.tfGeographie.getText();
        String optionStr = main.tfOption.getText();

        // Vérification des notes obligatoires
        if (anglaisStr.isEmpty() || mathStr.isEmpty() || infoStr.isEmpty() || geoStr.isEmpty()) {
            showErrorDialog("Veuillez saisir toutes les notes obligatoires.");
            return;
        }

        // Conversion des notes en double
        double anglais, math, info, geo, option = 0.0;
        try {
            anglais = Double.parseDouble(anglaisStr);
            math = Double.parseDouble(mathStr);
            info = Double.parseDouble(infoStr);
            geo = Double.parseDouble(geoStr);
            if (!optionStr.isEmpty()) {
                option = Double.parseDouble(optionStr);
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Veuillez saisir des valeurs numériques pour les notes.");
            return;
        }

        // Vérification des notes valides
        if (anglais < 0 || anglais > 20 || math < 0 || math > 20 || info < 0 || info > 20 || geo < 0 || geo > 20 || option < 0 || option > 20) {
            showErrorDialog("Veuillez saisir des notes comprises entre 0 et 20.");
            return;
        }

        // Vérification de la case à cocher "Prendre en compte les coefficients"
        boolean prendreEnCompteCoeff = main.cbCoeff.isSelected();

        // Récupération des coefficients
        double coeffAnglais = prendreEnCompteCoeff ? getSelectedToggleValue(main.rbAng) : 1.0;
        double coeffMath = prendreEnCompteCoeff ? getSelectedToggleValue(main.rbMath) : 1.0;
        double coeffInfo = prendreEnCompteCoeff ? getSelectedToggleValue(main.rbInfo) : 1.0;
        double coeffGeo = prendreEnCompteCoeff ? getSelectedToggleValue(main.rbGeo) : 1.0;
        double coeffOption = 0.0;
        if (main.listOption.getValue().equals("Latin") || main.listOption.getValue().equals("Grec")) {
            coeffOption = 2.0;
        } else if (main.listOption.getValue().equals("Sport")) {
            coeffOption = 1.0;
        }

        // Calcul de la somme des coefficients
        double sumCoeff = coeffAnglais + coeffMath + coeffInfo + coeffGeo;
        if (!optionStr.isEmpty()) {
            sumCoeff += coeffOption;
        }

        // Calcul de la moyenne générale
        double moyenne = (anglais * coeffAnglais + math * coeffMath + info * coeffInfo + geo * coeffGeo + option * coeffOption) / sumCoeff;

        // Affichage de la moyenne
        main.lMoy.setText(String.format("%.2f", moyenne));
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private double getSelectedToggleValue(ToggleGroup toggleGroup) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        return (double) selectedRadioButton.getUserData();
    }
}