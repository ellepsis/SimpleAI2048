package com.ellepsis.simpleAI.gameLogic;

import java.util.Scanner;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class GameControl {
    private GameField gameField = new GameField(4);
    private StringBuilder outputStringBuilder = new StringBuilder(512);

    public void readAction() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {
                case "up":
                    moveUp();
                    break;
                case "bottom":
                    moveBottom();
                    break;
                case "left":
                    moveLeft();
                    break;
                case "right":
                    moveRight();
                    break;
                default:
                    break;

            }
        }
    }

    private void moveLeft() {
        gameField.moveLeft();
        displayField();
    }

    private void moveRight() {
        gameField.moveRight();
        displayField();
    }

    private void moveUp() {
        gameField.moveUp();
        displayField();
    }

    private void moveBottom() {
        gameField.moveBottom();
        displayField();
    }

    public void displayField() {
        outputStringBuilder.setLength(0);
        outputStringBuilder.append("Score = ").append(gameField.getScore()).append("; Field now is:\n");
        final String firstRow = constructRow(0);
        final int rowLength = firstRow.length();
        outputStringBuilder.append(constructHorizontalDelimiter(rowLength));
        outputStringBuilder.append(firstRow);
        outputStringBuilder.append(constructHorizontalDelimiter(rowLength));
        for (int i = 1; i < gameField.length; i++) {
            outputStringBuilder.append(constructRow(i));
            outputStringBuilder.append(constructHorizontalDelimiter(rowLength));
        }
        System.out.println(outputStringBuilder.toString());
    }

    private String constructRow(int row) {
        StringBuilder sb = new StringBuilder(64);
        for (int j = 0; j < gameField.length; j++) {
            String number = String.format("|%4d  ", gameField.getField()[row * gameField.length + j]);
            sb.append(number);
        }
        sb.append("|");
        sb.append("\n");
        return sb.toString();
    }

    private String constructHorizontalDelimiter(int length) {
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < length; i++) {
            sb.append('-');
        }
        sb.append('\n');
        return sb.toString();
    }
}
