import java.util.Arrays;

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
	public int checkWinVertical(boolean xTurn, int column){
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
		int currentRow = height[column];
		
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
		

		return (count);
	}
	public void doThingy(){
		System.out.println(checkWinHorizontal(true, 1));
	}
	public int aiPicker(){
		int[] offensive = new int[7];
		for (int col = 0; col < 7; col++){
			
			int horizontal = checkWinHorizontal(false, col+1);
			int vertical = checkWinVertical(false, col+1);
			int diagonal = checkWinDiagonal(false, col+1);
			if (horizontal >= vertical && horizontal >= diagonal){
				offensive[col]= horizontal;
			}
			else if(vertical >= horizontal && vertical >= diagonal){
				offensive[col] = vertical;
			}
			if (diagonal >= horizontal && diagonal >= vertical){
				offensive[col] = diagonal;
			}	
		}
		
		int[] defensive = new int[7];
		for (int col=0; col<7; col++){
			int horizontal = checkWinHorizontal(true, col+1);
			int vertical = checkWinVertical(true, col+1);
			int diagonal = checkWinDiagonal(true, col+1);
			if (horizontal >= vertical && horizontal >= diagonal){
				defensive[col]= horizontal;
			}
			else if(vertical >= horizontal && vertical >= diagonal){
				defensive[col] = vertical;
			}
			if (diagonal >= horizontal && diagonal >= vertical){
				defensive[col] = diagonal;
			}	
			}
		int max = 0;
		int maxIndex = 0;
		for (int i=0; i<7; i++){
			

			if (offensive[i] > max){
				max = offensive[i];
				maxIndex = i;
			}

		}
		int defMax = 0;
		int defMaxIndex = 0;
		for (int i=0; i<7; i++){
			
			if (defensive[i] > defMax){
				defMax = defensive[i];
				defMaxIndex = i;
			}
		}
		System.out.println(defMax);
		if (defMax >3){
			return defMaxIndex+1;
		}
		else{
			return maxIndex+1;
		}
		
	}
	
	public boolean checkWin(boolean xTurn, int column){
		boolean horizontal = checkWinHorizontal(xTurn, column)>4;
		boolean vertical = checkWinVertical (xTurn, column)>4;
		boolean diagonal = checkWinDiagonal (xTurn, column)>4;
		return (horizontal || vertical || diagonal);
	}
	private int checkWinHorizontal(boolean xTurn, int column){
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
		int currentRow = height[column];
		
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

		return (count);
	}
	private int checkWinDiagonal(boolean xTurn, int column){
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
		int currentRow = height[column];
		
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
		
		
		temp = chip;
		
		currentCol = column;
		currentRow = height[column];
		
		int countOther = 0;
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
		if (count >= countOther){
			return count;
		}
		else{
			return countOther;
		}
	
		
	}
}
