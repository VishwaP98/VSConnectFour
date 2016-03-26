import javax.swing.*;

/**
 * Created by Vishwa on 3/10/2016.
 * Redesigned by Sunny on 3/21/2016.
 */
public class TextConnect {
	private boolean xTurn = true;
	private	boolean won = false;
	private Board thisBoard = new Board();
	public void addChip(int i)
    {
        int column = i;
		if(thisBoard.addPiece(column, xTurn)) {
			xTurn = !xTurn;
			if (thisBoard.checkWin(!xTurn, column)) {
				System.out.println("YAYYYY.  YOU WINNN!!!!!");
				won = true;
				String player = "Blue";
				if (xTurn) {
					player = "Red";
				}
				JOptionPane.showMessageDialog(null, player + " Wins!");
				GamePanel.gameOver();
			}
		}

    }
}
