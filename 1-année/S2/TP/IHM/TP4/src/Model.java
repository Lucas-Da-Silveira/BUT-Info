import javafx.beans.property.*;


import java.util.ArrayList;
import java.util.List;
public class Model {

    private List<String> imageNames;
    private IntegerProperty currentIndex;
    private IntegerProperty totalImages;
    private BooleanProperty slideShowRunning;

    public Model() {
        imageNames = new ArrayList<>();
        currentIndex = new SimpleIntegerProperty(-1);
        totalImages = new SimpleIntegerProperty(0);
        slideShowRunning = new SimpleBooleanProperty(false);
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setImageNames(List<String> imageNames) {
        this.imageNames = imageNames;
        totalImages.set(imageNames.size());
        currentIndex.set(-1);
    }

    public int getCurrentIndex() {
        return currentIndex.get();
    }

    public IntegerProperty currentIndexProperty() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex.set(currentIndex);
    }

    public int getTotalImages(){
        return totalImages.get();
    }

    public void setTotalImages(int totalImages){
        this.totalImages.set(totalImages);
    }

    public IntegerProperty totalImagesProperty(){
        return totalImages;
    }

    public boolean isSlideShowRunning(){
        return slideShowRunning.get();
    }

    public void setSlideShowRunning(boolean slideShowRunning){
        this.slideShowRunning.set(slideShowRunning);
    }
}
