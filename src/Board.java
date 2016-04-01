import java.util.Arrays;

/**
 *
 * Created by Sunny on 3/21/2016
 * Edited by Vishwa on 3/31/2016
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
			height[i] = 6;
		}
	}
	public void resetBoard(){

		for (int i=0; i<7; i++){
			height[i] = 6;
		}
	}
	public Box getBox(int column, int row)
	{
		return board[column][row];
	}
	public int getHeight(int column)
	{
		return height[column];
	}
	public boolean addpiece(int column, boolean xTurn){
		if (height[column] < 0 || height[column] > 6){
			return false;
		}
		if(xTurn){
			System.out.print(height[column]);
			board[column][height[column]].setX();
		}
		else{
			board[column][height[column]].setO();
		}
		GamePanel.putChip(column, height[column], xTurn);
		height[column]--;

		return true;
	}
	private void setPieceTemp(int column, boolean xTurn){
		if(xTurn){
			board[column][height[column]].setX();
		}
		else{
			board[column][height[column]].setO();
		}
		height[column]--;
	}
	private void removePiece(int column){
		board[column][height[column]+1].setBlank();
		height[column]++;
	}
	public int checkWinVertical(boolean xTurn, int column){
		char chip;
		if (!xTurn){
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
	public int aiPicker(){
		int[] offensive = new int[7];
		for (int col = 0; col < 7; col++){
			if (height[col]>0){
				setPieceTemp(col, false);
				int horizontal = checkWinHorizontal(true, col);
				int vertical = checkWinVertical(true, col);
				int diagonal = checkWinDiagonalRight(true, col);
				System.out.println(col + "" + horizontal + ""+vertical + ""+ diagonal);
				removePiece(col);
				int[] a = new int[]{horizontal, vertical, diagonal};
				Arrays.sort(a);
				offensive[col]=a[2];
			}
			else{
				offensive[col] = -1;
			}

		}

		int[] defensive = new int[7];
		for (int col=0; col<7; col++){
			if (height[col] >0){
				setPieceTemp(col, true);
				int horizontal = checkWinHorizontal(false, col);
				int vertical = checkWinVertical(false, col);
				int diagonal = checkWinDiagonalRight(false, col);
				int[] a = new int[]{horizontal, vertical, diagonal};
				Arrays.sort(a);
				defensive[col]=a[2];
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
		System.out.println("Offensive Max: " + max + " at " + maxIndex);
		System.out.println("Defensive Max: " + defMax + " at " + defMaxIndex);

		if (defMax >3 && max <4){
			System.out.println("Chose " + defMaxIndex);
			return defMaxIndex;
		}
		else{
			System.out.println("Chose " + maxIndex);
			return maxIndex;
		}

	}
	public boolean checkWin(boolean xTurn, int column){
		boolean horizontal = checkWinHorizontal(xTurn, column)>=4;
		boolean vertical = checkWinVertical (xTurn, column)>=4;
		boolean diagonalR = checkWinDiagonalRight (xTurn, column)>=4;
		boolean diagonalL = checkWinDiagonalLeft (xTurn, column)>=4;
		return (horizontal || vertical || diagonalR || diagonalL);
	}
	private int checkWinHorizontal(boolean xTurn, int column){
		char chip;
		if (!xTurn){
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
	private int checkWinDiagonalRight(boolean xTurn, int column){
		char chip;
		if (!xTurn){
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
	private int checkWinDiagonalLeft(boolean xTurn, int column){
		char chip;
		if (!xTurn){
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
