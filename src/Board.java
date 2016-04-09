import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Sunny Patel
 * @version 7.1.1
 * 
 * Class handling all activities for the board
 * Creates 49 Box object instances
 * 
 * Created by Sunny on 03/21/2016

 */
public class Board {
	private Box[][] board = new Box[7][7];
	private int[] height = new int[7];
	
	/**
	 * Board object constructor
	 * @param none
	 * @return none
	 */
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
	
	/**
	 * Returns the value of a given box on the board
	 * @param column
	 * 				int: the column number (where 0 is column 1 for user)
	 * @param row
	 * 				int: the row number
	 * @return
	 * 				Box: the box at given coordinates
	 */
	public Box getBox(int column, int row)
	{
		return board[column][row];
	}
	
	/**
	 * Returns the height of a column (where 6 is empty and 0 is full)
	 * @param column
	 * 				int: the column number (where 0 is column 1 for user)
	 * @return
	 * 				int: the height of the column following previously mentioned standards
	 */
	public int getHeight(int column)
	{
		return height[column];
	}
	
	/**
	 * Adds a piece to the board
	 * @param column
	 * 				int: the column number to add the piece (where 0 is column 1 for user)
	 * @param xTurn
	 * 				boolean: true if X's turn, false if O's turn
	 * @return
	 * 				boolean: false if column is full, true otherwise
	 */
	public boolean addpiece(int column, boolean xTurn){
		if (height[column] < 0 || height[column] > 6){
			return false;
		}
		if(xTurn){
			board[column][height[column]].setX();
		}
		else{
			board[column][height[column]].setO();
		}
		GamePanel.putChip(column, height[column], xTurn);
		height[column]--;

		return true;
	}
	
	/**
	 * Sets a temporary piece for Ai decisions
	 * @param column
	 * 				int: the column to which the piece will be added
	 * @param xTurn
	 * 				boolean: ture if adding X piece, false for O
	 */
	private void setPieceTemp(int column, boolean xTurn){
		if(xTurn){
			board[column][height[column]].setX();
		}
		else{
			board[column][height[column]].setO();
		}
		height[column]--;
	}
	
	/**
	 * Removes piece and undoes any changes made by the setPieceTemp method
	 * @param column
	 * 				int: the column from which to remove the piece
	 */
	private void removePiece(int column){
		board[column][height[column]+1].setBlank();
		height[column]++;
	}
	
	/**
	 * Prints out the behind-the-scenes text based board.  Use for troubleshooting and development.
	 */
	public void print(){
		for (int i=6; i>=0; i--){
			for (int c=0; c<7; c++){
			}
		}
	}
	
	/**
	 * Finds how many chips either player has stacked vertically
	 * @param xTurn
	 * 				boolean: True if X's turn, False for O
	 * @param column
	 * 				int: column to which the piece will be added
	 * @return
	 * 				int: The number of chips the player has in a vertical row
	 */
	private int checkWinVertical(boolean xTurn, int column){
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]+1;
		
