package com.adrianegl.typingtutor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Keyboard keyboard = new Keyboard();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(keyboard, 0, 0);
        //Label label = new Label("Press a key");
        //keyboardGrid.setHgap(5);
        //keyboardGrid.setVgap(5);
        Scene scene = new Scene(gridPane, 1200, 720);
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

}