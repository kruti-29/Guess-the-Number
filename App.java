import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    @FXML
    private Button GuessBtn;
    private Button clear;
    private Button giveup;

    @FXML
    private TextField inputNumber;

    @FXML
    private Label message;

    @FXML
    private Label guess;

    static int RandomNo = (int) (Math.random() * 100) + 1;

    public void setrandom() {
        RandomNo = (int) (Math.random() * 100) + 1;
    }

    public int getrandom() {
        return RandomNo;
    }

    static int totaltry = 0;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Number Gussing Game");
        stage.show();
    }

    public void clear() {
        totaltry = 0;
        message.setText("Restart Game");
        setrandom();
        guess.setText("");
    }

    public void giveup() {
        message.setText("No. of Guesses : " + totaltry + "\n Correct Number : " + getrandom());
        totaltry = 0;
        guess.setText("");
        setrandom();
    }

    public void checkGuess() {

        String input = inputNumber.getText();
        int Number;
        try {
            Number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            message.setText("Invalid input!");
            guess.setText("Guesses : " + totaltry);
            return;
        }
        totaltry++;
        if (Number > 100 || Number < 1) {
            message.setText("Enter the Value between 1 to 100");
            totaltry--;
            guess.setText("Guesses : " + totaltry);
        } else if (Number == RandomNo) {
            message.setText("You guessed the number in " + totaltry + " attempts.");
            guess.setText("");
            totaltry = 0;
            setrandom();
        } else if (Number < RandomNo) {
            message.setText("Enter the Higher Value");
            guess.setText("Guesses : " + totaltry);
        } else {
            message.setText("Enter the Lower Value");
            guess.setText("Guesses : " + totaltry);
        }
        inputNumber.clear();

    }

}
