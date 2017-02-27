package Peli;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Arto {

    /**
     * Kertoo onko Arto hyppäämässä
     */
    private boolean hyppy;
    /**
     * Kertoo onko Arto putoamassa
     */
    private boolean fall;
    /**
     * Arton x-koordinaatti
     */
    private int x;
    /**
     * Arton x-koordinaatin muutos
     */
    private int dx;
    /**
     * Arton y-koordinaatti
     */
    private int y;
    /**
     * Arton y-koordinaatin muutos
     */
    private int dy;
    /**
     * taustan x-koordinaatti 1. Käytetään ensimmäisen taustanpalasen piirtämistä varten.
     */
    private int bx;
    /**
     * taustan x-koordinaatti 2. Käytetään toisen taustanpalasen piirtämistä varten
     */
    private int bx2;
    /**
     * Boolean kontrolloimaan arton osumaa viholaiseen
     */
    private boolean osuma = false;
    /**
     * Boolean ammuksien suuntaa varten
     */
    private boolean right;
    /**
     * Boolean kontrolloimaan Artoon kohdistuvia osumia. Jos hitti on true on Artoon osunut, eikä osumia lasketa uudestaan saman kosketuksen aikana
     * 
     */
    private boolean hitti;
    /**
     * Arton kuva
     */
    private Image stilli;
    /**
     * Kuva oikealle liikkuvasta Artosta
     */
    private ImageIcon stopr = new ImageIcon(this.getClass().getResource("images/artostopr.gif"));
    ImageIcon stopl = new ImageIcon(this.getClass().getResource("images/artostopl.gif"));
    private ImageIcon oikea = new ImageIcon(this.getClass().getResource("images/artoright.gif"));
    /**
     * Kuva vasemmalle liikkuvasta Artosta
     */
    private ImageIcon vasen = new ImageIcon(this.getClass().getResource("images/artoleft.gif"));
    /**
     * Kuva vahingoittuneesta Artosta
     */
    private ImageIcon splatter = new ImageIcon(this.getClass().getResource("images/bloodpiggy.png"));
    /**
     * Arton elämien lukumäärä
     */
    private int elama;
    /**
     * Arton koordinaatit, kun Arto liikkuu eteenpäin
     */
    private int left;
    /**
     * Arton elämästatus
     */
    private boolean elossa;
    /**
     * ArrayList Arton ampumille ammuksille
     */
    public static ArrayList ammukset;

    /**
     * Konstruktori, antaa alkuarvot Artolle.
     */
    public Arto() {

        right = true;
        ammukset = new ArrayList();
        osuma = false;
        hitti = false;
        hyppy = false;
        fall = false;
        hyppy = false;
        left = 90;
        stilli = stopr.getImage();
        elama = 3;
        x = 90;
        y = 415;
        bx = 990; //vähän pienempi kuin koko tausta
        bx2 = 0;
        elossa = true;
    }

    /**
     * Metodi Arto-porsaan liikkumiseen. bx taustakuvan x-koordinaatti muuttuu
     * Arton liikkeen mukaan bx2 taustakuvan toinen x-koordinaatti muuttuu Arton
     * liikkeen mukaan x Arton x-koordinaatti muuttuu liikkeen mukaan. Jos Arton sijainti on
     * vähemmän kuin 250, muuttuu left dx:n verran ja Arto liikkuu ruudulla. Muuten muuttuu x ja tausta liikkuu.
     */
    public void move() {
        if (dx != -1) {
            if (left + dx <= 250) {
                left = left + dx;
            } else {
                bx = bx + dx;//bx muuttuu dx:n verran
                bx2 = bx2 + dx;//bx2 muuttuu aina dx:n verran
                x = x + dx; //x-koordinaatti muuttuu aina dx:n verran

            }
        } else {
            if (left + dx > 0) {
                left = left + dx;
            }
        }

    }

    /**
     * Metodi hyppimistä varten.
     */
    public void hyppy() {

        /*
         * Jos hyppy tapahtuu, mutta ei pudota niin y-koordinaatti pienenee
         */
        if (hyppy == true && fall == false) {
            y = y - 1;
        }

        /*
         * Jos saavutetaan hypyn katto, putoaminen tapahtuu
         */
        if (y <= 200) {
            fall = true;
        }
        /*
         * Jos putoaminen on totta, y-koordinaatti kasvaa
         */
        if (fall == true) {
            y = y + 1;
        }
        /*
         * kun ollaan maassa, niin putoamista eikä hyppyä tapahdu ja fall ja
         * hyppy saavat alkuarvonsa
         */
        if (y == 415) {
            fall = false;
            hyppy = false;
        }

    }

    /**
     * Metodi Arton x-koordinaatin hakemiseen
     *
     * @return palauttaa Arton x-koordinaatin
     */
    public int getX() {
        return x;
    }

    /**
     * Metodi dx:n muutokseen
     * @return palauttaa dx:n
     */
    public int getdx() {
        return dx;
    }

    /**
     * Metodi bx:n nollaamista varten
     * @return palauttaa bx:n
     */
    public int setBx() {
        bx = 0;
        return bx;
    }

    /**
     * Metodi bx2:n nollaamista varten
     * @return palauttaa bx:n
     */
    public int setBx2() {
        bx2 = 0;
        return bx2;
    }

    /**
     * bx:n getteri
     * @return bx
     */
    public int getBx() {
        return bx;
    }

    /**
     * bx2:n getteri
     * @return bx2
     */
    public int getBx2() {
        return bx2;
    }

    /**
     * left:n getteri
     * @return left
     */
    public int getLeft() {
        return left;
    }

    /**
     * Metodi Arton y-koordinaatin hakemiseen
     *
     * @return palauttaa Arton y-koordinaatin
     */
    public int getY() {
        return y;
    }

    /**
     * Metodi Arton elämätilanteen hakemiseen
     *
     * @return palauttaa Arton elämän
     */
    public int getLife() {
        if (elama == 0) {
            elossa = false;
        }

        return elama;
    }

    /**
     * Metodi Arton tämänhetkisen kuvan hakemiseen
     *
     * @return palauttaa Arton kuvan
     */
    public Image getImage() {
        return stilli;
    }

    /**
     * Metodi aiheuttaa Artolle vahinkoa.
     */
    public void damage() {
        if (osuma == true && hitti == false) {
            if (elama > 0) {
                elama = elama - 1;
                hitti = true;
            }
        }
    }

    public boolean elossa() {
        return elossa;
    }

    /**
     * Metodi, joka kontrolli Arton viholliskontaktia. Kun Arto osuu viholliseen muuttuu osuma trueksi. Arton
     * kuva vaihtuu splatterpossuksi ja Arto ottaa osumaa.
     */
    public void osui() {
        osuma = true;
        stilli = splatter.getImage();
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
     * Metodi lisää Arton elämää
     */
    public void gainLife() {
        if (elama > 0 && elama < 3) {
            elama = elama + 1;
        }
    }

    /**
     * Metodi tutkii onko Arto elossa
     *
     * @return palauttaa arvon false, jos Arto on kuollut
     */
    boolean isAlive() {
        return elossa;
    }

    /**
     * Palauttaa ArrayListin Arton ammuksista.
     * @return kaikki Arton ammukset
     */
    public static ArrayList getAmmukset() {
        return ammukset;
    }

    /**
     * Metodi ampumiseen. Kun Arto katsoo oikealle, ammutaan oikealle. Kun Arto katsoo vasemmalle, ammutaan vasemmalle.
     */
    public void tulitus() {
        if (right == true) {
            Kuti a = new Kuti((getLeft() + 90), (getY() + 60 / 2), right);
            ammukset.add(a);
        }
        if (right == false) {
            Kuti a = new Kuti((getLeft()), (getY() + 60 / 2), right);
            ammukset.add(a);
        }
    }

    /**
     * Metodi antaa dx:lle negatiivisen arvon, kun kuljetaan vasemmalle. Arton
     * kuvake muuttuu oikealle tai vasemmalle kun liikutaan. Space aiheuttaa ampumisen
     *
     * @param event Metodi antaa dx:lle positiivisen arvon, kun kuljetaan
     * oikealle.
     */
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            stilli = vasen.getImage();
            right = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = +1;
            stilli = oikea.getImage();
            right = true;
        }


        if (key == KeyEvent.VK_UP) {
            dy = -1;
            hyppy = true;
            stilli = oikea.getImage();
        }
        if (key == KeyEvent.VK_SPACE) {
            tulitus();
        }


    }

    /**
     * Metodi palauttaa dx:n arvon takaisin nollaan kun näppäimiä ei paineta.
     *
     * @param event
     */
    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            stilli=stopl.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            stilli=stopr.getImage();
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

    }

    /**
     * Metodi Arton rajojen tarkistamiseen collision detectionia varten
     * @param palauttaa uuden neliön
     */
    public Rectangle checkBounds() {
        return (new Rectangle(left, y, 90, 67));
    }
}
