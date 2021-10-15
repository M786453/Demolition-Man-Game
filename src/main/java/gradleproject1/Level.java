/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gradleproject1;

/**
 *
 * @author Ahtesham Sarwar
 */
public class Level {
    
    public String path;
    public int time;
    public char[][] mapArray;
    
    public Level(String path,int time){
        
        this.path = path;
        this.time = time;
        mapArray = null;
        
    }
    
    
    public void setMap(char[][] map){
        
        mapArray = map;
        
    }
    
    public void start(){
        
        
        
    }
    
    
    public void finish(){
        
        
        
    }
    
}
