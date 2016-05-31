/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;


/**
 *
 * @author Michał
 */
public class Menu extends JFrame{
    public int klawiszGora = KeyEvent.VK_W;
    public int klawiszDol = KeyEvent.VK_S;
    
    private Timer timer;
   
    class Krok extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
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
    
    public class Przyciski extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int klawisz = e.getKeyCode();
            
            if(klawisz == klawiszGora) {
                
            }
            if(klawisz == klawiszDol) {
                
            }
        }
        
        public void keyRelased(KeyEvent e) {
            
        }
        
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    Menu() {
        super("Pacman 3D - Woźniak Szymczak");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setVisible(true);
        createBufferStrategy(2);
        
        addKeyListener(new Menu.Przyciski());
    }
}
