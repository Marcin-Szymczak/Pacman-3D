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
    
    public void update(){
        
    }
    
    public void draw(Graphics2D g) {
        BufferStrategy bs = pacman.Pacman.frame.getBufferStrategy();
        //if( null == bs ) return;
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        if( null == g2d ) return;
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, 800, 600);
        
        g2d.setColor( Color.WHITE );
        g2d.setFont(new Font("Arial",Font.BOLD,50));
        g2d.drawString("PACMAN 3D", 270, 100);
        
        g2d.dispose();
        bs.show();
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
