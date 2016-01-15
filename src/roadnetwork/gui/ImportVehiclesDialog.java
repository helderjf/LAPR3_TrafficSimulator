/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.io.File;
import javax.swing.JOptionPane;
import roadnetwork.controllers.ImportVehiclesController;

/**
 *
 * @author Tiago
 */
public class ImportVehiclesDialog extends javax.swing.JDialog {

    MainFrame m_mainFrame;
    private ImportVehiclesController m_importVehiclesController;
    
    /**
     * Creates new form ImportVehiclesDialog
     */
    public ImportVehiclesDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
        m_mainFrame=parent;       
        m_importVehiclesController = new ImportVehiclesController(m_mainFrame.getManager());
        if (m_importVehiclesController.canImportVehicles()) {
            initComponents();
            setContentPane(new ImportVehiclesPane(this));
        }else{
            JOptionPane.showMessageDialog(this, "Error: Vehicles file already imported!", "Error: Import Vehicles", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


void importVehicles(File file) {
        if(m_importVehiclesController.importVehicles(file)){
            JOptionPane.showMessageDialog(this, "File succefully imported!", "Import Vehicles", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Error: Vehicles file not imported. Please try with a correct file!", "Error: Import Vehicles", JOptionPane.ERROR_MESSAGE);
        }
    }

}
