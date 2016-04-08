/**
 * @author Vishwa Patel
 * @since April 1st 2016
 * @version 1.1.90
 *
 * Created by Vishwa on 3/10/2016.
 * Re-created by Sunny on 3/21/2016.
 */
public class Box {
    private char piece;

    Box()
    {
        this.piece = '-';
    }
    public void setX()
    {
        this.piece = 'X';
    }
    public void setO() {this.piece = 'O';}
    public void setBlank() {
        this.piece = '-';
    }
    public char getPiece(){
        return (piece);
    }
    public boolean getFilled(){
        return !(piece == '-');
    }
}