import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Vishwa on 3/10/2016.
 * Redesigned by Sunny on 3/21/2016.
 */
public class TextConnect {
	private Board thisBoard = new Board();
	private boolean xTurn = true;
	private boolean won = false;
	private boolean computerTurn = false;
	public void addChip(int column) {
		column--;
		computerTurn = false;
		if (thisBoard.getHeight(column) < 0) {
			JOptionPane.showMessageDialog(null, "Column is already full");
		} else {
			thisBoard.addpiece(column, xTurn);
			String player = "";
			if (thisBoard.checkWin(!xTurn, column)) {
				if (!xTurn) {
					player = "Blue";
				} else {
					player = "Red";
				}
				won = true;
				JOptionPane.showMessageDialog(null, player + " Wins !");
				GamePanel.gameOver();
			}
			if(!thisBoard.getAIOn())
			{
				xTurn = !xTurn;
			}
			computerTurn = true;
			if(thisBoard.getAIOn())
			{
				System.out.print("Hi I am at AI");
				thisBoard.addAIpiece(!xTurn);
			}
		}
	}
		public boolean getWon() {
			return won;
		}
		public boolean getComputerTurn()
		{
			return computerTurn;
		}


}
