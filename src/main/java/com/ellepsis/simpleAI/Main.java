package com.ellepsis.simpleAI;

import com.ellepsis.simpleAI.gameLogic.GameControl;
import com.ellepsis.simpleAI.gameLogic.GameField;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class Main {


    public static void main(String[] args) {
        GameControl gameControl = new GameControl();
        gameControl.displayField();
        gameControl.readAction();
    }
}
