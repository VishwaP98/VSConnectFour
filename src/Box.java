/**
 * 
 * @author Vishwa Patel
 * @since April 1st 2016
 * @version 1.1.90
 *
 * Created by Vishwa on 3/10/2016.
 * Re-created by Sunny on 3/21/2016
 * Description: Class for the creation of a Box object
 */


public class Box {
    private char piece;
    
    /**
     * Constructor, no parameters
     */
    Box()
    {
        this.piece = '-';
    }
    
    /**
     * Sets box value to X
     */
    public void setX()
    {
        this.piece = 'X';
    }
    
    /**
     * Sets box value to O
     */
    public void setO() 
    {
    	this.piece = 'O';
    }
    
    /**
     * Sets box value to '-' again
     */
    public void setBlank() {
        this.piece = '-';
    }
    
    /**
     * Giver the value of the box as a char
     * @return
     * 			char: value of box
     */
    public char getPiece(){
        return (piece);
    }
    
    /**
     * Returns true if the box has been set to anything
     * @return
     * 			boolean: true if filled, false otherwise
     */
    public boolean getFilled(){
        return !(piece == '-');
    }
}