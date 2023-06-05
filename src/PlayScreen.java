import javax.swing.*;
import java.awt.*;

//play screen is extended from game panel
public class PlayScreen extends GamePanel{ //new class PlayScreen inherits GamePanel
    
    //variables declared for gameplay
    private final JPanel animationPanel = new JPanel(); //creates a new JPanel
    private int wrongAnswers = 0; //sets wrongAnswers to 0 ("index" of images[])
    private int problemIndex = 0; //sets problemIndex as 0
    private Problems[] problems = new Problems[10]; //creates a Problems[] array with 10 elements
    private final JLabel[] images = new JLabel[5]; //creates a JLabel[] array with 5 elements (contains the images)
    private final JTextField answerBox = new JTextField(); //creates a JTextField answerBox
    private final JTextArea problemField = new JTextArea(); //creates a JTextArea problemField
    private final JButton guess = new JButton("Enter"); //creates a JButton guess with text "Enter"

    //number of snowflakes
    private final SnowFlake[] snowflakes = new SnowFlake[15]; //makes a Snowflakes[] array for 15 SnowFlakes

    //constructor takes difficulty chosen and JFrame object
    public PlayScreen(String difficulty, JFrame frame ) { //public constructor for PlayScreen
        setFrame(frame); //links the frame to the panel
        setBackground(Color.LIGHT_GRAY); //colors the UI section of the Panel
        
        //problems generated based off difficulty
        generateProblems(difficulty);

        //set the location and size of answer box and problem field
        final int elementYLocation = 405; //sets Y coordinate of the UI elements
        final int elementHeight = 40; //sets the height of the UI elements
        final int spaceForElements = 100; //sets an area of space for the UI elements

        final Point answerBoxLocation = new Point(250, elementYLocation); //sets a point for the answerBox using elementYLocation
        final Dimension answerBoxSize = new Dimension(100, elementHeight); //sets dimensions for the answerBox using elementHeight
        answerBox.setLocation(answerBoxLocation); //sets location of answerBox according to the answerBoxLocation
        answerBox.setSize(answerBoxSize); //sets size of answerBox according to answerBoxSize

        //problem text field set up
        final int problemFieldCenter = 125; //sets the center of the problemField
        //sets the location and size of the problemField based on center, problemSize, and elementYLocation, (for location), and problemSize and elementHeight (for size)
        problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize()/2, elementYLocation + elementHeight / 4,
                problems[problemIndex].getProblemSize(), elementHeight); 
     
        //field properties
        problemField.setEditable(false); //makes the problemField unable to be edited
        problemField.setWrapStyleWord(true); //allows wrapping of the text
        problemField.setLineWrap(true); //allows wrapping of the text (for each word)
        problemField.setVisible(true); //sets the problemField as visible
        problemField.setFont(new Font("Comic Sans", Font.BOLD, 16)); //sets Font as Bold Comic Sans with size 16
        problemField.setText(problems[problemIndex].getQuestion() + " = ?"); //sets format of the problemField using getQuestion method of problems
        problemField.setBackground(Color.LIGHT_GRAY); //sets the background the problemField as Light Gray to blend in

