/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.gui.MainFrame;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Main {
    
    public static void main(String[] args){
        
                try {
            Manager manager = new Manager("Road Network Simulation");

            MainFrame janela=new MainFrame(manager);
            
            



        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
}
