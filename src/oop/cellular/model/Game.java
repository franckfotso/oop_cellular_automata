
package oop.cellular.model;

import java.util.Date;

/**
 *
 * @author romuald.fotso
 */
public class Game extends AbstractModel{
    
    private int trick;
    private long init_time;
    private long run_time;
    private Display display;
    private Setting setting;
    private boolean play = false;

    public Game() {
    }

    public Game(int trick, long init_time, Display display, Setting setting) {
        this.trick = trick;
        this.run_time = 0;
        this.init_time = init_time;
        this.display = display;
        this.setting = setting;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }


    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public long getInit_time() {
        return init_time;
    }

    public void setInit_time(long init_time) {
        this.init_time = init_time;
    }

    public long getRun_time() {
        return run_time;
    }

    public void setRun_time(long run_time) {
        this.run_time = run_time;
    }


    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
    
    
}
