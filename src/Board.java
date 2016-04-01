import javax.swing.*;

/**
 *
 * Created by Sunny on 3/21/2016
 * Edited by Vishwa on 3/31/2016
 */
public class Board {
	private Box[][] board = new Box[7][7];
	private int[] height = new int[7];
	private static boolean computerAI = false;

	public Board() {
		for (int i = 0; i < 7; i++) {
			for (int c = 0; c < 7; c++) {
				board[c][i] = new Box();
			}
		}
		for (int i = 0; i < 7; i++) {
			height[i] = 6;
		}
	}

	public void resetBoard() {

		for (int i = 0; i < 7; i++) {
			height[i] = 6;
		}
	}

	public Box getBox(int column, int row) {
		return board[column][row];
	}

	public int getHeight(int column) {
		return height[column];
	}

	public boolean addpiece(int column, boolean xTurn) {
		if (height[column] < 0 || height[column] > 6) {
			return false;
		}
		if (xTurn) {
			System.out.print(height[column]);
			board[column][height[column]].setX();
		} else {
			board[column][height[column]].setO();
		}
		GamePanel.putChip(column, height[column], xTurn);
		height[column]--;

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
		if (board[currentCol][0].getPiece() == chip){
			count++;
		}
		System.out.println("vertical" + count);
		return (count);
	}
	public void doThingy(){
		System.out.println(checkWinHorizontal(true, 1));
	}
	public int aiPicker(){
		int[] offensive = new int[7];
		for (int col = 0; col < 7; col++){
			if (height[col]>0){
				int horizontal = checkWinHorizontal(false, col);
				int vertical = checkWinVertical(false, col);
				int diagonal = checkWinDiagonalRight(false, col);
				if (horizontal >= vertical && horizontal >= diagonal){
					offensive[col]= horizontal;
				}
				else if(vertical >= horizontal && vertical >= diagonal){
					offensive[col] = vertical;
				}
				if (diagonal >= horizontal && diagonal >= vertical){
					offensive[col] = diagonal;
				}
				else{
					offensive[col] = -1;
				}
			}

		}

		int[] defensive = new int[7];
		for (int col=0; col<7; col++){
			if (height[col] >0){
				int horizontal = checkWinHorizontal(true, col);
				int vertical = checkWinVertical(true, col);
				int diagonal = checkWinDiagonalRight(true, col);
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
			else{
				defensive[col] = -1;
			}

		}
		int max = 0;
		int maxIndex = 0;
		for (int i=0; i<7; i++){


			if (offensive[i] > max && height[i] < 6){
				max = offensive[i];
				maxIndex = i;
			}

		}
		int defMax = 0;
		for (int i=0; i<7; i++){

			if (defensive[i] > defMax && height[i]<6){
				defMax = defensive[i];
			}
		}
		if (defMax >= 3 && max < 3){
			return defMax;
		}
		else{
			return max;
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
		int currentRow = height[column];


		int count = 0;
		while (temp == chip && currentCol >0){
			currentCol--;
			System.out.println("adsfadsf" + currentCol);
			System.out.println("ladksjfa" + currentRow);
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
		System.out.println("Horizontal: " + count);
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
		System.out.println("Diagonal UpRight: " + count);


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


		System.out.println("Diagonal UpLeft: " + count);

		return count;
	}
	public void setAIOn()
	{
		computerAI = true;
	}
	public boolean getAIOn()
	{
		return computerAI;
	}
	public boolean addAIpiece(boolean xTurn)
	{
		int columnAI = aiPicker();
		System.out.print(columnAI + " Column AI");
		char chip = board[columnAI][height[columnAI]].getPiece();
		if(xTurn && String.valueOf(chip).equals("-"))
		{
			board[columnAI][height[columnAI]].setX();
			GamePanel.putChip(columnAI, height[columnAI], xTurn);

		}
		else if(!xTurn && String.valueOf(chip).equals("-"))
		{
			board[columnAI][height[columnAI]].setO();
			GamePanel.putChip(columnAI, height[columnAI], xTurn);

		}
		else
		{
			JOptionPane.showMessageDialog(null,"Computer cannot think of a place to add chip");
		}
		height[columnAI]--;
		return true;
	}
}