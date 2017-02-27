package Peli;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saeru
 */
public class VihuTest {
    Vihu vihu;
    
    public VihuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        vihu=new Vihu(500,200,2);
    }
    
    @After
    public void tearDown() {
    }
    
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
         assertEquals(2,vihu.getLife());
     }
     
     
     //damagetestaus yhdellä
     @Test
     public void damage(){
         vihu.damage();
         assertEquals(1, vihu.getLife());
          
     }
     
    //damagetestaus kaikki
     
     @Test
     public void damageFull(){
         vihu.damage();
         vihu.damage();
         assertEquals(0, vihu.getLife());
         vihu.damage();
         assertEquals(0, vihu.getLife());
          
     }
     

    
     // onko Vihu elossa
     @Test
     public void isAlive(){
         assertEquals(true, vihu.isAlive());      
     }
    // onko vihu kuollut?
     
     
     @Test
     public void isDead(){
         vihu.damage();
         vihu.damage();
         assertEquals(false, vihu.isAlive());      
     }
     
}


