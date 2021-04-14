/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package body;

import head.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author boginni
 */
public class MainScreen extends javax.swing.JFrame {

    /** Creates new form MainScreen */
    public MainScreen() {
        initComponents();
        pack();
        String[] header = {"Ordem", "Interact ID"};
        for (int i = 0; i < comboCopy.getItemCount(); i++){
            DefaultTableModel dtm = new DefaultTableModel(null, header) {
                @Override
                public Class<?> getColumnClass(int col) {
                    return getValueAt(0, col).getClass();
                }

            };

            copyTables.add(dtm);
        }

        tableCopy.setModel(copyTables.get(comboCopy.getSelectedIndex()));
    }
    private ArrayList<DefaultTableModel> copyTables = new ArrayList<>();


    private static final int N_ROWS = 0;
    private static String[] header = {"Coluna", "ID"};
    private DefaultTableModel dtm = new DefaultTableModel(null, header) {
        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }

    };

    //private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private int row;
    private boolean isAutoScroll;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layoutPan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLayout = new javax.swing.JTable(dtm);
        jPanel5 = new javax.swing.JPanel();
        btnLayoutClear = new javax.swing.JButton();
        btnLayoutAddRow = new javax.swing.JButton();
        copyPan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableCopy = new javax.swing.JTable();
        comboCopy = new javax.swing.JComboBox<>();
        btnCopyAddRow = new javax.swing.JButton();
        btnCopyClear = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSwitchTitle = new javax.swing.JButton();
        switchPause = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnAply = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel lblTitleLinhas = new javax.swing.JLabel();
        lblRowsCont = new javax.swing.JLabel();
        javax.swing.JLabel lblTitleColunas = new javax.swing.JLabel();
        lblColCont = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTableField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnAbrirFormatador = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        layoutPan.setBackground(new java.awt.Color(220, 240, 240));

        tableLayout.setModel(dtm);
        jScrollPane1.setViewportView(tableLayout);

        jPanel5.setBackground(new java.awt.Color(220, 240, 240));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 10, 5));

        btnLayoutClear.setText("Limpar");
        btnLayoutClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayoutClearActionPerformed(evt);
            }
        });
        jPanel5.add(btnLayoutClear);

        btnLayoutAddRow.setText("Nova linha");
        btnLayoutAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayoutAddRowActionPerformed(evt);
            }
        });
        jPanel5.add(btnLayoutAddRow);

        javax.swing.GroupLayout layoutPanLayout = new javax.swing.GroupLayout(layoutPan);
        layoutPan.setLayout(layoutPanLayout);
        layoutPanLayout.setHorizontalGroup(
            layoutPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutPanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layoutPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layoutPanLayout.setVerticalGroup(
            layoutPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        copyPan.setBackground(new java.awt.Color(240, 220, 240));

        tableCopy.setModel(dtm);
        jScrollPane4.setViewportView(tableCopy);
        if (tableCopy.getColumnModel().getColumnCount() > 0) {
            tableCopy.getColumnModel().getColumn(1).setResizable(false);
        }

        comboCopy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mac", "PH e Sinal", "Dados MK", "Colar" }));
        comboCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCopyActionPerformed(evt);
            }
        });

        btnCopyAddRow.setText("Nova Linha");
        btnCopyAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyAddRowActionPerformed(evt);
            }
        });

        btnCopyClear.setText("Remover Linha");
        btnCopyClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout copyPanLayout = new javax.swing.GroupLayout(copyPan);
        copyPan.setLayout(copyPanLayout);
        copyPanLayout.setHorizontalGroup(
            copyPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(copyPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(copyPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboCopy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCopyAddRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCopyClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        copyPanLayout.setVerticalGroup(
            copyPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(copyPanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(copyPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(copyPanLayout.createSequentialGroup()
                        .addComponent(comboCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCopyAddRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCopyClear)
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(240, 240, 220));
        jPanel6.setLayout(new java.awt.GridLayout(1, 3, 7, 0));

        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel6.add(btnSave);

        btnLoad.setText("Carregar");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        jPanel6.add(btnLoad);

        jPanel2.setBackground(new java.awt.Color(240, 240, 220));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 7, 0));

        btnSwitchTitle.setText("Remover Titulo");
        btnSwitchTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwitchTitleActionPerformed(evt);
            }
        });
        jPanel2.add(btnSwitchTitle);

        switchPause.setText("Pause");
        switchPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchPauseActionPerformed(evt);
            }
        });
        jPanel2.add(switchPause);

        jPanel4.setBackground(new java.awt.Color(240, 200, 200));

        btnAply.setText("Aplicar");
        btnAply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplyActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 10, 3));

        lblTitleLinhas.setText("Linhas: ");
        jPanel3.add(lblTitleLinhas);

        lblRowsCont.setText("0");
        jPanel3.add(lblRowsCont);

        lblTitleColunas.setText("Colunas");
        jPanel3.add(lblTitleColunas);

        lblColCont.setText("0");
        jPanel3.add(lblColCont);

        txtTableField.setColumns(20);
        txtTableField.setRows(5);
        jScrollPane2.setViewportView(txtTableField);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tabela de Base");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAply, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButton3.setText("Atualizar CopyController");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        btnAbrirFormatador.setText("Abrir Formatador");
        btnAbrirFormatador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirFormatadorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbrirFormatador);

        btnOpen.setText("Abrir Interface");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(layoutPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(copyPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(layoutPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(copyPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLayoutAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayoutAddRowActionPerformed
        // TODO add your handling code here:
        dtm.addRow(new Object[]{
                row++,
                0
        });
    }//GEN-LAST:event_btnLayoutAddRowActionPerformed

    private void btnLayoutClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayoutClearActionPerformed
        // TODO add your handling code here:
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        row = 0;
    }//GEN-LAST:event_btnLayoutClearActionPerformed

    FloatingTable floatingTable = null;
    Formatador formatter = null;
    CopyController copyController = null;

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        if( floatingTable != null && JOptionPane.showConfirmDialog(this,
                "Isso vai apagar os dados da GUI anterior",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        ) == 1){
            return;
        }
        if(floatingTable == null){
            floatingTable = new FloatingTable();
        
            copyController = new CopyController();
            btnSwitchTitle.setVisible(true);
        }
        if(formatter == null){
            formatter = new Formatador();
        }



        if (tableLayout.getCellEditor() != null) {
            tableLayout.getCellEditor().stopCellEditing();
        }

        if (tableCopy.getCellEditor() != null) {
            tableCopy.getCellEditor().stopCellEditing();
        }


        floatingTable.setTable(tableContent, tableLayout);
        copyController.setTable(floatingTable, formatter);

        copyController.setConfig(copyTables);

    }//GEN-LAST:event_btnOpenActionPerformed

    Table tableContent;

    private void btnAplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplyActionPerformed
        // TODO add your handling code here:
        tableContent = new Table(txtTableField.getText());
        lblColCont.setText(String.valueOf(tableContent.getColumCont()));
        lblRowsCont.setText(String.valueOf(tableContent.getRowCount()));

    }//GEN-LAST:event_btnAplyActionPerformed

    boolean btnSwitchTitleBool = true;
    private void btnSwitchTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitchTitleActionPerformed
        // TODO add your handling code here:
        if(floatingTable == null){
            return;
        }
        floatingTable.dispose();
        floatingTable.setUndecorated(btnSwitchTitleBool);
        floatingTable.setVisible(true);
        btnSwitchTitle.setText(!btnSwitchTitleBool? "Remover Titulo": "Mostrar Titulo");
        btnSwitchTitleBool = !btnSwitchTitleBool;
        
    }//GEN-LAST:event_btnSwitchTitleActionPerformed
    
    boolean btnSitchPauseBool = true;
    private void switchPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchPauseActionPerformed
        // TODO add your handling code here:
        if(floatingTable == null){
            return;
        }
        floatingTable.setPause(btnSitchPauseBool);
        switchPause.setText(btnSitchPauseBool ? "Retomar": "Pausar");

        btnSitchPauseBool = !btnSitchPauseBool;


    }//GEN-LAST:event_switchPauseActionPerformed

    private void comboCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCopyActionPerformed
        // TODO add your handling code here:
        tableCopy.setModel(copyTables.get(comboCopy.getSelectedIndex()));
        cpyRow = copyTables.get(comboCopy.getSelectedIndex()).getRowCount();
    }//GEN-LAST:event_comboCopyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(copyController != null){
            copyController.setConfig(copyTables);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCopyClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyClearActionPerformed
        // TODO add your handling code here:
        if(tableCopy.getSelectedRow() != -1){
            copyTables.get(comboCopy.getSelectedIndex()).removeRow(tableCopy.getSelectedRow());
            System.out.println("Linha removida");
        }
    }//GEN-LAST:event_btnCopyClearActionPerformed

    private void btnAbrirFormatadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirFormatadorActionPerformed
        // TODO add your handling code here:
        if(formatter == null){
            formatter = new Formatador();
            formatter.setVisible(true);
        }
    }//GEN-LAST:event_btnAbrirFormatadorActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        floatingTable.toJsonObject(tableLayout);

        if(copyController != null){

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnLoadActionPerformed
    int cpyRow = 0;
    private void btnCopyAddRowActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        copyTables.get(comboCopy.getSelectedIndex()).addRow(new Object[]{
                cpyRow++,
                0
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirFormatador;
    private javax.swing.JButton btnAply;
    private javax.swing.JButton btnCopyAddRow;
    private javax.swing.JButton btnCopyClear;
    private javax.swing.JButton btnLayoutAddRow;
    private javax.swing.JButton btnLayoutClear;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSwitchTitle;
    private javax.swing.JComboBox<String> comboCopy;
    private javax.swing.JPanel copyPan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel layoutPan;
    private javax.swing.JLabel lblColCont;
    private javax.swing.JLabel lblRowsCont;
    private javax.swing.JButton switchPause;
    private javax.swing.JTable tableCopy;
    public javax.swing.JTable tableLayout;
    private javax.swing.JTextArea txtTableField;
    // End of variables declaration//GEN-END:variables

}
