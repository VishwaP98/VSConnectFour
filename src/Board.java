/**
 * 
 * Created by Sunny on 3/21/2016
 *
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
			height[i] = 0;
		}
	}
	
	public void resetBoard(){
		
		for (int i=0; i<7; i++){
			height[i] = 0;
		}
	}
	
	public boolean addpiece(int column, boolean xTurn){
		if (height[column-1] >= 7){
			return false;
		}
		
		if(xTurn){

			board[column-1][height[column-1]].setX();
			height[column-1]++;
			
		}
		else{
			board[column-1][height[column-1]].setO();
			height[column-1]++;
		}
		
		return true;
	}
	
	public void print(){
		for (int i=6; i>=0; i--){
			for (int c=0; c<7; c++){
				System.out.print(board[c][i].getPiece() + " ");
			}
			System.out.println("");
		}
	}
	
	public boolean checkWin(){ return true;};
	public boolean checkWinVertical(boolean xTurn, int column){
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
		
		int count = 0;
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
		
		int count = 0;
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
		
		int count = 0;
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
