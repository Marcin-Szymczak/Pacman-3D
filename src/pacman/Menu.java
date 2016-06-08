/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michał
 */
public class Menu extends State{
    private int klawiszGora = KeyEvent.VK_W;
    private int klawiszDol = KeyEvent.VK_S;
    private int klawiszEnter = KeyEvent.VK_ENTER;
    private int klawiszSpace = KeyEvent.VK_SPACE;
    private int pos;
    private menuOptions[] opt;
    
    Font font;
    FontRenderContext context;
    
    public enum menuOptions {
        Start, Opcje, Wyniki, Wyjdz
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics2D g2d) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, Pacman.WIDTH, Pacman.HEIGHT);
        
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/crackman.front.ttf")).deriveFont((float)(Pacman.HEIGHT*0.22));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/crackman.front.ttf")));
            g2d.setFont(customFont);
        } catch (IOException|FontFormatException e) {}
        
        g2d.setColor(Color.YELLOW);
        font = g2d.getFont();
        context = g2d.getFontRenderContext();
        g2d.drawString("Pacman 3d", (Pacman.WIDTH - (int)font.getStringBounds("pacman 3d", context).getWidth())/2, (int)(Pacman.HEIGHT*0.3));

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/joystix.ttf")).deriveFont((float)(Pacman.HEIGHT*0.05));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/joystix.ttf")));
            g2d.setFont(customFont);
        } catch (IOException|FontFormatException e) {}
        
        g2d.setColor(Color.WHITE);
        font = g2d.getFont();
        context = g2d.getFontRenderContext();
        g2d.drawString("START", (Pacman.WIDTH - (int)font.getStringBounds("START", context).getWidth())/2, Pacman.HEIGHT/2);
        g2d.drawString("OPCJE", (Pacman.WIDTH - (int)font.getStringBounds("OPCJE", context).getWidth())/2, Pacman.HEIGHT/2+60);
        g2d.drawString("WYNIKI", (Pacman.WIDTH - (int)font.getStringBounds("WYNIKI", context).getWidth())/2, Pacman.HEIGHT/2+120);
        g2d.drawString("WYJDŹ", (Pacman.WIDTH - (int)font.getStringBounds("WYJDŹ", context).getWidth())/2, Pacman.HEIGHT/2+180);

        g2d.drawString(">", (Pacman.WIDTH - (int)font.getStringBounds(opt[pos].toString().toUpperCase(), context).getWidth())/2 - (int)font.getStringBounds(">", context).getWidth(), Pacman.HEIGHT/2 + pos*60);
    }
    
    public void keyPressed(int key) {
        int klawisz = key;
            
        if(klawisz == klawiszGora) {
            pos = ((pos - 1)%4 + 4)%4;
        }
        if(klawisz == klawiszDol) {
            pos = (pos + 1)%4;
        }
        if(klawisz == klawiszEnter){
            switch(opt[pos]) {
                case Start:
                    pacman.StateManager.states.add(new Game(state));
                    state.setState(pacman.StateManager.GAMESTATE);
                    break;
                case Opcje:
                    break;
                case Wyniki:
                    break;
                case Wyjdz:
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
        
        opt = new menuOptions[4];
        opt[0] = menuOptions.Start;
        opt[1] = menuOptions.Opcje;
        opt[2] = menuOptions.Wyniki;
        opt[3] = menuOptions.Wyjdz;
    }
}
