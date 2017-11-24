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
package oop.cellular.controller;

import java.util.ArrayList;
import oop.cellular.model.AbstractModel;
import oop.cellular.model.Display;
import oop.cellular.model.Game;
import oop.cellular.model.Setting;
import oop.cellular.view.CellularGUI;
import oop.cellular.view.PanDisplay;

/**
 *
 * @author romuald.fotso
 */
public class GameController extends AbstractController{
    
    private CellularGUI cellularGUI;
    
    public GameController(AbstractModel model) {
        super(model);
    }

    public GameController(AbstractModel model, CellularGUI cellularGUI) {
        super(model);
        this.cellularGUI = cellularGUI; // set view to controller
    }

    @Override
    public int calculate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initialize() {
        String pattern = this.cellularGUI.getCb_pattern().getSelectedItem().toString();
        String form = this.cellularGUI.getCb_form().getSelectedItem().toString();
        int size = this.cellularGUI.getSlider_size().getValue();
        int speed = this.cellularGUI.getSlider_speed().getValue();        
        Setting setting = new Setting(pattern, form, size, speed);
        
        PanDisplay pan_display = this.cellularGUI.getPan_display();
        int display_W = pan_display.getWidth();
        int display_H = pan_display.getHeight();
        int nb_bins = display_W*display_H;
        boolean [][] bins = new boolean[display_H][display_W];
        
        for (int i=0; i<display_H; i++)
            for (int j=0; j<display_W; j++)
                bins[i][j] = false;
        
        Display display = new Display(pan_display.getWidth(), 
                pan_display.getHeight(), 0, nb_bins, bins);
        
        Game game = new Game(0, null, display, setting);
        this.model = game; // set model to controller
    }

    @Override
    public void browsing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
