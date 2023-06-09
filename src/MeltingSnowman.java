/*
CSCI 143 Obj-Orient Prog II with Java
Highline College
Professor Nizami, Syeda
Team: Cynthia Lopez, Thomas Huynh, Kenny Pham
Game Name: Melting Snowman
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class MeltingSnowman {

    // Creating all the necessary variables
    JPanel animationPanel = new JPanel(); // Image contained in this panel
    BufferedImage myImage; // Image that will be displayed
    int wrongAnswers = 0; // Number of wrong answers
    int problemIndex = 0; // Index of the current problem
    int[][] problems = new int[9][2]; // Array of problems
    Random rand = new Random(); // Random generator for problems

    // Timer for animation
    private final Timer wiggleTimer;
    private boolean isWiggling = false;

    // Title screen animation variables
    private int titleScreenY = 0; // Starting y-position of the title screen
    private final Timer titleScreenTimer;

    // Start wiggling win image
    private void startWiggleAnimation() {
        if (!isWiggling) {
            isWiggling = true;
            wiggleTimer.start();
        }
    }

    // Stop wiggling win image
    private void stopWiggleAnimation() {
        isWiggling = false;
        wiggleTimer.stop();
    }

    // Generates math problems depending on difficulty
    private void generateProblems(int difficulty) {
        int start = 0, end = 0;
        // Range defined for random numbers based on difficulty
        switch (difficulty) {
            case 0 -> {
                start = 1;
                end = 10;
            }
            case 1 -> {
                start = 10;
                end = 50;
            }
            case 2 -> {
                start = 100;
                end = 1000;
            }
        }

        // Generates problems
        for (int i = 0; i < 5; i++) {
            problems[i][0] = rand.nextInt(end - start) + start;
            problems[i][1] = rand.nextInt(end - start) + start;
        }
    }

    public MeltingSnowman() {

        // Import images and labels created
        importImage("images/title.png");
        JLabel titleScreen = new JLabel(new ImageIcon(myImage));
        titleScreen.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/title.png"))).getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH)));

        // Timer for title screen animation
        titleScreenTimer = new Timer(70, new ActionListener() { // Timing of the title screen animation
            boolean movingUp = true; // Flag indicating if the title screen is moving up or down

            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingUp) {
                    titleScreenY -= 1; // Move title screen up by 1 pixel
                    if (titleScreenY <= 0) {
                        movingUp = false; // Reverse direction when reaching the top
                    }
                } else {
                    titleScreenY += 1; // Move title screen down by 1 pixel
                    if (titleScreenY >= 20) {
                        movingUp = true; // Reverse direction when reaching the bottom
                    }
                }
                titleScreen.setLocation(titleScreen.getX(), titleScreenY); // Update title screen position
            }
        });

        titleScreenTimer.start(); // Start the title screen animation
        importImage("images/win.png");
        JLabel win = new JLabel(new ImageIcon(myImage));
        win.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/win.png"))).getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH)));
        importImage("images/snowman_1.png");
        JLabel image1 = new JLabel(new ImageIcon(myImage));
        importImage("images/snowman_2.png");
        JLabel image2 = new JLabel(new ImageIcon(myImage));
        importImage("images/snowman_3.png");
        JLabel image3 = new JLabel(new ImageIcon(myImage));
        importImage("images/snowman_4.png");
        JLabel image4 = new JLabel(new ImageIcon(myImage));
        importImage("images/snowman_5.png");
        JLabel image5 = new JLabel(new ImageIcon(myImage));
        importImage("images/lose.png");
        JLabel lose = new JLabel(new ImageIcon(myImage));
        lose.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/lose.png"))).getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));

        //array created to store images
        JLabel[] images = new JLabel[]{titleScreen,image1,image2,image3,image4,image5,lose};

        //setting up the window for images
        animationPanel.setBackground(Color.BLUE);
        animationPanel.setBounds(0, 0, 500, 400);
        animationPanel.add(images[wrongAnswers]);

        //timer for win animation
        wiggleTimer = new Timer(200, new ActionListener() {
            boolean shift = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (shift) {
                    //image position shifting
                    win.setLocation(win.getX() - 5, win.getY());
                    shift = false;
                } else {
                    win.setLocation(win.getX() + 5, win.getY());
                    shift = true;
                }

            }
        });

        //text fields and buttons created
        JTextField tf = new JTextField();
        tf.setBounds(250, 400, 100, 50);
        tf.setVisible(false);

        //problem text field
        JTextField problemField = new JTextField();
        problemField.setBounds(50, 400, 150, 50);
        problemField.setEditable(false);
        problemField.setVisible(false); //not set visible for title

        //text field for difficulty selection
        JTextField difficultyField = new JTextField();
        difficultyField.setBounds(50, 400, 150, 50);

        JButton guessButton = new JButton("Enter");
        guessButton.setBounds(375, 400, 80, 50);

        //adding an action listener to the guess button
        guessButton.addActionListener(e -> {

            // user picks difficulty
            if(difficultyField.isVisible()) {
                //receives users input
                String difficulty = difficultyField.getText();
                // easy difficulty
                int difficultyIndex = 0;
                // check if the user chose medium or hard
                if(difficulty.equalsIgnoreCase("medium")){
                    difficultyIndex = 1; //set index accordingly
                }
                else if(difficulty.equalsIgnoreCase("hard")){
                    difficultyIndex = 2;
                }

                //generate the problems for the chosen difficulty
                generateProblems(difficultyIndex);

                //hide the difficulty selection field and show problem and answer fields
                difficultyField.setVisible(false);
                problemField.setVisible(true);
                tf.setVisible(true);
                problemField.setText(problems[problemIndex][0] + " + " + problems[problemIndex][1] + " = ?");

                //update the animation panel with snowman_1.png
                animationPanel.removeAll();
                animationPanel.add(images[1]);
                animationPanel.revalidate();
                animationPanel.repaint();

                return;
            }

            /*after user picks difficulty,
            they are either answering problems or
            starting game over
             */
            String userAnswer = tf.getText();
            if(userAnswer.equalsIgnoreCase("again")){ //resets game
                wrongAnswers = 0;
                problemIndex = 0;

                //show difficulty field
                difficultyField.setVisible(true);
                tf.setVisible(false);
                problemField.setVisible(false); //hides problem field

                //update animation panel to show title screen
                animationPanel.removeAll();
                animationPanel.add(images[wrongAnswers]);
                animationPanel.revalidate();
                animationPanel.repaint();
                return;
            }

            /* if user is correct,
            move on to next problem
             */
            if(userAnswer.equals(String.valueOf(problems[problemIndex][0] + problems[problemIndex][1]))){

                //win screen displayed after set index of correct problems
                if(problemIndex == 4){
                    animationPanel.removeAll();
                    animationPanel.add(win);
                    animationPanel.revalidate();
                    animationPanel.repaint();
                    startWiggleAnimation();
                }
                else{
                    //moves on to next problem
                    problemIndex++;
                    problemField.setText(problems[problemIndex][0] + " + " + problems[problemIndex][1] + " = ?");
                    tf.setText("");
                }
            }
            else{
                /* if user's answer is wrong, increment
                the counter for wrong answers
                 */
                wrongAnswers++;
                if(wrongAnswers >= 5){ // user has 5 attempts
                    animationPanel.removeAll();
                    // displays lose screen after all 5 attempts
                    animationPanel.add(images[6]);
                    animationPanel.revalidate();
                    animationPanel.repaint();
                    stopWiggleAnimation();
                    return;
                }

                //update image to reflect number of wrong attempts
                animationPanel.removeAll();
                animationPanel.add(images[wrongAnswers + 1]);
                animationPanel.revalidate();
                animationPanel.repaint();
                stopWiggleAnimation();
            }
        });

        //creating and setting up game window
        JFrame frame = new JFrame();
        frame.setTitle("Melting Snowman Game");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(animationPanel);
        frame.add(tf);
        frame.add(guessButton);
        frame.add(difficultyField);
        frame.add(problemField);
    }

    //import images from file
    public void importImage(String filePath) {
        try {
            myImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MeltingSnowman::new);
    }
}
