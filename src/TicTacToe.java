/**
 * Tic-Tac-Toe game
 * @author 
 * @author
 * CIS 36B
 */

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    /**
     * Initializes the board by assigning all array elements the value of '-'
     * @param board the array representing the tic-tac-toe board
     */
    public static void initializeBoard(char board[]) {
        for(int i = 0; i < board.length; i++) {
        	board[i] = '-';
        }
    }
    
    /**
     * Capitalizes the x or o entered by a player
     * Ignores already capitalized X and O
     * @param character the x or o
     * @return the capitalized X or O
     */
    public static char capitalize(String character) {
        return character.toUpperCase().charAt(0);
    }
    
    /**
     * Prints the board to the console in the form of a grid,
     * including column and row numbers. Also prints
     * Tic-Tac-Toe Board above the board. For example:
     * Tic-Tac-Toe:
     *   1 2 3
     * 1 O - - 
     * 2 - X - 
     * 3 - - -
     * @param board the array representing the tic-tac-toe board
     */
    public static void printBoard(char board[]) {
        System.out.print("\nTic-Tac-Toe:\n ");
        
    }
    
    /**
     * Converts the player choice of row and column
     * in the form of RC into the correct index of 
     * the board array.
     * Hint: Use integer division by 10 to extract the row
     * Hint: Use modulus by 10 to extract the column
     * @param rowCol the row and column, e.g. 12 or 33
     * @return the correct index of the array that 
     * corresponds to the row and column
     */
    public static int convertToIndex(int rowCol) {
        return -1;
    }
    
    /**
     * Determines whether a particular position 
     * on the board has already been taken.
     * @param board the array representing the game board
     * @param position the position to check
     * @return whether that position has already been taken
     */
    public static boolean alreadyTaken(char board[], int position) {
        return false;
    }
    
    /**
     * Places an X or O into the correct position on the board.
     * Called when either the player or computer makes its move.
     * @param board the array representing the tic-tac-toe board
     * @param position the position in the array at which to place the X or O
     * @param character either X or O
     */
    public static void makePlacement(char board[], int position, char character) {
        
    }
    
    /**
     * Gives a random position on the board
     * Used for generating moves by the computer
     * @param size the length of the board array
     * @return a random index in the board array
     */
    public static int randomPosition(int size) {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(size);
        
    }
    
    /**
     * Determines whether three characters are all Xs or all Os
     * Used as a helper method to the gameOver method
     * @param a the first character to compare, either X, O, or -
     * @param b the second character to compare, either X, O, or -
     * @param c the third character to compare, either X, O or -
     * @return whether the characters are all Xs or all Os
     */
    public static boolean threeInRow(char a, char b, char c) {
        return false;
    }
    
    /**
     * Determines whether the game is over 
     * due to one player winning, using
     * a series of if statements.
     * Calls the threeInRow method for each
     * possible row on the board.
     * @param board the tic-tac-toe game board
     * @return whether the game is over
     */
    public static boolean gameOverWinner(char board[]) {
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        char board[] = new char[9];
        int numMoves = 0; //increments when player or game A.I. makes a move
        
    }
    
}