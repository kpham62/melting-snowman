import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.*;
import java.util.Locale;

public class PlayScreen extends GamePanel{
    private final JPanel animationPanel = new JPanel();
    private int wrongAnswers = 0;
    private int problemIndex = 0;
    private Problems[] problems = new Problems[10];
    private final JLabel[] images = new JLabel[5];
    private final JTextField answerBox = new JTextField();
    private final JTextArea problemField = new JTextArea();
    private final JButton guess = new JButton("Enter");

    private final SnowFlake[] snowflakes = new SnowFlake[15];

    public PlayScreen(String difficulty, JFrame frame ) {
        setLayout(new BorderLayout());
        setFrame(frame);
        setSize(getWidth(), getHeight());
        setBackground(Color.LIGHT_GRAY);
        generateProblems(difficulty);




        final int elementYLocation = 405;
        final int elementHeight = 40;
        final int spaceForElements = 100;

        final Point answerBoxLocation = new Point(250, elementYLocation);
        final Dimension answerBoxSize = new Dimension(100, elementHeight);
        answerBox.setLocation(answerBoxLocation);
        answerBox.setSize(answerBoxSize);

        //problem text field
        final int problemFieldCenter = 125;
        problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize()/2, elementYLocation + elementHeight / 4,
                problems[problemIndex].getProblemSize(), elementHeight);
        problemField.setEditable(false);
        problemField.setWrapStyleWord(true);
        problemField.setLineWrap(true);
        problemField.setVisible(true);
        problemField.setFont(new Font("Comic Sans", Font.BOLD, 16));
        problemField.setText(problems[problemIndex].getQuestion() + " = ?");
        problemField.setBackground(Color.LIGHT_GRAY);


        guess.setLocation(375, elementYLocation);
        guess.setSize(80, elementHeight);
        guess.addActionListener(e -> {
            String userAnswer = answerBox.getText();
            if(userAnswer.isBlank()) {
                JOptionPane.showMessageDialog(this, "Enter a number to submit your answer!");
            }
            else if(userAnswer.equalsIgnoreCase("AGAIN")) {
                getFrame().remove(this);
                getFrame().add(new HomeScreen(getFrame()));
                getFrame().revalidate();
                getFrame().repaint();

            }
            else if (userAnswer.equals(String.valueOf((int)(problems[problemIndex].getAnswer())))) {
                if(problemIndex == problems.length - 1) {
                    getFrame().remove(this);
                    getFrame().add(new EndScreen("win", getFrame()));
                    getFrame().revalidate();
                    getFrame().repaint();
                }
                else{
                    problemIndex++;
                    problemField.setBounds(problemFieldCenter - problems[problemIndex].getProblemSize() / 2,
                            elementYLocation + elementHeight /4, problems[problemIndex].getProblemSize(), elementHeight);
                    problemField.setText(problems[problemIndex].getQuestion() + " = ?");
                }
            }
            else {
                if (wrongAnswers == images.length - 1) {
                    getFrame().remove(this);
                    getFrame().add(new EndScreen("lose", getFrame()));
                    getFrame().revalidate();
                    getFrame().repaint();
                }
                else{
                    //animationPanel.remove(images[wrongAnswers]);
                    wrongAnswers++;
                    animationPanel.removeAll();
                    animationPanel.add(images[wrongAnswers]);
                    for(SnowFlake s: snowflakes) {
                        animationPanel.add(s);
                    }
                    animationPanel.revalidate();
                    animationPanel.repaint();
                }
            }
            answerBox.setText("");
        });


        for(int i = 0; i < images.length; i++) {
            images[i] = new JLabel(new ImageIcon(importImage("images/snowman_" + (i + 1) + ".png")));
            images[i].setBounds(0, 0, getWidth(), getHeight() - spaceForElements);
        }

        animationPanel.setBackground(getBackgroundColor());
        animationPanel.setBounds(0, 0, getWidth(), getHeight() - spaceForElements);
        animationPanel.add(images[wrongAnswers]);
        animationPanel.setLayout(null);




        setLayout(null);
        add(answerBox);
        add(problemField);
        add(guess);
        add(animationPanel);

        for(int i = 0; i < snowflakes.length; i++) {
            snowflakes[i] = new SnowFlake(animationPanel);
            animationPanel.add(snowflakes[i]);
            animationPanel.revalidate();
            animationPanel.repaint();
        }



    }

    public void generateProblems(String difficulty) {
        switch (difficulty) {
            case "Easy": {
                for (int i = 0; i < problems.length; i++) {
                    problems[i] = new EasyProblems();
                }
            }
            break;
            case "Medium": {
                for (int i = 0; i < problems.length; i++) {
                    problems[i] = new MediumProblems();
                }
            }
            break;
            case "Hard": {
                for (int i = 0; i < problems.length; i++) {
                    problems[i] = new HardProblems();
                }
            }
            break;
            case "Insanity": {
                for (int i = 0; i < problems.length; i++)
                    problems[i] = new InsanityProblems();
            }
        }
    }

}
