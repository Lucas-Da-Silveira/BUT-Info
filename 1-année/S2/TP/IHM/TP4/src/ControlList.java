import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class ControlList {
    private Model model;
    private View view;
    public ControlList(Model model, View view){
        this.model = model;
        this.view = view;

        view.getListView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue )-> {
            int selectIndex = view.getListView().getSelectionModel().getSelectedIndex();
            if (selectIndex >= 0) {
                model.setCurrentIndex(selectIndex);

                String imageName = model.getImageNames().get(model.getCurrentIndex());
                Image image = new Image(imageName);
                view.getImageView().setImage(image);
            }
        });
    }
}