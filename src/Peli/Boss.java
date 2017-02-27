package Peli;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Boss {

    /**
     * Vihun x-koordinaatti
     */
    private int x;
    /**
     * vihun y-koordinaatti
     */
    private int y;
    /**
     * integer a kontrolloi bossin hyppäämisen rajoja
     */
    private int a;
    /**
     * bossin kuva
     */
    private Image boss;
    /**
     * vihun elämien lukumäärä
     */
    private int elama;
    /**
     * bossista saatavat pongot
     */
    private int pongo;
    /**
     * boolean kontrolloi vihulaisen tippumista
     */
    private boolean fall;
    /**
     * boolean vihulaisen elämästatuksesta
     */
    private boolean elossa;
    /**
     * boolean kontrolloi bossin kontaktia
     */
    private boolean osuma;
    /**
     * boolean kontrolloi bossin kontaktista ottamaa hittiä
     */
    private boolean hitti;
    /**
     * boolean kontrolloi bossin suuntaa.
     */
    boolean right;
    /**
     * bossin kuva vasemmalle
     */
    private ImageIcon lefty = new ImageIcon(this.getClass().getResource("images/boss.png"));
    /**
     * bossin kuva oikealle
     */
    private ImageIcon righty = new ImageIcon(this.getClass().getResource("images/bossright.png"));
    /**
     * ArrayList bossin ammuksista
     */
    public static ArrayList ammukset;

    /**
     * Bossin konstruktori
     *
     * @param startX vihulaisen alkukoordinaatti x
     * @param startY vihulaisen alkukoordinaatti y
     * @param elama vihualisen elämien lukumäärä
     */
    public Boss(int startX, int startY) {
        ammukset = new ArrayList();
        pongo = 0;
        right = false;
        fall = false;
        hitti = false;
        osuma = false;
        elama = 10;
        x = startX;
        y = startY;
        a = startY;
        elossa = true;
        boss = lefty.getImage();
    }

    /**
     * liikkumismetodi. Kun boss kääntyy vasemmalle sen kuva vaihtuu ja x pienenee. Kun se on Arton kohdalla, sen suunta muuttuu
     * ja x-koordinaatti suurenee nopeammin.  Kun x on tarpeeksi suuri boss vaihtaa suuntaa. 
     * Boss pomppii samalla ylös ja alas. 
     * Boss ampuu vain kun se on kääntyneenä vasemmalle.
     */
    public void move(int dx, int left) {
        if (right == false) {
            x = x - 1;
            boss = lefty.getImage();
        }
        if (x == 250) {
            right = true;
        }

        if (right == true) {
            x = x + 3;
            boss = righty.getImage();

        }

        if (x >= 990) {
            right = false;
        }

        if (fall == false) {
            y = y - 1;
            if (y == 200) {
                fall = true;
            }
        }
        if (fall == true) {
            y = y + 1;
            if (y == a) {
                fall = false;
            }
        }
        if (right == false && x % 225 == 0 && elossa==true) {

            shoot();
        }
    }

    /**
     * Ampumismetodi
     */
    public void shoot() {
        BossKuti a = new BossKuti((getX() - 91), (getY() + 114 / 2));
        ammukset.add(a);
    }

    /**
     * Metodi palauttaa kaikki Bossin ammukset
     * @return palauttaa ArrayListin ammuksista
     */
    public static ArrayList getAmmukset() {
        return ammukset;
    }

    /**
     * metodi, joka hakee bossin kuvan
     *
     * @return palauttaa tämänhetkisen kuvan
     */
    public Image getImage() {
        return boss;
    }

    /**
     * metodi, joka hakee  tämänhetkisen x-koordinaatin
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * metodi, joka hakee y-koordinaatin
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * metodi, joka hakee elämätilanteen
     *
     * @return
     */
    public int getLife() {
        return elama;
    }

    /**
     * metodi, joka vähentää  elämiä
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
            pongo = 9000;
        }
    }

    /**
     * Metodi kontrolloi osumaa
     */
    public void osui() {
        osuma = true;
        damage();
    }

    /**
     * Metodi joka kontrolloi  viholliskontaktia. Palauttaa kontaktin ja damagen ennalleen.
     */
    public void eiOsu() {
        osuma = false;
        hitti = false;
    }

    /**
     * Metodi palauttaa pongot
     * @return palauttaa pongot
     */
    public int getPongo() {
        return pongo;
    }

    /**
     * metodi, joka palauttaa false, jos elämät loppuvat
     *
     * @return
     */
    boolean isAlive() {
        return elossa;
    }

    /**
     * Metodi, jolla tarkistetaan kuvan rajat ja luodaan neliö törmäyksen tarkistamista varten
     * @return palauttaa uuden neliön
     */
    public Rectangle checkBounds() {
        return (new Rectangle(x, y, 91, 114));
    }
}