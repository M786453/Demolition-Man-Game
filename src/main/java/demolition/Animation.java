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
    private int currentFrameNo;
    private PImage currentFrame;
    private boolean canChangeFrame;
    
    
    public Animation(float timePerFrame){
        
        
        
        this.timePerFrame = timePerFrame; 
        currentFrameNo = 0;
        canChangeFrame = true;
        
        
        
    }
    
    
    public PImage play(ArrayList<PImage> framesList){
        
                
        
                if(canChangeFrame){
            
        
                    //this boolean is used to avoid running the bellow code every time play function called by draw function
                    //because we want to change the frame of player after specific amount of time
                    
                    canChangeFrame = false;
                    
                    
                    
                    System.out.println("CurrentFrameNo: " + currentFrameNo);
        
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
    
    
    
}
