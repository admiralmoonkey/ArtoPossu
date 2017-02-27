package Peli;

/**
 * Luokka pitää kirjaa pisteistä
 * @author Saeru
 */
public class Score {

    /**
     * pisteet
     */
    private int score;
    /**
     * String pisteiden ruudulle tulostusta varten
     */
    private String scoreText;

    /**
     * Konstruktori. Alussa pisteitä on 0.
     */
    public Score() {
        score = 0;
    }

    /**
     * Pisteiden setteri. Pisteet muuttuvat sen mukaan, mitä tehdään. Juoko Arto kaljaa vai tappaako se vihulaisen. 
     * @param score
     */
    public void setScore(int a) {
        score = score + a;
    }

    /**
     * Pisteiden getteri
     * @return palauttaa pisteet score
     */
    public int getScore() {
        return score;
    }

    /**
     * Metodi tekee score integeristä stringin ruudulle tulostamista varten.
     * @return palauttaa Stringin scoreText
     */
    public String toString() {
        scoreText = Integer.toString(getScore());
        return scoreText;
    }
}
