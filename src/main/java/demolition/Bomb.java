/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

import java.util.ArrayList;

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
    public boolean isExploded;
    public boolean canExplode;    
    public ArrayList<int[][]> explodeRange;
    
    
    
    public Bomb(){
             
        bombTime = 2.0f;
        bombRange = 2;
        bombRangeWithWall = 1;
        
        explosionTime = 0.5f;
        
        bombX = -1;
        bombY = -1;
        isPlaced = false;
        isExploded = true;
        placeTime = 0;
        canExplode = false;
        explodeRange = new ArrayList<>();
        
        
        
    }
    
    
    public char[][] explode(int x,int y,char[][] map){
        
        explodeRange.clear();
        
        map[y][x] = 'C'; // y -> y-axis , x -> x-axis
        
        for(int i=0;i<4;i++){
            
            
                
                switch(i){
                    
                    case 0:
                        //down
                        
                        
                        explosionRange(x,x,y+1,y+2,map,'J','V');
                        
                        // J -> end bottom explosion char
                        // V -> before end vertical explosion end char
                        
                        
                        break;
                    case 1:
                        //up
                        
                        
                        explosionRange(x,x,y-1,y-2,map,'K','V');
                        
                        // K -> end top explosion char
                        
                        break;
                    case 2:
                        //right
                        
                        
                        
                        explosionRange(x+1,x+2,y,y,map,'M','H');
                        
                        // M -> end right explosion char
                        // H -> before end horizontal explosion char                       
                        
                        break;
                    case 3:
                        //left
                        
                        
                        
                        explosionRange(x-1,x-2,y,y,map,'L','H');
                        
                        // L -> end left explosion char
                        
                        break;
                    
                }
                
                
                
                
            
            
            
            
        }
        
        
        String log = "";
        
        for(int it=0;it<explodeRange.size();it++){
            
            log += "["+explodeRange.get(it)[0][0] + "," + explodeRange.get(it)[0][1] + "]" +
                    "["+explodeRange.get(it)[1][0] + "," + explodeRange.get(it)[1][1] + "]" + "\n";
            
            
        }
        
//        String mapStr = "Map:\n";
//        
//        for(int i=0;i<13;i++){
//            
//            for(int j=0;j<15;j++){
//                
//                mapStr += map[i][j];
//                
//            }
//                mapStr +="\n";
//            
//        }
//        
//        System.out.println("Explode Range: " + log);
//        System.out.println(mapStr);
//        
        
        return map;
    }
    
    
    private void explosionRange(int x1,int x2,int y1,int y2,char[][] map,char endChar,char beforeEndChar){
        
                int[][] rangeForDirection = new int[2][2];
        
                if(y1 > 0 && y1 < 12 && x1 > 0 && x1 < 14){
                            
                    
                    
                            if(y2 > 0 && y2 < 12 && x2 > 0 && x2 < 14){
                                
                                rangeForDirection[1][0] = y2;
                                rangeForDirection[1][1] = x2;
                                
                            }else{
                                
                                rangeForDirection[1][0] = -1;
                                rangeForDirection[1][1] = -1;
                                
                                y2 = -1;
                                x2 = -1;
                                
                            }
                           
                            
                            rangeForDirection[0][0] = y1;
                            rangeForDirection[0][1] = x1;
                            
                            
                            
                            
                            if(map[y1][x1] != 'W'){
                                
                                
                                if(map[y1][x1] != 'B'){
                                    
                                    
                                    
                                    if((y2 == -1 && x2 == -1) || map[y2][x2] == 'W'){
                                        
                                        map[y1][x1] = endChar;
                                        
                                        if(map[y1][x1] == 'P'){
                                            
//                                            map[1][1] = 'P';
//                                            App.lives--;
                                            App.canResetLevel = true;
                                  
                                        }
                                        
                                    }else{
                                        
                                        if(map[y1][x1] == 'P' || map[y2][x2] == 'P'){
                                            
//                                            map[1][1] = 'P';
//                                            App.lives--;
                                            App.canResetLevel = true;
                                  
                                        }
                                        
                                        map[y1][x1] = beforeEndChar;
                                        map[y2][x2] = endChar;
                                        
                                    }
                                    
                                    
                                    
                                    
                                    
                                    
                                }else{
                                    
                                    map[y1][x1] = endChar;
                                    
                                    rangeForDirection[1][0] = -1;
                                    rangeForDirection[1][1] = -1;
                                    
                                    
                                }
                                
                                
                                
                            }else{
                                
                                rangeForDirection[0][0] = -1;
                                rangeForDirection[0][1] = -1;
                                
                                rangeForDirection[1][0] = -1;
                                rangeForDirection[1][1] = -1;
                                
                            }
                            
                            
                }else{
                    
                      rangeForDirection[0][0] = -1;
                      rangeForDirection[0][1] = -1;
                      
                      rangeForDirection[1][0] = -1;
                      rangeForDirection[1][1] = -1;
                    
                }
                
                
                explodeRange.add(rangeForDirection);
        
            }
    
    
}
