package Choices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleText.setText("Choices Game");
        author.setText("By AnAmazon");
        startButton.setText("Start Game");
        quitButton.setText("Quit");
    }

    public void EasterEggHover(){
        easterEgg.setStyle("-fx-background-color: red");
    }

    public void EasterEggNoHover(){
        easterEgg.setStyle("-fx-background-color: white");
    }

    public void EasterEggHoverRainbow(){
        String[] colors ={"green", "blue", "red", "yellow", "purple", "pink"};

        int rand = new Random().nextInt(colors.length);
        String color = colors[rand];

        easterEgg.setStyle("-fx-background-color: " +color);
    }

    public  void startButton(){

    }

    public void quitButton(){
       System.exit(0);
    }

}
