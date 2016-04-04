/**
 * Created by Vishwa on 3/10/2016.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Vishwa Patel
 * @since April 1st 2016
 * @version 1.1.90
 *  Defines the panel for user interface including buttons and
 *  labels as well as the layout and the colour of components of
 *  user interface as well as the font of words for menu screen
 */
public class ConnectPanel extends JPanel {
    private JButton singPlayer = new JButton("Single Player");
    private JButton twoPlayer = new JButton("Two Player");
    private BufferedImage logoImage;
    private JLabel logo;
    private JFrame originalFrame;
    private GameController controller = new GameController();
    private JButton instructions = new JButton("Instructions");

    /**
     * Initializes the panel by setting background and the layout of the
     * panel as well as adds components like buttons and labels
     */
    public ConnectPanel(JFrame frame) {
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(700, 700));
        originalFrame = frame;
        try
        {
            // sets icon to the image read and adds Label onto panel
            logoImage = ImageIO.read(new File("connect4_logo.png"));
            logo = new JLabel(new ImageIcon(logoImage));
            logo.setVisible(true);
            add(logo);

            // Defines single player JButton and places it on panel
            singPlayer.setPreferredSize(new Dimension(400, 100));
            singPlayer.addActionListener(new ClassListener());
            singPlayer.setVisible(true);
            add(singPlayer);

            // Defines two player JButton and places it on panel
            twoPlayer.setPreferredSize(new Dimension(400, 100));
            twoPlayer.addActionListener(new ClassListener());
            twoPlayer.setVisible(true);
            add(twoPlayer);

            // Defines instructions JButton and places it on panel
            instructions.setPreferredSize(new Dimension(400, 100));
            instructions.addActionListener(new ClassListener());
            add(instructions);
        } catch (IOException e) {
            System.out.print(" Error " + e.getMessage());
        }
    }

    /**
     *  Defines response to buttons created in panel constructor
     *  and differentiates between buttons using event.getSource method
     */
    private class ClassListener implements ActionListener {
        /**
         * Handles the response of buttons upon click
         * @param actionEvent
         *              ActionEvent object to track
         */
        public void actionPerformed(ActionEvent actionEvent) {

            // Checks whether player is playing against computer or human opponent
            if (actionEvent.getSource() == twoPlayer || actionEvent.getSource() == singPlayer) {
                // Turns on AI mode
                if(actionEvent.getSource() == singPlayer)
                {
                    controller.setaiMode();
                }

                // Creates a new JFrame for game
                originalFrame.setVisible(false);
                originalFrame.removeAll();
                JFrame frame1 = new JFrame("Connect Four");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.getContentPane().add(new GamePanel());
                frame1.pack();
                frame1.setVisible(true);
                frame1.setResizable(false);
            }
            // Creates and opens Instruction frame upon button click
            else if(actionEvent.getSource() == instructions)
            {
                instructions.setVisible(false);
                JFrame frame = new JFrame("Instructions");
                frame.getContentPane().add(new InstructionsPanel());
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
            }
        }
    }

}
