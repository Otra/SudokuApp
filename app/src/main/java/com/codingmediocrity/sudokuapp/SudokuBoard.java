package com.codingmediocrity.sudokuapp;

/**
 * Model of the entire sudoku board
 */
public class SudokuBoard {

    private SudokuSpace[][] board = null;
    private boolean solved = false;

    /**
     * Initialize an empty board.
     */
    public SudokuBoard() {
        board = new SudokuSpace[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuSpace();
            }
        }
    }

    /**
     * Adds a user entry in the given position.
     * @param entry
     * @param x
     * @param y
     * @return True if a valid entry.
     */
    public boolean addUserEntry(int entry, int x, int y) {
        if (board != null || board[x][y] != null) {
            board[x][y].setUserEntry(entry);
            return true;
        }
        return false;
    }

    /**
     * Adds a board entry in the given position.
     * @param entry
     * @param x
     * @param y
     * @return True if a valid entry.
     */
    public boolean addBoardEntry(int entry, int x, int y) {
        if (board != null && board[x][y] != null) {
            board[x][y].setBoardEntry(entry);
            return true;
        }
        return false;
    }


    /**
     * Validate whether the board is solved or not
     * @return True if it is successfully solved
     */
    public boolean validate() {
        return  validateRows() &&
                validateColumns() &&
                validateBoxes();
    }

    /**
     * Should change to return a SudokuResult
     * Need to specify where the errors are
     * @return
     */
    private boolean validateRows() {
        if (board != null) {
            for (int i=0; i < 9; i++) { // Row number
                for (int j = 1; j <= 9; j++) { // Entry number
                    for (int k = 0; k < 9; k++) { // Column number
                        if (board[i][k].getEntry() == j)
                            break;
                        if (k == 8) // If we got here, then we didn't match at all.
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Should change to return a SudokuResult
     * Need to specify where the errors are
     * @return
     */
    private boolean validateColumns() {
        if (board != null) {
            for (int i=0; i < 9; i++) { // Column number
                for (int j = 1; j <= 9; j++) { // Entry number
                    for (int k = 0; k < 9; k++) { // row number
                        if (board[k][i].getEntry() == j)
                            break;
                        if (k == 8) // If we got here, then we didn't match at all.
                            return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validateBoxes() {
        // 9 boxes: [0-2][0-2], [0-2][3-5], [0-2][6-8]
        //          [3-5][0-2], [3-5][3-5], [3-5][6-8]
        //          [6-8][0-2], [6-8][3-5], [6-8][6-8]
        SudokuSpace[][] lists = new SudokuSpace[9][9];
        if (board != null) {
            int i=0;
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    lists[0][i] = board[j][k].clone();
                    lists[1][i] = board[j][k+3].clone();
                    lists[2][i] = board[j][k+6].clone();
                    lists[3][i] = board[j+3][k].clone();
                    lists[4][i] = board[j+3][k+3].clone();
                    lists[5][i] = board[j+3][k+6].clone();
                    lists[6][i] = board[j+6][k].clone();
                    lists[7][i] = board[j+6][k+3].clone();
                    lists[8][i] = board[j+6][k+6].clone();
                    i++;
                }
            }
            for (i=0; i < 9; i++) { // Row number
                for (int j = 1; j <= 9; j++) { // Entry number
                    for (int k = 0; k < 9; k++) { // Column number
                        if (lists[i][k].getEntry() == j)
                            break;
                        if (k == 8) // If we got here, then we didn't match at all.
                            return false;
                    }
                }
            }

        }
        return true;
    }
}
