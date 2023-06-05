/*
CSCI 143 Obj-Orient Prog II with Java
Highline College
Professor Nizami, Syeda
Team: Cynthia Lopez, Thomas Huynh, Kenny Pham
Game Name: Melting Snowman
 */

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame(); // create a new JFrame, which allows us to create the UI

        frame.setSize(500, 500); // set the size to 500x500 pixels
        frame.setResizable(false); // focused on absolute positioning to stylize so kept to false
        frame.setTitle("Melting Snowman Game"); // set title of panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // on close, exit
        frame.add(new HomeScreen(frame)); // add the HomeScreen to the panel
        frame.setVisible(true); // set the panel as visible


    }
}
