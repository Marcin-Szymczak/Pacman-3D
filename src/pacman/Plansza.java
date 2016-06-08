/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Plansza gry
 * 
 * @author Michał i Marcin
 */
public class Plansza {
    
    public enum Pole {
        Puste, Sciana, Punkt, Bonus, Brama
    }

    static final int WIDTH = 28;
    static final int HEIGHT = 31;
    static final int wlk = 12;
    static int fear = 0;
    
    Pole[][] mapa;
    Path.Node[] wezly;
    
    public Plansza() {
        mapa = new Pole[HEIGHT][WIDTH];
    }
    
    public void update() {
        if(fear > 0) fear--;
    }
    
    Path.Node[] zbudujWezly()
    {
        Path.Node[][] mapa2d = new Path.Node[HEIGHT][WIDTH];
        ArrayList<Path.Node> lista = new ArrayList<>();
        
        for( int y=0; y<HEIGHT; y++ )
        {
            for( int x=0; x<WIDTH; x++ )
            {
                mapa2d[y][x] = null;
                if( mapa[y][x] != Pole.Sciana )
                {
                    Path.Node n = new Path.Node();
                    mapa2d[y][x] = n;

                    n.x = x;
                    n.y = y;
                    n.state = Path.State.Blank;
                    n.cost = 0;
                    n.estimated = 0;
                    n.neighbours = new Path.Node[4]; // max 4 sasiadow
                    n.parent = null;
                    
                    lista.add( n );
                }
            }
        }
        
        for( Path.Node n : lista )
        {
            
            int i=0;
            int lx = ( (n.x-1)%WIDTH + WIDTH )%WIDTH;
            int rx = (n.x+1)%WIDTH;
            int uy = ( (n.y-1)%HEIGHT + HEIGHT)%HEIGHT;
            int dy = (n.y+1)%HEIGHT;
            
            if( lx >= 0 && null != mapa2d[n.y][lx] )
                n.neighbours[i++] = mapa2d[n.y][lx];
            
            if( rx < WIDTH && null != mapa2d[n.y][rx])
                n.neighbours[i++] = mapa2d[n.y][rx];
            
            if( uy >= 0 && null != mapa2d[uy][n.x])
                n.neighbours[i++] = mapa2d[uy][n.x];
            
            if( dy < HEIGHT && null != mapa2d[dy][n.x])
                n.neighbours[i++] = mapa2d[dy][n.x];
                
        }
        
        //wezly = lista.toArray( new Path.Node[ lista.size() ] );
        return lista.toArray( new Path.Node[ lista.size() ] );
    }
    
    public void ustaw(int x, int y, Pole pole) {
        if(wSrodku(x,y)) mapa[y][x] = pole;
        else System.err.println("Nie można ustawić pola o współrzędnych ["+x+"] ["+y+"]");
    }
    
    public Pole jakiePole( int x, int y )
    {
        if( wSrodku( x, y ) )
            return mapa[y][x];
        else
            return Pole.Sciana;
    }
    
    public boolean wSrodku(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }
    
    public void zaladuj( String nazwa ) {
        FileReader fr = null;
        String linia;
        try {
            fr = new FileReader(nazwa);
        }
        catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU MAPY!");
            System.exit(1);
        }
        BufferedReader bfr = new BufferedReader(fr);
        
        try {
            int r = 0;
            while((linia = bfr.readLine()) != null) {
                for(int c = 0; c < linia.length(); c++) {
                    switch (linia.charAt(c)) {
                        case '#':
                            mapa[r][c] = Pole.Sciana;
                            break;
                        case '.':
                            mapa[r][c] = Pole.Punkt;
                            break;
                        case ' ':
                            mapa[r][c] = Pole.Puste;
                            break;
                        case '+':
                            mapa[r][c] = Pole.Bonus;
                            break;
                        default:
                            break;
                    }
                }
                r++;
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }
           
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
            System.exit(3);
        }
        
        //zbudujWezly();
    }
    
    public void rysuj(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(Pacman.WIDTH-WIDTH*wlk-5, Pacman.HEIGHT-HEIGHT*wlk-5, WIDTH*wlk+1, HEIGHT*wlk+1);
        for(int r = 0; r < HEIGHT; r++) {
            for(int c = 0; c < WIDTH; c++) {
                if(null != mapa[r][c]) switch (mapa[r][c]) {
                    case Sciana:
                        g2d.setColor(Color.blue);
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawRoundRect(Pacman.WIDTH-WIDTH*wlk-5+c*wlk, Pacman.HEIGHT-HEIGHT*wlk-5+r*wlk, wlk, wlk, 5, 5);
                        break;
                    case Punkt:
                        g2d.setColor(new Color(255,204,102));
                        g2d.fillOval(Pacman.WIDTH-WIDTH*wlk-5+c*wlk+wlk/4, Pacman.HEIGHT-HEIGHT*wlk-5+r*wlk+wlk/4, wlk/4*2, wlk/4*2);
                        break;
                    case Bonus:
                        g2d.setColor(new Color(255,204,102));
                        g2d.fillOval(Pacman.WIDTH-WIDTH*wlk-5+c*wlk+wlk/6, Pacman.HEIGHT-HEIGHT*wlk-5+r*wlk+wlk/6, 2*wlk/3, 2*wlk/3);
                        break;
                    default:
                        break;
                }
            }
        }
    }
} 
