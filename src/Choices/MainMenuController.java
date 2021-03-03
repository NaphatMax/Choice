package Choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.print.DocFlavor;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    Pane easterEgg;

    @FXML
    Text titleText;

    @FXML
    Text author;

    @FXML
    Button startButton;

    @FXML
    Button quitButton;

    @FXML
    Button scoreBoard;

    @FXML
    Text volumnText;

    @FXML
    Slider volumnSlider;

    @FXML
    CheckBox muteCheck;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.songPlayer.stop();


        try {
            Main.mainsong = new Media(getClass().getResource("/Resource/mainmenu.wav").toURI().toString());
            Main.songPlayer = new MediaPlayer(Main.mainsong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volumn);
            if (Main.isMusicPLaying){
                Main.songPlayer.play();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        if(!Main.isMusicPLaying){
            muteCheck.setSelected(true);
        }
        else{
            muteCheck.setSelected(false);
        }

        titleText.setText("Choices Game");
        author.setText("By AnAmazon");
        startButton.setText("Start Game");
        quitButton.setText("Quit");
        scoreBoard.setText("Score Board");

                //volumn slider
        volumnSlider.setValue(Main.songPlayer.getVolume());
        volumnText.setText(String.format("%.1f", volumnSlider.getValue()));
        volumnSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                volumnText.setText((String.format("%.1f", volumnSlider.getValue())));
                Main.volumn = volumnSlider.getValue();
                Main.songPlayer.setVolume(volumnSlider.getValue());
            }
        });


    }

    public void EasterEggHover(){
        easterEgg.setStyle("-fx-background-color: red");
        Main.player.play();
    }

    public void EasterEggNoHover(){
        easterEgg.setStyle("-fx-background-color: white");
        Main.player.pause();
    }

    public void EasterEggHoverRainbow(){
        String[] colors ={"green", "blue", "red", "yellow", "purple", "pink"};

        int rand = new Random().nextInt(colors.length);
        String color = colors[rand];

        easterEgg.setStyle("-fx-background-color: " +color);
    }

    public void quitButton(){
        System.exit(0);
    }


    public void MusicToggle(){
        if(muteCheck.isSelected()){
            Main.songPlayer.pause();
            Main.isMusicPLaying = false;
        }
        else{
            Main.songPlayer.play();
            Main.isMusicPLaying = true;
        }
    }

    @FXML
    private void StartGame() throws IOException {
        AnchorPane gamePane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        easterEgg.getChildren().setAll(gamePane);
    }


}
