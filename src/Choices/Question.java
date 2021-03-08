package Choices;

public class Question {

    String eventTitle;
    int eventID;

    String eventDesc;

    String imagePath;

    String answerOneText;
    int answerOneDest;

    String answerTwoText;
    int answerTwoDest;

    String answerThreeText;
    int answerThreeDest;

    String music;

    /*
    Todo change music
    -When it reach some question, change the music
    -If music is not the same with the song is playing right now
     */

    public Question(String eventTitle, int eventID, String eventDesc, String imagePath, String answerOneText,int answerOneDest, String answerTwoText, int answerTwoDest, String answerThreeText, int answerThreeDest, String music){
        this.eventTitle = eventTitle;
        this.eventID = eventID;
        this.eventDesc = eventDesc;
        this.imagePath = imagePath;
        this.answerOneText = answerOneText;
        this.answerOneDest = answerOneDest;
        this.answerTwoText = answerTwoText;
        this.answerTwoDest = answerTwoDest;
        this.answerThreeText = answerThreeText;
        this.answerThreeDest = answerThreeDest;
        this.music = music;
    }

}
