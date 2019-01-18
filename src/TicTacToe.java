
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
	 * 
	 * @param board the array representing the tic-tac-toe board
	 */
	public static void initializeBoard(char board[]) {
		for (int i = 0; i < board.length; i++) {
			board[i] = '-';
		}
	}

	/**
	 * Capitalizes the x or o entered by a player Ignores already capitalized X and
	 * O
	 * 
	 * @param character the x or o
	 * @return the capitalized X or O
	 */
	public static char capitalize(String character) {
		return character.toUpperCase().charAt(0);
	}

	/**
	 * Prints the board to the console in the form of a grid, including column and
	 * row numbers. Also prints Tic-Tac-Toe Board above the board. For example:
	 * Tic-Tac-Toe: 
	 *  1 2 3
	 *1 O - -
	 *2 - X -
	 *3 - - -
	 * @param board the array representing the tic-tac-toe board
	 */
	public static void printBoard(char board[]) {
		int row = 0;
		System.out.print("\nTic-Tac-Toe:\n  ");
		for(int i = 0; i < Math.sqrt(board.length); i++) {
			System.out.printf("%d ", i + 1);
		}
		System.out.print("\n");
		for(int i = 0; i < board.length; i += (int) Math.sqrt(board.length)) {
			row++;
			for (int j = i; j < Math.sqrt(board.length) * row; j++) {
				if (j == i)
					System.out.printf("%.0f %c", j /Math.sqrt(board.length) + 1, board[j]);
				else
					System.out.printf(" %c", board[j]);
			}
			System.out.print("\n");
		}
	}

	/**
	 * Converts the player choice of row and column in the form of RC into the
	 * correct index of the board array. Hint: Use integer division by 10 to extract
	 * the row Hint: Use modulus by 10 to extract the column
	 * 
	 * @param rowCol the row and column, e.g. 12 or 33
	 * @param boardSize need this to find the 
	 * @return the correct index of the array that corresponds to the row and column
	 */
	public static int convertToIndex(int rowCol, int boardSize) {
		int row;
		int col;
		int index;
		
		row = rowCol / 10;
		col = rowCol % 10;
		index = (int) (Math.sqrt(boardSize) * (row - 1));
		index += col - 1;
		
		return index;
	}

	/**
	 * Determines whether a particular position on the board has already been taken.
	 * 
	 * @param board    the array representing the game board
	 * @param position the position to check
	 * @return whether that position has already been taken
	 */
	public static boolean alreadyTaken(char board[], int position) {
		if (board[position] != '-')
			return false;
		else
			return true;
	}

	/**
	 * Places an X or O into the correct position on the board. Called when either
	 * the player or computer makes its move.
	 * 
	 * @param board     the array representing the tic-tac-toe board
	 * @param position  the position in the array at which to place the X or O
	 * @param character either X or O
	 */
	public static void makePlacement(char board[], int position, char character) {
		String stringChar = "";
		stringChar += character;
		if(position < 11)
			board[position] = capitalize(stringChar);
		else
			board[convertToIndex(position, board.length)] = capitalize(stringChar);
	}

	/**
	 * Gives a random position on the board Used for generating moves by the
	 * computer
	 * 
	 * @param size the length of the board array
	 * @return a random index in the board array
	 */
	public static int randomPosition(int size) {
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(size);

	}

	/**
	 * Determines whether three characters are all Xs or all Os Used as a helper
	 * method to the gameOver method
	 * 
	 * @param a the first character to compare, either X, O, or -
	 * @param b the second character to compare, either X, O, or -
	 * @param c the third character to compare, either X, O or -
	 * @return whether the characters are all Xs or all Os
	 */
	public static boolean threeInRow(char a, char b, char c) {
		if(a == b && b == c && a == c)
			return true;
		else
			return false;
	}

	/**
	 * Determines whether the game is over due to one player winning, using a series
	 * of if statements. Calls the threeInRow method for each possible row on the
	 * board.
	 * 
	 * @param board the tic-tac-toe game board
	 * @return whether the game is over
	 */
	public static boolean gameOverWinner(char board[]) {
		if(inARow(board, (int) Math.sqrt(board.length), 'X'))
			return true;
		else if(inARow(board, (int) Math.sqrt(board.length), 'O'))
			return true;
		else
			return false;
	}
	
	/**
	 * Searches the board for any lines of a given character of any given length
	 * looks for all horizantal vertical and the two possible winning diagonal lines
	 * 
	 * @param board the tic-tac-toe game board
	 * @param expectedLength the lenght of the line searched for
	 * @param testChar the character that the program is looking for in the lines
	 * @return whether a satisfactory line exists
	 */
	public static boolean inARow(char[] board, int expectedLength, char testChar) {
		int count = 0;
		int sqrt =  (int) Math.sqrt(board.length);
		// Tests Horiz lines
		// This appears to work
		for(int i = 0; i < board.length; i += sqrt) {
			for (int j = i; j < sqrt * ((j/sqrt) +1 ); j++) {
				if(count == expectedLength)
					return true;
				else if (board[j] == testChar && count != expectedLength)
					count++;
				else if (expectedLength == sqrt && board[j] != testChar) {
					count = 0;
					break;
				}
			}
		}
		
		//Test Vert. Lines
		for (int i = 0; i < sqrt; i++) {
			for (int j = i; j <= ((board.length - sqrt) + i); j += sqrt) {
				if(count == expectedLength)
					return true;
				else if (board[j] == testChar && count != expectedLength)
					count++;
				else if (expectedLength == sqrt && board[j] != testChar) {
					count = 0;
				}
			}
		}
		
		//Test diagonally down and to the right.
		for (int i = 0; i < board.length; i += (sqrt + 1)) {
			if(count == expectedLength)
				return true;
			else if (board[i] == testChar && count != expectedLength)
				count++;
			else if (expectedLength == sqrt && board[i] != testChar) {
				count = 0;
				break;
			}
		}
		
		//Test diagonally down and to the left.
		for (int i = sqrt - 1; i < board.length - sqrt; i += (sqrt - 1)) {
			if(count == expectedLength)
				return true;
			else if (board[i] == testChar && count != expectedLength)
				count++;
			else if (expectedLength == sqrt && board[i] != testChar) {
				count = 0;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Tic-Tac-Toe!");
		Scanner input = new Scanner(System.in);
		int numMoves = 0; // increments when player or game A.I. makes a move
		int boardSize = 0, play = 0, compPlay = 0;
		boolean donePlaying = false;
		char compChar;
		
		do {
			System.out.print("What size board would you like to play?: ");
			boardSize =  input.nextInt();
			input.nextLine();
			if((Math.sqrt(boardSize) - (int) Math.sqrt(boardSize) > 0))
				System.out.println("That is not a perfect square. For default enter 9.");
		}while ((Math.sqrt(boardSize) - (int) Math.sqrt(boardSize) > 0));
		
		char board[] = new char[boardSize];
		initializeBoard(board);
		printBoard(board);
		System.out.print("do you want to play x or o?: ");
		String playerChar = input.nextLine();
		capitalize(playerChar);
		if (playerChar.equalsIgnoreCase("x"))
			compChar = 'O';
		else
			compChar = 'X';
		
		do {
			do {
				System.out.println("Where will you play?: ");
				play = input.nextInt();
				input.nextLine();
				makePlacement(board, play, playerChar.charAt(0));
				numMoves++;
				printBoard(board);
				if (gameOverWinner(board)) {
					System.out.println("Game over you win!");
					break;
				}
				do {
					compPlay = randomPosition(boardSize);
				}while (alreadyTaken(board, compPlay) == false);
				board[compPlay] = compChar;
				printBoard(board);
				numMoves++;
				if (gameOverWinner(board)) {
					System.out.println("Game over computer wins!");
					break;
				}
			}while (numMoves < boardSize - 1);
			System.out.print("would you like to play again?(y/n)");
			String userIn = input.nextLine();
			if (userIn.equalsIgnoreCase("y")) {
				numMoves = 0;
				initializeBoard(board);
				donePlaying = false;
			}
			else {
				System.out.print("Ok goodbye!");
				donePlaying = true;
			}
		}while (donePlaying == false);
		input.close();
	}

}