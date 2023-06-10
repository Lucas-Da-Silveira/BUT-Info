import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class Model {
    File folder;
    ArrayList<AnimalsImage> images;

    AnimalsImage selectedImage;

    public Model() {
        setImageFromPath("C:\\Users\\Robin\\Documents\\Github\\IUT___\\S2\\IHM\\TP4\\images");
    }

    public void setImageFromPath(String path) {
        images = new ArrayList<>();
        folder = new File(path);
//        TODO : verify if the file is an image
        for (final File fileEntry : folder.listFiles()) {
            images.add(new AnimalsImage(path, fileEntry.getName(), images.size()));
        }
        selectedImage = images.get(0);
    }

    public ObservableList<AnimalsImage> getImagesList() {

        return FXCollections.observableArrayList(images);
    }

    public void setSelectedImage(AnimalsImage animalsImage) {
        selectedImage = animalsImage;
    }

    public void selectImage(int ID) {
        assert ID >= 0 && ID < images.size();
        selectedImage = images.get(ID);
    }

    public void nextImage() {
        selectImage(Math.floorMod(selectedImage.ID + 1, images.size()));
    }

    public void previousImage() {
        selectImage(Math.floorMod(selectedImage.ID - 1, images.size()));
    }

    public AnimalsImage getImage() {
        return selectedImage;
    }

}

class AnimalsImage {
    private String name;

    private String image;
    private String path;
    int ID;

    public AnimalsImage(String path, String image, int ID) {
        this.path = path;
        this.image = "\\" + image;
        this.ID = ID;
        String tmp = image.split("\\.")[0];
        name = tmp.substring(0, 1).toUpperCase() + tmp.substring(1);
    }

    public String getPath() {
        System.out.println(path + image);
        return path + image;
    }

    public String toString() {
        return this.name;
    }
}