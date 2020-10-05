package com.workshop.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	private char[] board;
	private static char userSelection;
	private static char computerSelection;
	private static Scanner input=new Scanner(System.in);
	
	private char[] board() {
		board = new char[10];
		Arrays.fill(board, ' ');
		return board;
	}

	public static void choice() {
		System.out.println("Enter X or O");
		char charChoice = input.next().charAt(0);
		TicTacToeGame.userSelection = charChoice;
		TicTacToeGame.computerSelection = (charChoice == 'X') ? 'O' : 'X';
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
				System.out.println("\n-----------");
			}
		}
		System.out.println("\n");
	}

	public static boolean isPlaceAvailable(char[] board, int choice) {
		if (board[choice] == ' ')
			return true;
		return false;
	}

	public static boolean coinToss()
	{
		System.out.println("Enter heads or tails: (H/T)");
		char sideChoice = input.next().charAt(0);
		int toss=(int)(Math.random()*2);
		if(toss==0 && sideChoice=='H'||toss==0 && sideChoice=='h')
			return true;
		else if(toss==1 && sideChoice=='T'||toss==1 && sideChoice=='t')
			return true;
		else 
			return false;
		
	}
	
	public static void takeUserInput(char[] board) {
		System.out.println("Enter choice: \n");
		int choice = input.nextInt();
		if (isPlaceAvailable(board, choice))
			board[choice] = TicTacToeGame.userSelection;
		else
			System.out.println("Place not available!");
		displayCurrentBoard(board);
	}

	public static void main(String args[]) {
		TicTacToeGame newGame = new TicTacToeGame();
		char[] board = newGame.board();
		if(coinToss())
		{	
			choice();
			displayCurrentBoard(board);
			takeUserInput(board);
		}	
		else	
			System.out.println("Computer gets the first turn");
	}
}