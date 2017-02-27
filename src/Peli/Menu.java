package Peli;

import java.awt.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class Menu extends JPanel implements ActionListener {
    private Image bgImg;
    private Timer time;
    private boolean auki;
  
    public Menu() {
        auki=true;
        addKeyListener(new ActionListener());
        setFocusable(true);
        //haetaan taustakuva
        ImageIcon taustakuva = new ImageIcon(this.getClass().getResource("images/taustakuva.jpg"));
        bgImg = taustakuva.getImage();

        //tehdään timeri animointia varten
        time = new Timer(5, this);
        time.start();
        

    }

    public boolean auki(){

        return auki;
    }
    public void actionPerformed(ActionEvent tapahtuma) {
        repaint();
    }

    public void paint(Graphics grafiikat) {

        super.paint(grafiikat);
        Graphics2D grafiikat2d = (Graphics2D) grafiikat;
        grafiikat2d.drawImage(bgImg, 0, 0, null); // taustakuvan luominen
    }

    private class ActionListener extends KeyAdapter {
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_S) {
            
  
        }
        if (key == KeyEvent.VK_O) {
            
        }


        if (key == KeyEvent.VK_E) {
            System.exit(0);
        }
   
    

    }

    
    }
}