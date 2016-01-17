/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.sql.SQLRecoverableException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import roadnetwork.controllers.CopyProjectController;

/**
 *
 * @author josemiranda
 */
public class CopyProjectFrame extends javax.swing.JFrame {

    MainFrame m_mainFrame;
    CopyProjectController m_copyProjectController;

    /**
     * Creates new form JanelaCopyProject
     */
    public CopyProjectFrame(MainFrame frame) {
        m_mainFrame = frame;
        if (frame.getManager().getCurrentProject() != null) {
            m_copyProjectController = new CopyProjectController(m_mainFrame.getManager());
            if (m_copyProjectController.canCopyProject()) {
                initComponents();
                setContentPane(new CopyProjectPane(this));
                setLocationRelativeTo(null);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error! Can't copy project!", "Copy Project", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an active project!", "Copy Project", JOptionPane.ERROR_MESSAGE);
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
        setTitle("Copy Project");

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
    public MainFrame getMainFrame() {
        return m_mainFrame;
    }

    void newProjectCopy(String name, String description) {
        try {
            if(m_copyProjectController.projectExists(name)){
                JOptionPane.showMessageDialog(this, "A project with the same name exists. Please select a diferent name.", "Copy Project", JOptionPane.ERROR_MESSAGE);
            }else{
                if(m_copyProjectController.copyProject(name,description));
            }
        } catch (SQLRecoverableException ex) {
            JOptionPane.showMessageDialog(this, "Error found while trying to connect to database. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CopyProjectFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
