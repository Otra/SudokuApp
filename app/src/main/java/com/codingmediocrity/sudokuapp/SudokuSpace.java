package com.codingmediocrity.sudokuapp;

/**
 * Model of a single space on the sudoku board.
 * Maintains value and filled status
 *
 */
public class SudokuSpace {


    private boolean isFilled = false;
    private boolean isBoardFilled = false;
    private int entry = -1;


    public SudokuSpace() {

    }

    public SudokuSpace(boolean isFilled, boolean isBoardFilled, int entry) {
        this.isFilled = isFilled;
        this.isBoardFilled = isBoardFilled;
        this.entry = entry;
    }

    public SudokuSpace clone() {
        return new SudokuSpace(this.isFilled, this.isBoardFilled, this.entry);
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public int getEntry() {
        return entry;
    }

    /**
     * Set the entry value.
     * @param entry
     */
    public void setEntry(int entry) {
        this.entry = entry;
        if (entry > 0)
            this.isFilled = true;
    }

    /**
     * Set this space as a user entry
     * @param entry
     * @return
     */
    public boolean setUserEntry(int entry) {
        if (isBoardFilled) {
            return false;
        }
        else {
            this.entry = entry;
            this.isFilled = true;
            return true;
        }
    }

    /**
     * Set this space as a board entry
     * @param entry
     * @return
     */
    public boolean setBoardEntry(int entry) {
        this.entry = entry;
        this.isFilled = true;
        this.isBoardFilled = true;
        return true;
    }

    public void clear() {
        this.entry = -1;
        this.isFilled = false;
    }
}
