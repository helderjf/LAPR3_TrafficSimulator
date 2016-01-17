/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import javax.swing.JOptionPane;
import roadnetwork.controllers.ShowRoadNetworkController;
import roadnetwork.domain.RoadNetwork;

/**
 *
 * @author josemiranda
 */
public class ShowRoadNetworkDialog extends javax.swing.JDialog {

    private MainFrame m_ancestor;
    private ShowRoadNetworkController m_showRoadNetworkController;
    private RoadNetwork m_roadNetwork;
    
    /**
     * Creates new form ShowRoadNetworkUI
     */
    public ShowRoadNetworkDialog(MainFrame mainFrame, boolean modal) {
        super(mainFrame, modal);
        m_ancestor=mainFrame;
        m_showRoadNetworkController=new ShowRoadNetworkController(m_ancestor.getManager());
        run();
    }
    
    private void run(){
        if (!m_showRoadNetworkController.projectActive()) {
            JOptionPane.showMessageDialog(this, "Can't show Road Network. There is no active project.", "No active project", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        } else {
            m_roadNetwork = m_showRoadNetworkController.getRoadNetwork();
            if (m_roadNetwork == null) {
                JOptionPane.showMessageDialog(this, "Can't show Road Network. The current project does't contain a road network.", "No road network", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } else {
                initComponents();
                setContentPane(new ShowRoadNetworkPane(this, m_roadNetwork));
                setLocationRelativeTo(null);
                setVisible(true);
            }
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
}