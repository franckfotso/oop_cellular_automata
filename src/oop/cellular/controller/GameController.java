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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TimeZone;
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
        if (size == 0) size = 1;
        
        int speed = this.cellularGUI.getSlider_speed().getValue();        
        Setting setting = new Setting(pattern, form, size, speed);
               
        PanDisplay pan_display = this.cellularGUI.getPan_display();
        
        int cols = (int)((float)pan_display.getWidth()/(float)setting.getSize());
        int rows = (int)((float)pan_display.getHeight()/(float)setting.getSize());
        int nb_bins = cols*rows;
        boolean [][] bins = new boolean[rows][cols];
        
        for (int i=0; i<cols; i++)
            for (int j=0; j<rows; j++)
                bins[j][i] = false;
        
        bins = init_bins(pattern, cols, rows, bins);
        
        Display display = new Display(pan_display.getWidth(), 
                pan_display.getHeight(), 0, nb_bins, bins);
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long time = cal.getTimeInMillis();
        
        Game game = new Game(0, time, display, setting);
        this.model = game; // set model to controller
        this.cellularGUI.setPan_display(pan_display);
    }

    @Override
    public void browsing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean [][] init_bins(String pattern, int cols, int rows, boolean [][] bins)
    {       
        if (pattern.equals("Random"))
        {           
            double rate = 0.60;
            int nb_bins = cols*rows;
            int sel_bins = (int)((float) nb_bins * rate);
            
            ArrayList<HashMap<String, Integer>> prop_bins = 
                    new ArrayList<HashMap<String, Integer>>();
                    
            for (int x=0; x < cols; x++){
                for (int y=0; y < rows; y++){
                    HashMap<String, Integer> prop_bin = 
                            new HashMap<String, Integer>();
                    prop_bin.put("x", x);
                    prop_bin.put("y", y);
                    prop_bins.add(prop_bin);
                }
            }
            
            int nb_prop = prop_bins.size();
            int[] r_bins = new int[sel_bins];

            Random r = new Random();
            for (int i=0; i<sel_bins; i++){
                r_bins[i] = r.nextInt(nb_prop);
            }         
        
//            System.out.println("cols : "+cols);
//            System.out.println("rows : "+rows);        
//            System.out.println("sel_cols : "+sel_cols);
//            System.out.println("sel_rows : "+sel_rows);
//
//            System.out.print("\nr_rows: ");
//            for(int j=0; j<r_rows.length; j++)
//                System.out.print(r_rows[j]+" ");        
//
//            System.out.print("\nr_cols: ");
//            for(int i=0; i<r_cols.length; i++)
//                System.out.print(r_cols[i]+" ");
//            System.out.print("\n");

            for (int i=0; i<sel_bins; i++){
                int prop_id = r_bins[i];
                
                HashMap<String, Integer> prop_bin = prop_bins.get(prop_id);
                int prop_bin_X = prop_bin.get("x");
                int prop_bin_Y = prop_bin.get("y");
                bins[prop_bin_Y][prop_bin_X] = true;
            }
            
        }
        else if (pattern.equals("Centered"))
        {
            // TO-DO: as improvements (init pattern)      
        }
        
        return bins;
    }
    
}
