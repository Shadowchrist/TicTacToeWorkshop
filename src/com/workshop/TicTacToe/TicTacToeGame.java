package com.workshop.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	private char[] board;
	private static char userSelection;
	private static char computerSelection;
	private static boolean flag;
	private static boolean turn;
	private static Scanner input = new Scanner(System.in);

	private char[] board() {
		board = new char[10];
		flag = true;
		turn = true;
		Arrays.fill(board, ' ');
		return board;
	}

	public static void choice() {
		System.out.println("Enter X or O");
		char charChoice = input.next().charAt(0);
		userSelection = charChoice;
		computerSelection = (charChoice == 'X') ? 'O' : 'X';
		switch (charChoice) {
		case 'X':
			System.out.println("X is assigned to the player");
			System.out.println("O is assigned to the computer");
			break;
		case 'O':
			System.out.println("O is assigned to the player");
			System.out.println("X is assigned to the computer");
			break;
		default:
			System.out.println("Wrong Choice!");
		}
	}

	public static void displayCurrentBoard(char board[]) {
		for (int i = 1; i < 10; i++) {
			if (i % 3 != 0)
				System.out.print(" " + board[i] + " |");
			else
				System.out.print(" " + board[i] + " ");
			if (i % 3 == 0 && i != 9) {
				System.out.println("\n---|---|---");
			}
		}
		System.out.println("\n");
	}

	public static boolean isPlaceAvailable(char[] board, int choice) {
		if (board[choice] == ' ')
			return true;
		return false;
	}

	public static boolean areAnyMovesLeft(char[] board) {
		for (int i = 1; i < 10; i++) {
			if (board[i] == ' ')
				return true;
		}
		return false;
	}

	public static boolean coinToss() {
		System.out.println("Enter heads or tails: (H/T)");
		char sideChoice = input.next().charAt(0);
		int toss = (int) (Math.random() * 2);
		if ((toss == 0 && sideChoice == 'H') || (toss == 0 && sideChoice == 'h'))
			return true;
		else if ((toss == 1 && sideChoice == 'T') || (toss == 1 && sideChoice == 't'))
			return true;
		else
			return false;
	}

	public static boolean checkIfEqual(char[] board, int pos1, int pos2, int pos3, char playerSymbol) {
		if ((board[pos1] == playerSymbol) && (board[pos1] == board[pos2]) && (board[pos2] == board[pos3]))
			return true;
		return false;
	}

	public static int checkBoardConditions(char[] board, char playerSymbol) {
		if (areAnyMovesLeft(board)) {
			if ((checkIfEqual(board, 1, 2, 3, playerSymbol)) || (checkIfEqual(board, 4, 5, 6, playerSymbol))
					|| (checkIfEqual(board, 7, 8, 9, playerSymbol)) || (checkIfEqual(board, 1, 4, 7, playerSymbol))
					|| (checkIfEqual(board, 2, 5, 8, playerSymbol)) || (checkIfEqual(board, 3, 6, 9, playerSymbol))
					|| (checkIfEqual(board, 1, 5, 9, playerSymbol)) || (checkIfEqual(board, 3, 5, 7, playerSymbol)))

			{
				flag = false;
				return 2;
			} else
				return 0;
		} else {
			flag = false;
			return 1;
		}
	}

	public static void checkResult(int result, char playerSymbol) {
		if (result == 2) {
			System.out.println(playerSymbol + " is the winner \n Game Over!!!");
		} else if (result == 1) {
			System.out.println("It's a Tie.");
		} else
			turn = !turn;
	}

	public static int noWinningCase(char[] board) {
		int ar1[] = { 1, 3, 7, 9 }, ar2[] = { 2, 4, 6, 8 };
		int check = 0, temp = 0;
		while (check < 4) {
			if (isPlaceAvailable(board, ar1[check]))
				return ar1[check];
			else if (board[ar1[check]] == userSelection && isPlaceAvailable(board, ar1[3 - check]))
				return ar1[3 - check];
			else {
				check++;
				temp++;
			}
		}
		if (temp > 3) {
			if (isPlaceAvailable(board, 5))
				return 5;
			else {
				check = 0;
				while (check < 4) {
					if (isPlaceAvailable(board, ar2[check]))
						return ar2[check];
					check++;
				}
			}
		}
		return 0;
	}

	public static int checkIfPlayerIsWinning(char[] board, char playerSymbol) {
		int pos = 0;
		for (int j = 1; j < 10; j++) {
			if (isPlaceAvailable(board, j)) {
				board[j] = playerSymbol;
				if ((checkIfEqual(board, 1, 2, 3, playerSymbol)) || (checkIfEqual(board, 4, 5, 6, playerSymbol))
						|| (checkIfEqual(board, 7, 8, 9, playerSymbol)) || (checkIfEqual(board, 1, 4, 7, playerSymbol))
						|| (checkIfEqual(board, 2, 5, 8, playerSymbol)) || (checkIfEqual(board, 3, 6, 9, playerSymbol))
						|| (checkIfEqual(board, 1, 5, 9, playerSymbol))
						|| (checkIfEqual(board, 3, 5, 7, playerSymbol))) {
					pos = j;
					break;
				}
				board[j] = ' ';
			}
		}
		board[pos] = ' ';
		return pos;
	}

	public static int nextBestMove(char[] board) {
		if (checkIfPlayerIsWinning(board, computerSelection) != 0)
			return checkIfPlayerIsWinning(board, computerSelection);
		else if (checkIfPlayerIsWinning(board, userSelection) != 0)
			return checkIfPlayerIsWinning(board, userSelection);
		else if (noWinningCase(board) != 0)
			return noWinningCase(board);
		else
			return 0;
	}

	public static void putComputerInput(char[] board) {
		board[nextBestMove(board)] = computerSelection;
	}

	public static void takeUserInput(char[] board) {
		int check = 0;
		while (check == 0) {
			System.out.println("Enter choice: \n");
			int choice = input.nextInt();
			if (isPlaceAvailable(board, choice)) {
				board[choice] = userSelection;
				check = 1;
			} else
				System.out.println("Place not available!");
		}
		displayCurrentBoard(board);
	}

	public static void main(String args[]) {
		TicTacToeGame newGame = new TicTacToeGame();
		char[] board = newGame.board();
		if (coinToss()) {
			System.out.println("You win!");
			choice();
		} else {
			computerSelection = 'X';
			userSelection = 'O';
			System.out.println("You lose! Computer gets the first turn.");
			turn = false;
		}
		displayCurrentBoard(board);
		while (flag) {
			if (turn) {
				System.out.println("User's Turn.");
				takeUserInput(board);
				int result = checkBoardConditions(board, userSelection);
				checkResult(result, userSelection);
			} else {
				System.out.println("Computer's turn... Please wait...");
				putComputerInput(board);
				displayCurrentBoard(board);
				int result = checkBoardConditions(board, computerSelection);
				checkResult(result, computerSelection);
			}
		}
	}
}