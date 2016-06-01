package pacman;

import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame{
    public static Panel frame;
    
    Pacman() { 
        frame = new Panel();
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
    }
}
