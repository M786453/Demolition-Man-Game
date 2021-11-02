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

public class Animation{
    
    
    
    private float timePerFrame;        
    public int currentFrameNo;
    private PImage currentFrame;
    public boolean canChangeFrame;
    private PApplet parent;
    
    
    public Animation(PApplet parent,float timePerFrame){
        
        
        
        this.timePerFrame = timePerFrame; 
        currentFrameNo = 0;
        canChangeFrame = true;
        this.parent = parent;
        
        
    }
    
    
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
    
    
    public void positionBombAnimation(int i, int j,ArrayList<PImage> bombFramesList){
        
        if(Bomb.bombX == j && Bomb.bombY == i)
                                    parent.image(play(bombFramesList),Bomb.bombX*32,(Bomb.bombY*32) + Map.OFFSET);
        
        
    }
    
    
    
    
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
