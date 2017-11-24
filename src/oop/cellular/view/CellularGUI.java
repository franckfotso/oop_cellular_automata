/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.cellular.view;

import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import oop.cellular.controller.AbstractController;
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
    private javax.swing.JPanel pan_display;
    private javax.swing.JPanel pan_settings;
    private javax.swing.JSlider slider_size;
    private javax.swing.JSlider slider_speed;

    public CellularGUI(HashMap<String, AbstractController> controllers) {
        this.controllers = controllers;
        
        this.setTitle("OOP - Cellular Automata - Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
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
    
    public void initControllers(){
        
    }
    
    public void initComponents(){
        
        pan_display = new javax.swing.JPanel();
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
        
        
    }
    
    public void initEvents(){
        
    }
    
    @Override
    public void update(Observable observ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
