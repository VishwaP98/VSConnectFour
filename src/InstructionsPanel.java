import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Vishwa on 3/31/2016.
 */

/**
 * Defines the panel for displaying instructions to the user
 * with an image of instructions set on a JLabel
 */
public class InstructionsPanel extends JPanel
{
    private JLabel title = new JLabel();
    private static BufferedImage instructions;

    /**
     * Initializes the panel by setting the background and size of the panel
     */
    public InstructionsPanel()
    {
        setBackground(java.awt.Color.red);
        setPreferredSize(new Dimension(550, 300));
        // Reads image
        try
        {
            instructions = ImageIO.read(new File("instructions.png"));

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // Sets the icon of JLabel to the image read
        title.setIcon(new ImageIcon(instructions));
        // Adds JLabel to the panel
        add(title);

    }
}
