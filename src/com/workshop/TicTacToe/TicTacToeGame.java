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
		flag=true;
		turn=true;
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
	
	public static boolean checkIfEqual(char[] board, int pos1, int pos2, int pos3, char playerSymbol)
	{
		if((board[pos1]==playerSymbol)&&(board[pos1]==board[pos2])&&(board[pos2]==board[pos3]))
			return true;
		return false;
	}

	public static int checkBoardConditions(char[] board, char playerSymbol)
	{
		if(board.length!=9)
		{	
			if((checkIfEqual(board, 1,2,3,playerSymbol))||(checkIfEqual(board, 4,5,6,playerSymbol))||(checkIfEqual(board, 7,8,9,playerSymbol))||
			  (checkIfEqual(board, 1,4,7,playerSymbol))||(checkIfEqual(board, 2,5,8,playerSymbol))||(checkIfEqual(board, 3,6,9,playerSymbol))||
			  (checkIfEqual(board, 1,5,9,playerSymbol))||(checkIfEqual(board, 3,5,7,playerSymbol)))
					
				{
					flag=false;
					return 2;
				}
			else
				return 0;
		}	
		else
		{
			flag=false;
			return 1;
		}
	}
	
	public static void checkResult(int result, char playerSymbol)
	{
		if(result==2)
			System.out.println(playerSymbol + " is the winner \n Game Over!!!");
		else if(result==1)
			System.out.println("It's a Tie.");
		else
			turn=!turn;
	}
	
	public static void takeUserInput(char[] board) {
		int check=0;
		while(check==0)
		{
			System.out.println("Enter choice: \n");
			int choice = input.nextInt();
			if (isPlaceAvailable(board, choice))
			{	
				board[choice] = userSelection;
				check=1;
			}
			else
				System.out.println(choice+": place not available!");
		}
		displayCurrentBoard(board);
	}
	
	public static void takeComputerInput(char[] board)
	{
		int check=0;
		while(check==0)
		{
			int position=1+(int)(Math.random()*9);
			if (isPlaceAvailable(board, position))
			{	
				board[position] = computerSelection;
				check=1;
			}
			else
				System.out.println(position+": place not available!");
		}
		displayCurrentBoard(board);
	}

	public static void main(String args[]) {
		TicTacToeGame newGame = new TicTacToeGame();
		char[] board = newGame.board();
		if (coinToss())
		{	
			System.out.println("You win!");
			choice();
		}
		else
		{	
			computerSelection='X';
			userSelection='O';
			System.out.println("You lose! Computer gets the first turn.");
			turn=false;
		}	
		
		while(flag)
		{
			if(turn)
			{
				System.out.println("User's Turn.");
				displayCurrentBoard(board);
				takeUserInput(board);
				int result=checkBoardConditions(board,userSelection);
				checkResult(result,userSelection);
			}
			else
			{
				System.out.println("Computer's turn... Please wait...");
				takeComputerInput(board);
				int result=checkBoardConditions(board,computerSelection);
				checkResult(result,computerSelection);
				
			}
			
		}
	}	
}