/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import roadnetwork.controllers.SaveProjectController;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.Road;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.Vehicle;
import roadnetwork.domain.Wind;
import roadnetwork.state.ProjectStateSimulationReady;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class MainFrame extends javax.swing.JFrame {

    private Manager m_manager;
    private NewProjectFrame m_newProjectFrame;
    private OpenProjectFrame m_openProjectFrame;
    private CopyProjectFrame m_copyProjectFrame;
    private ImportVehiclesFrame m_importVehiclesFrame;
    private BestPathAnalysisFrame m_bestPathAnalysisFrame;
    private VehicleComparisonFrame m_vehicleComparisonFrame;

    /**
     * Creates new form JanelaPrincipal
     *
     * @param manager
     */
    public MainFrame(Manager manager) {
        m_manager = manager;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void newProject() {
        m_newProjectFrame = new NewProjectFrame(this);
        //revalidate();
    }

    private void openProject() {
        m_openProjectFrame = new OpenProjectFrame(this);
        revalidate();
    }

    private void copyProject() {
        m_copyProjectFrame = new CopyProjectFrame(this);
        revalidate();
    }

    private void importVehicles() {
        m_importVehiclesFrame = new ImportVehiclesFrame(this);
        revalidate();
    }

    private void properties() {
        new EditProjectPropertiesFrame(this);
        revalidate();

    }

    private void bestPathAnalysis() {
        m_bestPathAnalysisFrame = new BestPathAnalysisFrame(this);
        revalidate();
    }

    private void vehicleComparison() {
        m_vehicleComparisonFrame = new VehicleComparisonFrame(this);
        revalidate();
    }

    private void saveProject() {
        SaveProjectController m_saveProjectController = new SaveProjectController(m_manager);
        if(m_saveProjectController.checkProjectSaved()){
            JOptionPane.showMessageDialog(this, "The project is already saved", "Save Project", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(m_saveProjectController.saveProject()){
                JOptionPane.showMessageDialog(this, "Project saved", "Save Project", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Ther was an error! The project has not been saved!", "Save Project", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Manager getManager() {
        return m_manager;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Load Dummy Project");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(316, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jMenu1.setText("Project");

        jMenuItem1.setText("New Project");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Open Project");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem8.setText("Save Project");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem3.setText("Copy Project");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Import Vehicles");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem4.setText("Properties");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Network Analysis");

        jMenuItem6.setText("Best Path Analysis");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Vehicle Comparison");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu7.setText("Simulations");

        jMenuItem9.setText("Create Simulation");
        jMenu7.add(jMenuItem9);

        jMenuItem12.setText("Open Simulation");
        jMenu7.add(jMenuItem12);

        jMenuItem13.setText("Run Simulation");
        jMenu7.add(jMenuItem13);

        jMenu8.setText("Simulation Run(s)");

        jMenuItem14.setText("View Runs");
        jMenu8.add(jMenuItem14);

        jMenuItem16.setText("Export Run Results");
        jMenu8.add(jMenuItem16);

        jMenuItem15.setText("Delete Runs");
        jMenu8.add(jMenuItem15);

        jMenu7.add(jMenu8);

        jMenuItem10.setText("Copy Simulation");
        jMenu7.add(jMenuItem10);

        jMenuItem11.setText("Properties");
        jMenu7.add(jMenuItem11);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        newProject();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        openProject();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        copyProject();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        importVehicles();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        properties();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        bestPathAnalysis();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        vehicleComparison();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        saveProject();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
               //CREATE MOCK OBJECTS
        Junction node0 = new Junction("node0");
        Junction node1 = new Junction("node1");
        Junction node2 = new Junction("node2");
        ArrayList<Junction> nodeList = new ArrayList();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node2);

        Segment segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        Segment segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
        ArrayList<Segment> list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);

        Section section1 = new Section("E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        Segment segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        Segment segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        ArrayList<Segment> list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        Section section2 = new Section("E01", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
        ArrayList<Section> sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        Road road1 = new Road(1, "E01");
        road1.setSections(sectionlist1);

        Segment segment5 = new Segment(1, 100, 0, 100, 120, 50, 100);
        ArrayList<Segment> list3 = new ArrayList();
        list3.add(segment5);
        Section section3 = new Section("A01", node0, node2, SectionTypology.highway, SectionDirection.bidirectional, 12, new Wind(-5, 3), list3);
        section3.setToll(12);
        ArrayList<Section> sectionlist2 = new ArrayList();
        sectionlist2.add(section3);

        Road road2 = new Road(2, "A01");
        road2.setSections(sectionlist2);

        Segment segment6 = new Segment(1, 100, 0.125, 10, 61, 50, 100);
        ArrayList<Segment> list4 = new ArrayList();
        list4.add(segment6);
        Section section4 = new Section("A03", node1, node2, SectionTypology.highway, SectionDirection.bidirectional, 4, new Wind(-5, 3), list4);
        section4.setToll(4);
        ArrayList<Section> sectionlist3 = new ArrayList();
        sectionlist3.add(section4);

        Road road3 = new Road(3, "A03");
        road3.setSections(sectionlist3);

        ArrayList<Section> completeSectionList = new ArrayList();
        completeSectionList.add(section1);
        completeSectionList.add(section2);
        completeSectionList.add(section3);
        completeSectionList.add(section4);

        RoadNetwork roadNetwork1 = new RoadNetwork();
        roadNetwork1.setName("road network1");
        roadNetwork1.setDescription("road network de testes 1");
        roadNetwork1.setNodeList(nodeList);
        roadNetwork1.setSectionList(completeSectionList);

        HashMap <String,Double>velocityLimit = new HashMap();
        velocityLimit.put("highway", 60d);
        ArrayList<Double> gearList = new ArrayList();
        gearList.add(3.5);
        gearList.add(2.5);
        gearList.add(1.25);
        gearList.add(0.9);

        Regime r1 = new Regime(85, 1000, 2499, 8.2);
        Regime r2 = new Regime(95, 2500, 3999, 6.2);
        Regime r3 = new Regime(80, 4000, 5500, 10.2);
        ArrayList<Regime> lr1 = new ArrayList();
        lr1.add(r1);
        lr1.add(r2);
        lr1.add(r3);
        Throttle t1 = new Throttle("25", lr1);

        Regime r4 = new Regime(135, 1000, 2499, 5.2);
        Regime r5 = new Regime(150, 2500, 3999, 3.2);
        Regime r6 = new Regime(140, 4000, 5500, 8.2);
        ArrayList<Regime> lr2 = new ArrayList();
        lr2.add(r4);
        lr2.add(r5);
        lr2.add(r6);
        Throttle t2 = new Throttle("50", lr2);

        Regime r7 = new Regime(200, 1000, 2499, 2.2);
        Regime r8 = new Regime(240, 2500, 3999, 1.2);
        Regime r9 = new Regime(190, 4000, 5500, 4.2);
        ArrayList<Regime> lr3 = new ArrayList();
        lr3.add(r7);
        lr3.add(r8);
        lr3.add(r9);
        Throttle t3 = new Throttle("100", lr3);

        ArrayList<Throttle> throttleList1 = new ArrayList();
        throttleList1.add(t1);
        throttleList1.add(t2);
        throttleList1.add(t3);

        Vehicle vehicle1 = new CombustionVehicle("dummy1", //name
                "descricao", //descricao
                1400, //mass
                "car",//type
                120,//load
                0.35,//drag
                1.8,//frontal area
                0.01,//rcc
                0.5,//wheel size
                velocityLimit,//
                5500,//max rpm
                1000,//min rpm
                2.6,//final drive ratio
                "gasoline",//fuel
                gearList,
                throttleList1);

        ArrayList<Vehicle> vehicleList = new ArrayList();
        vehicleList.add(vehicle1);

        Project projecto1 = new Project();
        projecto1.setName("projecto1");
        projecto1.setDescription("projecto de testes 1");
        projecto1.setRoadNetwork(roadNetwork1);
        projecto1.setVehicleList(vehicleList);
        projecto1.setState(new ProjectStateSimulationReady(projecto1));
        
        m_manager.setCurrentProject(projecto1);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
