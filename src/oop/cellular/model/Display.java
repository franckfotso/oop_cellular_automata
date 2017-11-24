
package oop.cellular.model;

import java.util.ArrayList;

/**
 *
 * @author romuald.fotso
 */
public class Display {
    
    private int width;
    private int height;
    private int live;
    private int dead;
    private boolean [][] bins;

    public Display() {
    }

    public Display(int width, int height, int live, int dead, boolean [][] bins) {
        this.width = width;
        this.height = height;
        this.live = live;
        this.dead = dead;
        this.bins = bins;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public boolean [][] getBins() {
        return bins;
    }

    public void setBins(boolean [][] bins) {
        this.bins = bins;
    }
    
}
