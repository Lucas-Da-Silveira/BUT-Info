import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class Model {

    private final List<String> imageNames;
    private int currentIndex;
    private int totalImages;
    private boolean slideShowRunning;

    public Model() {
        imageNames = new ArrayList<>();
        currentIndex = -1;
        totalImages = 0;
        slideShowRunning = false;

        File folder = new File("images");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                imageNames.add(file.getName());
                totalImages++;
            }
        }
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }


}