		int count = 0;
		while (temp == chip &&currentRow <6){
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				if(temp != chip){
					currentRow--;
				}
			}
		temp = chip;
		count = 0;
		while (temp == chip && currentRow <=6 && currentRow >=0){
			if (currentRow != 0){
				currentRow--;	
				
				temp = board[currentCol][currentRow].getPiece();
				count++;
			}
			else{
				if (temp == chip){
					count++;
				}
				break;
			}
				
			
		}
		return (count);
	}
	
	/**
	 * The AI/COM that will choose the best column to add the piece to
	 * @return
	 * 				int: The column to add the piece to
	 */
	public int aiPicker(){
		int[] offensive = new int[7];
		for (int col = 0; col < 7; col++){
			if (height[col]>0){
				setPieceTemp(col, false);
				int horizontal = checkWinHorizontal(false, col);
				int vertical = checkWinVertical(false, col);
				int diagonalR = checkWinDiagonalRight(false, col);
				int diagonalL = checkWinDiagonalLeft(false, col);
				removePiece(col);
				int[] a = new int[]{horizontal, vertical, diagonalR, diagonalL};
				Arrays.sort(a);
				offensive[col]=a[3];
			}
			else{
				offensive[col] = -1;
			}
			
		}
		
		int[] defensive = new int[7];
		for (int col=0; col<7; col++){
			if (height[col] >0){
				setPieceTemp(col, true);
				int horizontal = checkWinHorizontal(true, col);
				int vertical = checkWinVertical(true, col);
				int diagonalR = checkWinDiagonalRight(true, col);
				int diagonalL = checkWinDiagonalLeft(true, col);
				int[] a = new int[]{horizontal, vertical, diagonalR, diagonalL};
				Arrays.sort(a);
				defensive[col]=a[3];
				removePiece(col);
					
			}
			else{
				defensive[col] = -1;
			}
			
			}
		
		int max = 0;
		int maxIndex = 0;
		for (int i=0; i<7; i++){
			if (offensive[i] > max ){
				max = offensive[i];
				maxIndex = i;
			}

		}
		int defMax = 0;
		int defMaxIndex = 0;
		for (int i=0; i<7; i++){
			
			if (defensive[i] > defMax ){
				defMax = defensive[i];
				defMaxIndex = i;
			}
		}
		
		if (defMax >3 && max <4){
			return defMaxIndex;
		}
		else{
			return maxIndex;
		}
		
	}
	
	/**
	 * checks for victory
	 * @param xTurn
	 * 				boolean: True if X's turn, false for O's turn
	 * @param column
	 * 				int: column number (where column 0 is column 1 for user)
	 * @return
	 * 				boolean: True if won, false otherwise
	 */
	public boolean checkWin(boolean xTurn, int column){
		boolean horizontal = checkWinHorizontal(xTurn, column)>=4;
		boolean vertical = checkWinVertical (xTurn, column)>=4;
		boolean diagonalR = checkWinDiagonalRight (xTurn, column)>=4;
		boolean diagonalL = checkWinDiagonalLeft (xTurn, column)>=4;
		return (horizontal || vertical || diagonalR || diagonalL);
	}
	
	/**
	 * Checks to see how many chips the player has in a row horizontally
	 * @param xTurn
	 * 				boolean: True if X's turn, false for O's turn
	 * @param column
	 * 				int: column number (where column 0 is column 1 for user)
	 * @return
	 * 				int: number of chips given player has in a row horizontally
	 */
	private int checkWinHorizontal(boolean xTurn, int column){
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}

		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]+1;
		

		int count = 0;
		while (temp == chip && currentCol >0){
			currentCol--;
			temp = board[currentCol][currentRow].getPiece();
			
			if(temp != chip){
				currentCol++;
			}
		}

		temp = chip;
		count = 0;
		while (temp == chip&&currentCol <= 6){

				if (currentCol!=6){
					currentCol++;
					temp = board[currentCol][currentRow].getPiece();
					count++;
				}
				else{
					if (temp == chip){
						count++;
					}
					break;
				}

		}
		return (count);
	}
	
	/**
	 * Checks to see how many chips the player has in a row diagonally to the right
	 * @param xTurn
	 * 				boolean: True if X's turn, false for O's turn
	 * @param column
	 * 				int: column number (where column 0 is column 1 for user)
	 * @return
	 * 				int: number of chips given player has in a row 
	 */
	private int checkWinDiagonalRight(boolean xTurn, int column){
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]+1;
		
		int count = 0;
		while (temp == chip && currentCol >0 && currentRow <6){
				currentCol--;
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				if(temp != chip){
					currentRow++;
					currentCol--;
				}
			}
		temp = chip;
		while (temp == chip){
			if (currentCol <= 6 && currentRow >=0){
				if (currentCol == 6){
					if (temp == chip){
						count++;
					}
					break;
				}
				if (currentRow == 0){
					if (temp == chip){
						count++;
					}
					break;
				}
				currentCol++;
				currentRow--;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
			}
			else{
				temp = '-';
			}
			
		}
		
		
		return count;
	
		
	}

	/**
	 * Checks to see how many chips the player has in a row diagonally to the left
	 * @param xTurn
	 * 				boolean: True if X's turn, false for O's turn
	 * @param column
	 * 				int: column number (where column 0 is column 1 for user)
	 * @return
	 * 				int: number of chips given player has in a row 
	 */
	private int checkWinDiagonalLeft(boolean xTurn, int column){
		char chip;
		if (xTurn){
			chip = 'X';
		}
		else{
			chip = 'O';
		}
		
		char temp = chip;
		int currentCol = column;
		int currentRow = height[column]+1;
		
		int count = 0;
		while (temp == chip && currentCol <6 && currentRow <6){
				currentCol++;
				currentRow++;
				temp = board[currentCol][currentRow].getPiece();
				if(temp != chip){
					currentRow--;
					currentCol--;
				}
			}
		temp = chip;
		while (temp == chip && currentCol >=0 && currentRow > 0){
			
			if (currentCol == 0){
				if (temp == chip){
					count++;
				}
				break;
			}
			if (currentRow == 0){
				if (temp == chip){
					count++;
				}
				break;
			}
				currentCol--;
				currentRow--;
				temp = board[currentCol][currentRow].getPiece();
				count ++;
		}
			
		
		
		
		return count;
	
		
	}
}


        