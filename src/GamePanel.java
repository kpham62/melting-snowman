import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel {
    private JFrame frame;
    private final int width = 500;
    private final int height = 500;
    private final Color backgroundColor = Color.BLUE;

    public GamePanel() {
        setSize(width, height);
        setBackground(backgroundColor);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public BufferedImage importImage(String filePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
