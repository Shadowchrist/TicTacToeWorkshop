package com.workshop.TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	private char board[];
	private void board()
	{
		board=new char[10];
		Arrays.fill(board, ' ');
	}
	
	public static char choice()
	{
		System.out.println("Enter X or O");
		@SuppressWarnings("resource")
		char charChoice=(new Scanner(System.in)).next().charAt(0);
		return charChoice;
	}
	
	public static void main(String args[])
	{
		TicTacToeGame newGame=new TicTacToeGame();
		newGame.board();
		char selection= choice();
		switch(selection)
		{
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
}	