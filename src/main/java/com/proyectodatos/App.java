package com.proyectodatos;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Text;

public class App extends Application {

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Button playPauseButton;
    private Button previousButton;
    private Button nextButton;
    private LinkedList<String> videoQueue = new LinkedList<>();
    private ListIterator<String> iterator;

    @Override
    public void start(Stage stage) throws IOException {
        //Inicializar los Iteradores y listas
        initVideoList();
        iterator = videoQueue.listIterator();
        // inicializar los componentes graficos
        mediaView = new MediaView();
        playPauseButton = new Button("Pause");
        previousButton = new Button("Previous");
        nextButton = new Button("Next");
        //Manejo de Layout
        BorderPane mainPane = new BorderPane();
        BorderPane labelPane = new BorderPane();
        Text title = new Text("Proyecto Estructura de Datos");
        title.setStyle("-fx-font: 24 arial;");
        labelPane.setCenter(title);
        mainPane.setTop(labelPane);
        Group videoPanel = new Group();
        videoPanel.getChildren().add(mediaView);
        mainPane.setCenter(videoPanel);

        // Eventos de los Botones
        playPauseButton.setOnAction(e -> togglePlayPause());
        previousButton.setOnAction(e -> playPreviousVideo());
        nextButton.setOnAction(e -> playNextVideo());
        //Manejo de los botones y su posicionamiento
        HBox buttonPane = new HBox(10, previousButton, playPauseButton, nextButton);
        buttonPane.setAlignment(Pos.CENTER);
        //Agregar Botones
        BorderPane botones = new BorderPane();
        botones.setCenter(buttonPane);
        mainPane.setBottom(botones);
        buttonPane.setMinHeight(150);
        //Mostrar Escnea
        Scene rootScene = new Scene(mainPane, 900, 900);
        stage.setTitle("Proyecto Estructura de Datos");
        stage.setScene(rootScene);
        stage.show();

        // reproducir primer video
        playNextVideo();
    }


    private void togglePlayPause() {
        if (mediaPlayer != null) {
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                playPauseButton.setText("Play");
            } else {
                mediaPlayer.play();
                playPauseButton.setText("Pause");
            }
        }
    }

    private void initVideoList() {
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v1.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v2.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v3.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v4.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v5.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v6.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v7.mp4");
        videoQueue.add("src\\main\\resources\\com\\proyectodatos\\videos\\v8.mp4");
    }

    private void playNextVideo() {
        if (iterator != null && iterator.hasNext()) {
            String videoPath = iterator.next();
            Media media = new Media(new File(videoPath).toURI().toString());
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(this::playNextVideo);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            playPauseButton.setText("Pause");
        } else {
            iterator = videoQueue.listIterator();
            playNextVideo();
        }
    }

    private void playPreviousVideo() {
        if (iterator != null && iterator.hasPrevious()) {
            iterator.previous();
            if (iterator.hasPrevious()) {
                String videoPath = iterator.previous();
                Media media = new Media(new File(videoPath).toURI().toString());
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setOnEndOfMedia(this::playNextVideo);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                playPauseButton.setText("Pause");
            } else {
                playLastVideo();
            }
        } else {
            playLastVideo();
        }
    }

    private void playLastVideo() {
        iterator = videoQueue.listIterator(videoQueue.size());
        if (iterator.hasPrevious()) {
            String videoPath = iterator.previous();
            Media media = new Media(new File(videoPath).toURI().toString());
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(this::playNextVideo);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            playPauseButton.setText("Pause");
        }
    }

    private void addToQueue(String videoPath) {
        videoQueue.add(videoPath);
        if (iterator == null) {
            iterator = videoQueue.listIterator();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
