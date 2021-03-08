package Choices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.util.Duration;

public class Main extends Application {

    public  static MediaPlayer player, songPlayer, inGameSong;
    public static boolean isMusicPLaying;
    public static Media mainsong;
    public static  double volumn;
    public static String playingSong;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Media nyancat = new Media(getClass().getResource("/Resource/NyanCat.mp3").toURI().toString());
        player = new MediaPlayer(nyancat);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));

        mainsong = new Media(getClass().getResource("/Resource/mainmenu.wav").toURI().toString());
        playingSong = "/Resource/mainmenu.wav";
        songPlayer = new MediaPlayer(mainsong);
        songPlayer.setOnEndOfMedia(() -> songPlayer.seek(Duration.ZERO));


        //bgsong
        volumn = 0.5;
        Main.songPlayer.setVolume(volumn);
        Main.songPlayer.play();
        isMusicPLaying = true;


        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Choice");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
