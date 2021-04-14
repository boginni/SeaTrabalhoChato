/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

/**
 *
 * @author boginni
 */
public class FloatingTable extends JFrame {

    interface onClickListener {
        
        void mouseClick(FloatingTableCell cell, int click);
        
    }
    private static ArrayList<onClickListener> listeners = new ArrayList<>();

    public static void addClickListener(onClickListener object) {
        listeners.add(object);
    }
    
    public static void mouseClick(FloatingTableCell cell, int click){
        for (onClickListener curListener : listeners) {
            curListener.mouseClick(cell, click);
        }
    }
    
    class HeaderTableCell extends StaticCell {

        int HeaderID;

        public HeaderTableCell(String header, String value, int hID) {
            super(header, value);
            HeaderID = hID;
            updateValue();
        }

        @Override
        void updateValue() {
            setValue((String) getCurrentLayer().getValueAt(curRow, HeaderID));
        }

        @Override
        void setValue(String v) {
            super.setValue(v); //To change body of generated methods, choose Tools | Templates.
            getCurrentLayer().setValueAt(v, curRow, HeaderID);
        }

    }

    public FloatingTable(TableModel originalTable, TableModel layout, int startRow, LayoutManager gridLay) {
        super();
        
        setLayout(gridLay);
        
        setOriginalTable(originalTable);

        for (int i = 0; i < layout.getRowCount(); i++) {

            String header = layout.getValueAt(i, 1).toString();
            String value = layout.getValueAt(i, 2).toString();
            String columID = layout.getValueAt(i, 3).toString();
            FloatingTableCell curCell;

            if (columID.equals("N/A")) {
                curCell = new StaticCell(header, value);
            } else {
                int hID = Integer.parseInt(columID);
                curCell = new HeaderTableCell(header, "===========", hID);
            }

            cells.add(curCell);
            
            add(curCell);
        }

        pack();
        setVisible(true);
    }

    private int curRow = 0;
    private int curLayTable = 0;
    
    public void setRow(int newRow){
        int maxSize = getCurrentLayer().getRowCount();
        if(newRow >= maxSize){
            
            return;
        }
        if(newRow < 0){
            
            return ;
        }
        
        curRow = newRow;
        
        cells.forEach(cell -> {
            cell.updateValue();
        });
        
    }
    
    public void sumRow(int sum){
        setRow(curRow + sum);
    }
    
    public int getRow(){
        return curRow;
    }

    public ArrayList<FloatingTableCell> getCells() {
        return cells;
    }
    
    public TableModel getCurrentLayer(){
        return layerTables.get(curLayTable);
    }
    
    public int getCurrentLayerIndex(){
        return curLayTable;
    }
    
    //TODO
    public void setLayer(int newLayer){
        
    }
    
    ArrayList<FloatingTableCell> cells = new ArrayList<>();

    public void setOriginalTable(TableModel t) {
        originalTable = t;
        layerTables.clear();
        layerTables.add(originalTable);
    }
    
    TableModel originalTable;
    ArrayList<TableModel> layerTables = new ArrayList<>();

}

class FloatingTableLayout {

    int maxCol;
}

abstract class FloatingTableCell extends JPanel {
    
    String value;
    
    //int id;
    
    public FloatingTableCell(String value) {
        this.value = value;
    }

    abstract void updateValue();

    void setValue(String v) {
        value = v;
    }
}

class ClickListenerCell extends FloatingTableCell {

    MouseAdapter standartClick = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            click(e.getButton());
        }
    };

    void click(int click){
        FloatingTable.mouseClick(this, click);
    }
    
    public ClickListenerCell(String value) {
        super(value);
        addMouseListener(standartClick);
    }

    @Override
    void updateValue() {

    }


}

class StaticCell extends ClickListenerCell {

    String Header;

    JLabel lblHeader;
    JLabel lblValue;

    public StaticCell(String header, String value) {
        super(value);
        this.Header = header;
        StaticCell.this.setLayout(new GridLayout(2, 1));

        lblHeader = new JLabel(header);
        lblValue = new JLabel(value);

        add(lblHeader);
        add(lblValue);

        setMinimumSize(new Dimension(30, 20));
        lblHeader.setBackground(getBackground().darker());
        lblHeader.setOpaque(true);
        lblValue.setOpaque(true);
    }

    @Override
    void updateValue() {

    }

    @Override
    void setValue(String v) {
        super.setValue(v); //To change body of generated methods, choose Tools | Templates.
        lblValue.setText(v);
    }

}
