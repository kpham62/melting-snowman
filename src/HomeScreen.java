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
            new JButton("Hard"), new JButton("Insanity")}; // with JButton, add clickable buttons that allow the user to choose the difficulty
    // initialize image, myImage, isWiggling, titleScreenY, and titleScreenTimer
    private JLabel image;
    private BufferedImage myImage;
    private boolean isWiggling = false;

    /* 
     * titleScreenY: This integer variable represents the vertical position of the title image.
     * It determines the distance the image should move up or down in each animation frame. By
     * changing the value of titleScreenY, the title image is moved vertically on the screen.
     */
    
    private int titleScreenY = 0;
    private Timer titleScreenTimer;


    public HomeScreen(JFrame frame) {
        setFrame(frame);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(getBackgroundColor()); // get background color from GamePanel
        final Dimension buttonPanelSize = new Dimension(400, 50); // set button panel size containing the buttons
        final Point buttonPanelLocation = new Point(50, 300); // adding the absolute location of the buttons in the middle
        buttonPanel.setLocation(buttonPanelLocation); // call back to buttonPanelLocation for setLocation
        buttonPanel.setSize(buttonPanelSize); // call back to buttonPanelSize

        // JButton with ActionListener fires an action event. This results in the invocation of the action listener's method.
        for(JButton b: buttonList) {
            b.addActionListener(this);
            b.setActionCommand(b.getText());
            buttonPanel.add(b);
        }

        // define image by importing title2 using a relative path
        image = new JLabel(new ImageIcon(importImage("images/title2.png")));
        image.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass(). // setting icon
                getResource("images/title2.png"))).getImage(). // get image
                getScaledInstance(500, 400, Image.SCALE_SMOOTH))); // scaled image to allow for bottom text panel
        image.add(buttonPanel);
        add(image);
        
        /*
         * movingUp: this boolean variable determines the direction of the animation.
         * When movingUp is true, the title image moves up, and when it's false, the
         * title image moves down.
         */

        titleScreenTimer = new Timer(70, new ActionListener() { // moving up and down animation for the title image, timer is how long it takes to go through the motion
            boolean movingUp = true; // moving up

            // using if/else, move title screen up and down, also using the integer value to determine the location
            @Override
            public void actionPerformed(ActionEvent e) { // setting up ActionEvent for moving up and down
                if(movingUp) {
                    titleScreenY -= 1;
                    if(titleScreenY <= 0) {
                        movingUp = false; // moving down
                    }
                }
                else {
                    titleScreenY += 1;
                    if(titleScreenY >= 20) {
                        movingUp = true; // moving up
                    } 
                }
                image.setLocation(getX(), titleScreenY); // set location based on x value of the imagee and value of titleScreenY determined previously
            }


        });
        titleScreenTimer.start(); // timer starts to time the movement




    }

    public void actionPerformed(ActionEvent event) {
        getFrame().remove(this); // if the button is clicked, go to the playscreen depending on the button
        getFrame().add(new PlayScreen(event.getActionCommand(), getFrame())); // going to the playscreen and problem chosing
        //getFrame().revalidate();
        
        /*
         * The repaint method is responsible for handling update to the paint cycle of the applet.
         * Whenever we want a component to repaint itself, we need to call the repaint method.
         */
        
        getFrame().repaint();

    }
}
