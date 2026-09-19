/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adrianegl.typingtutor;

import javafx.scene.text.TextFlow;

/**
 *
 * @author adria
 */
public class SentenceDisplay {
    public static void updateSentence(TextFlow txtFlow, String str, int currentIdx) {
        txtFlow.getChildren().clear();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
        }
    }
}
