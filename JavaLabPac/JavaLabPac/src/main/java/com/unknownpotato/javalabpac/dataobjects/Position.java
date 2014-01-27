/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unknownpotato.javalabpac.dataobjects;

/**
 *
 * @author Victor Bankowski
 */
public class Position {
    
    private int x;
    private int y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * shifts the position by adding the parameters to current coordinates
     * 
     * @param x
     * @param y 
     */
    
    public void changePosition(int x, int y){
        this.x += x;
        this.y += y;
    }
    
    /**
     * switches the current coordinates with the parameters.
     * can probably be used in teleportation and level resets.
     * 
     * @param x
     * @param y 
     */
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}
