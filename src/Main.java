import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Animation");
        primaryStage.setResizable(false);

        jour(root);
        nuit(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void jour(Group root){
        Rectangle bg = new Rectangle(0,0,400,400);
        bg.setFill(Color.LIGHTGRAY);
        root.getChildren().add(bg);

        Group maisonGroup = maison();
        maisonGroup.setTranslateX(-400);
        root.getChildren().add(maisonGroup);

        Polygon toit = new Polygon(50,0, 0,100, 100,100);
        Rectangle maison = new Rectangle(10,10,200,100);
        Rectangle fenetre = new Rectangle(10,10,200,100);
        Rectangle porte = new Rectangle(10,10,200,100);
        Line[] fenetreLigne = {new Line(0,0,200,200),new Line(0,0,200,200)};
        Arc[] oiseau1 = {new Arc(50,50,50,50,0,90),new Arc(50,50,50,50,0,90)};
        Arc[] oiseau2 = {new Arc(50,50,50,50,0,90),new Arc(50,50,50,50,0,90)};

        Circle soleil = new Circle(350,50, 25);
        soleil.setFill(Color.YELLOW);
        root.getChildren().add(soleil);
    }

    public void nuit(Group root){
        Rectangle bg = new Rectangle(400,0,400,400);
        bg.setFill(Color.BLACK);
        root.getChildren().add(bg);

        root.getChildren().add(maison());

        Circle lune = new Circle(750,50, 25);
        Stop[] stops = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.BLACK),};
        LinearGradient gradient = new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE, stops);
        lune.setFill(gradient);
        root.getChildren().add(lune);
    }

    public Group maison(){
        Group maisonGroup = new Group();
        Rectangle maison = new Rectangle(525,150,150,100);
        maison.setFill(Color.BEIGE);
        maisonGroup.getChildren().add(maison);

        Polygon toit = new Polygon(505,160, 600,80, 695,160);
        toit.setFill(Color.BROWN);
        maisonGroup.getChildren().add(toit);

        Rectangle fenetre = new Rectangle(600,175,50,25);
        fenetre.setStrokeWidth(2);
        fenetre.setStroke(Color.LIGHTGRAY);
        fenetre.setFill(Color.AQUA);
        maisonGroup.getChildren().add(fenetre);

        Line[] fenetreLigne = {new Line(625,175,625,200),new Line(600,187,650,187)};
        for(int i=0;i<fenetreLigne.length;i++){
            fenetreLigne[i].setStrokeWidth(2);
            fenetreLigne[i].setStroke(Color.LIGHTGRAY);
            maisonGroup.getChildren().add(fenetreLigne[i]);
        }

        Rectangle porte = new Rectangle(540,200,25,50);
        porte.setFill(Color.SADDLEBROWN);
        maisonGroup.getChildren().add(porte);

        Circle handle = new Circle(560,225, 5);
        handle.setFill(Color.YELLOWGREEN);
        maisonGroup.getChildren().add(handle);

        maisonGroup.setScaleX(0.75);
        maisonGroup.setScaleY(0.75);

        return maisonGroup;
    }
}
