/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import java.awt.event.WindowEvent;
import javax.swing.table.TableModel;

/**
 *
 * @author boginni
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     */
    
    TableLayoutPanel layoutTableHandlerPanel;
    
    TableHandlerPanel inputTableHandlerPanel;
    
    FloatingTable floatingTable;
    FormattersPanel formatters;
    
    public MainFrame() {
        initComponents();
        formatters = new FormattersPanel();
        
        inputTableHandlerPanel = new TableHandlerPanel();
        layoutTableHandlerPanel = new TableLayoutPanel();
        inputTableHandlerPanel.layoutTableHanLayoutPanel = layoutTableHandlerPanel;
        jTabbedPane2.add("TableHandler", inputTableHandlerPanel);
        jTabbedPane2.add("TableLayout", layoutTableHandlerPanel);
        jTabbedPane2.add("Extratores", formatters);
        
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(660, 360));

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        // TODO add your handling code here:

        switch(jTabbedPane2.getSelectedIndex()){
            case 0:
                
                break;
            case 1:
                layoutTableHandlerPanel.updatePanHeaderButtons(inputTableHandlerPanel.tableModel);
                break;
            case 2:
                break;
                
        }
        
        
    }//GEN-LAST:event_jTabbedPane2StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables
}
