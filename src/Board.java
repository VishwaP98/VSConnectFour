import javax.swing.*;

/**
 * 
 * Created by Sunny on 3/21/2016
 * Edited by Vishwa on 3/26/2016
 */
public class Board {
	private Box[][] board = new Box[7][7];
	private int[] height = new int[7];
	public Board(){
		for (int i=0; i<7; i++){
			for (int c=0; c<7; c++){
				board[c][i] = new Box();
			}
		}
		for (int i=0; i<7; i++){
			height[i] = 6;
		}
	}
	
	public void resetBoard(){

		for (int i=0; i<7; i++){
			height[i] = 0;
		}
	}
	public Box getBox(int row,int column)
	{
		return board[row][column];
	}
	public boolean addPiece(int column, boolean xTurn){
		column--;
		if (height[column] < 0){
			JOptionPane.showMessageDialog(null, "Error. Column is full");
			return false;
		}
		
		if(xTurn){
			board[column][height[column]].setX();

		}
		else{
			board[column][height[column]].setO();
		}
		GamePanel.putChip((column), height[column], xTurn);
		height[column]--;

		return true;
	}

	
	public boolean checkWin(){ return true;}
	public boolean checkWinVertical(boolean xTurn, int column){
		column--;
		char chip;
		if (xTurn) {
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]-1;
		
		int count;
		while (temp == chip && currentRow >0){
				currentRow--;
				temp = board[currentCol][currentRow].getPiece();
			}
		
		temp = chip;
		count = 0;
		while (temp == chip){
			if (currentRow != 6){
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
			}
			else{
				temp = '-';
			}
			
		}
		

		return (count >=4);
	}
	
	public boolean checkWin(boolean xTurn, int column){
		boolean horizontal = checkWinHorizontal(xTurn, column);
		boolean vertical = checkWinVertical (xTurn, column);
		boolean diagonal = checkWinDiagonal (xTurn, column);
		return (horizontal || vertical || diagonal);
	}
	public boolean checkWinHorizontal(boolean xTurn, int column){
		column--;
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]-1;
		
		int count;
		while (temp == chip && currentCol >0){
				currentCol--;
				temp = board[currentCol][currentRow].getPiece();
			}
		temp = chip;
		count = 0;
		while (temp == chip){
			if (currentCol != 6){
				currentCol++;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
			}
			else{
				temp = '-';
			}
			
		}

		return (count >=4);
	}
	public boolean checkWinDiagonal(boolean xTurn, int column){
		column--;
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]-1;
		
		int count;
		while (temp == chip && currentCol >0 && currentRow >0){
				currentCol--;
				currentRow--;
				temp = board[currentCol][currentRow].getPiece();
			}
		temp = chip;
		count = 0;
		while (temp == chip){
			if (currentCol != 6 && currentRow != 6){
				currentCol++;
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
			}
			else{
				temp = '-';
			}
			
		}
		if (count >= 4){
			return true;
		}
		
		
		temp = chip;
		
		currentCol = column;
		currentRow = height[column]-1;
		
		count = 0;
		while (temp == chip && currentCol !=6 && currentRow >0){
				currentCol++;
				currentRow--;
				temp = board[currentCol][currentRow].getPiece();
			}
		temp = chip;
		count = 0;
		while (temp == chip){
			if (currentCol >0 && currentRow != 6){
				currentCol--;
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
			}
			else{
				temp = '-';
			}
		}
		return (count >=4);
	

	
		
	}
}
