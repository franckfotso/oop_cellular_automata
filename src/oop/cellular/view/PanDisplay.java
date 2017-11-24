/*
 * Copyright (C) 2017 romuald.fotso
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package oop.cellular.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author romuald.fotso
 */
public class PanDisplay extends JPanel{
    
    private int bin_X = 0;
    private int bin_Y = 0;
    private int bin_W = 1;
    private int bin_H = 1;

    public PanDisplay() {
        super();
    }
    
    public PanDisplay(int bin_W, int bin_H) {
        super();
        this.bin_W = bin_W;
        this.bin_H = bin_H;
    }   
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.WHITE);
        g.fillOval(bin_X, bin_Y, bin_W, bin_H);
    }
}
