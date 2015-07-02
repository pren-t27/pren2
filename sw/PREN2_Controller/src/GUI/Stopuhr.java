/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Slade
 */
public class Stopuhr {
    long startZeit;
    double stoppZeit;
   
    public void start(){
        startZeit = System.currentTimeMillis();
    }
    
    public void stop(){
        stoppZeit = System.currentTimeMillis()-startZeit;
    }
   
    public String getTime(){
        
        long secs = (long) (stoppZeit / 1000);
        
        long restsecs = (long) (stoppZeit % 1000);
        String returnString = secs +","+restsecs;
        
        return returnString;  
    }
 
}
