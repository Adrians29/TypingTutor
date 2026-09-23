package com.adrianegl.typingtutor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private int currentIdx = 0;
    private int currentMistake = 0;
    private int mistake = 0;
    private int i = 1;
    private String str = nextPhrase(i);
    private TextFlow strContainer;
    @Override
    public void start(Stage stage) {
        strContainer = new TextFlow();
        strContainer.getStyleClass().add("sentence-container");
        
        SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Keyboard keyboard = new Keyboard();
        Button bRest = new Button();
        bRest.setText("RESET");
        Button bNext = new Button();
        bNext.setText("NEXT");
        
        Label lbCount = new Label(wordCount(currentIdx, str.length()));
        Label lbMistake = new Label("" + mistake);
        Label lb = new Label();

        lbCount.setPrefSize(100, 40);
        lbMistake.setPrefSize(200, 40);
        
        lbMistake.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-font-size: 18px;");
        lbCount.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-font-size: 18px;");
        
        bRest.getStyleClass().add("control-button");
        bRest.getStyleClass().add("reset-button");
        bNext.getStyleClass().add("control-button");
        bNext.getStyleClass().add("next-button");
        
        bRest.setFocusTraversable(false);
        
        gridPane.add(keyboard, 1, 7);
        gridPane.add(lbCount, 1, 3);
        gridPane.add(lbMistake, 2, 3);
        gridPane.add(lb, 0, 4);
        gridPane.add(strContainer, 1, 5);
        gridPane.add(bRest, 0, 8);
        gridPane.add(bNext, 2, 8);
        
        
        bNext.setDisable(true);
        
        
        Scene scene = new Scene(gridPane, 1200, 720);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.isShiftDown() && str.toUpperCase().charAt(currentIdx) == str.charAt(currentIdx)) {
                KeyCode code = e.getCode();
                keyboard.highlightRight(e.getCode());
                if (code.isLetterKey()) {
                    char upperCharKey = e.getText().toUpperCase().charAt(0);
                    if (upperCharKey == str.charAt(currentIdx)) {
                        keyboard.highlightRight(e.getCode());
                        currentIdx++;
                        SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                    } else if (upperCharKey != str.toUpperCase().charAt(currentIdx)) {
                        keyboard.highlightWrong(e.getCode());
                        ++currentMistake;
                        lbCount.setText(wordCount(currentIdx, str.length()));
                        lbMistake.setText("" + mistake);
                        SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                        --currentMistake;
                    }
                }
            } else if (e.isShiftDown() && str.toUpperCase().charAt(currentIdx) != str.charAt(currentIdx)){
                KeyCode code = e.getCode();
                keyboard.highlightWrong(e.getCode());
                char charKey = e.getText().charAt(0);

                if (charKey == str.charAt(currentIdx)) {
                    keyboard.highlightRight(e.getCode());
                    currentIdx++;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                    lbCount.setText(wordCount(currentIdx, str.length()));
                } else if (charKey != str.toUpperCase().charAt(currentIdx)) {
                    keyboard.highlightWrong(e.getCode());
                    ++currentMistake;
                    lbCount.setText(wordCount(currentIdx, str.length()));
                    lbMistake.setText("" + mistake);
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                    --currentMistake;
                }
            } else{
                KeyCode code = e.getCode();
                keyboard.highlightWrong(e.getCode());
                char charKey = e.getText().charAt(0);

                if (charKey == str.charAt(currentIdx)) {
                    keyboard.highlightRight(e.getCode());
                    currentIdx++;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                    lbCount.setText(wordCount(currentIdx, str.length()));
                } else if (charKey != str.toUpperCase().charAt(currentIdx)) {
                    keyboard.highlightWrong(e.getCode());
                    ++currentMistake;
                    ++mistake;
                    SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
                    --currentMistake;
                    lbCount.setText(wordCount(currentIdx, str.length()));
                    lbMistake.setText("" + mistake);
                }
                if (currentIdx == str.length()) {
                    bNext.setDisable(false);
                }
            }

        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            keyboard.unHighLight(e.getCode());
        });
        
        bRest.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            e.consume();
        });
        
        bRest.addEventHandler(MouseEvent.MOUSE_CLICKED,e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                currentIdx = 0;
                currentMistake = 0;
                mistake = 0;
                lbCount.setText(wordCount(currentIdx, str.length()));
                lbMistake.setText("" + mistake);
            }
        });
        
        bNext.setOnAction(e -> {
            ++i;
            str = nextPhrase(i);
            strContainer.getStyleClass().add("sentence-container");
            currentMistake = 0;
            currentIdx = 0;
            mistake = 0;
            SentenceDisplay.updateSentence(strContainer, str, currentIdx, currentMistake);
            lbCount.setText(wordCount(currentIdx, str.length()));
            lbMistake.setText("" + mistake);
            bNext.setDisable(true);
        });
        
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public String nextPhrase(int num) {
        switch (num) {
            case 1:
                return "Try typing this text. Do it as quickly and accurately as you can.";
            case 2:
                return "Next type another line of input data.";
            case 3:
                return "The quick brown fox jumps over the lazy dog.";
            case 4:
                return "Five big quacking zephyrs jolt my wax bed.";
            case 5:
                return "Sympathizing would fix Quaker objectives.";
            case 6:
                return "A large fawn jumped quickly over white zinc boxes.";
            default:
                return "Try typing this text. Do it as quickly and accurately as you can.";
        }
    }
    
    public static String wordCount(int currentIdx, int length) {
        return String.format("%d/%d", currentIdx, length);
    }
    
}