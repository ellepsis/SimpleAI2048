package com.ellepsis.simpleAI.gameLogic;

import java.util.Random;

/**
 * @author Ellepsis
 * @since 0.0.1
 */
public class GameField {
    private Random r = new Random();
    public final byte length;
    private byte freePlaces;
    private int score = 0;
    private int[] field;

    public GameField(int length) {
        this.length = (byte) length;
        freePlaces = (byte) (length * length);
        field = new int[length * length];
        addElement();
        addElement();
    }

    /**
     * Adds initial element to random free position of the field.
     */
    private void addElement() {
        final int position = r.nextInt(freePlaces);
        final int elementValue = r.nextInt(4);
        int reviewedPositions = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i] == 0) {
                if (position == reviewedPositions) {
                    field[i] = elementValue == 3 ? 4 : 2;
                    freePlaces--;
                    return;
                } else {
                    reviewedPositions++;
                }
            }
        }
    }

    private int getElement(int row, int col) {
        return field[row * length + col];
    }

    private void setElement(int row, int col, int val) {
        field[row * length + col] = val;
    }

    private void reverseHorizontal() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length / 2; y++) {
                int val = getElement(x, length - y - 1);
                setElement(x, length - y - 1, getElement(x, y));
                setElement(x, y, val);
            }
        }
    }

//    private void rotateLeft() {
//        int[] newField = new int[length * length];
//        for (int x = 0; x < length; x++) {
//            for (int y = 0; y < length; y++) {
//                newField[x * length + y] = field[y * length + length - x - 1];
//            }
//        }
//        this.field = newField;
//    }

    /**
     * Method rotates array to 90 degrees left.
     * <p>
     * This realization is memory effective, because it is not allocating a new array for swap elements.
     * Therefore performance gain is achieved by cache locality for small arrays and avoiding unnecessary GC.
     * </p>
     */
    private void rotateLeft() {
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j < length - 1 - i; j++) {
                int topLeft = getElement(i, j);
                setElement(i, j, getElement(j, length - i - 1));
                setElement(j, length - i - 1, getElement(length - i - 1, length - j - 1));
                setElement(length - i - 1, length - j - 1, getElement(length - j - 1, i));
                setElement(length - j - 1, i, topLeft);
            }
        }
    }

    private void mergeLeft() {
        int previousScore = score;
        boolean isMoved = false;
        for (int i = 0; i < length; i++) {
            int lastNonZero = 0;
            for (int j = 0; j < length; j++) { //move all nonzero elements to left side of the row
                int element = getElement(i, j);
                if (element != 0) {
                    if (j != lastNonZero) {
                        setElement(i, lastNonZero, element);
                        setElement(i, j, 0);
                        isMoved = true;
                    }
                    lastNonZero++;
                }
            }
            for (int j = 0; j < length - 1; j++) { //we cant only mergeLeft last element and pre last, thereby length-1
                int val = getElement(i, j);
                if (val != 0 && val == getElement(i, j + 1)) { //if 2 elements are equal, merge it
                    setElement(i, j, val * 2);
                    for (int k = j + 1; k < length - 1; k++) { //move all elements to one col left
                        setElement(i, k, getElement(i, k + 1));
                    }
                    setElement(i, length - 1, 0);
                    score += val * 2;
                    freePlaces++;
                }
            }
        }
        //if not elements is moved or score isn't increased, then not adding a new element
        if (previousScore != score || isMoved) {
            addElement();
        }
    }

    public void moveLeft() {
        mergeLeft();
    }

    public void moveRight() {
        reverseHorizontal();
        mergeLeft();
        reverseHorizontal();
    }

    public void moveUp() {
        rotateLeft();
        mergeLeft();
        rotateLeft();
        rotateLeft();
        rotateLeft();
    }

    public void moveBottom() {
        rotateLeft();
        rotateLeft();
        rotateLeft();
        mergeLeft();
        rotateLeft();
    }

    public int[] getField() {
        return field;
    }

    public int getScore() {
        return score;
    }
}
