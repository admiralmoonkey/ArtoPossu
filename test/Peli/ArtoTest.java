package Peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/*
 *
 * @author Saeru
 */
public class ArtoTest {
    Arto arto;
    int elama;
    public ArtoTest() {
        
    }   

    @BeforeClass
    public static void setUpClass() throws Exception {
     
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        arto=new Arto();
        
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    //etiäpäin
     @Test
     public void moveForwards() {
      
  
        

     
     }
        //taaksepäin
     @Test
     public void moveBackwards() {
      

     }
     
     //elämä alussa
     @Test
     public void getLife(){
         assertEquals(3,arto.getLife());
     }
     
     
     //damagetestaus yhdellä
     @Test
     public void damage(){
         arto.damage();
         assertEquals(2, arto.getLife());
          
     }
     
    //damagetestaus kaikki
     
     @Test
     public void damageFull(){
         arto.damage();
         arto.damage();
         arto.damage();
         assertEquals(0, arto.getLife());
         arto.damage();
         assertEquals(0, arto.getLife());
          
     }
     
     //gain life -testaus täysillä elämillä
     @Test
     public void gainLifeFull(){
         arto.gainLife();
         assertEquals(3, arto.getLife());
                   
     }
     
    //gain life -testaus damagella
     @Test
     public void gainLifeDamaged(){
         arto.damage();
         arto.gainLife();
         assertEquals(3, arto.getLife());                
     }
     
    
     // onko Arto elossa
     @Test
     public void isAlive(){
         assertEquals(true, arto.isAlive());      
     }
    // onko Arto kuollut?
     
     
     @Test
     public void isDead(){
         arto.damage();
         arto.damage();
         arto.damage();
         assertEquals(false, arto.isAlive());      
     }
     
}
