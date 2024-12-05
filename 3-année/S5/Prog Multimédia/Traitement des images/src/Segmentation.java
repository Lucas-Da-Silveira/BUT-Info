import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class Segmentation extends Application {

    static {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
    }

    public Image inputImage;
    public Image outputImage;
    ImageView inputImageView;
    ImageView outputImageView;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Récupération du chemin vers l'image depuis les arguments du programme
        String imagePath = getParameters().getRaw().get(0);

        // Chargement de l'image au format PNG (ou autre)
        inputImage = new Image("file:" + imagePath, 600, 0, true, true);
        outputImage = new Image("file:" + imagePath, 600, 0, true, true);

        Mat ocvInputImage = imageToMat(inputImage);
        Mat ocvMaskImage = ocvInputImage.clone();

        Imgproc.cvtColor(ocvInputImage, ocvInputImage, Imgproc.COLOR_BGR2HSV);

        System.out.println("ocvInputImage dims : " + ocvInputImage.dims() + "/" + ocvInputImage.channels() + " --> " + ocvInputImage.rows() + ":" + ocvInputImage.cols());

        // split the hsv image into 3 channels
        List<Mat> channels = new ArrayList<>();
        Core.split(ocvInputImage, channels);

        Scalar rougeMin = new Scalar(160, 150, 0);
        Scalar rougeMax = new Scalar(180, 255, 255);


        Core.inRange(ocvInputImage, rougeMin, rougeMax, ocvMaskImage);

        int okPixels = Core.countNonZero(ocvMaskImage);
        System.out.println("okPixels : " + okPixels + " / " + ocvMaskImage.total() + " = " + (okPixels * 100.0 / ocvMaskImage.total()) + "%");

        outputImage = mat2Image(ocvMaskImage);

        outputImageView = new ImageView(outputImage);
        //outputImageView.setFitWidth(600);
        outputImageView.preserveRatioProperty().set(true);

        inputImageView = new ImageView(inputImage);
        //inputImageView.setFitWidth(600);
        inputImageView.preserveRatioProperty().set(true);
        // Création d'une mise en page pour placer les éléments ImageView
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));
        imageBox.setSpacing(10);
        imageBox.getChildren().addAll(inputImageView, outputImageView);

        // Création de la scène et ajout de la mise en page
        Scene scene = new Scene(imageBox);

        //mouse event
        //Creating the mouse event handler
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                System.out.println("Mouse event detected");
                System.out.println("X : " + e.getX() + " / Y : " + e.getY());
                Color color = inputImageView.getImage().getPixelReader().getColor((int) e.getX(), (int) e.getY());
                System.out.println("Color : " + inputImageView.getImage().getPixelReader().getColor((int) e.getX(), (int) e.getY()));
                outputImage = processImage(inputImage, color);
                outputImageView.setImage(outputImage);
            }
        };

        inputImageView.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);

        //pour un affichage plus net
        inputImageView.setSmooth(true);

        // Configuration de la fenêtre principale
        primaryStage.setTitle("Sélection par couleur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Image processImage(Image input, javafx.scene.paint.Color color) {
        Mat ocvInputImage = imageToMat(input);
        Mat ocvMaskImage = ocvInputImage.clone();

        Imgproc.cvtColor(ocvInputImage, ocvInputImage, Imgproc.COLOR_BGR2HSV);

        System.out.println("ocvInputImage dims : " + ocvInputImage.dims() + "/" + ocvInputImage.channels() + " --> " + ocvInputImage.rows() + ":" + ocvInputImage.cols());

        // split the hsv image into 3 channels
        List<Mat> channels = new ArrayList<>();
        Core.split(ocvInputImage, channels);

        float[] hsl = new float[3];
        int red = (int) (255 * color.getRed());
        int green = (int) (255 * color.getGreen());
        int blue = (int) (255 * color.getBlue());

        RgbToHsl(red, green, blue, hsl);
        Scalar colorPicked = new Scalar(hsl[0], hsl[1], hsl[2]);

        int hmin = Math.max((int) (hsl[0] / 2 + -5), 0);
        int hmax = Math.min((int) (hsl[0] / 2 + 5), 180);

        int smin = Math.max((int) (hsl[1] * 255 - 30), 0);
        int smax = Math.min((int) (hsl[1] * 255 + 30), 255);

        Scalar colorMin = new Scalar(hmin, smin, 0);
        Scalar colorMax = new Scalar(hmax, smax, 255);

        //Scalar colorMin = new Scalar(hmin, 150, 0);
        //Scalar colorMax = new Scalar(hmax, 255, 255);

        System.out.println("RGB : " + red + "/" + green + "/" + blue);
        System.out.println("HSL : " + hsl[0] / 2 + "/" + hsl[1] * 255 + "/" + hsl[2] * 255);
        System.out.println("hmin : " + hmin + " / hmax : " + hmax);


        Core.inRange(ocvInputImage, colorMin, colorMax, ocvMaskImage);

        int okPixels = Core.countNonZero(ocvMaskImage);
        System.out.println("okPixels : " + okPixels + " / " + ocvMaskImage.total() + " = " + (okPixels * 100.0 / ocvMaskImage.total()) + "%");
        return mat2Image(ocvMaskImage);
    }

    public static void RgbToHsl(int red, int green, int blue, float hsl[]) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;
        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float f;/*from  w  ww. j ava2s  . co  m*/
        if (max == min)
            f = 0;
        else if (max == r && g >= b)
            f = (60 * (g - b)) / (max - min);
        else if (max == r && g < b)
            f = 360 + (60 * (g - b)) / (max - min);
        else if (max == g)
            f = 120 + (60 * (b - r)) / (max - min);
        else if (max == b)
            f = 240F + (60F * (r - g)) / (max - min);
        else
            f = 0;
        float f1 = (max + min) / 2;
        float f2;
        if (f1 != 0 && max != min) {
            if (0 < f1 && f1 <= 0.5) {
                f2 = (max - min) / (max + min);
            } else if (f1 == 0.5) {
                f2 = (max - min) / (2.0F - (max + min));
            } else {
                f2 = 0;
            }
        } else {
            f2 = 0.0F;
        }
        hsl[0] = f;
        hsl[1] = f2;
        hsl[2] = f1;
    }

    public static void RgbToHsl(int color, float hsl[]) {
        RgbToHsl(0xff & color >>> 16, 0xff & color >>> 8, color & 0xff, hsl);
    }

    /**
     * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
     *
     * @param frame the {@link Mat} representing the current frame
     * @return the {@link Image} to show
     */
    public static Image mat2Image(Mat frame) {
        try {
            return SwingFXUtils.toFXImage(matToBufferedImage(frame), null);
        } catch (Exception e) {
            System.err.println("Cannot convert the Mat object: " + e);
            return null;
        }
    }

    /**
     * Convert a Image FX object in the corresponding Mat object (OpenCV)
     *
     * @param image the javaFx image to be converted
     * @return the opencv Mat representation of the image
     */
    public static Mat imageToMat(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        byte[] buffer = new byte[width * height * 4];

        PixelReader reader = image.getPixelReader();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);

        Mat mat = new Mat(height, width, CvType.CV_8UC4);
        mat.put(0, 0, buffer);
        return mat;
    }

    /**
     * Support for the {@link ()} method
     *
     * @param original the {@link Mat} object in BGR or grayscale
     * @return the corresponding {@link BufferedImage}
     */
    private static BufferedImage matToBufferedImage(Mat original) {
        // init
        BufferedImage image = null;
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);

        if (original.channels() > 1) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        } else {
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return image;
    }

    public static void main(String[] args) {
        //OpenCV.loadShared();
        // Vérification que le chemin vers l'image est spécifié en argument
        if (args.length == 0) {
            System.out.println("Veuillez spécifier le chemin vers l'image en argument.");
            System.exit(0);
        }
        launch(args);
    }
}

