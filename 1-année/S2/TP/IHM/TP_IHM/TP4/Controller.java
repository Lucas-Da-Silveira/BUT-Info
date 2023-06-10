import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

class ListController implements EventHandler<MouseEvent> {
    View view;
    Model model;

    public ListController(View view, Model model) {
        this.view = view;
        this.model = model;
        view.listView.setOnMouseClicked(this);
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        AnimalsImage image = (AnimalsImage) ((ListView) mouseEvent.getSource()).getSelectionModel().getSelectedItems().get(0);
        model.setSelectedImage(image);
        view.setImage();
    }
}

class ButtonController implements EventHandler<ActionEvent> {
    View view;
    Model model;
    Timer timer;

    public ButtonController(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        Button btnSource = (Button) actionEvent.getSource();
        if (btnSource == view.btnFirst) {
            model.selectImage(0);
            view.setImage();
        } else if (btnSource == view.btnPrevious) {
            model.previousImage();
            view.setImage();
        } else if (btnSource == view.btnPlay) {
            if (btnSource.getText().equals("Play")) {
                startLoop();
            } else if (btnSource.getText().equals("Pause")) {
                stopLoop();
            }
        } else if (btnSource == view.btnNext) {
            model.nextImage();
            view.setImage();
        } else if (btnSource == view.btnLast) {
            model.selectImage(model.images.size() - 1);
            view.setImage();
        }
    }

    public void startLoop() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                model.nextImage();
                view.setImage();
            }
        }, 2000, 2000);
        view.btnPlay.setText("Pause");
    }

    public void stopLoop() {
        timer.cancel();
        timer.purge();
        view.btnPlay.setText("Play");
    }
}

class DirectoryController implements EventHandler<ActionEvent> {
    View view;
    Model model;

    public DirectoryController(View view, Model model) {
        this.view = view;
        this.model = model;
        view.directoryBTN.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File path = directoryChooser.showDialog(view);
        model.setImageFromPath(path.toString());
        view.update();
    }
}