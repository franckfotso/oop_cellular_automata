
package oop.cellular.model;

import java.util.Date;

/**
 *
 * @author romuald.fotso
 */
public class Game extends AbstractModel{
    
    private int trick;
    private Date runtime;
    private Display display;
    private Setting setting;

    public Game() {
    }

    public Game(int trick, Date runtime, Display display, Setting setting) {
        this.trick = trick;
        this.runtime = runtime;
        this.display = display;
        this.setting = setting;
    }

    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public Date getRuntime() {
        return runtime;
    }

    public void setRuntime(Date runtime) {
        this.runtime = runtime;
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
