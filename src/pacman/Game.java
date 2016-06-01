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
public class Game extends State{
    
    public Plansza plansza;
    public Player player;
    public Ghost ghost;
    public int klawiszGora = KeyEvent.VK_W;
    public int klawiszDol = KeyEvent.VK_S;
    public int klawiszLewo = KeyEvent.VK_A;
    public int klawiszPrawo = KeyEvent.VK_D;
   
    public void init() {
        
    }
    
    public void update() {
            player.ruch();
            ghost.ruch();
    }
    
    public void draw(Graphics2D g) {
        BufferStrategy bs = pacman.Pacman.frame.getBufferStrategy();
        //if( null == bs ) return;
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        //if( null == g2d ) return;
        g2d.setColor( Color.DARK_GRAY );
        g2d.fillRect(0, 0, 800, 600);
        g2d.translate(10, 30);
        
        plansza.rysuj(g2d);
        player.rysuj(g2d);
        ghost.rysuj(g2d);
        
        g2d.setColor( Color.BLACK );
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("X: " + player.getTX(), 500, 20);
        g2d.drawString("Y: "+ player.getTY(), 500, 45);
        
        g2d.dispose();
        bs.show();
    }
    
    public void keyPressed(int key) {
        int klawisz = key;
            
        if(klawisz == klawiszGora) {
            player.setDirection( Player.Direction.Up );
        }
        if(klawisz == klawiszDol) {
            player.setDirection( Player.Direction.Down );
        }
        if(klawisz == klawiszLewo) {
            player.setDirection( Player.Direction.Left );
        }
        if(klawisz == klawiszPrawo) {
            player.setDirection( Player.Direction.Right );
        }
    }
    
    public void keyReleased(int key){
        
    }
    
    Game(StateManager state) {
        this.state = state;
        
        plansza = new Plansza();
        player = new Player(plansza, 13.0, 17.0 );
        ghost = new Ghost(plansza, player, 13.0, 15.0 );
    }
    
}
