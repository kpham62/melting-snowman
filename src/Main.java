import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame(); // create a new JFrame, which allows us to create the UI

        frame.setSize(500, 500); // set the size to 500x500 pixels
        frame.setLayout(null); // null layout allows for absolute positioning, allows us to manually edit the UI
        frame.setResizable(false); // focused on absolute positioning to stylize so kept to false
        frame.setTitle("Melting Snowman Game"); // set title of panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // on close, exit
        frame.add(new HomeScreen(frame)); // add the HomeScreen to the panel
        frame.setVisible(true); // set the panel as visible


    }
}
