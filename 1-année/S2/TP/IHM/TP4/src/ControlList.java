import javafx.scene.image.Image;
public class ControlList {
    private Model model;
    private View view;
    public ControlList(Model model, View view){
        this.model = model;
        this.view = view;
        view.getListView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                model.setCurrentIndex(model.getImageNames().indexOf(newValue));
                view.getImageView().setImage(new Image("file:" + newValue));
            }
        });
    }
}