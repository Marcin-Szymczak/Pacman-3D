package pacman;

import java.awt.BasicStroke;
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
 * @author Michał i Marcin
 */
public class Pacman{
    public Game game;
    public Menu menu;
    public static State state = State.Menu;
    
    public enum State {
        Game, Menu
    }

    public void getState(State state) {
        if(state == State.Game) game = new Game();
        else menu = new Menu();
    }
    
    Pacman() { 
        getState(state);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
        pacman = new Pacman();
        pacman.game.repaint();
        
        pacman.game.plansza.zaladuj( "data/001.p3dm" );
    }
    
}
