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
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Michał
 */
public class Panel extends JFrame {
    
    //Dimension
//    public static final int WIDTH = 640;
//    public static final int HEIGHT = 360;
//    public static int SCALE = 2;
    
    //Main loop
    private Timer timer;
    
    //State Manager
    public StateManager state;
    
    class Krok extends TimerTask {
        @Override
        public void run() {
            state.update();
            repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        //if( null == bs ) return;
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        //if( null == g2d ) return;
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, 800, 600);
        g2d.translate(10, 30);
        
        state.draw(g2d);
        
        g2d.dispose();
        bs.show();
    }
    
    public class Przyciski extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            state.keyPressed(e.getKeyCode());
        }
        
        public void keyRelased(KeyEvent e) {
            
        }
        
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    public Panel() {
        setTitle("Pacman 3D - Woźniak Szymczak");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        createBufferStrategy(2);
        setFocusable(true);
        requestFocus();
        
        addKeyListener(new Przyciski());
        
        state = new StateManager();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 100);
    }
}
