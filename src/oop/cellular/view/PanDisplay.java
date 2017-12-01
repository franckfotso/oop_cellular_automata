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
import java.util.HashMap;
import javax.swing.JPanel;
import oop.cellular.controller.AbstractController;
import oop.cellular.controller.GameController;
import oop.cellular.model.Display;
import oop.cellular.model.Game;
import oop.cellular.model.Setting;

/**
 *
 * @author romuald.fotso
 */
public class PanDisplay extends JPanel{
    
    private CellularGUI cellularGUI;
    private int bin_W;
    private int bin_H;

    public PanDisplay() {
        super();
    }
    
    public PanDisplay(CellularGUI cellularGUI) {
        super();
        this.cellularGUI = cellularGUI;
    }

    public CellularGUI getCellularGUI() {
        return cellularGUI;
    }

    public void setCellularGUI(CellularGUI cellularGUI) {
        this.cellularGUI = cellularGUI;
    }    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);       
        System.out.println("PanDisplay > drawing with paintComponent");        
             
        GameController game_controller = (GameController) 
                this.cellularGUI.getControllers().get("game_controller");
        Game game = (Game) game_controller.getModel();
        Display display = game.getDisplay();
        Setting setting = game.getSetting();
        
        System.out.println("display.getWidth(): "+display.getWidth());
        System.out.println("display.getHeight(): "+display.getHeight());
        System.out.println("setting.getSize(): "+setting.getSize());
        
        int d_width = this.getWidth();
        int d_height = this.getHeight();
        this.bin_W = setting.getSize();
        this.bin_H = setting.getSize();
        
        g.setColor(Color.GRAY);
        
        boolean [][] bins = display.getBins();
        int cols = (int)((float)this.getWidth()/(float)setting.getSize());
        int rows = (int)((float)this.getHeight()/(float)setting.getSize());
        
        // draw all bins
        for (int x=0; x < d_width; x+=this.bin_W){
            for (int y=0; y < d_height; y+=this.bin_H){
                g.drawRect(x, y, bin_W, bin_H);
            }
        }
        
        g.setColor(Color.WHITE);
        // draw live bins
        for (int x=0; x < cols; x++){
            for (int y=0; y < rows; y++){
                boolean state = bins[y][x];
                
                int bin_X = x * this.bin_W;
                int bin_Y = y * this.bin_H;
                //System.out.println("bin_X: "+bin_X);
                //System.out.println("bin_Y: "+bin_Y);
                
                if (state)
                    g.fillRect(bin_X, bin_Y, bin_W, bin_H);
            }
        }
        
    }
    
    public void initDisplay(){
        
    }
}
