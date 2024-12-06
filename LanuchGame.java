package com.game;

import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;

	public TicTacToe()
	{
		 board = new char[3][3];
		 initializeBoard();
	}
	
	void initializeBoard()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				board[i][j] = ' ';
			}
		}
	}
	
	static void displayBoard()
	{
		System.out.println("-------------");
		
		for (int i = 0; i < board.length; i++) 
		{
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) 
			{
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	 
	static void placeMark(int row, int column, char mark)
	{
		if(row >= 0 && row <= 2 && column >= 0 && column <= 2)
		{
			board[row][column] = mark;
		}
		else
		{
			System.out.println("Invalid Position");
		}
	}

	static Boolean checkColumnWin()
	{
		for (int j = 0; j <= 2; j++) 
		{
			if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])  
 			{
				return true;
			}
		}
		return false;
	}
	
	static Boolean checkRowWin()
	{
		for (int i = 0; i <= 2; i++) 
		{
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])  
 			{
				return true;
			}
		}
		return false;
	}
	
	static Boolean checkDiagonalWin() 
	{
		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]  ||
	       board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])  
		{
			return true;
		}else {
			return false;
		}
	}
	
	static Boolean checkDraw()
	{
		for (int i = 0; i <= 2; i++)
		{
			for (int j = 0; j <= 2; j++) 
			{
				if(board[i][j] == ' ')
				{
					return false;
				}
			}
		}
		return true;
	}
	
}


abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	Boolean isValidMove(int row, int column)
	{
		if(row >= 0 && row <= 2 &&
		   column >= 0 && column <= 2)
		{
			if(TicTacToe.board[row][column] == ' ')
			{
				return true;
			}
		}
		return false;
	}
}


 

class HumanPlayer extends Player
{
	
	public HumanPlayer(String name, char mark) 
	{
 		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
		Scanner scanner = new Scanner(System.in);
		int row,column; 
		do 
		{
		  System.out.println("Enter the row and column");
		  row = scanner.nextInt();
		  column = scanner.nextInt();
		   
		  if (!isValidMove(row, column)) {
              System.out.println("Invalid Position");
          }

		} 
		while(! isValidMove(row, column));
		 
		TicTacToe.placeMark(row, column, mark);
	} 
	
}



class AIPlayer extends Player
{
 	
	public AIPlayer(String name, char mark) 
	{
 		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
 		int row,column; 
		do 
		{
			Random random = new Random();
			row = random.nextInt(3);
			column = random.nextInt(3);
		} 
		while(! isValidMove(row, column));
		 
		TicTacToe.placeMark(row, column, mark);
	} 
	
}


  
public class LanuchGame 
{
 	public static void main(String[] args)
	{
 		TicTacToe t = new TicTacToe();
		
 		HumanPlayer player1 = new HumanPlayer("Akash",'X');
 		AIPlayer player2 = new AIPlayer("AI",'O');
 		
 		Player currentPlayer;
 		currentPlayer = player1;
 		
		while(true)
		{
			System.out.println(currentPlayer.name + " Turn");
			currentPlayer.makeMove(); 
			TicTacToe.displayBoard();
			
			if(TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() ||
					TicTacToe.checkDiagonalWin())
			{
				System.out.println(currentPlayer.name + " has Won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game is a draw");
				break;
			}
			else
			{
				if(currentPlayer == player1)
				{
					currentPlayer = player2;
				}
				else
				{
					currentPlayer = player1;
				}
	 		}
			
		}
 	}
 
}
