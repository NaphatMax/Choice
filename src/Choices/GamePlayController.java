package Choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    Pane pane;

    @FXML
    Text volumnText;

    @FXML
    Slider volumnSlider;

    @FXML
    CheckBox muteCheck;

    @FXML
    Menu backMain;


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
    private void setBackMain() throws IOException {
        AnchorPane gamePane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        pane.getChildren().setAll(gamePane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.songPlayer.stop();

        try {
            Main.mainsong = new Media(getClass().getResource("/Resource/ingame.wav").toURI().toString());
            Main.songPlayer = new MediaPlayer(Main.mainsong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volumn);
            if (Main.isMusicPLaying){
                System.out.println("here");
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

    public void about() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane about = loader.load(getClass().getResource("about.fxml").openStream());

        Scene scene = new Scene(about);
        Stage nStage = new Stage();
        nStage.setTitle("About choice");
        nStage.setScene(scene);
        nStage.setAlwaysOnTop(true);

        pane.setDisable(true);

        nStage.showAndWait();

        pane.setDisable(false);
    }

}
