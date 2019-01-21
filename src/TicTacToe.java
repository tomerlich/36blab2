/**
 * Tic-Tac-Toe game
 * @author Tomer Erlich
 * @author Henry Nguyen
 * CIS 36B
 */

import java.io.*;
import java.util.*;

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
	 * Capitalizes the x or o entered by a player. Ignores already capitalized X and
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
	 * Tic-Tac-Toe:   1 2 3
     *              1 O - -
     *              2 - X -
     *              3 - - -
	 * 
	 * @param board the array representing the tic-tac-toe board
	 */
	public static void printBoard(char board[]) {
		int row = 0;
		System.out.print("\nTic-Tac-Toe:\n  ");
		for (int i = 0; i < Math.sqrt(board.length); i++) {
			System.out.printf("%d ", i + 1);
		}
		System.out.print("\n");
		for (int i = 0; i < board.length; i += (int) Math.sqrt(board.length)) {
			row++;
			for (int j = i; j < Math.sqrt(board.length) * row; j++) {
				if (j == i)
					System.out.printf("%.0f %c", j / Math.sqrt(board.length) + 1, board[j]);
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
	 * @param rowCol    the row and column, e.g. 12 or 33
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
		if (position < 11)
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
	 * method to the gameOver method.
     * Refer to the inARow method which if used for boards from 3x3 to 9x9.
	 * 
	 * @param a the first character to compare, either X, O, or -
	 * @param b the second character to compare, either X, O, or -
	 * @param c the third character to compare, either X, O or -
	 * @return whether the characters are all Xs or all Os
	 */
	public static boolean threeInRow(char a, char b, char c) {
		if (a == b && b == c && a == c)
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
		if (inARow(board, (int) Math.sqrt(board.length), 'X'))
			return true;
		else if (inARow(board, (int) Math.sqrt(board.length), 'O'))
			return true;
		else
			return false;
	}

	/**
	 * Searches the board for any lines of a given character of any given length
	 * looks for all horizontal, vertical, and the two possible winning diagonal lines.
	 * 
	 * @param board          the tic-tac-toe game board
	 * @param expectedLength the length of the line searched for
	 * @param testChar       the character that the program is looking for in the
	 *                       lines
	 * @return whether a satisfactory line exists
	 */
	public static boolean inARow(char[] board, int expectedLength, char testChar) {
		int count = 0;
		int sqrt = (int) Math.sqrt(board.length);
		// Tests Horizontal lines
		for (int i = 0; i < board.length; i += sqrt) {
			for (int j = i; j < sqrt * ((j / sqrt) + 1); j++) {
				if (count == expectedLength)
					return true;
				else if (board[j] == testChar && count != expectedLength) {
					count++;
					if (count == expectedLength)
						return true;
				} else if (expectedLength == sqrt && board[j] != testChar) {
					count = 0;
					break;
				}
			}
		}

		// Test Vertical Lines
		for (int i = 0; i < sqrt; i++) {
			for (int j = i; j <= ((board.length - sqrt) + i); j += sqrt) {
				if (count == expectedLength)
					return true;
				else if (board[j] == testChar && count != expectedLength) {
					count++;
					if (count == expectedLength)
						return true;
				} else if (expectedLength == sqrt && board[j] != testChar) {
					count = 0;
					break;
				}
			}
		}

		// Test diagonally down and to the right.
		for (int i = 0; i < board.length; i += (sqrt + 1)) {
			if (count == expectedLength)
				return true;
			else if (board[i] == testChar && count != expectedLength) {
				count++;
				if (count == expectedLength)
					return true;
			} else if (expectedLength == sqrt && board[i] != testChar) {
				count = 0;
				break;
			}
		}

		// Test diagonally down and to the left.
		for (int i = sqrt - 1; i <= board.length - sqrt; i += (sqrt - 1)) {
			if (count == expectedLength)
				return true;
			else if (board[i] == testChar && count != expectedLength) {
				count++;
				if (count == expectedLength)
					return true;
			} else if (expectedLength == sqrt && board[i] != testChar) {
				count = 0;
				break;
			}
		}
		return false;
	}

	/**
	 * Loads the board state from a file
	 * 
	 * @param board the tic-tac-toe game board
	 */
	private static void loadGame(char[] board) throws FileNotFoundException {
		Scanner inputFromFile = new Scanner(new File("gameSaves.txt"));
		inputFromFile.nextLine();
		inputFromFile.nextLine();
		for (int i = 0; i < board.length; i++) {
			board[i] = inputFromFile.nextLine().charAt(0);
		}
		inputFromFile.close();
	}

	/**
	 * Counts the number of lines in a file
	 * 
	 * @param inputFromFile scanner with for the file with the game data
	 * @return length of the file
	 */
	private static int count(Scanner inputFromFile) {
		int i = 0;
		while (inputFromFile.hasNextLine()) {
			i++;
			inputFromFile.nextLine();
		}
		return i;
	}

	/**
	 * Saves the condition of the gameboard to a file
	 * 
	 * @param board the tic-tac-toe game board
	 */
	private static void saveGame(char[] board, String playerChar, String lastPlayed) throws FileNotFoundException {
		PrintWriter saveToFile = new PrintWriter(new File("gameSaves.txt"));
		saveToFile.print("");
		saveToFile.println(playerChar);
		saveToFile.println(lastPlayed);
		for (int i = 0; i < board.length; i++) {
			saveToFile.println(board[i]);
		}

		saveToFile.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to Tic-Tac-Toe!");

		Scanner input = new Scanner(System.in);
		int numMoves = 0; // increments when player or game A.I. makes a move
		int boardSize = 0, play = 0, compPlay = 0;
		boolean donePlaying = false, playerTurn = false, inputValid = false;
		char[] board;
		String playerChar, lastPlayed, compChar, reply;

		do {
			
			// Check if the user wants to continue a game other wise start a brand new game
			System.out.print("Would you like to continue a past game?(Y/N)");
			String continueGame = input.nextLine();
			Scanner boardLoader = new Scanner(new File("gameSaves.txt"));
			if (continueGame.equalsIgnoreCase("y") && boardLoader.hasNext()) {
				playerChar = boardLoader.nextLine();
				if (playerChar.equalsIgnoreCase("x"))
					compChar = "O";
				else
					compChar = "X";

				lastPlayed = boardLoader.nextLine();
				if (playerChar.equalsIgnoreCase(lastPlayed))
					playerTurn = true;
				else
					playerTurn = true;

				boardSize = count(boardLoader);
				board = new char[boardSize];
				boardLoader.close();
				loadGame(board);
				printBoard(board);
				
			} else {
				if (!boardLoader.hasNext() && continueGame.equalsIgnoreCase("y")){
					System.out.println("You don't have any games saved! Lets start a new one!");
				}
				// Takes user input and makes sure it is a valid board size.
				// Size of boards is limited to 9x9
				do {
					System.out.print("What size board would you like to play?\nAs an example enter 9 for a 3x3 grid: ");
					boardSize = input.nextInt();
					input.nextLine();
					if ((Math.sqrt(boardSize) - (int) Math.sqrt(boardSize) > 0))
						System.out.println("That is not a perfect square. For default board enter 9.");
				} while ((Math.sqrt(boardSize) - (int) Math.sqrt(boardSize) > 0) && Math.sqrt(boardSize) > 9);

				board = new char[boardSize];
				initializeBoard(board);
				printBoard(board);

				do {
					System.out.print("Do you want to play X or O?\nX always goes first!: ");
					playerChar = input.nextLine();
					capitalize(playerChar);
					if (playerChar.equalsIgnoreCase("x")) {
						playerTurn = true;
						compChar = "O";
						inputValid = true;
					} else if (playerChar.equalsIgnoreCase("o")) {
						playerTurn = false;
						compChar = "X";
						inputValid = true;
					} else {
						compChar = "-"; // give comChar a default value
						System.out.println("That is not a valid input");
						inputValid = false;
					}
				} while (!inputValid);
			}
			do {
				if (playerTurn && numMoves < boardSize - 1) {
					System.out.print("Where will you play(rowcolumn)?: ");

					play = input.nextInt();
					input.nextLine();
					while (!alreadyTaken(board, convertToIndex(play, boardSize))) {
						System.out.print("That spot is taken already. Try again!");
						play = input.nextInt();
						input.nextLine();
					}
					makePlacement(board, play, playerChar.charAt(0));
					numMoves++;
					printBoard(board);
					if (gameOverWinner(board)) {
						System.out.println("Game over you win!");
						break;
					}
					playerTurn = false;
				} else if (!playerTurn && numMoves < boardSize - 1) {
					do {
						compPlay = randomPosition(boardSize);
					} while (!alreadyTaken(board, compPlay));
					board[compPlay] = compChar.charAt(0);
					printBoard(board);
					numMoves++;
					if (gameOverWinner(board)) {
						System.out.println("Game over computer wins!");
						break;
					}
					if (numMoves != boardSize - 1) {
						System.out.print(
								"Would you like to stop and save your game for later, press 'X' to quit without saving?(Y/N) ");
						reply = input.nextLine();
					}
					else {
						System.out.println("Game over its a tie!");
						reply = "x";
					}
					if (reply.equalsIgnoreCase("y")) {
						if (!playerTurn)
							lastPlayed = compChar;
						else {
							lastPlayed = playerChar;
							saveGame(board, playerChar, lastPlayed);
							break;
						}
					} else if (reply.equalsIgnoreCase("x")) {
						numMoves = boardSize;
						break;
					}
					playerTurn = true;
				}
			} while (numMoves < boardSize - 1);
			if(numMoves == boardSize - 1 && !gameOverWinner(board))
				System.out.println("Game over its a tie!");
			
			System.out.print("Would you like to play another game?(Y/N) ");
			String userIn = input.nextLine();

			if (userIn.equalsIgnoreCase("y")) {
				numMoves = 0;
				initializeBoard(board);
				donePlaying = false;
			} else {
				System.out.print("Ok goodbye!");
				donePlaying = true;
			}

		} while (donePlaying == false);
		input.close();
	}
}