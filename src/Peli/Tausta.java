package Peli;

import java.awt.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Tausta extends JPanel implements ActionListener {

    private Random generator;
    /**
     * Arton sydämet
     */
    private Heart heart;
    /**
     * Arto-possu
     */
    private Arto arto;
    /**
     * yksi vihulainen
     */
    private Vihu vihu;
    /**
     * toinen vihulainen
     */
    private Vihu vihu2;
    private Vihu vihu3;
    private Vihu vihu4;
    private Vihu vihu5;
    private Vihu vihu6;
    private boolean piirra;
    /**
     * pääpahis
     */
    private Boss boss;
    /**
     * yksi kalja
     */
    private Kalja kalja;
    /**
     * taustakuva
     */
    private Image bgImg;
    /**
     * maakuva
     */
    private Image groundImg;
    /**
     * ajastin
     */
    private Timer time;
    /**
     * Arraylist Arton ammuksille
     */
    private ArrayList ammukset;
    /**
     * ArrayList bossin ammuksille
     */
    private ArrayList bossAmmukset;
    private ArrayList kaljat;
    private int kaljax = 20;
    private ArrayList puut;
    /**
     * pisteet
     */
    private Score score;
    /**
     * integer elkkujen laskemista varten
     */
    private int elkut;
    private Puu este;
    private boolean ohje;
    private boolean tarina;
    private boolean voitto;
    private boolean fail;
    private ImageIcon taustakuva = new ImageIcon(this.getClass().getResource("images/taustakuva.png"));
    private ImageIcon taustatarina = new ImageIcon(this.getClass().getResource("images/taustakuvatarina.jpg"));
    private ImageIcon taustaohje = new ImageIcon(this.getClass().getResource("images/taustakuvaohje.png"));
    private ImageIcon taustawin = new ImageIcon(this.getClass().getResource("images/taustakuvawin.png"));
    private ImageIcon taustafail = new ImageIcon(this.getClass().getResource("images/taustafail.png"));

    /**
     * Konstruktori Taustalle
     */
    public Tausta() {

        voitto = false;
        fail = false;
        ohje = true;
        piirra = false;
        tarina = false;
        generator = new Random();
        kaljat = new ArrayList();
        for (int i = 0; i < 50; i++) {
            int luku = generator.nextInt(300);
            kaljax = kaljax + luku;
            kalja = new Kalja(kaljax, 438);
            kaljat.add(kalja);
        }
        puut = new ArrayList();
        for (int i = 0; i < 20; i++) {
            int luku = generator.nextInt(10000);
            este = new Puu(luku, 181);
            puut.add(este);
        }
        vihu = new Vihu(300, 425, 2);
        vihu2 = new Vihu(800, 425, 3);
        vihu3 = new Vihu(1000, 425, 5);
        vihu4 = new Vihu(1250, 425, 3);
        vihu5 = new Vihu(1800, 425, 1);
        vihu6 = new Vihu(2000, 425, 3);

        heart = new Heart();
        score = new Score();
        //vihu = new Vihu(500, 425, 2); //vihun alkukoordinaatit mukaan
        //vihu2 = new Vihu(1200, 425, 2); //vihun alkukoordinaatit mukaan
        for (int i = 0; i < 20; i++) {
        }

        arto = new Arto();

        boss = new Boss(2000, 367);
        addKeyListener(new ActionListener());
        setFocusable(true);

        //haetaan taustakuva


        //haetaan groundimage
        ImageIcon maakuva = new ImageIcon(this.getClass().getResource("images/ground.png"));
        groundImg = maakuva.getImage();

        //tehdään timeri animointia varten
        time = new Timer(5, this);
        time.start();

    }

    /**
     * Metodi, joka animoi tapahtumat ruudulle 5ms välein.
     *
     * @param tapahtuma
     */
    public void actionPerformed(ActionEvent tapahtuma) {
        /*  hakee statuksen sydämien piirtämistä varten   
         */
        heart.status(elkut);
        if (arto.elossa() == true && boss.isAlive() == true) {
            /* Arton liikkuminen*/
            arto.move();

            /*Arton hyppiminen*/
            arto.hyppy();

            if (vihu.isAlive() == true) {
                vihu.move(arto.getdx(), arto.getLeft());
            }
            if (vihu2.isAlive() == true) {
                vihu2.move(arto.getdx(), arto.getLeft());
            }
            if (vihu3.isAlive() == true) {
                vihu3.move(arto.getdx(), arto.getLeft());
            }
            if (vihu4.isAlive() == true) {
                vihu4.move(arto.getdx(), arto.getLeft());
            }
            if (vihu5.isAlive() == true) {
                vihu5.move(arto.getdx(), arto.getLeft());
            }
            if (vihu6.isAlive() == true) {
                vihu6.move(arto.getdx(), arto.getLeft());
            }
            /*Boss liikkuu vasta kun Arto ylittää tietyn rajan */
            if (arto.getX() > 2000) {
                boss.move(arto.getdx(), arto.getLeft());
            }
            /* kaljan liike ruudulla
             * 
             */
            for (int i = 0; i < kaljat.size(); i++) {
                Kalja a = (Kalja) kaljat.get(i);
                a.move(arto.getdx(), arto.getLeft());
            }

            /* lisää arraylistiin ammukset kaikki tällä hetkellä olevat Arton ammukset*/
            ammukset = arto.getAmmukset();
            for (int i = 0; i < ammukset.size(); i++) {
                Kuti a = (Kuti) ammukset.get(i);
                if (a.visible() == true) {
                    a.move();
                }
            }
            for (int i = 0; i < puut.size(); i++) {
                Puu a = (Puu) puut.get(i);
                a.move(arto.getdx(), arto.getLeft());
            }
            /* Lisää arraylistiin kaikki tällä hetkellä olevat bossin ammukset*/
            bossAmmukset = boss.getAmmukset();
            for (int i = 0; i < bossAmmukset.size(); i++) {
                BossKuti b = (BossKuti) bossAmmukset.get(i);
                if (b.visible() == true) {
                    b.move();
                }
            }
        }


        /* Metodeja collisioneihin*/
        checkCollision();
        checkBulletCollision();
        checkKaljaCollision();
        checkShootBossCollision();
        checkBossShootsCollision();
        backGround();

        /*hakee scoren*/
        score.getScore();
        /*piirtää kaiken uudelleen*/
        repaint();

    }

    /**
     * Metodi, joka piirtää ruudulle grafiikat. Ensin luodaan taustakuva, jonka
     * jälkeen luodaan groundImage, joka piirretään uudestaan ja uudestaan Arton
     * liikkuessa. Piirretään Arto ja vihulaiset ruudulle.
     *
     * @param grafiikat
     */
    public void backGround() {
        if (ohje == true) {
            bgImg = taustaohje.getImage();
        }
        if (tarina == true) {
            bgImg = taustatarina.getImage();
        }
        if (voitto == true) {
            bgImg = taustawin.getImage();
        }
        if (fail == true) {
            bgImg = taustafail.getImage();
        }
        if (piirra == true) {
            bgImg = taustakuva.getImage();
        }
    }

    public void paint(Graphics grafiikat) {

        super.paint(grafiikat);
        Graphics2D grafiikat2d = (Graphics2D) grafiikat;
        Font fontti1 = new Font("Verdana", Font.PLAIN, 14);
        Font fontti = new Font("Arial", Font.BOLD, 20);

        if (voitto == true) {
            grafiikat2d.drawImage(bgImg, 0, 0, null); // taustakuvan luominen  
            grafiikat2d.setFont(fontti);
            grafiikat2d.setColor(Color.black);
            grafiikat2d.drawString("VOITIT! Arton perhe on pelastettu! Keräsit:  " + score.toString() + " PONGOA!", 250, 50);
        }
        if (fail == true) {
            grafiikat2d.drawImage(bgImg, 0, 0, null);
            grafiikat2d.setFont(fontti);
            grafiikat2d.setColor(Color.red);
            grafiikat2d.drawString("HÄVISIT :'( ! Arton perhe on menetetty. Keräsit:  " + score.toString() + " PONGOA!", 250, 550);
        }
        if (tarina == true) {
            grafiikat2d.drawImage(bgImg, 0, 0, null); // taustakuvan luominen  
        }
        if (ohje == true) {
            grafiikat2d.drawImage(bgImg, 0, 0, null); // taustakuvan luominen
        }
        if (piirra == true) {
            grafiikat2d.setFont(fontti1);
            grafiikat2d.drawImage(bgImg, 0, 0, null); // taustakuvan luominen
            if ((arto.getX() - 190) % 2180 == 0) {
                arto.setBx2();
            }
            if ((arto.getX() - 1280) % 2180 == 0) {
                arto.setBx();
            }


            /*
             * framet n. 1090 pitkiä. yksi frame piirretään uudestan aina 2180
             * välein
             */

            grafiikat2d.drawImage(groundImg, 990 - arto.getBx(), 440, null); //maalataan hyppelytaso ukkelia varten, x-koordinaatti negatiivisena koska mennään eteenpäin.     
            if (arto.getX() >= 190) {
                grafiikat2d.drawImage(groundImg, 990 - arto.getBx2(), 440, null);
            }
            
                        for (int i = 0; i < puut.size(); i++) {
                Puu a = (Puu) puut.get(i);
                grafiikat2d.drawImage(a.getImage(), a.getX(), a.getY(), null);

            }

            grafiikat2d.drawImage(arto.getImage(), arto.getLeft(), arto.getY(), null); //piirretään arto     
        /*if (vihu.isAlive() == true) {
            grafiikat2d.drawImage(vihu.getImage(), vihu.getX(), vihu.getY(), null);//piirretään vihulainen
            }*/

            // String myString1 = Integer.toString(vihu.getLife());
            // grafiikat2d.drawString(myString1, (vihu.getX() + 50), (vihu.getY() + 50));

            if (boss.isAlive() == true && arto.getX() > 2000) {
                grafiikat2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);//piirretään vihulainen
                String myString3 = Integer.toString(boss.getLife());
                grafiikat2d.drawString("Left to kill the boss: " + myString3, 450, 10);
            }

            // String myString2 = Integer.toString(vihu2.getLife());
            //grafiikat2d.drawString(myString2, (vihu2.getX() + 50), (vihu2.getY() + 50));
            if (vihu.isAlive() == true) {
                grafiikat2d.drawImage(vihu.getImage(), vihu.getX(), vihu.getY(), null);
            }
            if (vihu2.isAlive() == true) {
                grafiikat2d.drawImage(vihu2.getImage(), vihu2.getX(), vihu2.getY(), null);
            }
            if (vihu3.isAlive() == true) {
                grafiikat2d.drawImage(vihu3.getImage(), vihu3.getX(), vihu3.getY(), null);
            }
            if (vihu4.isAlive() == true) {
                grafiikat2d.drawImage(vihu4.getImage(), vihu4.getX(), vihu4.getY(), null);
            }
            if (vihu5.isAlive() == true) {
                grafiikat2d.drawImage(vihu5.getImage(), vihu5.getX(), vihu5.getY(), null);
            }
            if (vihu6.isAlive() == true) {
                grafiikat2d.drawImage(vihu6.getImage(), vihu6.getX(), vihu6.getY(), null);
            }

            for (int i = 0; i < kaljat.size(); i++) {
                Kalja a = (Kalja) kaljat.get(i);
                grafiikat2d.drawImage(a.getImage(), a.getX(), a.getY(), null);

            }
            for (int i = 0; i < ammukset.size(); i++) {
                Kuti a = (Kuti) ammukset.get(i);
                if (a.visible() == true) {
                    grafiikat2d.drawImage(a.getImage(), a.getX(), a.getY(), null);
                }
            }

            for (int i = 0; i < bossAmmukset.size(); i++) {
                BossKuti b = (BossKuti) bossAmmukset.get(i);
                if (b.visible() == true) {
                    grafiikat2d.drawImage(b.getImage(), b.getX(), b.getY(), null);
                }
            }



            grafiikat2d.drawImage(heart.getImage(), 0, 10, null);

            grafiikat2d.drawString("SCORE: " + score.toString(), 900, 10);
            //String myString = Integer.toString(arto.getLife());
            //grafiikat2d.drawString(myString, 400, 200);

            /*
            if (arto.elossa() == false) {
            grafiikat2d.setFont(fontti);
            grafiikat2d.setColor(Color.red);
            grafiikat2d.drawString("YOU LOSE!  YOU COLLECTED  " + score.toString() + " PONGOS!", 310, 300);
            }
            if (boss.isAlive() == false) {
            grafiikat2d.setFont(fontti);
            grafiikat2d.setColor(Color.red);
            grafiikat2d.drawString("YOU WIN!  YOU COLLECTED  " + score.toString() + " PONGOS!", 310, 300);
            }
             */
        }
    }

    /**
     * Metodi törmäyksen tarkistamista varten
     */
    public void checkCollision() {
        Rectangle r1 = arto.checkBounds();
        Rectangle r2 = vihu.checkBounds();
        Rectangle r3 = vihu2.checkBounds();
        Rectangle r4 = boss.checkBounds();
        Rectangle r5 = vihu3.checkBounds();
        Rectangle r6 = vihu4.checkBounds();
        Rectangle r7 = vihu5.checkBounds();
        Rectangle r8 = vihu6.checkBounds();

        if (r1.intersects(r4) && boss.isAlive() == true) {
            arto.osui();
        }

        if (arto.isAlive()) {
            if (r1.intersects(r2) && vihu.isAlive() == true) {
                arto.osui();
            }
            if (r1.intersects(r3) && vihu2.isAlive() == true) {
                arto.osui();
            }
            if (r1.intersects(r5) && vihu3.isAlive() == true) {
                arto.osui();
            }
            if (r1.intersects(r6) && vihu4.isAlive() == true) {
                arto.osui();
            }
            if (r1.intersects(r7) && vihu5.isAlive() == true) {
                arto.osui();
            }
            if (r1.intersects(r8) && vihu6.isAlive() == true) {
                arto.osui();
            }

        }
        if (!r1.intersects(r3) && !r1.intersects(r2) && !r1.intersects(r4) && !r1.intersects(r5) && !r1.intersects(r6) && !r1.intersects(r7) && !r1.intersects(r8)) {
            arto.eiOsu();

            elkut = arto.getLife();


        }
        if (arto.elossa() == false) {
            fail = true;
            piirra = false;
        }
    }

    /**
     * Metodi tarkistaa osuuko Arton ammukset viholaisiin
     */
    public void checkBulletCollision() {
        Rectangle r1 = vihu.checkBounds();
        Rectangle r2 = vihu2.checkBounds();
        Rectangle r5 = vihu3.checkBounds();
        Rectangle r6 = vihu4.checkBounds();
        Rectangle r7 = vihu5.checkBounds();
        Rectangle r8 = vihu6.checkBounds();

        ammukset = arto.getAmmukset();
        for (int i = 0; i < ammukset.size(); i++) {
            Kuti a = (Kuti) ammukset.get(i);
            if (a.visible() == true) {
                Rectangle r3 = a.checkBounds();
                if (r3.intersects(r1) && vihu.isAlive() == true) {
                    vihu.osui();
                    a.osuma();
                    score.setScore(vihu.getPongo());
                }
                if (r3.intersects(r2) && vihu2.isAlive() == true) {
                    vihu2.osui();
                    a.osuma();
                    score.setScore(vihu2.getPongo());
                }
                if (r3.intersects(r2) && vihu2.isAlive() == true) {
                    vihu2.osui();
                    a.osuma();
                    score.setScore(vihu2.getPongo());
                }
                if (r3.intersects(r5) && vihu3.isAlive() == true) {
                    vihu3.osui();
                    a.osuma();
                    score.setScore(vihu3.getPongo());
                }
                if (r3.intersects(r6) && vihu4.isAlive() == true) {
                    vihu4.osui();
                    a.osuma();
                    score.setScore(vihu4.getPongo());
                }
                if (r3.intersects(r7) && vihu5.isAlive() == true) {
                    vihu5.osui();
                    a.osuma();
                    score.setScore(vihu5.getPongo());
                }
                if (r3.intersects(r8) && vihu6.isAlive() == true) {
                    vihu6.osui();
                    a.osuma();
                    score.setScore(vihu6.getPongo());
                }


            } else {
                vihu.eiOsu();
                vihu2.eiOsu();
                vihu3.eiOsu();
                vihu4.eiOsu();
                vihu5.eiOsu();
                vihu6.eiOsu();
                boss.eiOsu();
            }
        }
    }

    /**
     * Metodi tarkistaa osuuko Arton ammukset Bossiin
     */
    public void checkShootBossCollision() {
        Rectangle r1 = boss.checkBounds();

        ammukset = arto.getAmmukset();
        for (int i = 0; i < ammukset.size(); i++) {
            Kuti a = (Kuti) ammukset.get(i);
            if (a.visible() == true) {
                Rectangle r2 = a.checkBounds();
                if (r2.intersects(r1) && boss.isAlive() == true) {
                    boss.osui();
                    a.osuma();
                    score.setScore(boss.getPongo());
                }
            } else {
                boss.eiOsu();
            }
        }
        elkut = arto.getLife();
        if (boss.isAlive() == false) {
            voitto = true;
            piirra = false;
        }
    }

    /**
     * Metodi tarkistaa osuuko bossin ammukset Artoon
     */
    public void checkBossShootsCollision() {
        Rectangle r1 = arto.checkBounds();
        bossAmmukset = boss.getAmmukset();
        for (int i = 0; i < bossAmmukset.size(); i++) {
            BossKuti b = (BossKuti) bossAmmukset.get(i);
            if (b.visible() == true) {
                Rectangle r2 = b.checkBounds();
                if (r1.intersects(r2)) {
                    arto.osui();
                    b.osuma();
                }

            } else {
                arto.eiOsu();
            }
        }
    }

    /**
     * Metodi tarkistaa osuuko Arto kaljaan
     */
    public void checkKaljaCollision() {
        Rectangle r1 = arto.checkBounds();
        for (int i = 0; i < kaljat.size(); i++) {
            Kalja a = (Kalja) kaljat.get(i);
            if (a.visible() == true) {
                Rectangle r2 = a.checkBounds();
                if (r1.intersects(r2)) {
                    arto.gainLife();
                    a.osuma();
                    score.setScore(a.getPongo());
                    elkut = arto.getLife();
                }
            }

        }
        /*
        Rectangle r2 = kalja1.checkBounds();
        if (r1.intersects(r2) && kalja1.visible() == true) {
        arto.gainLife();
        kalja1.osuma();
        score.setScore(kalja1.getPongo());
        elkut = arto.getLife();
        }*/
    }

    /**
     * Luokka, joka kuuntelee näppäimistöä
     */
    private class ActionListener extends KeyAdapter {

        /**
         * Metodi, joka huomaa, kun näppäintä ei enää paineta
         *
         * @param event
         */
        public void keyReleased(KeyEvent event) {
            arto.keyReleased(event);

        }

        /**
         * Metodi, joka huomaa kun näppäintä painetaan.
         *
         * @param event
         */
        public void keyPressed(KeyEvent event) {
            arto.keyPressed(event);
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_E) {
                System.exit(0);
            }

            if (key == KeyEvent.VK_S) {
                voitto = false;
                fail = false;
                ohje = false;
                tarina = false;
                arto = new Arto();
                score = new Score();
                boss = new Boss(2000, 367);
                vihu = new Vihu(300, 425, 2);
                vihu2 = new Vihu(800, 425, 3);
                vihu3 = new Vihu(1000, 425, 5);
                vihu4 = new Vihu(1250, 425, 3);
                vihu5 = new Vihu(1800, 425, 1);
                vihu6 = new Vihu(2000, 425, 3);
                piirra=true;
            }
            if (key == KeyEvent.VK_O) {
                piirra = false;
                tarina = false;
                ohje = true;
            }
            if (key == KeyEvent.VK_T) {
                ohje = false;
                piirra = false;
                tarina = true;
            }

            if (key == KeyEvent.VK_F) {
                fail = true;
                piirra = false;
                tarina = false;
                ohje = false;
                voitto = false;
            }
            if (key == KeyEvent.VK_V) {

                fail = false;
                piirra = false;
                tarina = false;
                ohje = false;
                voitto = true;
            }
        }
    }
}
