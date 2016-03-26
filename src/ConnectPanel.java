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
    private JLabel logo;
    private JFrame frame1;
    private JFrame originalframe;

    public ConnectPanel(JFrame frame) {
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(700, 700));
        originalframe = frame;
        try {
            logoImage = ImageIO.read(new File("connect4_logo.png"));
            logo = new JLabel(new ImageIcon(logoImage));
            add(logo);
            start.setPreferredSize(new Dimension(300, 100));
            add(start);
            logo.setVisible(true);
            start.setVisible(true);
        } catch (IOException e) {
            System.out.print(" Error " + e.getMessage());
        }
        start.addActionListener(new ClassListener());
    }

    private class ClassListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == start) {
                originalframe.setVisible(false);
                originalframe.removeAll();
                JFrame frame1 = new JFrame("Connect Four");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.getContentPane().add(new GamePanel());
                frame1.pack();
                frame1.setVisible(true);
                frame1.setResizable(false);
            }
        }
    }

}
