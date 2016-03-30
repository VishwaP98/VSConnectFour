import java.util.Scanner;

/**
 * Created by Vishwa on 3/10/2016.
 * Redesigned by Sunny on 3/21/2016.
 */
public class TextConnect {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean xTurn = true;
        boolean won = false;
        Board thisBoard = new Board();
        int column;
        while (!won){
        	System.out.println("Enter the column you wish to add");
        		column = in.nextInt();
            	while (!thisBoard.addpiece(column, xTurn)){
            		System.out.println("Column full, try another one");
            		
            		column = in.nextInt();
            		thisBoard.addpiece(column, xTurn);
            	}
        	
        	xTurn = !xTurn;
        	System.out.println("");
        	thisBoard.print();
        	if(thisBoard.checkWin(!xTurn, column)){
        		System.out.println("YAYYYY.  YOU WINNN!!!!!");
        		won = true;
        	}
        }
        
        in.close();
        

    }
}
