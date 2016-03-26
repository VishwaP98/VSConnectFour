

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

public class GamePanel extends JPanel {
    private static final int numberBoxes = 7;
    static BufferedImage redCircle;
    static BufferedImage blueCircle;
    BufferedImage emptyBox;
    private Board board;
    private static JLabel[][] labels = new JLabel[numberBoxes][numberBoxes];
    //http://usaopoly.com/brand/connect-4  Got the Connect Four picture from here
    private static JButton[] buttons;
    private TextConnect connect;
    private final int FONT_SIZE = 45;
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
        for(int i=0; i<numberBoxes;i++)
        {
            buttons[i] = new JButton();
        }
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
        for(int i=0;i<numberBoxes;i++)
        {
            for(int j=0; j<numberBoxes;j++) {
                labels[i][j] = new JLabel("");
                Color color = new Color(187,187,187);
                labels[i][j].setBackground(color);
                board = new Board();
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
        connect = new TextConnect();

    }
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            JButton button = (JButton)event.getSource();
            String text = button.getText();
            connect.addChip(Integer.parseInt(text));
        }
    }
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
    public static void gameOver()
    {
        for(int i=0;i<numberBoxes;i++)
        {
            buttons[i].setEnabled(false);
        }
    }

}
