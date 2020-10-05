package com.workshop.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	private char[] board;
	@SuppressWarnings("unused")
	private static char userSelection;
	@SuppressWarnings("unused")
	private static char computerSelection;

	private char[] board() {
		board = new char[10];
		Arrays.fill(board, 'X');
		return board;
	}

	public static char choice() {
		System.out.println("Enter X or O");
		@SuppressWarnings("resource")
		char charChoice = (new Scanner(System.in)).next().charAt(0);
		TicTacToeGame.userSelection = charChoice;
		TicTacToeGame.computerSelection = (charChoice == 'X') ? 'O' : 'X';
		return charChoice;
	}

	public static void displayCurrentBoard(char board[]) {
		for (int i = 1; i < 10; i++) {
			if(i%3!=0)
				System.out.print(" " + board[i] + " |");
			else
				System.out.print(" " + board[i] + " ");
			if (i % 3 == 0 && i != 9) {
				System.out.println("\n-----------");
			}
		}
	}

	public static void main(String args[]) {
		TicTacToeGame newGame = new TicTacToeGame();
		char[] board = newGame.board();
		char selection = choice();
		switch (selection) {
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

		displayCurrentBoard(board);
	}
}