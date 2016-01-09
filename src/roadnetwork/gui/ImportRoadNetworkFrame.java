/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.io.File;
import javax.swing.JOptionPane;
import roadnetwork.controllers.ImportRoadNetworkController;

/**
 *
 * @author Tiago
 */
public class ImportRoadNetworkFrame extends javax.swing.JFrame {

    MainFrame m_mainFrame;
    ImportRoadNetworkController m_importRNController;

    ImportRoadNetworkFrame(MainFrame frame) {
        m_mainFrame = frame;
        m_importRNController = new ImportRoadNetworkController(m_mainFrame.getManager());
        if (m_importRNController.canImportRoadNetwork()) {
            initComponents();
            setContentPane(new ImportRoadNetworkPane(this));
            setLocationRelativeTo(null);
            setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Error: Road Network file already imported!", "Error: Import Road Network", JOptionPane.ERROR);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void importRoadNetwork(File file) {
        if(m_importRNController.importRoadNetwork(file)){
            JOptionPane.showMessageDialog(this, "File succefully imported!", "Import Road Network", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Error: Road Network file not imported. Please try with a correct file!", "Error: Import Road Network", JOptionPane.ERROR);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}