package Peli;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *Luokka Arton kuteja varten
 * @author Saeru
 */
public class Kuti {

    /**
     * boolean kudin näkyvyyttä varten
     */
    private boolean visible;
    /**
     * boolean kudin suuntaa varten
     */
    private boolean right;
    /** 
     * x-koordinaatti
     */
    private int x;
    /**
     * y-koordinaatti
     */
    private int y;
    /**
     * kudin kuva
     */
    private Image kuti;
    /**
     * kudin kuva
     */
    private ImageIcon bullet = new ImageIcon(this.getClass().getResource("images/oink.png"));

    /**
     * Konstruktori kudille
     * @param startX
     * @param startY
     * @param direction kertoo kudin suunnan
     */
    public Kuti(int startX, int startY, boolean direction) {
        right = direction;
        visible = true;
        x = startX;
        y = startY;
        kuti = bullet.getImage();
    }

    /**
     * metodi palauttaa kuvan
     * @return kuti
     */
    public Image getImage() {
        return kuti;
    }

    /**
     * x-koordinaatin getteri
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * y-koordinaatin getteri
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * liikkumismetodi. Jos luodin suuntan oikealle niin x-koordinaatti kasvaa. Jos suunta on vasemmalle niin x-koordinaatti pienenee.
     */
    public void move() {
        if (visible == true && right == true) {
            x = x + 2;
        }
        if (visible == true && right == false) {
            x = x - 2;
        }
        if (x > 1000 || x < -50) {
            visible = false;
        }
    }

    /**
     * metodi kertoo luodin kontaktin
     */
    public void osuma() {
        visible = false;
    }

    /**
     * metodi kertoo onko luoti näkyvissä
     * @return visible
     */
    public boolean visible() {
        return visible;
    }

    /**
     * palauttaa luodin kokoisen neliön
     * @return uusi neliö
     */
    public Rectangle checkBounds() {
        return (new Rectangle(x, y, 30, 30));
    }
}
