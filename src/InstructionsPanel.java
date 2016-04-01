import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Vishwa on 3/31/2016.
 */
public class InstructionsPanel extends JPanel
{
    JLabel title = new JLabel();
    JLabel info = new JLabel();
    JLabel info2 = new JLabel();
    JLabel goal = new JLabel();
    static BufferedImage instructions;
    private final int FONT_SIZE = 45;
    public InstructionsPanel()
    {
        setBackground(java.awt.Color.red);
        setPreferredSize(new Dimension(550, 300));
        try
        {
            instructions = ImageIO.read(new File("instructions.png"));

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        title.setIcon(new ImageIcon(instructions));
        add(title);

    }
}
