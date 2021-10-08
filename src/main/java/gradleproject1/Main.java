/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gradleproject1;



import processing.core.*;

/**
 * @author Ahtesham Sarwar
 */
public class Main extends PApplet{

    
    PImage img;
    /**
     * @param args the command line arguments
     * we extend this class from PApplet 
     * so that we can use powerful animations of processing
     * library
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        PApplet.main(Main.class);
        
        
        
    }
    
    
    public void settings(){
        //set the size of window here
        this.size(480,480);
        
    }
    
    public void setup(){
        
        //here we need to instantiate every object which we draw on the window
        
        img = loadImage("C:\\Users\\Ahtesham Sarwar\\Documents\\NetBeansProjects\\gradleproject1\\src\\main\\res\\solid.png");
        
    }
    
    public void draw(){
        
        
        //this method is used to draw content on the window
        //this method is called 60 times per minute
        
        background(255,140,0);
    
//        background(img);

            for(int i=0;i<15;i++){
            
                image(img,i*32,64);
                if(i>2)
                image(img,0,i*32);
                
                
            }
            
            
//        rect(200,250,100,100);
        
//        fill(random(255),random(255),random(255));
//        ellipse(mouseX,mouseY,80,80);
//        this.ellipse(200,250,100,100);
        
    }
    
    
}
