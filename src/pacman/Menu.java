/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;


/**
 *
 * @author Michał
 */
public class Menu extends State{
    public int klawiszGora = KeyEvent.VK_W;
    public int klawiszDol = KeyEvent.VK_S;
   
    public void init() {
        
    }
    
    public void update(Graphics2D g2d){
        
    }
    
    public void draw(Graphics2D g2d) {
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, pacman.Panel.WIDTH*pacman.Panel.SCALE, pacman.Panel.HEIGHT*pacman.Panel.SCALE);
        
        g2d.setColor( Color.WHITE );
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("PACMAN 3D", 100, 30);
        
        g2d.dispose();
    }
    
    public void keyPressed(int key) {
        int klawisz = key;
            
        if(klawisz == klawiszGora) {

        }
        if(klawisz == klawiszDol) {
                
        }
    }
        
    public void keyReleased(int key) {
            
    }
    
    Menu(StateManager state) {
        this.state = state;
    }
}
