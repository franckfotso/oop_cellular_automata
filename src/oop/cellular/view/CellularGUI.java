/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.cellular.view;

import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import oop.cellular.controller.AbstractController;
import oop.cellular.controller.GameController;
import oop.cellular.observer.Observable;
import oop.cellular.observer.Observer;

/**
 *
 * @author romuald.fotso
 */
public class CellularGUI extends JFrame implements Observer{

    private HashMap<String,AbstractController> controllers;
    
    private javax.swing.JButton btn_play;
    private javax.swing.JButton btn_resume;
    private javax.swing.JComboBox<String> cb_form;
    private javax.swing.JComboBox<String> cb_pattern;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lab_form;
    private javax.swing.JLabel lab_pattern;
    private javax.swing.JLabel lab_size;
    private javax.swing.JLabel lab_speed;
    private javax.swing.JLabel lab_stat_dead;
    private javax.swing.JLabel lab_stat_form;
    private javax.swing.JLabel lab_stat_live;
    private javax.swing.JLabel lab_stat_runtime;
    private javax.swing.JLabel lab_stat_trick;
    private javax.swing.JPanel pan_controls;
    //private javax.swing.JPanel pan_display;
    private PanDisplay pan_display;
    private javax.swing.JPanel pan_settings;
    private javax.swing.JSlider slider_size;
    private javax.swing.JSlider slider_speed;

    public CellularGUI(HashMap<String, AbstractController> controllers) {
        this.controllers = controllers;
        
        this.setTitle("OOP - Cellular Automata - Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setLocationRelativeTo(null);
        
        // configure GUI
        this.initComponents();		
	
        // Use Look & Feel System
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(this);
	} 
        catch (InstantiationException e) {} 
        catch (ClassNotFoundException e) {}
        catch (UnsupportedLookAndFeelException e) {} 
        catch (IllegalAccessException e) {}
        
        this.initControllers();
        this.initEvents();        
	this.setVisible(true);
    }

    public PanDisplay getPan_display() {
        return pan_display;
    }

    public void setPan_display(PanDisplay pan_display) {
        this.pan_display = pan_display;
    }

    public JComboBox<String> getCb_form() {
        return cb_form;
    }

    public void setCb_form(JComboBox<String> cb_form) {
        this.cb_form = cb_form;
    }

    public JComboBox<String> getCb_pattern() {
        return cb_pattern;
    }

    public void setCb_pattern(JComboBox<String> cb_pattern) {
        this.cb_pattern = cb_pattern;
    }

    public JSlider getSlider_size() {
        return slider_size;
    }

    public void setSlider_size(JSlider slider_size) {
        this.slider_size = slider_size;
    }

    public JSlider getSlider_speed() {
        return slider_speed;
    }

    public void setSlider_speed(JSlider slider_speed) {
        this.slider_speed = slider_speed;
    }
    
    
    public void initControllers(){
        // init game controller
        GameController game_controller = new GameController(null, this);
        game_controller.initialize(); // involving model initialization
        
        this.controllers.put("game_controller", game_controller);
    }
    
    public void initDisplay(){
        
    }
    
    public void initComponents(){
        
        //pan_display = new javax.swing.JPanel();
        pan_display = new PanDisplay();
        pan_settings = new javax.swing.JPanel();
        lab_pattern = new javax.swing.JLabel();
        cb_pattern = new javax.swing.JComboBox<>();
        lab_form = new javax.swing.JLabel();
        cb_form = new javax.swing.JComboBox<>();
        lab_size = new javax.swing.JLabel();
        slider_size = new javax.swing.JSlider();
        slider_speed = new javax.swing.JSlider();
        lab_speed = new javax.swing.JLabel();
        pan_controls = new javax.swing.JPanel();
        btn_play = new javax.swing.JButton();
        btn_resume = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lab_stat_form = new javax.swing.JLabel();
        lab_stat_trick = new javax.swing.JLabel();
        lab_stat_live = new javax.swing.JLabel();
        lab_stat_dead = new javax.swing.JLabel();
        lab_stat_runtime = new javax.swing.JLabel();
        
        pan_display.setBackground(new java.awt.Color(0, 0, 0));
        pan_display.setBorder(new javax.swing.border.SoftBevelBorder(0));

        javax.swing.GroupLayout pan_displayLayout = new javax.swing.GroupLayout(pan_display);
        pan_display.setLayout(pan_displayLayout);
        pan_displayLayout.setHorizontalGroup(
            pan_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pan_displayLayout.setVerticalGroup(
            pan_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pan_settings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        lab_pattern.setText("Pattern");
        lab_form.setText("Form");
        lab_size.setText("Size");
        lab_speed.setText("Speed");

        cb_pattern.setModel(new javax.swing
                .DefaultComboBoxModel<>(new String[] { "Random", "Centered" }));
        cb_form.setModel(new javax.swing
                .DefaultComboBoxModel<>(new String[] { "Square" }));

        javax.swing.GroupLayout pan_settingsLayout = new javax.swing.GroupLayout(pan_settings);
        pan_settings.setLayout(pan_settingsLayout);
        pan_settingsLayout.setHorizontalGroup(
            pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_settingsLayout.createSequentialGroup()
                        .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_pattern)
                            .addComponent(lab_form))
                        .addGap(18, 18, 18)
                        .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_form, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_pattern, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pan_settingsLayout.createSequentialGroup()
                        .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_speed)
                            .addComponent(lab_size))
                        .addGap(18, 18, 18)
                        .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slider_speed, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(slider_size, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pan_settingsLayout.setVerticalGroup(
            pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_pattern)
                    .addComponent(cb_pattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_form)
                    .addComponent(cb_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_size)
                    .addComponent(slider_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_speed)
                    .addComponent(slider_speed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pan_controls.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));

        btn_play.setText("Play");

        btn_resume.setText("Resume");
        btn_resume.setEnabled(false);

        javax.swing.GroupLayout pan_controlsLayout = new javax.swing.GroupLayout(pan_controls);
        pan_controls.setLayout(pan_controlsLayout);
        pan_controlsLayout.setHorizontalGroup(
            pan_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_controlsLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_resume, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_controlsLayout.setVerticalGroup(
            pan_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_play)
                    .addComponent(btn_resume))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        lab_stat_form.setText("Form: Square");
        lab_stat_trick.setText("Trick: 0");
        lab_stat_live.setText("Live: 0");
        lab_stat_dead.setText("Dead: 0");
        lab_stat_runtime.setText("Runtime: 00h:00m:00s");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pan_settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pan_controls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pan_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lab_stat_form)
                        .addGap(97, 97, 97)
                        .addComponent(lab_stat_trick)
                        .addGap(101, 101, 101)
                        .addComponent(lab_stat_live)
                        .addGap(103, 103, 103)
                        .addComponent(lab_stat_dead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(lab_stat_runtime)
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pan_settings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pan_controls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_stat_form)
                    .addComponent(lab_stat_trick)
                    .addComponent(lab_stat_live)
                    .addComponent(lab_stat_dead)
                    .addComponent(lab_stat_runtime))
                .addContainerGap())
        );
        pack();       
    }
    
    public void initEvents(){
        
    }
    
    @Override
    public void update(Observable observ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
