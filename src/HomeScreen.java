import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.*;

public class HomeScreen extends GamePanel implements ActionListener {
    JButton[] buttonList = new JButton[] {new JButton("Easy"), new JButton("Medium"),
            new JButton("Hard"), new JButton("Insanity")};
    private JLabel image;
    private BufferedImage myImage;
    private boolean isWiggling = false;

    private int titleScreenY = 0;
    private Timer titleScreenTimer;


    public HomeScreen(JFrame frame) {
        setFrame(frame);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(getBackgroundColor());
        final Dimension buttonPanelSize = new Dimension(400, 50);
        final Point buttonPanelLocation = new Point(50, 300);
        buttonPanel.setLocation(buttonPanelLocation);
        buttonPanel.setSize(buttonPanelSize);

        for(JButton b: buttonList) {
            b.addActionListener(this);
            b.setActionCommand(b.getText());
            buttonPanel.add(b);
        }

        image = new JLabel(new ImageIcon(importImage("images/title2.png")));
        image.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().
                getResource("images/title2.png"))).getImage().
                getScaledInstance(500, 400, Image.SCALE_SMOOTH)));
        image.add(buttonPanel);
        add(image);

        titleScreenTimer = new Timer(70, new ActionListener() {
            boolean movingUp = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(movingUp) {
                    titleScreenY -= 1;
                    if(titleScreenY <= 0) {
                        movingUp = false;
                    }
                }
                else {
                    titleScreenY += 1;
                    if(titleScreenY >= 20) {
                        movingUp = true;
                    }
                }
                image.setLocation(getX(), titleScreenY);
            }


        });
        titleScreenTimer.start();




    }

    public void actionPerformed(ActionEvent event) {
        getFrame().remove(this);
        getFrame().add(new PlayScreen(event.getActionCommand(), getFrame()));
        //getFrame().revalidate();
        getFrame().repaint();

    }
}
