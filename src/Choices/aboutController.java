package Choices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class aboutController implements Initializable {

    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String aboutText = "Game Name: Choice\n" +
                "Develop by AnAmazon\n" +
                "https://github.com/NaphatMax/Choice\n" +
                "Single and looking for girlfriend\n";

        textArea.setText(aboutText);
    }

    public void Close(){
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.close();
    }
}
