package Peli;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Luokka kaljaa, eli elämännestettä varten.
 * @author Saeru
 */
public class Kalja {

    /**
     * boolean kontrolloi kaljan näkyvyyttä
     */
    private boolean visible;
    /**
     * kaljasta saatavat pisteet
     */
    private int pongo;
    /**
     * x-koordinaatti
     */
    private int x;
    /**
     * y-ökoordinaatti
     */
    private int y;
    /**
     * kaljan kuva
     */
    private Image kalja;
    /**
     * juomattoman kaljan kuva
     */
    private ImageIcon pullo = new ImageIcon(this.getClass().getResource("images/kalja.png"));
    /** juodun kaljan kuva
     * 
     */
    private ImageIcon juotu = new ImageIcon(this.getClass().getResource("images/juotu.png"));

    /**
     * Konstruktori kaljalle
     * @param startX
     * @param startY 
     */
    public Kalja(int startX, int startY) {
        pongo = 50;
        visible = true;
        x = startX;
        y = startY;
        kalja = pullo.getImage();
    }

    /**
     * Kaljan liikkuminen ruudulla
     * @param dx, Arton dx
     * @param left, Arton left
     */
    public void move(int dx, int left) {
        if (dx > 0) {
            if (left == 250) {
                x = x - dx;
            }
        }
    }

    /**
     * metodi kaljan kuvan noutoa varten
     * @return palauttaa tämänhetkisen kuvan
     */
    public Image getImage() {
        return kalja;
    }

    /**
     * palauttaa x-koordinaatin
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * palauttaa y-koordinaatin
     * @return  y
     */
    public int getY() {
        return y;
    }

    /**
     * kontrolloi kosketusta. Kun kaljaan osuu niin se muuttuu juoduksi.
     */
    public void osuma() {
        visible = false;
        kalja = juotu.getImage();
    }

    /**
     * palauttaa pisteet
     * @return pongo
     */
    public int getPongo() {
        return pongo;
    }

    /**
     * palauttaa kaljan näkyvyyden
     * @return visible
     */
    public boolean visible() {
        return visible;
    }

    /**
     * palauttaa kaljan kokoisen neliön.
     * @return 
     */
    public Rectangle checkBounds() {
        return (new Rectangle(x, y, 30, 30));
    }
}
