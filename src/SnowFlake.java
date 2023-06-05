//reuired packages imported
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//class defined extending to JLabel
public class SnowFlake extends JLabel {
    private Timer timer; //timer object declared
    final private Dimension size = new Dimension(10, 10); //set constant dimension of snowflake
    final int speed = (int)((Math.random() * 3)) + 1; //snowflake speed

    //constructor
    public SnowFlake(JPanel panel) { 
        //set a random location for the snowflakes
        setLocation((int)(Math.random() * panel.getWidth()), (int)Math.random() * panel.getHeight());
        //size of snowflake
        setSize(size);
        //snowflake color
        setBackground(Color.WHITE);
        try {
            //set the icon for the snowflake using an image
            setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream("images/snowflake.png")))));
        }
        catch(Exception e) {
            //throws expection if error when loading image
            throw new RuntimeException();
        }

      //set up a timer to run every 70 milliseconds
        timer = new Timer(70, new ActionListener() {
            @Override

            /* if the snowflake has moved from the bottom of the panel
            it resets it's psostition back to the top
            */
            public void actionPerformed(ActionEvent e) {
                if(getY() > panel.getHeight()) { //tests if Y passes the bottom of the panel
                    setLocation((int)(Math.random() * panel.getWidth()), 0); //resets the snowflake at a new x coordinate and at the top of the panel
                }

                //move the snowflake down the panel at the defined speed
                setLocation(getX(), getY() + speed);
            }
        });
        //timer starts
        timer.start();
    }

}

