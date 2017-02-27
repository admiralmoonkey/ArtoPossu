
package Peli;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Saeru
 */
public class Puu {
    private int x;
    private int y;
    private Random generator=new Random();
    private int luku;
    private Image boksi;
    private ImageIcon kuva=new ImageIcon(this.getClass().getResource("images/este.png"));
    private ImageIcon kuva2=new ImageIcon(this.getClass().getResource("images/este2.png"));

    
    
    public Puu(){

    }
    public Puu(int startX, int startY){  
                randomTree();
        x=startX;
        y=startY;
    }
    
    
        public void move(int dx, int left) {
        if (dx > 0) {
            if (left == 250) {
                x = x - dx;
            }
        }
    }
        public void randomTree(){
            luku=generator.nextInt(3);
            if(luku==2){
                boksi=kuva2.getImage();
            }
            else
                boksi=kuva.getImage();
        }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Image getImage(){
        return boksi;
    }
}
