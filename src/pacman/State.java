/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Graphics2D;

/**
 *
 * @author Michał
 */
public abstract class State {
    
    protected StateManager state;
    
    public abstract void update();
    public abstract void draw(Graphics2D g2d);
    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
}
