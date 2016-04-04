
/**
 * Created by Vishwa on 3/25/2016.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Vishwa Patel
 * @since April 1st 2016
 * @version 1.1.90
 *
 * Defines the panel for gameplay user interface including a grid
 * with buttons, labels and images of chips of different players
 */
public class GamePanel extends JPanel {
    private static final int numberBoxes = 7;
    private static BufferedImage redCircle;
    private static BufferedImage blueCircle;
    private BufferedImage emptyBox;
    private Board board = new Board();
    private static JLabel[][] labels = new JLabel[numberBoxes][numberBoxes];
    //http://usaopoly.com/brand/connect-4  Got the Connect Four picture from here
    private static JButton[] buttons;
    private GameController connect;
    private final int FONT_SIZE = 45;

    /**
     * Initializes the panel by setting the background and layout of the panel
     * and adding GUI components like JButtons and JLabels
     */
    public GamePanel()
    {
        setBackground(Color.gray);
        setPreferredSize(new Dimension(700, 700));
        setLayout(new GridLayout(8,8));
        try
        {
            // Image taken from Internet
            redCircle = ImageIO.read(new File("red.png"));

            // Edited by Vishwa
            emptyBox = ImageIO.read(new File("emptyBox.png"));

            // Edited by Vishwa
            blueCircle = ImageIO.read(new File("blue.png"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        buttons = new JButton[numberBoxes];

        // Initializes all the buttons
        for(int i=0; i<numberBoxes;i++)
        {
            buttons[i] = new JButton();
        }
        // Defines buttons for game including their colour and adds to panel
        for(int i=0;i<numberBoxes;i++)
        {
            int colNum = i+1;
            JButton button = new JButton();
            button.addActionListener(new ButtonListener());
            button.setBackground(Color.yellow);
            button.setText(String.valueOf(colNum));
            button.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            add(button);
        }

        // Defines labels for game including their appearance and adds to panel
        for(int i=0;i<numberBoxes;i++)
        {
            for(int j=0; j<numberBoxes;j++) {
                labels[i][j] = new JLabel("");
                Color color = new Color(187,187,187);
                labels[i][j].setBackground(color);
                if (board.getBox(i,j).getFilled()){
                    labels[i][j].setBackground(Color.lightGray);
                    labels[i][j].setIcon(new ImageIcon(redCircle));
                }
                else
                {
                    labels[i][j].setIcon(new ImageIcon(emptyBox));
                }
                add(labels[i][j]);
            }
        }
        connect = new GameController();

    }

    /**
     * Defines responses to the buttons created in panel constructor
     * and differentiates between buttons using event.getSource() and
     * getting the text on buttons
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Handles response of buttons upon click
         * @param event
         *         ActionEvent Object to track
         */
        public void actionPerformed(ActionEvent event)
        {
            JButton button = (JButton)event.getSource();
            String text = button.getText();
            if(!connect.getWon())
            {
                // Adds chip to the number of column that the button represents
                connect.addChip(Integer.parseInt(text));
            }

        }
    }

    /**
     * Adds chip to the game grid by changing the icon of label at the location
     * of move
     * @param column
     *          integer representing the columnn in which to put the chip in
     * @param row
     *          integer representing the row in which to put the chip in
     * @param xTurn
     *          boolean indicating which player's turn it is
     *          true for red player and false for blue player
     *          If game is against computer, blue is AI and red is player
     */
    public static void putChip(int column, int row, boolean xTurn)
    {
        if(xTurn) {
            labels[row][column].setIcon(new ImageIcon(redCircle));
        }
        else
        {
            labels[row][column].setIcon(new ImageIcon(blueCircle));
        }
    }

}
