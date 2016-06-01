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

/**
 *
 * @author Michał
 */
public class Menu extends State{
    private int klawiszGora = KeyEvent.VK_W;
    private int klawiszDol = KeyEvent.VK_S;
    private int klawiszEnter = KeyEvent.VK_ENTER;
    private menuOptions pos;
    
    public enum menuOptions {
        Start, Wyniki, Koniec
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics2D g2d) {
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, 800, 600);
        
        g2d.setColor( Color.WHITE );
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("PACMAN 3D", 100, 30);
    }
    
    public void keyPressed(int key) {
        int klawisz = key;
            
        if(klawisz == klawiszGora) {
            
        }
        if(klawisz == klawiszDol) {
                
        }
        if(klawisz == klawiszEnter){
            switch(pos) {
                case Start:
                    state.setState(pacman.StateManager.GAMESTATE);
                    break;
                default:
                    break;
            }
            
        }
    }
        
    public void keyReleased(int key) {
            
    }
    
    Menu(StateManager state) {
        this.state = state;
        
        pos = menuOptions.Start;
    }
}
