/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

/**
 * Represent a Level of Game
 * @author Ahtesham Sarwar
 */
public class Level {
    
    /**
     * Represent the path of map text file
     */
    public String path;
    
    /**
     * Represent the total time of level
     */
    public int time;
    
    /**
     * Represent the map which will modify during the game play
     */
    public char[][] mapArray;
    
    /**
     * Represents the original map
     */
    public char[][] originalMap;
    
    
    public Level(String path,int time){
        
        this.path = path;
        this.time = time;
        mapArray = null;
        
        
    }
    
    
    
    /**
     * Used to set the map of level
     * @param map Represent the map (a 2-D Char Array)
     */
    public void setMap(char[][] map){
        
        mapArray = map;
        
        
        originalMap = new char[Map.ROWS][Map.COLUMNS];
        
        for(int i=0;i<Map.ROWS;i++){
            
            for(int j=0;j<Map.COLUMNS;j++){
                
            
                originalMap[i][j] =  mapArray[i][j];
                       
                
            }
            
        }
        
        
        
    }
    
   
    /**
     * Used to reset the map of level
     */
    public void resetLevel(){
        
        char[][] resetedMap = new char[Map.ROWS][Map.COLUMNS];
        
        for(int i=0;i<Map.ROWS;i++){
            
            for(int j=0;j<Map.COLUMNS;j++){
                
            
                resetedMap[i][j] =  originalMap[i][j];
                       
                
            }
            
        }
        
        mapArray = resetedMap;
        
    }
    
}
