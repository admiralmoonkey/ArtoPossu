package Peli;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Frame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Arto-Possun seikkailut");
        frame.add(new Tausta());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        Music music=new Music();
    }
}