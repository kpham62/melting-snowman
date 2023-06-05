import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomeScreen extends GamePanel implements ActionListener { //new class HomeScreen inherits GamePanel and implements ActionListener
    JButton[] buttonList = new JButton[] {new JButton("Easy"), new JButton("Medium"),
            new JButton("Hard"), new JButton("Insanity")}; // with JButton, add clickable buttons that allow the user to choose the difficulty
    // initialize image, myImage, isWiggling, titleScreenY, and titleScreenTimer
    private JLabel image; //makes JLabel datafield in order to use to store title screen image

    /* 
     * titleScreenY: This integer variable represents the vertical position of the title image.
     * It determines the distance the image should move up or down in each animation frame. By
     * changing the value of titleScreenY, the title image is moved vertically on the screen.
     */
    
    private int titleScreenY = 0;
    private Timer titleScreenTimer; //makes a timer in order to animate the title screen


    public HomeScreen(JFrame frame) { //constructor for HomeScreen
        setFrame(frame); //links the frame to the HomeScreen
        JPanel buttonPanel = new JPanel(new FlowLayout()); //creates a new buttonPanel with FlowLayout to store buttons
        buttonPanel.setBackground(getBackgroundColor()); // get background color from GamePanel
        final Dimension buttonPanelSize = new Dimension(400, 50); // set button panel size containing the buttons
        final Point buttonPanelLocation = new Point(50, 300); // adding the absolute location of the buttons in the middle
        buttonPanel.setLocation(buttonPanelLocation); // call back to buttonPanelLocation for setLocation
        buttonPanel.setSize(buttonPanelSize); // call back to buttonPanelSize

        // JButton with ActionListener fires an action event. This results in the invocation of the action listener's method.
        for(JButton b: buttonList) { //iterates through each JButton through the list
            b.addActionListener(this); //adds the ActionListener to the button
            b.setActionCommand(b.getText()); //sets the action command according to the text on Kbutton
            buttonPanel.add(b); //adds the JButton to the buttonPanel
        }

        // define image by importing title2 using a relative path
        image = new JLabel(new ImageIcon(importImage("images/title2.png")));
        //sets image as icon
        image.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass(). // setting icon
                getResource("images/title2.png"))).getImage(). // get image
                getScaledInstance(500, 400, Image.SCALE_SMOOTH))); // scaled image to allow for bottom text panel
        image.add(buttonPanel); //adds the buttonPanel to the image
        add(image); //adds the image to HomeScreen
        
        /*
         * movingUp: this boolean variable determines the direction of the animation.
         * When movingUp is true, the title image moves up, and when it's false, the
         * title image moves down.
         */

        titleScreenTimer = new Timer(70, new ActionListener() { // moving up and down animation for the title image, with 70 millisecond delay
            boolean movingUp = true; // moving up

            // using if/else, move title screen up and down, also using the integer value to determine the location
            @Override
            public void actionPerformed(ActionEvent e) { // setting up ActionEvent for moving up and down
                if(movingUp) { //tests if moving up
                    titleScreenY -= 1; //moves the Y coordinate up
                    if(titleScreenY <= 0) { //checks if y coordinate of label is 0
                        movingUp = false; // sets the Label to be moving down
                    }
                }
                else {
                    titleScreenY += 1; //moves the Label down
                    if(titleScreenY >= 20) { //checks if y coordinate of label is 20
                        movingUp = true; // sets the Label to be moving up
                    } 
                }
                image.setLocation(getX(), titleScreenY); // set location based on x value of the imagee and value of titleScreenY determined previously
            }


        });
        titleScreenTimer.start(); // timer starts to time the movement




    }

    public void actionPerformed(ActionEvent event) {
        getFrame().remove(this); // if the button is clicked, go to the playscreen depending on the button
        getFrame().add(new PlayScreen(event.getActionCommand(), getFrame())); // going to the playscreen using text on buttons
        
        /*
         * The repaint method is responsible for handling update to the paint cycle of the applet.
         * Whenever we want a component to repaint itself, we need to call the repaint method.
         */
        getFrame().repaint();

    }
}
