package view;

import javafx.geometry.Bounds;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.BrickModel;
import model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class View {

    private Model model;
    private Pane rootPane;

    // window related attributes
    private int width;
    private int height;
    // thickness of boundaries walls (needed to compute if racket does not overlap walls)
    private double wallThickness;
    // look
    private BallLook ballLook;
    private RacketLook racketLook;
    private BrickLook[][] bricks;
    private Rectangle northWall;
    private Rectangle southWall;
    private Rectangle eastWall;
    private Rectangle westWall;
    // text
    private Text result;

    public View(Model model) {

        this.model = model;
        width = model.getWidth();
        height = model.getHeight();
        wallThickness = 10;

        /* A COMPLETER :
          - creer le look de la balle
          - creer le look de la raquette
          - créer le texte de fin de partie, pour l'instant vide.
          - créer les murs au bord de la fenêtre.
         */
        ballLook = new BallLook(model.getBallModel(),2.0);
        racketLook = new RacketLook(model.getRacketModel(),2.0);
        result = new Text();
        result.setFont(new Font(20));
        result.setFill(Color.RED);
        result.setTranslateX(width/2);
        result.setTranslateY(height/2);
        result.setText("");

        northWall = new Rectangle(width, wallThickness);
        northWall.setFill(Color.BLACK);
        northWall.setTranslateX(0);
        northWall.setTranslateY(0);

        southWall = new Rectangle(width, wallThickness);
        southWall.setFill(Color.BLACK);
        southWall.setTranslateX(0);
        southWall.setTranslateY(height - wallThickness);

        eastWall = new Rectangle(wallThickness, height);
        eastWall.setFill(Color.BLACK);
        eastWall.setTranslateX(width - wallThickness);
        eastWall.setTranslateY(0);

        westWall = new Rectangle(wallThickness, height);
        westWall.setFill(Color.BLACK);
        westWall.setTranslateX(0);
        westWall.setTranslateY(0);

        rootPane = new Pane();
        rootPane.setPrefSize(width,height);


        rootPane.getChildren().addAll(northWall, southWall, eastWall, westWall);
        rootPane.getChildren().addAll(racketLook.getBody(), ballLook.getBody());
        rootPane.getChildren().add(result);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getWallThickness() {
        return wallThickness;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public BallLook getBallLook() {
        return ballLook;
    }

    public RacketLook getRacketLook() { return racketLook; }

    public Rectangle getNorthWall() {
        return northWall;
    }

    public Rectangle getSouthWall() {
        return southWall;
    }

    public Rectangle getEastWall() {
        return eastWall;
    }

    public Rectangle getWestWall() {
        return westWall;
    }

    public void update() {
        racketLook.update();
        ballLook.update();
        for(int i=0;i<model.getBrickRows();i++) {
            for (int j = 0; j < model.getBrickCols(); j++) {
                bricks[i][j].update();
            }
        }
    }

    public BrickLook[][] getBricks() {
        return bricks;
    }

    /*
    createBricks is called by the controller when it initialize a party (cf. Controller.initGame() )
     */
    public void createBricks() {
        int nbRows = model.getBrickRows();
        int nbCols = model.getBrickCols();

        /* A COMPLETER :
           - si bricks != null (i.e. la partie recommence), enlever les briques existantes du panneau racine de la scène (cf. rootPane)
           - créer les look des briques. Attention, le modèle contient leur position mais pas leur taille qu'il faut calculer/fixer.
           - ajouter le "body"  des briques au panneau racine de la scène.

         */
        if(bricks != null) {
            for(int i=0;i<nbRows;i++) {
                for (int j = 0; j < nbCols; j++) {
                    rootPane.getChildren().remove(bricks[i][j].getBody());
                }
            }
        }
        bricks = new BrickLook[nbRows][nbCols];
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                BrickModel brickModel = model.getBrickModel(i,j);
                bricks[i][j] = new BrickLook(brickModel, 2.0,3.0);
                rootPane.getChildren().add(bricks[i][j].getBody());
            }
        }
    }

    public void showWinMessage() {
        result.setText("You won");
        Bounds bt = result.getBoundsInLocal();
        result.setX((width-bt.getWidth())/2.0);
        result.setVisible(true);
    }

    public void showLostMessage() {
        result.setText("You lost");
        Bounds bt = result.getBoundsInLocal();
        result.setX((width-bt.getWidth())/2.0);
        result.setVisible(true);
    }

    public void hideResultMessage() {
        result.setVisible(false);
    }
}
