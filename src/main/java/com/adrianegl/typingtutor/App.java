package com.adrianegl.typingtutor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private int currentIdx = 0;
    private int temp = 0;
    private int mistake = 0;
    private String str = "Try typing this text. Do it as quickly and accurately as you can.";
    private TextFlow strContainer;
    @Override
    public void start(Stage stage) {
        strContainer = new TextFlow();
        strContainer.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 8;");
        
        SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Keyboard keyboard = new Keyboard();
        Label label = new Label("Try typing this text. Do it as quickly and accurately as you can.");
        
        gridPane.add(keyboard, 0, 7);
        gridPane.add(label, 0, 6);
        gridPane.add(strContainer, 0, 5);
        //Label label = new Label("Press a key");
        //keyboardGrid.setHgap(5);
        //keyboardGrid.setVgap(5);
        
        Scene scene = new Scene(gridPane, 1200, 720);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.isShiftDown() && str.toUpperCase().charAt(currentIdx) == str.charAt(currentIdx)) {
                KeyCode code = e.getCode();
                keyboard.highlightRight(e.getCode());
                if (code.isLetterKey()) {
                    char upperCharKey = e.getText().toUpperCase().charAt(0);
                    if (upperCharKey == str.charAt(currentIdx)) {
                        keyboard.highlightRight(e.getCode());
                        currentIdx++;
                        SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                    } else if (upperCharKey != str.toUpperCase().charAt(currentIdx)) {
                        keyboard.highlightWrong(e.getCode());
                        ++mistake;
                        SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                        temp++;
                    }
                }
            } else if (e.isShiftDown() && str.toUpperCase().charAt(currentIdx) != str.charAt(currentIdx)){
                KeyCode code = e.getCode();
                keyboard.highlightWrong(e.getCode());
                char charKey = e.getText().charAt(0);

                if (charKey == str.charAt(currentIdx)) {
                    keyboard.highlightRight(e.getCode());
                    currentIdx++;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                } else if (charKey != str.toUpperCase().charAt(currentIdx)) {
                    keyboard.highlightWrong(e.getCode());
                    ++mistake;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                    temp++;
                }
            } else{
                KeyCode code = e.getCode();
                keyboard.highlightWrong(e.getCode());
                char charKey = e.getText().charAt(0);

                if (charKey == str.charAt(currentIdx)) {
                    keyboard.highlightRight(e.getCode());
                    currentIdx++;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                } else if (charKey != str.toUpperCase().charAt(currentIdx)) {
                    keyboard.highlightWrong(e.getCode());
                    ++mistake;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, mistake, temp);
                    temp++;
                }
            }

        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            keyboard.unHighLight(e.getCode());
        });
        /*keyboardGrid.getChildren().add(label);
        scene.setOnKeyPressed(e -> {
           KeyCode keyCode = e.getCode();
           String keyText = e.getText();
           label.setText(keyText);
        });*/
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /*public String nextPhrase(int num) {
        String str = switch(num) {
            case 1 -> "Try typing this text. Do it as quickly and accurately as you can.";
            case 2 -> "Next type another line of input data.";
            case 3 -> "The quick brown fox jumps over the lazy dog.";
            case 4 -> "Five big quacking zephyrs jolt my wax bed.";
            case 5 -> "Sympathizing would fix Quaker objectives.";
            case 6 -> "A large fawn jumped quickly over white zinc boxes.";
        };
        
        char c = ' ';
        for (int i = 0; i < str.length(); i++) {
            str.charAt(i);
        }
        
        return str;
    }*/

}