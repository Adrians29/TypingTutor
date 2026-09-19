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
            {KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B, KeyCode.N, KeyCode.M}
        };
        
        for (KeyCode[] row : display) {
            HBox hbox = new HBox(5);
            hbox.setAlignment(Pos.CENTER);
                  
            for (KeyCode code : row) {
                Button button = createKey(code.getName(), code); 
            }
        }
        
    }
    /**
     * @param label the keys name
     * @param code the key's code
     * @return the keys button
     */
    private Button createKey(String label, KeyCode code) {
        Button b = new Button(label);
        b.setPrefSize(45, 45);
        b.setFocusTraversable(false);
        keyButtonMap.put(code, b);
        return b;
    }
}
