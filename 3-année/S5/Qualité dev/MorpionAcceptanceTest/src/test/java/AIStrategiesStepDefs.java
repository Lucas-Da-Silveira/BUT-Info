import io.cucumber.java.en.Then;

public class AIStrategiesStepDefs {
    @Then("the AI must play in {int}")
    public void theAIMustPlayIn(int arg0) {
        assert !TestStorage.grille.estLibre(arg0);
    }
}
