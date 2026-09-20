/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adrianegl.typingtutor;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author adria
 */
public class Keyboard extends VBox {
    
    private final Map<KeyCode, Button> keyButtonMap = new HashMap<>();
    
    public Keyboard() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        
        KeyCode[][] display = {
            {KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T, KeyCode.Y, KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P},
            {KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G, KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L},
            {KeyCode.SHIFT, KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B, KeyCode.N, KeyCode.M, KeyCode.COMMA, KeyCode.PERIOD}
        };
        
        for (int i = 0; i < display.length; i++) {
            HBox hbox = new HBox(7);
            
            if(i == display.length - 1) { 
                hbox.setAlignment(Pos.CENTER_LEFT);
            } else {
                hbox.setAlignment(Pos.CENTER);
            }
            
                  
            for (KeyCode code : display[i]) {
                if (code == KeyCode.COMMA) {
                    Button button = createKey(",", code); 
                    hbox.getChildren().add(button);
                } else if (code == KeyCode.PERIOD) {
                    Button button = createKey(".", code); 
                    hbox.getChildren().add(button);
                } else {
                    Button button = createKey(code.getName(), code);
                    hbox.getChildren().add(button);
                }
            }
            this.getChildren().add(hbox);
        }
        
        HBox hSpace = new HBox(7);
        hSpace.setAlignment(Pos.CENTER);
        Button bSpace = createKey("Space", KeyCode.SPACE);
        bSpace.setPrefWidth(340);
        hSpace.getChildren().add(bSpace);
        this.getChildren().add(hSpace);
    }
    /**
     * @param label the keys name
     * @param code the key's code
     * @return the keys button
     */
    private Button createKey(String label, KeyCode code) {
        Button b = new Button(label);
        if (code.equals(KeyCode.SHIFT)) {
            b.setPrefSize(100, 60);
            b.setFocusTraversable(false);
            keyButtonMap.put(code, b);
            b.getStyleClass().add("shift-key");
            return b;
        }
        b.setPrefSize(60, 60);
        b.setFocusTraversable(false);
        keyButtonMap.put(code, b);
        b.getStyleClass().add("keyboard-key");
        return b;
    }
    
    public void highlightRight(KeyCode code) {
        Button b = keyButtonMap.get(code);
        if (b != null) {
            b.getStyleClass().remove("key-wrong");
            b.getStyleClass().add("key-correct");
        }
    }
    
    public void highlightWrong(KeyCode code) {
        Button b = keyButtonMap.get(code);
        if (b != null) {
            b.getStyleClass().remove("key-correct");
            b.getStyleClass().add("key-wrong");
        }
    }
    
    public void unHighLight(KeyCode code) {
        Button b = keyButtonMap.get(code);
        if (b != null) {
            b.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5;");
        }
    }
}
