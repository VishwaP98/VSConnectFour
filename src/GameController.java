import javax.swing.*;

/**
 * Created by Vishwa on 3/10/2016.
 * Redesigned by Sunny on 3/21/2016.
 * Edited by Vishwa on 3/31/2016
 */
public class GameController {
	private Board thisBoard = new Board();
	private boolean xTurn = true;
	private boolean won = false;
	private static boolean aiMode = false;
	public void addChip(int column) {
		column--;
		if (thisBoard.getHeight(column) < 0) {
			JOptionPane.showMessageDialog(null, "Column is already full");
		} else {
			if (!aiMode){
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
				xTurn = !xTurn;
			}
			else if (aiMode){
				thisBoard.addpiece(column, xTurn);
				if (thisBoard.checkWin(!xTurn,  column)){
					JOptionPane.showMessageDialog(null, "PLAYER WINS Wins !");
					GamePanel.gameOver();
					won = true;
				}
				if(won != true){
					column = thisBoard.aiPicker();
					thisBoard.addpiece(column, false);
					if (thisBoard.checkWin(true, column)){
						JOptionPane.showMessageDialog(null, "AI WINS Wins !");
						GamePanel.gameOver();
					}
				}

			}


		}
	}
	public boolean getWon() {
		return won;
	}
	public boolean getaiMode()
	{
		return aiMode;
	}
	public void setaiMode()
	{
		this.aiMode = true;
	}

}
