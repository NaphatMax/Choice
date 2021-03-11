package Choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    ImageView imageview;

    @FXML
    Button answer1;

    @FXML
    Button answer2;

    @FXML
    Button answer3;

    @FXML
    Text questionText;

    @FXML
    TextArea questionDesc;


    Question[] questions = {
            //0
            new Question("I am Batman",0, "I am Batman Gotham's greatest hero. Deathstroke is on the run. How should Batman pursuit Deathstroke??  ", "/Resource/batmanvsdeathstroke.jpg",
                    "BatPod", 1, "BatMobile", 2,"BatWing", 3, "/Resource/songFive.wav"),

            //1
            new Question("Pursuing Deathstroke with BatPod",1, "Batman is going after Deathstroke by riding BatPod. However Deathstroke attacks Batman with assault rifle!!!\n How should Batman counter Deathstroke's attack??", "/Resource/batpod.jpg",
                    "Batarang", 4, "Sonic wave", 5,"Bat Claw", 6, "/Resource/songTwo.wav"),

            //2
            new Question("Pursuing Deathstroke with BatMobile",2, "Batman is going after Deathstroke by driving BatMobile. However Deathstroke attacks Batman with assault rifle!!!\n How should Batman counter Deathstroke's attack??", "/Resource/batmobile.jpg",
                    "Batarang", 4, "Sonic wave", 5,"Bat Claw", 6, "/Resource/songTwo.wav"),

            //3
            new Question("Pursuing Deathstroke with BatWing",3, "Batman is going after Deathstroke by flying BatWing. However Deathstroke attacks Batman with assault rifle!!!\n How should Batman counter Deathstroke's attack??", "/Resource/batwing.jpg",
                    "Batarang", 4, "Sonic wave", 5,"Bat Claw", 6, "/Resource/songTwo.wav"),

            //4
            new Question("Batman counter attack Deathstroke by Batarang",4, "Batman throws Batarangs at Deathstroke, but Deathstroke dodge all the Batarangs that Batman threw at him!!!\n Now they are facing each other one-one on hand combat. Which hand combat style should Batman uses??", "/Resource/batarang.jpg",
                    "Muay Thai", 7, "Jiu-jitsu", 8,"Krav maga", 9, "/Resource/songThree.wav"),

            //5
            new Question("Batman counter attack Deathstroke by Sonic wave",5, "Batman uses Sonic wave gun at Deathstroke, but Deathstroke dodge all the wave that Batman shoot at him!!!\n Now they are facing each other one-one on hand combat. Which hand combat style should Batman uses??", "/Resource/sonicwave.jpg",
                    "Muay Thai", 7, "Jiu-jitsu", 8,"Krav maga", 9, "/Resource/songThree.wav"),

            //6
            new Question("Batman counter attack Deathstroke by Bat Claw",6, "Batman shoot Bat Claw at Deathstroke, but Deathstroke dodge the Bat Claw that Batman shoot at him!!!\n Now they are facing each other one-one on hand combat. Which hand combat style should Batman uses??", "/Resource/batclaw.jpg",
                    "Muay Thai", 7, "Jiu-jitsu", 8,"Krav maga", 9, "/Resource/songThree.wav"),

            //7
            new Question("Muay Thai hand to hand combat against Deathstroke",6, "Batman is using Muay Thai combat style against Deathstroke and giving DeathStroke a hard time to beating Batman.\n Finally Batman has taken Deathstroke down. Where should Batman lock him??", "/Resource/batmanvsdeathstroke.jpg",
                    "Batcave", 10, "Arkham Asylum", 11,"GCPD", 12, "/Resource/songOne.wav"),

            //8
            new Question("Jiu-jitsu hand to hand combat against Deathstroke",6, "Batman is using Jiu-jitsu combat style against Deathstroke and giving DeathStroke a hard time to beating Batman.\n Finally Batman has taken Deathstroke down. Where should Batman lock him??", "/Resource/batmanfight.jpg",
                    "Batcave", 10, "Arkham Asylum", 11,"GCPD", 12, "/Resource/songOne.wav"),

            //9
            new Question("Krav mage hand to hand combat against Deathstroke",6, "Batman is using Krav mage combat style against Deathstroke and giving DeathStroke a hard time to beating Batman.\n Finally Batman has taken Deathstroke down. Where should Batman lock him??", "/Resource/batmanfight2.jpg",
                    "Batcave", 10, "Arkham Asylum", 11,"GCPD", 12, "/Resource/songOne.wav"),

            //10
            new Question("Batman put Deathstroke in the Batcave",6, "Batman lock Deathstroke up in the Batcave", "/Resource/batcave.jpg",
                    "End", 0, "End", 0,"End", 0, "/Resource/songFour.wav"),

            //11
            new Question("Batman put Deathstroke in the Arkham Asylum",6, "Batman lock Deathstroke up in the Arkham Asylum", "/Resource/arkhamasylum.jpg",
                    "End", 0, "End", 0,"End", 0, "/Resource/songFour.wav"),

            //12
            new Question("Batman hand Deathstroke over GCPD",6, "Batman lock Deathstroke up in the GCPD cage", "/Resource/gcpd.png",
                    "End", 0, "End", 0,"End", 0, "/Resource/songFour.wav"),



    };

    Question currentQuestion;


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

        currentQuestion = questions[0];

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Main.songPlayer.stop();

        try {
            Main.mainsong = new Media(getClass().getResource("/Resource/ingame.wav").toURI().toString());
            Main.playingSong = "/Resource/ingame.wav";
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

    public void changeQuestion(Button button) {
        int nextQuestionId = Integer.parseInt(button.getId());
        currentQuestion = questions[nextQuestionId];

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void SetDisplayQuestion(Question question) throws URISyntaxException {
        if(!question.music.equalsIgnoreCase(Main.playingSong)){
            Main.songPlayer.stop();
            Main.mainsong = new Media(getClass().getResource(question.music).toURI().toString());
            Main.playingSong = "/Resource/ingame.wav";
            Main.songPlayer = new MediaPlayer(Main.mainsong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volumn);
            if (Main.isMusicPLaying){
                System.out.println("here");
                Main.songPlayer.play();
            }
        }

        questionText.setText(question.eventTitle);
        questionDesc.setText(question.eventDesc);

        answer1.setText(question.answerOneText);
        answer1.setId(String.valueOf(question.answerOneDest));
        answer1.setOnAction(actionEvent -> changeQuestion(answer1));

        answer2.setText(question.answerTwoText);
        answer2.setId(String.valueOf(question.answerTwoDest));
        answer2.setOnAction(actionEvent -> changeQuestion(answer2));

        answer3.setText(question.answerThreeText);
        answer3.setId(String.valueOf(question.answerThreeDest));
        answer3.setOnAction(actionEvent -> changeQuestion(answer3));

        imageview.setImage(new Image(question.imagePath));

    }

}
