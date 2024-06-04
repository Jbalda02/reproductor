package com.proyectodatos;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;  
import java.io.IOException;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
 

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       /**  StackPane rootPane = new StackPane();
        Button buttonBack = new Button("Back");
        Button buttonPlay = new Button("Play/Pause");
        Button buttonNext = new Button("Back");
        rootPane.getChildren().add(buttonBack);
        rootPane.getChildren().add(buttonPlay);
        rootPane.getChildren().add(buttonNext);
        Scene scene = new Scene(rootPane);
        
        stage.setTitle("Proyecto Estructura de Datos");
        stage.setScene(scene);
        stage.show();
    */
    BorderPane mainPane = new BorderPane();
    BorderPane LabelPane = new BorderPane();
    Text title = new Text("Proyecto Estructura de Datos");
    title.setStyle("-fx-font: 24 arial;");
    Label titleLabel = new Label();
    titleLabel.setAlignment(Pos.CENTER);
    /**Titulo */ 
    LabelPane.setCenter(title);
    mainPane.setTop(LabelPane);
    
    /**Video */
    String videoPath = "src\\main\\resources\\com\\proyectodatos\\videos\\Y2meta.app-Ecuatorianos, se armó el despelote! (6 de Junio 2022)-(480p).mp4";
    Media media = new Media(new File(videoPath).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    MediaView view = new MediaView(mediaPlayer);
    Group videoPanel = new Group();
    videoPanel.getChildren().add(view);
    mainPane.setCenter(videoPanel);

    /**Botones */
    BorderPane botones = new BorderPane();
    HBox buttonPane = new HBox();
    Button buttonBack = new Button("Back");
    Button buttonPlay = new Button("Play/Pause");
    Button buttonNext = new Button("Next");
        buttonPane.setSpacing(10);
        buttonPane.setAlignment(Pos.CENTER);
    buttonPane.getChildren().add(buttonBack);
    buttonPane.getChildren().add(buttonPlay);
    buttonPane.getChildren().add(buttonNext);
    
    botones.setCenter(buttonPane);
    mainPane.setBottom(botones);
    buttonPane.setMinHeight(150);
    Scene rootScene = new Scene(mainPane,900,900);

    stage.setTitle("Proyecto Estructura de Datos");
    stage.setScene(rootScene);
    stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

}