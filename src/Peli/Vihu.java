package Peli;

import java.awt.*;
import javax.swing.ImageIcon;

public class Vihu {

    /**
     * Vihun x-koordinaatti
     */
    private int x;
    /**
     * vihun y-koordinaatti
     */
    private int y;
    /**
     * integer a kontrolloimaan vihulaisen hyppelyitä.
     */
    private int a;
    /**
     * vihun kuva
     */
    private Image vihu;
    /**
     * vihun elämien lukumäärä
     */
    private int elama;
    /**
     * boolean kontrolloimaan vihulaisen tippumista
     */
    private boolean fall;
    /**
     * vihun elämästatus
     */
    private boolean elossa;
    /**
     * boolean kontrolloimaan viholaisen ottamaa kontaktia
     */
    private boolean osuma;
    /**
     *boolean kontrolloimaan vihlaisen kontaktista ottamaa damagea.
     */
    private boolean hitti;
    /**
     * Viholaisen kuva
     */
    private ImageIcon kuva = new ImageIcon(this.getClass().getResource("images/vihu.png"));
    /**
     * viholaisen tappamisesta saatavat pisteet
     */
    private int pongo;

    /**
     * Vihun konstruktori
     *
     * @param startX vihulaisen alkukoordinaatti x
     * @param startY vihulaisen alkukoordinaatti y
     * @param elama vihualisen elämien lukumäärä
     */
    public Vihu(int startX, int startY, int elkut) {
        pongo = 0;
        fall = false;
        hitti = false;
        osuma = false;
        elama = elkut;
        x = startX;
        y = startY;
        a = startY;
        elossa = true;
        vihu = kuva.getImage();
    }

    /**
     * vihulaisen liikkumismetodi
     */
    public void move(int dx, int left) {
        if (fall == false) {
            y = y - 1;
            if (y == 300) {
                fall = true;
            }
        }
        if (fall == true) {
            y = y + 1;
            if (y == a) {
                fall = false;
            }
        }
        if (dx > 0) {
            if (left == 250) {
                x = x - dx;
            }
        }
    }

    /**
     * metodi, joka hakee vihulaisen kuvan
     *
     * @return palauttaa vihun tämänhetkisen kuvan
     */
    public Image getImage() {
        return vihu;
    }

    /**
     * metodi, joka hakee vihun tämänhetkisen x-koordinaatin
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * metodi, joka hakee vihun y-koordinaatin
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * metodi, joka hakee vihulaisen elämätilanteen
     *
     * @return
     */
    public int getLife() {
        return elama;
    }

    /**
     * metodi, joka vähentää vihun elämiä. Kun vihu ottaa kontaktia siltä vähennetään yksi elämä, jonka älkeen hitti saa arvon true. 
     * Kun hitin arvo on true, ei viholainen voi menettää enempiä elämiä kerralla. Kun viholainen kuolee, siitä saa pisteitä.
     */
    public void damage() {
        if (osuma == true && hitti == false) {
            if (elama > 0) {
                elama = elama - 1;
                hitti = true;
            }
        }
        if (elama == 0) {
            elossa = false;
            pongo = 100;
        }
    }

    /**
     * pongojen getteri
     * @return 
     */
    public int getPongo() {
        return pongo;
    }

    /**
     * Metodi kontrolloi kontaktia
     */
    public void osui() {
        osuma = true;
        damage();
    }

    /**
     * Metodi joka kontrolloi Arton viholliskontaktia. Palauttaa kontaktin ja damagen ennalleen.
     */
    public void eiOsu() {
        osuma = false;
        hitti = false;
    }

    /**
     * metodi, joka palauttaa false, jos vihulaisen elämät loppuvat
     *
     * @return
     */
    boolean isAlive() {
        return elossa;

    }

    /**
     * Metodi, jolla tarkistetaan vihulaisen kuvan rajat ja luodaan neliö törmäyksen tarkistamista varten
     * @return palauttaa uuden neliön
     */
    public Rectangle checkBounds() {
        return (new Rectangle(x, y, 49, 57));
    }
}