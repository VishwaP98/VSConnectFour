
/**
 * Created by Vishwa on 3/10/2016.
 */
import javax.swing.*;

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
