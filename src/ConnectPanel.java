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
    private JButton singPlayer = new JButton("Single Player");
    private JButton twoPlayer = new JButton("Two Player");
    BufferedImage logoImage;
    private JLabel logo;
    private JFrame frame1;
    private JFrame originalframe;
    Board board = new Board();
    GameController controller = new GameController();
    private JButton instructions = new JButton("Instructions");

    public ConnectPanel(JFrame frame) {
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(700, 700));
        originalframe = frame;
        try {
            logoImage = ImageIO.read(new File("connect4_logo.png"));
            logo = new JLabel(new ImageIcon(logoImage));
            add(logo);
            singPlayer.setPreferredSize(new Dimension(400, 100));
            add(singPlayer);
            twoPlayer.setPreferredSize(new Dimension(400, 100));
            twoPlayer.addActionListener(new ClassListener());
            add(twoPlayer);
            instructions.setPreferredSize(new Dimension(400, 100));
            instructions.addActionListener(new ClassListener());
            add(instructions);
            logo.setVisible(true);
            singPlayer.setVisible(true);
        } catch (IOException e) {
            System.out.print(" Error " + e.getMessage());
        }
        singPlayer.addActionListener(new ClassListener());
    }

    private class ClassListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == twoPlayer || actionEvent.getSource() == singPlayer) {
                if(actionEvent.getSource() == singPlayer)
                {
                    controller.setaiMode();
                }
                originalframe.setVisible(false);
                originalframe.removeAll();
                JFrame frame1 = new JFrame("Connect Four");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.getContentPane().add(new GamePanel());
                frame1.pack();
                frame1.setVisible(true);
                frame1.setResizable(false);
            }
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
