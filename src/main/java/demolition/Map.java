/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

/**
 *
 * @author Ahtesham Sarwar
 */
public class Map {
    
    public static final int ROWS = 13;
    public static final int COLUMNS = 15;
    public static final int OFFSET = 64;
    
    
    
 
    public static boolean validateMap(char[][] map){
        
        
        boolean isBoundaryPresent = true;
        boolean isPlayerPresent = false;
        boolean isGoalTilePresent = false;
        
        int initial_i = -1,initial_j = -1,end_i = -1,end_j = -1;
        
        
        for(int i=0;i<ROWS;i++){
            
            for(int j=0;j<COLUMNS;j++){
                
                
                if(map[i][j] == 'P')
                    isPlayerPresent = true;
                
                if(map[i][j] == 'G')
                    isGoalTilePresent = true;
                
                
                if(map[i][j] == 'W'){
                    
                    if(initial_i == -1 && initial_j == -1){
                        
                        initial_i = i;
                        initial_j = j;
                        
                    }
                    
                    
                   if(i == ROWS-1 || j == COLUMNS-1){
                       
                       if(initial_i != -1 && initial_j != -1){
                        
                        
                        
                        if(i == initial_i){
                           if(end_j == -1)                             
                            end_j = j;
                            
                        }else if(j == initial_j){
                            if(end_i == -1)
                            end_i = i;
                            
                        }
                        
                        
                    }
                       
                   } 
                    
                    
                }else if(map[i][j] == ' '){
                    
                    
                    
                    if(initial_i != -1 && initial_j != -1){
                        
                        
                        
                        if(i == initial_i){
                           if(j>0 && end_j == -1) 
                           if(map[i][j-1] == 'W') 
                            end_j = j-1;
                            
                        }else if(j == initial_j){
                            if(i>0 && end_i == -1)
                            if(map[i-1][j] == 'W')
                            end_i = i-1;
                            
                        }
                        
                        
                    }
                    
                    
                    
                    
                }
                
            }
            
        }
        
        
        //PRINTING COORDINATED OF BOUNDARY EDGES
        
//        System.out.println( initial_i + "," + initial_j);
//        System.out.println( initial_i + "," + end_j);
//        System.out.println( end_i + "," + initial_j);
//        System.out.println( end_i + "," + end_j);
        
        
        
        if(initial_i != end_i && initial_j != end_j){
        
        if(initial_i != -1 && initial_j != -1 && end_i != -1 && end_j != -1){
       
                 for(int i=initial_i;i<=end_i;i++){
                     
                     for(int j=initial_j;j<=end_j;j++){
                         
                         if(i == initial_i || i == end_i || j == initial_j || j == end_j){
                             if(map[i][j] != 'W'){
                                 
                                 isBoundaryPresent = false;
                                 
                                 break;
                                 
                             }
                         
                                 }
                     }
                     
                     
                     if(!isBoundaryPresent)
                         break;
                     
                 }
                 
        }else{
            isBoundaryPresent = false;
        }
        
        }else{
            
            isBoundaryPresent = false;
            
        }
        
        
        return isBoundaryPresent && isPlayerPresent && isGoalTilePresent;
    }
    
    
    
}
