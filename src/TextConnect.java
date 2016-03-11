import java.util.Scanner;

/**
 * Created by Vishwa on 3/10/2016.
 */
public class TextConnect {
    private static final int num = 7;
    private static Box[][] array = new Box[num][num];
    private static int[] col = new int[num];
    private static boolean full = false;
    private static int fullCol = 0;
    public static void main(String[] World)
    {
        Scanner in = new Scanner(System.in);
        setArray();
        boolean isX = true;


        while(!full) {
            draw();
            fullCol = 0;
            isX = !isX;
            System.out.print("Which Column would you like to add the chip in (1 - 7): ");
            int position = in.nextInt();
            position--;
            try {
                Box box = array[col[position]][position];
                box.setFilled(isX);
                col[position]--;

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Exception. Either the Column is full or Column you are entering is invalid.");
            }

            for (int i = 0; i < col.length; i++)
            {
                if(col[i] < 0)
                {
                    fullCol++;
                }
            }
            if(fullCol == 7)
            {
                draw();
                full = true;
            }
        }
        System.out.println("Yea We are done filling the boxes but the game is not Over!");
        in.close();
    }
    public static void draw()
    {
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<array[i].length;j++) {
                if (array[i][j].getFilled()) {
                    if (array[i][j].isX()){
                        System.out.print("X");
                    }
                    else{
                        System.out.print("O");
                    }
                }
                else
                {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    public static void setArray()
    {
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<array[i].length;j++)
            {
                array[i][j] = new Box();
            }
        }
        for(int i=0;i<col.length;i++)
        {
            col[i] = num-1;
        }

    }
}
