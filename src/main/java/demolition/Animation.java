/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

/**
 *
 * @author Ahtesham Sarwar
 */

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import processing.core.*;

/**
 * Used to Animate Objects in Game
 * @author Ahtesham Sarwar
 */

public class Animation{
    
    
    
    private float timePerFrame; 
    /**
     * Represent the Current Frame Number of Animation
     */
    public int currentFrameNo;
    
    private PImage currentFrame; 
    /**
     * Represent whether program can change frame of Animation or not
     */
    
    public boolean canChangeFrame;
    private PApplet parent;
    
    /**
     * Constructor of Animation Class
     * @param parent Represents the Reference to PApplet of Main Class 
     * @param timePerFrame Represents the time for one Frame in Animation Cycle
     */
    public Animation(PApplet parent,float timePerFrame){
        
        
        
        this.timePerFrame = timePerFrame; 
        currentFrameNo = 0;
        canChangeFrame = true;
        this.parent = parent;
        
        
    }
    
    
    /**
     * Used to play the Animation of Given FramesList of a Game Object
     * @param framesList Represent the list of frames(images) of an Game Object 
     * @return Return a PImage, which is the current frame in Animation 
     */
    
    public PImage play(ArrayList<PImage> framesList){
        
                
        
                if(canChangeFrame){
            
        
                    //this boolean is used to avoid running the bellow code every time play function called by draw function
                    //because we want to change the frame of player after specific amount of time
                    
                    canChangeFrame = false;
                    
                    
                    
                    
        
                        new Timer().schedule(new TimerTask(){
                              
                                  
                                  @Override
                                  public void run(){
                                      
                                      if(currentFrameNo != framesList.size()-1)
                                          currentFrameNo++;
                                      else
                                          currentFrameNo = 0;
                                     
                                      canChangeFrame = true;
                                      
                                  }
                              
                              }, (int)(timePerFrame*1000));
        
                        
                }
        
                        
        currentFrame = framesList.get(currentFrameNo);        
                        
        return currentFrame;
                        
    }
    
    
    /**
     * Used to Animate the Bomb at given position
     * @param i Represent the Y-axis Position of Bomb
     * @param j Represent the X-axis Position of Bomb
     * @param bombFramesList Represent a list of Frames(images) of Bomb
     */
    public void positionBombAnimation(int i, int j,ArrayList<PImage> bombFramesList){
        
        if(Bomb.bombX == j && Bomb.bombY == i)
                                    parent.image(play(bombFramesList),Bomb.bombX*32,(Bomb.bombY*32) + Map.OFFSET);
        
        
    }
    
    
    
        /**
         * Used to Animate the Game Object (BombGuy, Red Enemy, Yellow Enemy) at given position
         * @param mDirection    Represent the direction of motion of given Game Object
         * @param xPos Represents the X-axis Position of Game Object
         * @param yPos Represents the Y-axis Position of Game Object
         * @param downFramesList Represents the list of frames of Game Object for downward motion
         * @param upFramesList Represents the list of frames of Game Object for upward motion
         * @param rightFramesList Represents the list of frames of Game Object for rightward motion
         * @param leftFramesList Represents the list of frames of Game Object for leftward motion
         */
        public void positionAnimation(int mDirection,int xPos,int yPos,ArrayList<PImage> downFramesList,ArrayList<PImage> upFramesList,
                                ArrayList<PImage> rightFramesList,ArrayList<PImage> leftFramesList){
        
        
        switch (mDirection) {
                          
                          case -1:
                          case 0:
                              
                              parent.image(play(downFramesList),xPos,yPos);
                              
                              break;
                          case 1:
                              
                              parent.image(play(upFramesList),xPos,yPos);
                              
                              break; 
                          case 2:
                              
                              parent.image(play(rightFramesList),xPos,yPos);
                              
                              break;
                          case 3:
                              
                              parent.image(play(leftFramesList),xPos,yPos);
                              
                              break;
                          default:
                              break;
                      
                      }
        
        
    }
    
    
    
}
