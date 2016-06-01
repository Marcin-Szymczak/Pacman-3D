/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Michał
 */
public class Menu extends State{
    private int klawiszGora = KeyEvent.VK_W;
    private int klawiszDol = KeyEvent.VK_S;
    private int klawiszEnter = KeyEvent.VK_ENTER;
    private int pos;
    private menuOptions[] opt;
    
    public enum menuOptions {
        Start, Wyniki, Koniec
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics2D g2d) {
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, 800, 600);
        
        g2d.setColor( Color.WHITE );
        g2d.setFont(new Font("Arial",Font.BOLD,50));
        g2d.drawString("PACMAN 3D", 250, 100);
        
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("START", 350, 200);
        g2d.drawString("WYNIKI", 345, 240);
        g2d.drawString("ZAKOŃCZ", 340, 280);
        
        g2d.fillOval(340 - pos*5, 190 + pos*40, 5, 5);
    }
    
    public void keyPressed(int key) {
        int klawisz = key;
            
        if(klawisz == klawiszGora) {
            pos = ((pos - 1)%3 + 3)%3;
        }
        if(klawisz == klawiszDol) {
            pos = (pos + 1)%3;
        }
        if(klawisz == klawiszEnter){
            switch(opt[pos]) {
                case Start:
                    state.setState(pacman.StateManager.GAMESTATE);
                    break;
                case Wyniki:
                    break;
                case Koniec:
                    System.exit(0);
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
        
        pos = 0;
        
        opt = new menuOptions[3];
        opt[0] = menuOptions.Start;
        opt[1] = menuOptions.Wyniki;
        opt[2] = menuOptions.Koniec;
    }
}
