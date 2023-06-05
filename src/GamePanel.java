import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel { //new class GamePanel inherits JPanel
    private JFrame frame; //data field frame links GamePanels to the frame it is on
    private final int width = 500; //sets the width of the frame
    private final int height = 500; //sets the height of the frame
    private final Color backgroundColor = Color.BLUE; //sets the background color of the frame to be blue

    public GamePanel() { //public constructor of GamePanel
        setSize(width, height); //sets the size of the GamePanel using datafields width and height
        setBackground(backgroundColor); //sets the background color using datafield backgroundColor
    }

    public int getWidth() { //new int getter method getWidth declared
        return width; //returns the width of GamePanel
    }

    public int getHeight() { //new int getter method getHeight declared
        return height; //returns the height of GamePanel
    }

    public Color getBackgroundColor() { //new Color getter method getBackgroundColor declared
        return backgroundColor; //returns the color of the panel
    }

    public void setFrame(JFrame frame) { //new setter method setFrame declared with parameter frame
        this.frame = frame; //assigns frame to datafield
    }

    public JFrame getFrame() { //new JFrame getter method getFrame declared
        return frame; //returns the getFrame datafield
    }

    public BufferedImage importImage(String filePath) { //new BufferedImage importImage declared with parameter filePath

        try { //tries to perform action
            //returns the BufferedImage using the filePath parameter
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)));
        }
        catch (IOException e) { //if action is unable to be performed
            throw new RuntimeException(e); //throws new RuntimeException if action unable to be performed
        }
    }



}
