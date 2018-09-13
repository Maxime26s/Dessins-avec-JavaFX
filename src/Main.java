import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        Text jour = new Text(165,300, "Jour");
        jour.setFill(Color.BLACK);
        jour.setFont(new Font("Arial", 40));
        root.getChildren().add(jour);

        Group maisonGroup = maison(true);
        maisonGroup.setTranslateX(-400);
        root.getChildren().add(maisonGroup);

        Group[] oiseau = {oiseau(),oiseau()};
        oiseau[0].setTranslateX(25);
        oiseau[0].setTranslateY(25);
        root.getChildren().add(oiseau[0]);
        oiseau[1].setTranslateX(155);
        oiseau[1].setTranslateY(15);
        root.getChildren().add(oiseau[1]);

        Group lignes = new Group();

        Line[] ligne = {new Line(315,15,385,85),new Line(315,85, 385,15),new Line(310,50, 390,50),new Line(350,10, 350,90)};
        for (int i=0;i<ligne.length;i++) {
            ligne[i].setStrokeWidth(1);
            ligne[i].setStroke(Color.YELLOW);
            lignes.getChildren().add(ligne[i]);
        }
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), lignes);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(180);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();
        root.getChildren().add(lignes);

        Circle soleil = new Circle(350,50, 30);
        soleil.setFill(Color.YELLOW);
        soleil.setStrokeWidth(5);
        soleil.setStroke(Color.LIGHTGRAY);
        FillTransition trans = new FillTransition(Duration.seconds(2), soleil);
        trans.setToValue(Color.ORANGE);
        trans.setCycleCount(Timeline.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();
        root.getChildren().add(soleil);
    }

    public void nuit(Group root){
        Rectangle bg = new Rectangle(400,0,400,400);
        bg.setFill(Color.BLACK);
        root.getChildren().add(bg);

        Text nuit = new Text(565,300, "Nuit");
        nuit.setFill(Color.WHITE);
        nuit.setFont(new Font("Arial", 40));
        root.getChildren().add(nuit);

        root.getChildren().add(maison(false));

        Polygon[] etoile = new Polygon[4];
        for(int i=0;i<etoile.length;i++){
            etoile[i]=new Polygon(75,0, 100,50, 150,50, 110,85, 125,125, 75,100, 25,125, 40,85, 0,50, 50,50);
            etoile[i].setFill(Color.YELLOW);
            etoile[i].setScaleX(0.15);
            etoile[i].setScaleY(0.15);
            etoile[i].setTranslateX(400+25*i);
            etoile[i].setTranslateY(10*(int)(Math.random()*10)-30);
            FadeTransition fade = new FadeTransition(Duration.seconds(i+0.5), etoile[i]);
            fade.setFromValue(1.0);
            fade.setToValue(0);
            fade.setAutoReverse(true);
            fade.setCycleCount(Timeline.INDEFINITE);
            fade.play();
            root.getChildren().add(etoile[i]);
        }

        Circle lune = new Circle(750,50, 25);
        Stop[] stops = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.BLACK),};
        LinearGradient gradient = new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE, stops);
        lune.setFill(gradient);
        root.getChildren().add(lune);
    }

    public Group maison(boolean jour){
        Group maisonGroup = new Group();
        DropShadow ds = new DropShadow(20,-10,10, Color.DARKGRAY);

        Rectangle maison = new Rectangle(525,150,150,100);
        maison.setFill(Color.BEIGE);

        maisonGroup.getChildren().add(maison);

        Polygon toit = new Polygon(505,160, 600,80, 695,160);
        toit.setFill(Color.BROWN);
        maisonGroup.getChildren().add(toit);

        if (jour)
            maisonGroup.setEffect(ds);

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

    public Group oiseau(){
        Group oiseauGroup = new Group();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        CubicCurve[] oiseau = {new CubicCurve(0,25, 15,-25, 25,25, 35,50),new CubicCurve(70,25, 55,-25, 45,25, 35,50)};
        for (int i=0;i<oiseau.length;i++) {
            oiseau[i].setStrokeWidth(2);
            oiseau[i].setStroke(Color.BLACK);
            oiseau[i].setFill(Color.LIGHTGRAY);
            oiseauGroup.getChildren().add(oiseau[i]);
        }

        KeyValue[] kv1 = {new KeyValue(oiseau[0].startXProperty(),0,Interpolator.LINEAR),new KeyValue(oiseau[0].startYProperty(),25,Interpolator.LINEAR),new KeyValue(oiseau[0].endYProperty(),50,Interpolator.LINEAR)};
        KeyValue[] kv2 = {new KeyValue(oiseau[0].startXProperty(),-15,Interpolator.LINEAR),new KeyValue(oiseau[0].startYProperty(),0,Interpolator.LINEAR),new KeyValue(oiseau[0].endYProperty(),60,Interpolator.LINEAR)};
        KeyValue[] kv3 = {new KeyValue(oiseau[1].startXProperty(),70,Interpolator.LINEAR),new KeyValue(oiseau[1].startYProperty(),25,Interpolator.LINEAR),new KeyValue(oiseau[1].endYProperty(),50,Interpolator.LINEAR)};
        KeyValue[] kv4 = {new KeyValue(oiseau[1].startXProperty(),85,Interpolator.LINEAR),new KeyValue(oiseau[1].startYProperty(),0,Interpolator.LINEAR),new KeyValue(oiseau[1].endYProperty(),60,Interpolator.LINEAR)};

        KeyFrame kf1= new KeyFrame(Duration.seconds(0),kv1[0],kv1[1],kv1[2],kv3[0],kv3[1],kv3[2]);
        KeyFrame kf2= new KeyFrame(Duration.seconds(1),kv2[0],kv2[1],kv2[2],kv4[0],kv4[1],kv4[2]);

        timeline.getKeyFrames().addAll(kf1,kf2);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return oiseauGroup;
    }
}
