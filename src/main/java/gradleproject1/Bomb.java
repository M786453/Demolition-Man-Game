/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gradleproject1;

/**
 *
 * @author Ahtesham Sarwar
 */
public class Bomb {
    
    public float bombTime;
    public int bombRange;
    public int bombRangeWithWall;
    public float explosionTime;
    
    public int bombX;
    public int bombY;
    public boolean isPlaced;
    public long placeTime;
    
    
    
    public Bomb(){
             
        bombTime = 2.0f;
        bombRange = 2;
        bombRangeWithWall = 1;
        
        explosionTime = 0.5f;
        
        bombX = -1;
        bombY = -1;
        isPlaced = false;
        placeTime = 0;
        
        
    }
    
    
    public void explode(){
        
        
        
    }
    
    
}
