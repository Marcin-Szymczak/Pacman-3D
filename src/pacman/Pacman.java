package pacman;

import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame{
    public static JFrame frame;
    
    Pacman() { 
        frame = new JFrame();
        frame.setTitle("Pacman 3D - Woźniak Szymczak");
        //frame.setBounds(50, 50, pacman.Panel.WIDTH*pacman.Panel.SCALE, pacman.Panel.HEIGHT*pacman.Panel.SCALE);
        frame.setBounds(50, 50, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        frame.setContentPane(new Panel());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
    }
}
