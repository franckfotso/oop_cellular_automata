/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.cellular.model;

/**
 *
 * @author romuald.fotso
 */
public class Setting {
    
    private String pattern;
    private String form;
    private int size;
    private int speed;

    public Setting() {
    }

    public Setting(String pattern, String form, int size, int speed) {
        this.pattern = pattern;
        this.form = form;
        this.size = size;
        this.speed = speed;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
