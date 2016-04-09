/**
 * Created by Vishwa on 3/10/2016.
 * Redesigned by Sunny on 3/21/2016.
 * Edited by Vishwa on 3/31/2016
 */
import javax.swing.*;

/**
 * @author Vishwa Patel
 * @since April 1st 2016
 * @version 1.1.90
 *
 * Controls the execution of Connect Four game by adding pieces to
 * board, checking for AI, and checking winner of the game
 */
public class GameController {
	private Board thisBoard = new Board();
	private boolean xTurn = true;
	private final int numberBoxes = 7;
	private boolean won = false;
	private static boolean aiMode = false;

	/**
	 * Controls addition of chip and checking for win
	 * @param column
	 * 			integer containing the column where to put the chip in
	 */
	public void addChip(int column) {
		column--;
		if (thisBoard.getHeight(column) < 0) {
			JOptionPane.showMessageDialog(null, "Column is already full");
		} else {
			if (!aiMode){
				thisBoard.addpiece(column, xTurn);
				String player = "";
				if (thisBoard.checkWin(xTurn, column)) {
					if (!xTurn) {
						player = "Blue";
					} else {
						player = "Red";
					}
					won = true;
					JOptionPane.showMessageDialog(null, player + " Wins !");
				}
				if(checkTie())
				{
					JOptionPane.showMessageDialog(null,"It's a Tie!");
				}
				xTurn = !xTurn;
			}
			else if (aiMode){
				thisBoard.addpiece(column, xTurn);
				if (thisBoard.checkWin(xTurn,  column)){
					JOptionPane.showMessageDialog(null, "PLAYER WINS !");
					won = true;
				}
				if(won != true){
					column = thisBoard.aiPicker();
					thisBoard.addpiece(column, false);
					if (thisBoard.checkWin(false, column)){
						JOptionPane.showMessageDialog(null, "AI WINS!");
					}
				}
				if(checkTie())
				{
					JOptionPane.showMessageDialog(null,"It's a Tie!");
				}

			}
		}
	}

	/**
	 * Returns whether there is a winner or not
	 * @return
	 * 		boolean value indicating winner if true and
	 * 		no winner if false
	 */
	public boolean getWon() {
		return won;
	}

	/**
	 * Sets AI Mode to true
	 */
	public void setaiMode()
	{
		this.aiMode = true;
	}

	/**
	 * Checks if there is a tie or not
	 * @return
	 * 		Boolean value indicating a tie if true
	 * 		and no tie if false
	 */
	public boolean checkTie(){
		int count = 0;
		for(int i =0 ;i<numberBoxes;i++)
		{
			for(int j=0;j<numberBoxes;j++)
			{
				if(thisBoard.getBox(i,j).getFilled())
				{
					count++;
				}
			}
		}
		return (count == 49);
	}

}