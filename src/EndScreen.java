import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EndScreen extends GamePanel{ //creates EndScreen that inherits GamePanel
    JPanel UIPanel = new JPanel(new FlowLayout()); //creates a bottom panel that contains the UI
    JLabel outcomeScreen; //creates a label to contain image
    private Timer wiggleTimer; //creates a timer to animate the outcomeScreen

    public EndScreen(String outcome, JFrame frame) { //public constructor for EndScreen with parameters outcome and frame
        setFrame(frame); //links the JFrame used to EndScreen
        //sets outcomeScreen as a JLabel importing win/lose image (depending on outcome parameter)
        outcomeScreen = new JLabel(new ImageIcon(importImage("images/" + outcome + ".png")));
        //sets the win/lose image as an icon
        outcomeScreen.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().
                getResource("images/" + outcome + ".png"))).getImage().
                getScaledInstance(350, 300, Image.SCALE_SMOOTH)));

        outcomeScreen.setBounds(0, 0, 500, 400); //sets the bounds of the outcomeScreen


        JButton again = new JButton("Again?"); //creates a button called Again
        again.addActionListener(e -> { //sets up a new ActionListener using a lambda expression
            getFrame().remove(this); //removes the EndScreen from the frame
            getFrame().add(new HomeScreen(getFrame())); //adds a HomeScreen to the frame
            getFrame().revalidate(); //adjusts the screen to fix removal/addition of components
            getFrame().repaint(); //repaints the screen
        });
        JButton quit = new JButton("Quit"); //creates a button called Quit
        quit.addActionListener(e -> { //sets up a new ActionListener using a lambda expression
            System.exit(0); //quits the program
        });


        UIPanel.setBounds(25, 400, 400, 50); //sets the bounds of the UIPanel
        UIPanel.setBackground(getBackgroundColor()); //sets the background color of the UIPanel
        UIPanel.add(again); //adds JButton again to UIPanel
        UIPanel.add(quit); //adds JButton quit to UIPanel

        add(outcomeScreen); //adds the outcomeScreen to the EndScreen
        add(UIPanel); //adds the UIPanel to the EndScreen


        wiggleTimer = new Timer(200, new ActionListener() { //uses the wiggleTimer with delay 200 and a new ActionListener

            boolean shift = true; //sets the outScreen to shift to the left
            @Override
            public void actionPerformed(ActionEvent e) { //overridden method actionPerformed is defined
                if(shift) {//checks if shift is true or false
                    //shifts the X coordinate of outcomeScreen 5 units to the left
                    outcomeScreen.setLocation(outcomeScreen.getX() - 5, outcomeScreen.getY());
                    shift = false; //sets the shifting to the right
                }
                else{ //run if shift is false
                    //shifts the X coordinate of outcomeScreen 5 units to the right
                    outcomeScreen.setLocation(outcomeScreen.getX() + 5, outcomeScreen.getY());
                    shift = true; //sets the shifting to the left
                }
            }
        });
        if(outcome == "win") { //checks if outcome is win or lose
            wiggleTimer.start(); //starts the animation if the outcome is win
        }


    }
}
