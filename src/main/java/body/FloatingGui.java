package body;

import head.Table;

import javax.swing.*;
import java.awt.*;


public class FloatingGui extends javax.swing.JFrame {

    JLabel columns[];
    JLabel alias[];
    Table tr;

    public FloatingGui() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop( true );
        pack();
        setVisible(true);
        this.setLayout(new GridLayout(2, 4));
        int amm = 4;
        columns = new JLabel[amm];
        alias = new JLabel[amm];

        for (int i = 0; i < columns.length; i++) {
            alias[i] = new JLabel("Alias "+i+1);
            this.add(alias[i]);
            this.add(new JLabel("  "));
        }

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new JLabel();

            this.add(columns[i]);
            this.add(new JLabel("  "));

        }



    }

    public void setAlias(int id, String value){

    }

    public void setRow(int row) {
        if(tr == null){
            return;
        }
        String[] rowStr = tr.getRow(row);
        for (int i = 0; i < columns.length; i++) {
            columns[i].setText(rowStr[i]);
        }
        pack();
    }

    public void setTable(Table tr) {
        this.tr = tr;
    }

    public Table getTable() {
        return this.tr;
    }
}
