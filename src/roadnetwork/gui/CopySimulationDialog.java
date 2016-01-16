/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import javax.swing.JOptionPane;
import roadnetwork.controllers.CopySimulationController;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CopySimulationDialog extends javax.swing.JDialog {

    private MainFrame m_ancestor;
    private CopySimulationController m_copySimulationController;

    /**
     * Creates new form CopySimulationDialog
     */
    public CopySimulationDialog(MainFrame ancestor, boolean modal) {
        super(ancestor, modal);
        m_ancestor = ancestor;
        m_copySimulationController = new CopySimulationController(m_ancestor.getManager());

        run();
    }

    private void run() {
        int response = m_copySimulationController.canCopySimulation();
        if (response == -1) {
            JOptionPane.showMessageDialog(this, "Can't copy simulation. There is no active project.", "Copy Simulation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (response == -2) {
            JOptionPane.showMessageDialog(this, "Error. There is no active simulation.", "Copy Simulation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (response == -3) {
            JOptionPane.showMessageDialog(this, "Error. There is no active simulation.", "Copy Simulation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (response == -4) {
            JOptionPane.showMessageDialog(this, "Error. The simulation can't be copied", "Copy Simulation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            initComponents();
            setContentPane(new CopySimulationPane(this));
            setLocationRelativeTo(null);
            setVisible(true);
            
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
    void newSimulationCopy(String name, String description) {
        if (m_copySimulationController.simulationExists(name)) {
            JOptionPane.showMessageDialog(this, "A simulation with the same name exists. Please select a diferent name.", "Copy Simulation", JOptionPane.ERROR_MESSAGE);
        } else {
            if (m_copySimulationController.copySimulation(name, description));
            JOptionPane.showMessageDialog(this, "Simulation copied.", "Copy Simulation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
        }
    }
}
