/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javafx.print.Collation;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author boginni
 */
public class TableLayoutPanel extends javax.swing.JPanel {

    /**
     * Creates new form TablePanel
     */
    public TableLayoutPanel() {
        initComponents();
        updateButtons();
    }

    /**
     * TABLE MODDEL
     * <editor-fold desc="Table Layout Moddel">
     */
    Object[][] values = {};

    String[] tableHeader = {
        "ID", "Titulo", "Conte�do", "ColumID"
    };
    class MyTableModel extends DefaultTableModel{
        
        Class[] types = new Class[]{
            java.lang.Integer.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class
        };

        ArrayList<Boolean[]> canEdit = new ArrayList();

        private MyTableModel(Object[][] values, String[] tableHeader) {
            super(values, tableHeader);
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            try {
                return canEdit.get(rowIndex)[columnIndex];
            } catch (Exception e) {
                return false;
            }
        }
        
        public void setCellEditable(int rowIndex, int columnIndex, boolean editable){
            try{
                canEdit.get(rowIndex)[columnIndex] = editable;
            } catch(Exception e){
                System.out.println(e);
            }
        }

        @Override
        public void removeRow(int row) {
            super.removeRow(row); //To change body of generated methods, choose Tools | Templates.
            canEdit.remove(row);
            updateIndexes();
        }

        @Override
        public void moveRow(int start, int end, int to) {
            super.moveRow(start, end, to); //To change body of generated methods, choose Tools | Templates.
            Collections.swap(canEdit, start, to);
            updateIndexes();
        }

        private void updateIndexes() {
            for (int i = 0; i < this.getRowCount(); i++) {
                this.setValueAt(i, i, 0);
            }
        }

        @Override
        public void addRow(Object[] rowData) {
            super.addRow(rowData);
            Boolean b[] = new Boolean[]{false, true, true, false};
            canEdit.add(b);
        }
    }
    
    MyTableModel tableLayoutModel = new MyTableModel(values, tableHeader);

