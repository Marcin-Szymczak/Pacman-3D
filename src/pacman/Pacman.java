package pacman;

import javax.swing.JFrame;
import static pacman.Panel.HEIGHT;
import static pacman.Panel.SCALE;
import static pacman.Panel.WIDTH;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame{
    public static JFrame frame;
    
    Pacman() { 
        frame = new JFrame();
        frame.setTitle("Pacman 3D - Woźniak Szymczak");
        frame.setBounds(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        frame.setContentPane(new Panel());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
    }
    
}
