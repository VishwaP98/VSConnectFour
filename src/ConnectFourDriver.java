
/**
 * Created by Vishwa on 3/10/2016.
 */

import javax.swing.*;

/**
 * @author Vishwa Patel
 * @version 1.1.90
 * @since April 1st 2016
 *
 * Driver of the Connect Four game program, which creates
 * a JFrame and adds a panel to it for user interface
 */
public class ConnectFourDriver {
    public static void main(String[] World)
    {
        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ConnectPanel(frame));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