    ListSelectionListener layoutTableSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            updateButtons();
        }
    };

    void updateTable() {

    }

    //</editor-fold>
    /**
     * <editor-fold desc="Header Buttons">
     */

    DefaultMutableTreeNode treeHeader;

    class HeaderButton extends JButton {

        int headerID;

        public HeaderButton(String s, int headerID) {
            super(s);
            arrHeaderButtons.add(this);
            this.headerID = headerID;
            
            addActionListener((ActionEvent e) -> {
                int rowIndex = tableLayout.getSelectedRow() ;
                if (rowIndex != -1) {
                    
                    tableLayoutModel.setValueAt(s, rowIndex, 1);
                    tableLayoutModel.setValueAt("N/A", rowIndex, 2);
                    tableLayoutModel.setValueAt(headerID, rowIndex, 3);
                    
                    tableLayoutModel.setCellEditable(rowIndex, 0, false);
                    tableLayoutModel.setCellEditable(rowIndex, 2, false);
                    tableLayoutModel.setCellEditable(rowIndex, 3, false);
                    
                    
                    
                }

            });

        }

    }

    ArrayList<HeaderButton> arrHeaderButtons = new ArrayList<>();

    void updatePanHeaderButtons(TableModel tableModel) {

        panHeaders.removeAll();
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weighty = 0;
        cons.weightx = 1;
        cons.gridx = 0;
        cons.gridy = 0;
        cons.ipadx = 0;
        cons.ipady = 0;
        cons.anchor = GridBagConstraints.NORTH;
        JButton dummy = new JButton("none");
        int Width, Height, defaultBtnHeight = dummy.getPreferredSize().height;
        Width = panHeaders.getWidth() - 20;
        Height = (tableModel.getColumnCount() + 1) * defaultBtnHeight;

        panHeaders.setPreferredSize(new Dimension(Width, Height));
        Dimension btnDimension = new Dimension(Width * (2 / 3), defaultBtnHeight);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            JButton b = new HeaderButton(tableModel.getColumnName(i), i);
            b.setPreferredSize(btnDimension);
            panHeaders.add(b, cons);
            cons.gridy++;
        }
        cons.weighty = 1;
        panHeaders.add(Box.createGlue(), cons);
        scrollHeaders.setViewportView(panHeaders);

    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableLayout = new javax.swing.JTable();
        layoutEditor = new javax.swing.JTabbedPane();
        layoutContructor = new javax.swing.JPanel();
        btnNewLine = new javax.swing.JButton();
        btnMoveUp = new javax.swing.JButton();
        btnRemoveRow = new javax.swing.JButton();
        btnMoveDown = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        layoutLoader = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        scrollHeaders = new javax.swing.JScrollPane();
        panHeaders = new javax.swing.JPanel();

        setBorder(new javax.swing.border.MatteBorder(null));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 360));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 360));

        tableLayout.setModel(tableLayoutModel);
        tableLayout.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableLayout.getSelectionModel().addListSelectionListener
        (layoutTableSelectionListener);
        jScrollPane1.setViewportView(tableLayout);

        layoutEditor.setBackground(new java.awt.Color(220, 220, 240));
        layoutEditor.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(230, 230, 240)));
        layoutEditor.setMinimumSize(new java.awt.Dimension(200, 360));
        layoutEditor.setPreferredSize(new java.awt.Dimension(200, 360));

        layoutContructor.setBackground(new java.awt.Color(220, 220, 240));
        layoutContructor.setMinimumSize(new java.awt.Dimension(180, 360));
        layoutContructor.setPreferredSize(new java.awt.Dimension(180, 360));

        btnNewLine.setText("Nova Linha");
        btnNewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLineActionPerformed(evt);
            }
        });

        btnMoveUp.setText("Mover Para Cima");
        btnMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveUpActionPerformed(evt);
            }
        });

        btnRemoveRow.setText("Remover Linha");
        btnRemoveRow.setEnabled(false);
        btnRemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRowActionPerformed(evt);
            }
        });

        btnMoveDown.setText("Mover Para Baixo");
        btnMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveDownActionPerformed(evt);
            }
        });

        jButton5.setText("Salvar Layout");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layoutContructorLayout = new javax.swing.GroupLayout(layoutContructor);
        layoutContructor.setLayout(layoutContructorLayout);
        layoutContructorLayout.setHorizontalGroup(
            layoutContructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutContructorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layoutContructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoveUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoveRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoveDown, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layoutContructorLayout.setVerticalGroup(
            layoutContructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutContructorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewLine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveRow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveDown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        layoutEditor.addTab("Construtor", layoutContructor);

        layoutLoader.setMinimumSize(new java.awt.Dimension(180, 360));
        layoutLoader.setPreferredSize(new java.awt.Dimension(180, 360));

        jPanel1.setBackground(new java.awt.Color(230, 240, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(170, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(160, 360));
        jPanel1.setPreferredSize(new java.awt.Dimension(170, 360));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        layoutLoader.setViewportView(jPanel1);

        layoutEditor.addTab("Layouts Salvos", layoutLoader);

        jPanel2.setMinimumSize(new java.awt.Dimension(180, 360));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(180, 360));

        scrollHeaders.setMinimumSize(new java.awt.Dimension(200, 360));

        panHeaders.setBorder(javax.swing.BorderFactory.createTitledBorder("Headers"));
        panHeaders.setMaximumSize(new java.awt.Dimension(140, 32767));
        panHeaders.setMinimumSize(new java.awt.Dimension(140, 360));
        panHeaders.setPreferredSize(new java.awt.Dimension(140, 200));
        panHeaders.setLayout(new java.awt.GridBagLayout());
        scrollHeaders.setViewportView(panHeaders);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollHeaders, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollHeaders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layoutEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(layoutEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    TreePath lastSelected = null;

    private void updateButtons() {
        int selectedRow = tableLayout.getSelectedRow();

        if (selectedRow == -1) {
            btnRemoveRow.setEnabled(false);
            btnMoveUp.setEnabled(false);
            btnMoveDown.setEnabled(false);
        } else {
            btnRemoveRow.setEnabled(true);
            btnMoveUp.setEnabled(selectedRow > 0);
            btnMoveDown.setEnabled(selectedRow < tableLayoutModel.getRowCount() - 1);
        }

    }

    /**
     * <editor-fold defaultstate="collapsed" desc="Button Methods">
     */
    private void btnNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLineActionPerformed
        // TODO add your handling code here:
        tableLayoutModel.addRow(new Object[]{
            tableLayoutModel.getRowCount(),
            "",
            "",
            "N/A"
        });
        updateButtons();
    }//GEN-LAST:event_btnNewLineActionPerformed

    private void btnRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRowActionPerformed
        // TODO add your handling code here:
        int selected = tableLayout.getSelectedRow();
        if (selected == -1) {
            return;
        }
        tableLayoutModel.removeRow(selected);
        if (tableLayoutModel.getRowCount() > selected) {
            tableLayout.setRowSelectionInterval(selected, selected);
        } else if (selected - 1 >= 0 && tableLayoutModel.getRowCount() == selected) {
            tableLayout.setRowSelectionInterval(selected - 1, selected - 1);
        }
        updateButtons();

    }//GEN-LAST:event_btnRemoveRowActionPerformed

    private void btnMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveUpActionPerformed
        // TODO add your handling code here:
        int selected = tableLayout.getSelectedRow();
        tableLayoutModel.moveRow(selected, selected, selected - 1);
        tableLayout.setRowSelectionInterval(selected - 1, selected - 1);
        updateButtons();
    }//GEN-LAST:event_btnMoveUpActionPerformed

    private void btnMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveDownActionPerformed
        // TODO add your handling code here:
        int selected = tableLayout.getSelectedRow();
        tableLayoutModel.moveRow(selected, selected, selected + 1);
        tableLayout.setRowSelectionInterval(selected + 1, selected + 1);
        updateButtons();

    }//GEN-LAST:event_btnMoveDownActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoveDown;
    private javax.swing.JButton btnMoveUp;
    private javax.swing.JButton btnNewLine;
    private javax.swing.JButton btnRemoveRow;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel layoutContructor;
    private javax.swing.JTabbedPane layoutEditor;
    private javax.swing.JScrollPane layoutLoader;
    private javax.swing.JPanel panHeaders;
    private javax.swing.JScrollPane scrollHeaders;
    private javax.swing.JTable tableLayout;
    // End of variables declaration//GEN-END:variables

}
