import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.*;
import java.util.Locale;

//play screen is extended from game panel
public class PlayScreen extends GamePanel{
    
    //variables declared for gameplay
    private final JPanel animationPanel = new JPanel();
    private int wrongAnswers = 0;
    private int problemIndex = 0;
    private Problems[] problems = new Problems[10];
    private final JLabel[] images = new JLabel[5];
    private final JTextField answerBox = new JTextField();
    private final JTextArea problemField = new JTextArea();
    private final JButton guess = new JButton("Enter");

    //number of snowflakes
    private final SnowFlake[] snowflakes = new SnowFlake[15];

    //constructor takes difficulty chosen and JFrame object
    public PlayScreen(String difficulty, JFrame frame ) {
        setLayout(new BorderLayout());
        setFrame(frame);
        setSize(getWidth(), getHeight());
        setBackground(Color.LIGHT_GRAY);
        
        //problems generated based off difficulty
        generateProblems(difficulty);

        //set the location and size of answer box and problem field
        final int elementYLocation = 405;
        final int elementHeight = 40;
        final int spaceForElements = 100;

        final Point answerBoxLocation = new Point(250, elementYLocation);
        final Dimension answerBoxSize = new Dimension(100, elementHeight);
        answerBox.setLocation(answerBoxLocation);
        answerBox.setSize(answerBoxSize);

        //problem text field set up
        final int problemFieldCenter = 125;
        problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize()/2, elementYLocation + elementHeight / 4,
                problems[problemIndex].getProblemSize(), elementHeight);
        //field properties
        problemField.setEditable(false);
        problemField.setWrapStyleWord(true);
        problemField.setLineWrap(true);
        problemField.setVisible(true);
        problemField.setFont(new Font("Comic Sans", Font.BOLD, 16));
        problemField.setText(problems[problemIndex].getQuestion() + " = ?");
        problemField.setBackground(Color.LIGHT_GRAY);

        //guess button configuration
        guess.setLocation(375, elementYLocation);
        guess.setSize(80, elementHeight);
        //action listener added to guess button
        guess.addActionListener(e -> {
            String userAnswer = answerBox.getText(); //answer received from field
            if(userAnswer.isBlank()) {
                //show dialog box propmting user to enter a number
                JOptionPane.showMessageDialog(this, "Enter a number to submit your answer!");
            }
            
            // user enters again to start game over
            else if(userAnswer.equalsIgnoreCase("AGAIN")) { 
                getFrame().remove(this); 
                getFrame().add(new HomeScreen(getFrame())); 
                getFrame().revalidate();
                getFrame().repaint();

            }
            //if the users answers all the questions correctly, the win screen appears
            else if (userAnswer.equals(String.valueOf((int)(problems[problemIndex].getAnswer())))) {
                if(problemIndex == problems.length - 1) {
                    getFrame().remove(this);
                    getFrame().add(new EndScreen("win", getFrame()));
                    getFrame().revalidate();
                    getFrame().repaint();
                }
                else{ //moves to next problem
                    problemIndex++;
                    //set bounds and text for problem
                    problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize() / 2,
                            elementYLocation + elementHeight /4, problems[problemIndex].getProblemSize(), elementHeight);
                    problemField.setText(problems[problemIndex].getQuestion() + " = ?");
                }
            }
            else { //if all incorrect attempts are maxed, lose screen appears
                if (wrongAnswers == images.length - 1) {
                    getFrame().remove(this);
                    getFrame().add(new EndScreen("lose", getFrame()));
                    getFrame().revalidate();
                    getFrame().repaint();
                }
                else{
     
                    wrongAnswers++;
                    animationPanel.removeAll();
                    animationPanel.add(images[wrongAnswers]);
                    for(SnowFlake s: snowflakes) { //snowflakes are added to the animation panel
                        animationPanel.add(s);
                    }
                    animationPanel.revalidate();
                    animationPanel.repaint();
                }
            }
            answerBox.setText(""); //clear text in the answer box
        });

        //load images and set bounds
        for(int i = 0; i < images.length; i++) {
            images[i] = new JLabel(new ImageIcon(importImage("images/snowman_" + (i + 1) + ".png")));
            images[i].setBounds(0, 0, getWidth(), getHeight() - spaceForElements);
        }

        //configure animation panel
        animationPanel.setBackground(getBackgroundColor());
        animationPanel.setBounds(0, 0, getWidth(), getHeight() - spaceForElements);
        animationPanel.add(images[wrongAnswers]);
        animationPanel.setLayout(null);
        
        //set layout and add components to Playscreen
        setLayout(null);
        add(answerBox);
        add(problemField);
        add(guess);
        add(animationPanel);

        //add snowflakes to the animation panel
        for(int i = 0; i < snowflakes.length; i++) {
            snowflakes[i] = new SnowFlake(animationPanel);
            animationPanel.add(snowflakes[i]);
            animationPanel.revalidate();
            animationPanel.repaint();
        }



    }

    // problems generated based on difficulty
    public void generateProblems(String difficulty) {
        switch (difficulty) { //switch to handle each difficulty level
            case "Easy": {
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) { 
                    //create new instance of easy problems
                    problems[i] = new EasyProblems();
                }
            }
            break;
            case "Medium": {
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) {
                    //create new instance of medium problems
                    problems[i] = new MediumProblems();
                }
            }
            break;
            case "Hard": {
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) {
                    //create new instance of hard problems
                    problems[i] = new HardProblems();
                }
            }
            break;
            case "Insanity": {
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++)
                    //create new instance of insane problems
                    problems[i] = new InsanityProblems();
            }
        }
    }

}
