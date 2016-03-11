import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vishwa on 3/10/2016.         
 */
public class ConnectPanel extends JPanel {
    private JButton start = new JButton("Play Game");
    BufferedImage logoImage;
    BufferedImage redCircle;
    private final int numberBoxes = 7;
    private Box[][] boxes = new Box[numberBoxes][numberBoxes];
    private JLabel[][] labels = new JLabel[numberBoxes][numberBoxes];
    //http://usaopoly.com/brand/connect-4  Got the Connect Four picture from here
    private JLabel logo;



    public ConnectPanel()
    {
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(700, 700));
        try
        {
            logoImage = ImageIO.read(new File("connect4_logo.png"));
            redCircle = ImageIO.read(new File("red.png"));
            logo = new JLabel(new ImageIcon(logoImage));
            add(logo);
            start.setPreferredSize(new Dimension(300,100));
            add(start);
            logo.setVisible(true);
            start.setVisible(true);
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage() + " Hi ");
        }
        start.addActionListener(new ClassListener());
    }
    private JLabel[][] createLabels()
    {
        for(int i=0;i<numberBoxes;i++)
        {
            for(int j=0; j<numberBoxes;j++) {
                if (boxes[i][j].getFilled()) {
                    labels[i][j].setBackground(Color.lightGray);
                    add(labels[i][j]);
                }
                else
                {
                    labels[i][j].setIcon(new ImageIcon(redCircle));
                }
                add(labels[i][j]);
            }
        }
        return labels;
    }
    private class ClassListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent)
        {
            if(actionEvent.getSource() == start)
            {
                start.setVisible(false);
                logo.setVisible(false);
                createLabels();
            }
        }
    }

}
