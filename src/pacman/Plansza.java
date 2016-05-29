/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;

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
    static final int HEIGHT = 36;
    
    Pole[][] mapa;
    
    public Plansza() {
        mapa = new Pole[HEIGHT][WIDTH];
    }
    
    public void ustaw(int x, int y, Pole pole) {
        if(wSrodku(x,y)) mapa[y][x] = pole;
        else System.err.println("Nie można ustawić pola o współrzędnych ["+x+"] ["+y+"]");
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
    }
    
    public void rysuj(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        int wlk = 12;
        g2d.fillRect(0, 0, WIDTH*wlk, HEIGHT*wlk);
        for(int r = 0; r < HEIGHT; r++) {
            for(int c = 0; c < WIDTH; c++) {
                if(null != mapa[r][c]) switch (mapa[r][c]) {
                    case Sciana:
                        g2d.setColor(Color.blue);
                        g2d.fillRect(c*wlk, r*wlk, wlk, wlk);
                        break;
                    case Punkt:
                        g2d.setColor(Color.yellow);
                        g2d.fillRect(c*wlk, r*wlk, wlk, wlk);
                        break;
                    case Bonus:
                        g2d.setColor(Color.red);
                        g2d.fillRect(c*wlk, r*wlk, wlk, wlk);
                        break;
                    default:
                        break;
                }
            }
        }
    
    }
} 
