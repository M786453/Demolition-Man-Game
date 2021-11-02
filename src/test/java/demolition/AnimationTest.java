/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Ahtesham Sarwar
 */
public class AnimationTest extends PApplet {
    
    boolean isFrameChange = false;
    
    public AnimationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of play method, of class Animation.
     */
    @Test
    public void testPlay() throws InterruptedException {
        
        
        Animation animation = new Animation(this,2f);
        
        ArrayList<PImage> sampleFramesList = new ArrayList<>();
        
        PImage frame1 = new PImage();
        PImage frame2 = new PImage();
        
        
        sampleFramesList.add(frame1);

        sampleFramesList.add(frame2);

        PImage p1 = animation.play(sampleFramesList);
        
        sleep(4000);
        
        PImage p2 = animation.play(sampleFramesList);
        
        sleep(4000);
        
        //check if the frames are changed or not
        assertTrue(p1 != p2);
        

        
        
        
    }
    
}
