/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import roadnetwork.controllers.DeleteRunController;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class DeleteRunDialog extends javax.swing.JDialog {

    private MainFrame m_ancestor;
    private DeleteRunController m_deleteRunController;

    /**
     * Creates new form DeleteRunDialog
     */
    public DeleteRunDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
        m_ancestor = parent;
        m_deleteRunController = new DeleteRunController(m_ancestor.getManager());
        run();
        initComponents();
    }

    private void run() {

        try {
            int response = m_deleteRunController.canDeleteRun();
            if (response == -1) {
                JOptionPane.showMessageDialog(this, "Can't copy simulation. There is no active project.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (response == -2) {
                JOptionPane.showMessageDialog(this, "Error. Not possible to delete run.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (response == -3) {
                JOptionPane.showMessageDialog(this, "Error. There is no active simulation.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (response == -4) {
                JOptionPane.showMessageDialog(this, "Error. Not possible to delete run.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (response == -5) {
                JOptionPane.showMessageDialog(this, "The active simulation does not exist on the data base server.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (response == -6) {
                JOptionPane.showMessageDialog(this, "The active simulation does not have Runs saved on the data base server.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                initComponents();
                ArrayList<String> runNames = m_deleteRunController.getSimulationRuns();
                setContentPane(new DeleteRunPane(this, runNames));
                setLocationRelativeTo(null);
                setVisible(true);
                
            }
        } catch (SQLRecoverableException ex) {
            JOptionPane.showMessageDialog(this, "Error found while trying to connect to database. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DeleteRunDialog.class.getName()).log(Level.SEVERE, null, ex);
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
    void deleteRun(String runName) {
        try {
            if (m_deleteRunController.deleteRun(runName)) {
            } else {
                JOptionPane.showMessageDialog(this, "Error! Not possible to delete run.", "Delete Run", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLRecoverableException ex) {
            JOptionPane.showMessageDialog(this, "Error found while trying to connect to database. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DeleteRunDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}