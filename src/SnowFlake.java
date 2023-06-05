import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SnowFlake extends JLabel {
    private Timer timer;
    final private Dimension size = new Dimension(10, 10);
    final int speed = (int)((Math.random() * 3)) + 1;

    public SnowFlake(JPanel panel) {
        setLocation((int)(Math.random() * panel.getWidth()), (int)Math.random() * panel.getHeight());
        setSize(size);
        setBackground(Color.WHITE);
        try {
            setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream("images/snowflake.png")))));
        }
        catch(Exception e) {
            throw new RuntimeException();
        }

        timer = new Timer(70, new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if(getY() > panel.getHeight()) {
                    setLocation((int)(Math.random() * panel.getWidth()), 0);
                }

                setLocation(getX(), getY() + speed);
            }
        });
        timer.start();
    }

}

