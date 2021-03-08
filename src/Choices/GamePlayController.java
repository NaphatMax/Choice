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
            new Question("Welcome to choice game",0, "Welcome you guys to the game", "/Resource/Riddler-Dangerous.jpg",
                    "First question", 1, "Second question", 2,"Third question", 3),

            new Question("First Question",1, "First question", "/Resource/Riddler-Dangerous.jpg",
                    "Second question", 2, "Third question", 3,"Forth question", 4),

            new Question("Second Question",2, "Second question", "/Resource/Riddler-Dangerous.jpg",
                    "Third question", 3, "Forth question", 4,"Fifth question", 5),

            new Question("Third Question",3, "Third question", "/Resource/Riddler-Dangerous.jpg",
                    "Forth question", 4, "Final question", 5,"Final question", 5),

            new Question("Forth Question",4, "Forth question", "/Resource/Riddler-Dangerous.jpg",
                    "Final question", 5, "Final question", 5,"Final question", 5),

            new Question("Final Question",5, "Final question", "/Resource/Riddler-Dangerous.jpg",
                    "The End", 0, "The End", 0,"The End", 0)

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

        SetDisplayQuestion(currentQuestion);

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

    public void changeQuestion(Button button){
        int nextQuestionId = Integer.parseInt(button.getId());
        currentQuestion = questions[nextQuestionId];

        SetDisplayQuestion(currentQuestion);
    }
    public void SetDisplayQuestion(Question question){

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
