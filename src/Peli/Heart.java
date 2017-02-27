package Peli;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Saeru
 */
public class Heart {

    private int x;
    private int y;
    private Image heart;
    private ImageIcon sydan3 = new ImageIcon(this.getClass().getResource("images/heart3.png"));
    private ImageIcon sydan2 = new ImageIcon(this.getClass().getResource("images/heart2.png"));
    private ImageIcon sydan1 = new ImageIcon(this.getClass().getResource("images/heart.png"));
    private ImageIcon sydan0 = new ImageIcon(this.getClass().getResource("images/heart0.png"));

    public Heart() {
        heart = sydan3.getImage();
    }

    public Image getImage() {
        return heart;
    }

    public void status(int a) {
        if (a == 3) {
            heart = sydan3.getImage();
        }
        if (a == 2) {
            heart = sydan2.getImage();
        }
        if (a == 1) {
            heart = sydan1.getImage();
        }
        if (a == 0) {
            heart = sydan0.getImage();
        }
    }
}
