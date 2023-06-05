import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EndScreen extends GamePanel{
    JPanel UIPanel = new JPanel();
    JLabel outcomeScreen;
    private Timer wiggleTimer;
    private boolean isWiggling = false;

    private void startWiggleAnimation() {
        if (!isWiggling) {
            isWiggling = true;
            wiggleTimer.start();
        }

    }

    private void stopWiggleAnimation() {
        isWiggling = false;
        wiggleTimer.stop();
    }

    public EndScreen(String outcome, JFrame frame) {
        setFrame(frame);
        outcomeScreen = new JLabel(new ImageIcon(importImage("images/" + outcome + ".png")));
        outcomeScreen.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/" + outcome + ".png"))).getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH)));
        outcomeScreen.setBounds(0, 0, 500, 400);
        add(outcomeScreen);

        JButton again = new JButton("Again?");
        again.addActionListener(e -> {
            getFrame().remove(this);
            getFrame().add(new HomeScreen(getFrame()));
            getFrame().revalidate();
            getFrame().repaint();
        });
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> {
            System.exit(0);
        });

        again.setBounds(25, 400, 150, 50);
        quit.setBounds(125, 400, 150, 50);

        UIPanel.setBounds(25, 400, 400, 50);
        UIPanel.setBackground(getBackgroundColor());
        UIPanel.add(again);
        UIPanel.add(quit);
        add(UIPanel);


        wiggleTimer = new Timer(200, new ActionListener() {

            boolean shift = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shift) {
                    outcomeScreen.setLocation(outcomeScreen.getX() - 5, outcomeScreen.getY());
                    shift = false;
                }
                else{
                    outcomeScreen.setLocation(outcomeScreen.getX() + 5, outcomeScreen.getY());
                    shift = true;
                }
            }
        });
        if(outcome == "win") {
            wiggleTimer.start();
        }


    }
}
