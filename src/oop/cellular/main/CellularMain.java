
package oop.cellular.main;

import java.util.HashMap;
import oop.cellular.controller.AbstractController;
import oop.cellular.view.CellularGUI;

/**
 *
 * @author romuald.fotso
 */
public class CellularMain {
    
    public static void main(String[] args) throws Exception{
        final HashMap<String,AbstractController> listControler = 
            new HashMap<String,AbstractController>();
    
        javax.swing.SwingUtilities.invokeLater( 
            new Runnable(){
                public void run(){
                        new CellularGUI(listControler);
                }
            }
        );
    }  
    
}
