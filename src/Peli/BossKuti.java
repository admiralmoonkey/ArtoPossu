package Peli;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Saeru
 */
public class BossKuti {

    /**
     * Boolean kontrolloimaan ammuksen näkyvyyttä
     */
    private boolean visible;
    /**
     * luodin x-koordinaatti
     */
    private int x;
    /**
     * luodin y-koordinaatti
     */
    private int y;
    /**
     * integer kontrolloimaan ammuksen y-koordinaatin muutosta
     */
    private int a;
    /**
     * boolean kontrolloimaan ammuksen ylösalasliikettä
     */
    private boolean fall;
    /**
     * ammuksen kuva
     */
    private Image kuti;
    /**
     * ammuksen kuva
     */
    private ImageIcon bullet = new ImageIcon(this.getClass().getResource("images/bosskuti.png"));

    public BossKuti(int startX, int startY) {
        fall = false;
        visible = true;
        x = startX;
        y = startY;
        a = startY;
        kuti = bullet.getImage();
    }

    /**
     * getteri ammuksen kuvaa varten
     * @return palauttaa ammuksen kuvan
     */
    public Image getImage() {
        return kuti;
    }

    /**
     * getteri ammuksen x-koordinaattia varten
     * @return palauttaa x-koordinaatin
     */
    public int getX() {
        return x;
    }

    /**
     * getteri ammuksen y-koordinaattia varten
     * @return palauttaa y-koordinaatin
     */
    public int getY() {
        return y;
    }

    /**
     * metodi ammuksen liikettä varten
     */
    public void move() {
        if (visible == true) {
            x = x - 2;
            /* kun ammus ei putoa, se nousee 10 yksikköä lähtökoordinaatistaan*/
            if (fall == false) {
                y = y - 1;
                if (y == (a - 10)) {
                    fall = true;
                }
            }

            /* kun ammus putoaa, se laskeutuu 10 yksikköä lähtökoordinaatistaan alaspäin*/
            if (fall == true) {
                y = y + 1;
                if (y == (a + 10)) {
                    fall = false;
                }
            }

            /* kun ammus menee yli ruudun vasemmasta reunasta se ei enää näy*/
            if (x < -50) {
                visible = false;
            }

        }
    }

    /**
     * metodi kontrolloi luodin osumaa Artoon, jonka jälkeen se ei enää näy
     */
    public void osuma() {
        visible = false;
    }

    /**
     * metodi palauttaa booleanin visible arvon
     * @return 
     */
    public boolean visible() {
        return visible;
    }

    /**
     * metodi palauttaa ammuksen kokoisen neliön
     * @return uusi neliö
     */
    public Rectangle checkBounds() {
        return (new Rectangle(x, y, 30, 50));
    }
}