        //guess button configuration
        guess.setLocation(375, elementYLocation); //sets location of the button using elementYLocation
        guess.setSize(80, elementHeight); //sets the size of the element using elementHeight
        //action listener added to guess button
        guess.addActionListener(e -> { //adds ActionListener using lambda expression
            String userAnswer = answerBox.getText(); //answer received from field
            if(userAnswer.isBlank()) { //checks to see if userAnswer is empty
                //show dialog box prompting user to enter a number
                JOptionPane.showMessageDialog(this, "Enter a number to submit your answer!");
            }
            
            // user enters again to start game over
            else if(userAnswer.equalsIgnoreCase("AGAIN")) { 
                getFrame().remove(this); //remove element from the frame
                getFrame().add(new HomeScreen(getFrame()));  //add HomeScreen to the frame
                getFrame().revalidate(); //revalidates for removal/addition of components
                getFrame().repaint(); //repaints the frame

            }
            //if the users answers all the questions correctly, the win screen appears
            else if (userAnswer.equals(String.valueOf((int)(problems[problemIndex].getAnswer())))) { //checks to see if userAnswer is equal to the answer
                if(problemIndex == problems.length - 1) { //checks to see if problemIndex is at the end of problems[] array
                    getFrame().remove(this); //remove element from the frame
                    getFrame().add(new EndScreen("win", getFrame())); //add EndScreen with parameter "win" to the frame
                    getFrame().revalidate(); //revalidates for removal/addition of components
                    getFrame().repaint(); //repaints the frame
                }
                else{ //moves to next problem
                    problemIndex++; 
                    //set bounds and text for problem according to new problemSize
                    problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize() / 2,
                            elementYLocation + elementHeight /4, problems[problemIndex].getProblemSize(), elementHeight);
                    problemField.setText(problems[problemIndex].getQuestion() + " = ?"); //sets text according to question datafield of problem
                }
            }
            else { //answer is wrong
                if (wrongAnswers == images.length - 1) { //checks to see if images is at the end
                    getFrame().remove(this); //remove element from the frame
                    getFrame().add(new EndScreen("lose", getFrame())); //add EndScreen with parameter "lose" to the frame
                    getFrame().revalidate(); //revalidates for removal/addition of components
                    getFrame().repaint(); //repaints the frame
                }
                else{
                    wrongAnswers++; //increments the wrongAnswers by 1
                    animationPanel.removeAll(); //removes all the elements from the panel
                    animationPanel.add(images[wrongAnswers]); //adds the next image to the panel
                    for(SnowFlake s: snowflakes) { //for loop goes through each snowflake in snowflakes[]
                        animationPanel.add(s); //add each snowflake to the panel
                    }
                    animationPanel.revalidate(); //revalidates for removal/addition of components
                    animationPanel.repaint(); //repaints the frame
                }
            }
            answerBox.setText(""); //clear text in the answer box
        });

        //load images and set bounds
        for(int i = 0; i < images.length; i++) { //goes through each index in images[]
            images[i] = new JLabel(new ImageIcon(importImage("images/snowman_" + (i + 1) + ".png"))); //adds each snowman image to the array
            images[i].setBounds(0, 0, getWidth(), getHeight() - spaceForElements); //sets the bounds and size of each image 
        }

        //configure animation panel
        animationPanel.setBackground(getBackgroundColor()); //sets background color according to backgroundColor datafield
        animationPanel.setBounds(0, 0, getWidth(), getHeight() - spaceForElements); //sets the bounds of animationPanel according to 
        animationPanel.add(images[wrongAnswers]); //add the first Label in images[] to the animationPanel 
        animationPanel.setLayout(null); //sets the animationPanel layout to null (for snowflakes)
        
        //set layout and add components to Playscreen
        setLayout(null); //sets the layout to null (to use absolute positioning of elements)
        add(answerBox); //adds answerBox 
        add(problemField); //adds problemField
        add(guess); //adds guess button
        add(animationPanel); //adds animationPanel

        //add snowflakes to the animation panel
        for(int i = 0; i < snowflakes.length; i++) { //iterates through each element in snowflakes[]
            snowflakes[i] = new SnowFlake(animationPanel); //adds a new snowflake to the animationPanel
            animationPanel.add(snowflakes[i]); //adds snowflake to animationPanel
            animationPanel.revalidate(); //revalidates for removal/addition
            animationPanel.repaint(); //repaints the panel
        }



    }

    // problems generated based on difficulty
    public void generateProblems(String difficulty) {
        switch (difficulty) { //switch to handle each difficulty level
            case "Easy": { //difficulty is Easy
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) { 
                    //create new instance of easy problems
                    problems[i] = new EasyProblems();
                }
            }
            break; //creates break to avoid stacking cases
            case "Medium": { //difficulty is Medium
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) {
                    //create new instance of medium problems
                    problems[i] = new MediumProblems();
                }
            }
            break; //creates break to avoid stacking cases
            case "Hard": {//difficulty is hard
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++) {
                    //create new instance of hard problems
                    problems[i] = new HardProblems();
                }
            }
            break; //creates break to avoid stackign cases
            case "Insanity": { //difficulty is Insanity
                //for each index in the problems array
                for (int i = 0; i < problems.length; i++)
                    //create new instance of insane problems
                    problems[i] = new InsanityProblems();
            }
        }
    }

}
