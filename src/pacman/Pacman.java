package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame {
    
    public Plansza plansza;
    public Path path;
    final private Timer timer;
    
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
        //if( null == g2d ) return;
        g2d.translate(10, 30);
        
        plansza.rysuj(g2d);
        path.rysuj(g2d);
        
        g2d.dispose();
        bs.show();
    }
    
    Pacman() {
        super("Pacman 3D - Woźniak Szymczak");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        createBufferStrategy(2);
        
        plansza = new Plansza();
        path = new Path();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 20, 20);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
        pacman.repaint();
        
        pacman.plansza.zaladuj( "001.p3dm" );
        pacman.path.setData( pacman.plansza.wezly );
        
        pacman.path.find( 1, 1, 1, 13);
    }
    
}
