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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
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
        
        System.out.println("display.getWidth(): "+game.getDisplay().getWidth());
        System.out.println("display.getHeight(): "+game.getDisplay().getHeight());
        System.out.println("setting.getSize(): "+game.getSetting().getSize());
        System.out.println("game.trick: "+game.getTrick());
        
        this.bin_W = game.getSetting().getSize();
        this.bin_H = game.getSetting().getSize();
        
        this.initGrid(g, game);

        if (game.isPlay()){
            int trick = game.getTrick();
            System.out.println("[INFO]: Game is on play ");
            boolean [][] bins_updated = this.slidingWindow(g, game);
            game.getDisplay().setBins(bins_updated);
            trick++;
            game.setTrick(trick);
            game_controller.setModel(game);
            this.cellularGUI.getControllers().put("game_controller", game_controller);
        }
        // rendering bins in the panel
        this.renderBins(g, game);
    }
    
    public void initGrid(Graphics g, Game game){
        g.setColor(Color.GRAY);        
        
        // draw all bins
        for (int x=0; x < this.getWidth(); x+=this.bin_W){
            for (int y=0; y < this.getHeight(); y+=this.bin_H){
                g.drawRect(x, y, bin_W, bin_H);
            }
        }
    }
    
    public void renderBins(Graphics g, Game game){
        boolean [][] bins = game.getDisplay().getBins();
        
        int cols = (int)((float)this.getWidth()/(float)game.getSetting().getSize());
        int rows = (int)((float)this.getHeight()/(float)game.getSetting().getSize());
        
        g.setColor(Color.WHITE);
        int nb_live_bins = 0;
        
        // draw live bins
        for (int x=0; x < cols; x++){
            for (int y=0; y < rows; y++){
                boolean state = bins[y][x];
                
                int bin_X = x * this.bin_W;
                int bin_Y = y * this.bin_H;                
                if (state){
                    g.fillRect(bin_X, bin_Y, bin_W, bin_H);
                    nb_live_bins++;
                }
            }
        }
        
        // update the GUI
        this.cellularGUI.getLab_stat_live()
                .setText("Live: "+Integer.toString(nb_live_bins));
        this.cellularGUI.getLab_stat_dead()
                .setText("Dead: "+Integer.toString((cols*rows)-nb_live_bins));
        this.cellularGUI.getLab_stat_form()
                .setText("Form: "+game.getSetting().getForm());
        this.cellularGUI.getLab_stat_trick()
                .setText("Trick: "+Integer.toString(game.getTrick()));
        
        long init_time = game.getInit_time();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long current_time = cal.getTimeInMillis();
        long run_time = game.getRun_time() + (current_time - init_time);
        game.setRun_time(run_time);
                
        System.out.println("[INFO]: run_time = "+run_time);        
        this.cellularGUI.getLab_stat_runtime()
                //.setText("Runtime: "+df.format(run_time)); 
                .setText("Runtime: "+run_time/1000+"s");
    }
    
    public boolean [][] slidingWindow(Graphics g, Game game){        
        boolean [][] bins = game.getDisplay().getBins();        
        boolean [][] bins_updated = applyGOLrules(bins, game);
        
        return bins_updated;
    }
    
    public boolean [][] applyGOLrules(boolean [][] bins, Game game){
        int cols = (int)((float)this.getWidth()/(float)game.getSetting().getSize());
        int rows = (int)((float)this.getHeight()/(float)game.getSetting().getSize());
        
        boolean [][] bins_updated = new boolean[rows][cols];
        //boolean [][] bins_updated = bins;
        int bins_lives = 0;
        int bins_updated_lives = 0;
        
        for (int x=0; x<cols; x++){
            for (int y=0; y<rows; y++){
                HashMap<String,Integer> nb1 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb2 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb3 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb4 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb5 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb6 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb7 = new HashMap<String,Integer>();
                HashMap<String,Integer> nb8 = new HashMap<String,Integer>();
                
                // neighbour 1
                nb1.put("x", x-1); nb1.put("y", y-1);
                // neighbour 2
                nb2.put("x", x); nb2.put("y", y-1);
                // neighbour 3
                nb3.put("x", x+1); nb3.put("y", y-1);
                // neighbour 4
                nb4.put("x", x+1); nb4.put("y", y);
                // neighbour 5
                nb5.put("x", x+1); nb5.put("y", y+1);
                // neighbour 6
                nb6.put("x", x); nb6.put("y", y+1);
                // neighbour 7
                nb7.put("x", x-1); nb7.put("y", y+1);
                // neighbour 8
                nb8.put("x", x-1); nb8.put("y", y);
                
                ArrayList<HashMap<String,Integer>> NBs = new ArrayList<HashMap<String,Integer>>();
                NBs.add(nb1); NBs.add(nb2); NBs.add(nb3); NBs.add(nb4);
                NBs.add(nb5); NBs.add(nb6); NBs.add(nb7); NBs.add(nb8);
                
                int live_NBs = 0;
                for (HashMap<String,Integer> nb: NBs){
                    if (nb.get("x") >= 0 && nb.get("y") >= 0
                            && nb.get("x") < cols && nb.get("y") < rows)
                    {
//                        System.out.println("nb_X: "+nb.get("x"));
//                        System.out.println("nb_Y: "+nb.get("y"));
                        
                        if (bins[nb.get("y")][nb.get("x")])
                            live_NBs++;
                    }
                }
                
                //current bin state: dead/live
                boolean bin_state = bins[y][x];
                
                if(bin_state){
                    if (live_NBs >= 0 && live_NBs < 2) // GOL rule 1
                        bins_updated[y][x] = false;
                    else if (live_NBs >= 2 && live_NBs <= 3)
                        bins_updated[y][x] = true; // GOL rule 2
                    else if (live_NBs > 3)
                        bins_updated[y][x] = false; // GOL rule 3
                }
                else {
                    if (live_NBs == 3)
                        bins_updated[y][x] = true; // GOL rule 4
                }
                
                if (bins_updated[y][x])
                    bins_updated_lives++;
                if (bins[y][x])
                    bins_lives++;
            }
        }
        
        System.out.println("[INFO]: bins_lives = "+bins_lives);
        System.out.println("[INFO]: bins_updated_lives = "+bins_updated_lives);
        
        return bins_updated;
    }
}
