import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javafx.scene.image.ImageView;


public class Model {

    private List<String> imageNames;
    private int currentIndex;
    private int totalImages;
    private boolean slideShowRunning;

    public Model() {
        imageNames = new ArrayList<>();
        currentIndex = -1;
        totalImages = 0;
        slideShowRunning = false;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }


}
